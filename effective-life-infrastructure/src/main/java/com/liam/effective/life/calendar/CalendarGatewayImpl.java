package com.liam.effective.life.calendar;

import com.alibaba.cola.exception.BizException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.liam.effective.life.domain.constant.BaiduConstant;
import com.liam.effective.life.domain.customer.gateway.ICalendarGateway;
import com.liam.effective.life.domain.schedule.calendar.Calendar;
import com.liam.effective.life.dto.data.ErrorCode;
import com.liam.effective.life.util.HttpUtil;
import com.liam.effective.life.util.JacksonUtil;
import org.apache.commons.collections4.MapUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liam.li
 */
@Component
public class CalendarGatewayImpl implements ICalendarGateway {

    private static final Map<String, Calendar> MAP = new HashMap<>();


    @Override
    public Calendar calendar(int year , int month, int day) {
        return MAP.get(BaiduCalendarDTO.Almanac.buildYmd(year, month, day));
    }

    @Override
    public Map<String, Calendar> calendarMap() {
        DateTime now = new DateTime();
        DateTime after30 = now.plusDays(31);
        if(MapUtils.isEmpty(MAP) || MAP.get(after30.toString()) == null){
            //refresh MAP
            int year = now.getYear();
            int month = now.getMonthOfYear();
            for (int i = 0; i < 12; i++) {
                String yearMonth = year + "年" + month + "月";
                String respStr = HttpUtil.get(String.format(BaiduConstant.API.BAIDU_CALENDAR_URL, yearMonth), null, null);
                BaiduResponse<List<BaiduCalendarDTO>> response = JacksonUtil.json2Object(respStr,
                        new TypeReference<BaiduResponse<List<BaiduCalendarDTO>>>() {
                        });
                if(response.hasError()){
                    throw new BizException(ErrorCode.THIRD_ERROR.getErrCode(), ErrorCode.THIRD_ERROR.getErrDesc() + "[" + response.errorMsg() + "]");
                }
                if(response.isBlack()){
                    return MAP;
                }
                response.getData().get(0).getAlmanac().forEach(almanac -> {
                        Calendar calendar = Calendar.builder()
                                .ymd(almanac.buildYmd())
                                .lunarYmd(almanac.buildLunarYmd())
                                .cnDay(almanac.getCnDay())
                                .holiday(almanac.holiday())
                                .judgeWork(almanac.judgeWork())
                                .timezone(DateTimeZone.getDefault().toString())
                                .build();
                        MAP.put(calendar.getYmd(), calendar);
                });

                month ++;
                if(month > 12){
                    month = 1;
                    year ++;
                }
            }
        }
        return MAP;
    }


}

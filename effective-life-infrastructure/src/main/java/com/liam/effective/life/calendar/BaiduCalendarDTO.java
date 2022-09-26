package com.liam.effective.life.calendar;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liam.li
 */
@Data
public class BaiduCalendarDTO implements Serializable {


    private List<Almanac> almanac;

    /**
     * @see   {
     *                     "animal": "虎",
     *                     "avoid": "结婚.出行.搬家.理发.搬新房.动土.分居.安香.出火.破土",
     *                     "cnDay": "四",
     *                     "day": "1",
     *                     "gzDate": "丁巳",
     *                     "gzMonth": "戊申",
     *                     "gzYear": "壬寅",
     *                     "isBigMonth": "1",
     *                     "lDate": "初六",
     *                     "lMonth": "八",
     *                     "lunarDate": "6",
     *                     "lunarMonth": "8",
     *                     "lunarYear": "2022",
     *                     "month": "9",
     *                     "oDate": "2022-08-31T16:00:00.000Z",
     *                     "suit": "打扫.签订合同.交易.纳财.纳畜.祭祀.收养子女.开光",
     *                     "term": "",
     *                     "year": "2022",
     *                     "yj_from": "51wnl"
     *          }
     */
    @Data
    public static class Almanac{
        private String animal;
        private String cnDay;
        private String day;
        private String gzDate;
        private String gzMonth;
        private String gzYear;
        private String isBigMonth;
        private String lDate;
        private String lMonth;
        private String lunarDate;
        private String lunarMonth;
        private String lunarYear;
        private String month;
        private String status;

        private String oDate;
        private String suit;
        private String year;

        public String buildYmd(){
            return year + "-"
                    + ((Integer.parseInt(month) > 9) ? "0" : "") + month
                    + "-"
                    + ((Integer.parseInt(day) > 9) ? "0" : "") + day;
        }

        public static String buildYmd(int year, int month, int day){
            return year + "-"
                    + ((month > 9) ? "0" : "") + month
                    + "-"
                    + ((day > 9) ? "0" : "") + day;
        }

        public String buildLunarYmd(){
            return lunarYear + "-"
                    + ((Integer.parseInt(lunarMonth) > 9) ? "0" : "") + lunarMonth
                    + "-"
                    + ((Integer.parseInt(lunarDate) > 9) ? "0" : "") + lunarDate;
        }

        public Boolean holiday(){
            return "1".equals(status);
        }

        public Boolean judgeWork(){
            return "2".equals(status);
        }

    }

}

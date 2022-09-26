package com.liam.effective.life.domain.constant;

/**
 * @author liam.li
 */
public interface BaiduConstant {

    /**
     * 百度API
     */
    interface API{
        /**
         * 百度节假日接口
         */
        String BAIDU_HOLIDAY_URL = "http://opendata.baidu.com/api.php?query=YEAR_MONTH&resource_id=6018&format=json";

        /**
         * 百度万年历接口
         */
        String BAIDU_CALENDAR_URL = "http://opendata.baidu.com/api.php?query=[%s]&resource_id=39043&format=json&tn=wisetpl";
    }

}

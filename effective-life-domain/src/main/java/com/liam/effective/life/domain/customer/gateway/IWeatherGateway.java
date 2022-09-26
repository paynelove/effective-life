package com.liam.effective.life.domain.customer.gateway;

import com.liam.effective.life.domain.schedule.weather.Weather;


/**
 * @author liam.li
 */
public interface IWeatherGateway {

    /**
     * holiday 获取当日天气
     * @return Weather 日历对象
     */
    Weather weather();

}

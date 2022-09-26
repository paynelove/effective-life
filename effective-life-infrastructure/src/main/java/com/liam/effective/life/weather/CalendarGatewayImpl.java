package com.liam.effective.life.weather;

import com.liam.effective.life.domain.customer.gateway.IWeatherGateway;
import com.liam.effective.life.domain.schedule.weather.Weather;
import org.springframework.stereotype.Component;

/**
 * @author liam.li
 */
@Component
public class CalendarGatewayImpl implements IWeatherGateway {



    @Override
    public Weather weather() {
        return null;
    }

}

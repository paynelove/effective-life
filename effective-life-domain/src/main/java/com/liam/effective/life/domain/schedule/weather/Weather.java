package com.liam.effective.life.domain.schedule.weather;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

/**
 * @author liam.li
 */
@Data
@Entity
public class Weather {

    private Boolean rain;

    private Double lowTemperature;

    private Double highTemperature;

    private Double currTemperature;

}

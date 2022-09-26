package com.liam.effective.life.domain.schedule.calendar;

import lombok.Builder;
import lombok.Data;

/**
 * @author liam.li
 */
@Data
@Builder
public class Calendar {

    private String ymd;

    private String lunarYmd;

    private Boolean holiday;

    private String timezone;

    private String cnDay;

    private Boolean judgeWork;

}

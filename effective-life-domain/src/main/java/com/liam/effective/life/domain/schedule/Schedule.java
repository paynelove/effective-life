package com.liam.effective.life.domain.schedule;

import lombok.Data;
import org.joda.time.DateTime;


/**
 * @author liam.li
 */
@Data
public class Schedule {

    private String scheduleId;
    private String scheduleName;
    private DateTime startTime;
    private DateTime endTime;

    public Schedule() {
    }

}

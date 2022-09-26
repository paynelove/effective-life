package com.liam.effective.life.dto.data;

import lombok.Data;

import java.util.Date;


@Data
public class ScheduleDTO {

    private String scheduleId;
    private String scheduleName;
    private Date startTime;
    private Date endTime;

}

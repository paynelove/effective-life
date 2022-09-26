package com.liam.effective.life.dto.data;

import lombok.Data;

/**
 * @author liam.li
 */
@Data
public class HolidayDTO {

    private String nextHoliday;
    private Integer distance2nextHoliday;
    private String isFree;
    private String customerType;

}

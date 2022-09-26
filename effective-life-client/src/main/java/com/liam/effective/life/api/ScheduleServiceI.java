package com.liam.effective.life.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.liam.effective.life.dto.CustomerListByNameQry;
import com.liam.effective.life.dto.ScheduleAddCmd;
import com.liam.effective.life.dto.data.CustomerDTO;
import com.liam.effective.life.dto.data.HolidayDTO;

/**
 * @author liam.li
 */
public interface ScheduleServiceI {

    /**
     * 假期
     * @return
     */
    SingleResponse<HolidayDTO> holiday();

    /**
     * 添加任务
     * @param scheduleAddCmd scheduleAddCmd
     * @return
     */
    Response addSchedule(ScheduleAddCmd scheduleAddCmd);


    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);

}

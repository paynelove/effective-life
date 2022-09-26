package com.liam.effective.life.schedule;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.liam.effective.life.api.ScheduleServiceI;
import com.liam.effective.life.dto.CustomerListByNameQry;
import com.liam.effective.life.dto.ScheduleAddCmd;
import com.liam.effective.life.dto.data.CustomerDTO;
import com.liam.effective.life.dto.data.HolidayDTO;
import org.springframework.stereotype.Service;

/**
 * @author liam.li
 */
@Service
@CatchAndLog
public class ScheduleServiceImpl implements ScheduleServiceI {

    @Override
    public SingleResponse<HolidayDTO> holiday() {
        return null;
    }

    @Override
    public Response addSchedule(ScheduleAddCmd scheduleAddCmd) {
        return null;
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return null;
    }

}

package com.liam.effective.life.task;

import com.liam.effective.life.domain.customer.gateway.ICalendarGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liam.li
 */
@Component
public class BaiduCalendarTask {

    @Autowired
    private ICalendarGateway calendarGateway;

    public void syncBaiduCalendarTask(){

    }

}

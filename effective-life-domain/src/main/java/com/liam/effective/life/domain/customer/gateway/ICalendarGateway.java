package com.liam.effective.life.domain.customer.gateway;

import com.liam.effective.life.domain.schedule.calendar.Calendar;

import java.util.Map;


/**
 * @author liam.li
 */
public interface ICalendarGateway {

    /**
     * holiday 获取日历
     * @param year 年
     * @param month 月
     * @param day 日
     * @return Calendar 日历对象
     */
    Calendar calendar(int year , int month, int day);

    /**
     * 获取日历Map
     * @return Calendar 日历MAP
     */
    Map<String, Calendar> calendarMap();

}

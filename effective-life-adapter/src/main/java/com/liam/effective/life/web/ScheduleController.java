package com.liam.effective.life.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.liam.effective.life.api.CustomerServiceI;
import com.liam.effective.life.dto.CustomerAddCmd;
import com.liam.effective.life.dto.CustomerListByNameQry;
import com.liam.effective.life.dto.data.CustomerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liam.li
 */
@RestController("/v1/schedule")
@Api(value = "ScheduleController",tags = "日程api")
public class ScheduleController {

    @Autowired
    private CustomerServiceI customerService;

    @ApiOperation("获取节假日")
    @GetMapping(value = "/holiday")
    public MultiResponse<CustomerDTO> holiday(@RequestParam(required = false) String name){
        CustomerListByNameQry customerListByNameQry = new CustomerListByNameQry();
        customerListByNameQry.setName(name);
        return customerService.listByName(customerListByNameQry);
    }

    @PostMapping(value = "/customer")
    public Response addCustomer(@RequestBody CustomerAddCmd customerAddCmd){
        return customerService.addCustomer(customerAddCmd);
    }
}

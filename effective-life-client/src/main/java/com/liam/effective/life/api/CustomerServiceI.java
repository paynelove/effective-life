package com.liam.effective.life.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.liam.effective.life.dto.CustomerAddCmd;
import com.liam.effective.life.dto.CustomerListByNameQry;
import com.liam.effective.life.dto.data.CustomerDTO;

public interface CustomerServiceI {

    public Response addCustomer(CustomerAddCmd customerAddCmd);

    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}

package com.liam.effective.life.domain.customer.gateway;

import com.liam.effective.life.domain.customer.Customer;
import com.liam.effective.life.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}

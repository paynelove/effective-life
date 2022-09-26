package com.liam.effective.life.domain.customer.gateway;

import com.liam.effective.life.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}

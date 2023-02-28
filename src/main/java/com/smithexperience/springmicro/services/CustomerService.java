package com.smithexperience.springmicro.services;

import com.smithexperience.springmicro.web.model.CustomerDTO;


import java.util.UUID;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID customerId);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(UUID customerId, CustomerDTO customerDTO);

    void deleteCustomerById(UUID customerId);
}



package com.smithexperience.springmicro.web.controller;

import com.smithexperience.springmicro.services.CustomerService;
import com.smithexperience.springmicro.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") UUID customerId){
       return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO theCustomer = customerService.saveCustomer(customerDTO);
        if(theCustomer.getId() == null) {
            throw new RuntimeException("Customer details invalid");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/customer/" + theCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/customerId")
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        log.info("customer deleted");
        customerService.deleteCustomerById(customerId);

        /**
         *  with @ResponseStatus annotation; we eliminate the need to have
         *  a return method [ResponseEntity when 'no-content' is passed back
         */
    }
}

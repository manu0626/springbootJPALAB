package com.chtti.springboot.demo.Demo7JPADocker.service;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> retrieveCustomers();
    Customer getCustomer(Integer customerId);
    void saveCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer customerId);

}

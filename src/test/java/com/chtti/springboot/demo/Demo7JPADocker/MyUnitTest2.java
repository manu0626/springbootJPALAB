package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;
import com.chtti.springboot.demo.Demo7JPADocker.repository.CustomerDemoRepository;
import com.chtti.springboot.demo.Demo7JPADocker.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyUnitTest2 {
    @Autowired
    CustomerService service;

    @Autowired
    private CustomerDemoRepository repository;

    @BeforeEach
    public void clearData(){
        repository.deleteAll();
    }

    @Test
    public void test2_1(){
        service.saveCustomer(new Customer(null,"Mark","Ho"));
        Assertions.assertEquals(repository.count(),1);
    }

}

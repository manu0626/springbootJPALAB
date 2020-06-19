package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;
import com.chtti.springboot.demo.Demo7JPADocker.repository.CustomerDemoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MyUnitTest1 {

    private static final int ALEADY_EXIST_COUNT = 9;

    @BeforeEach
    public void prepare(){
        System.out.println("clear all data");
        repository.deleteAll();
    }

    @Autowired
    private CustomerDemoRepository repository;


    @Test
    public void foo(){
        System.out.println("hello test");
        repository.save(new Customer(null,"Mark","Ho"));
        Assertions.assertEquals(repository.count(),1);
    }

}

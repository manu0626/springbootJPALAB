package com.chtti.springboot.demo.Demo7JPADocker.runner;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;
import com.chtti.springboot.demo.Demo7JPADocker.repository.CustomerDemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@Order(3)
public class CustomerRunner2 implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRunner2.class.getSimpleName());

    @Autowired
    CustomerDemoRepository repository;

    @Override
    public void run(String... args) throws Exception {

        printOrdered();
        printOrderedByPage();

    }

    private void printOrdered() {
        LOGGER.info("print ordered data");
        repository.findAllOrderByName().forEach(customer -> LOGGER.info("[Ord[All]:{}",customer));
    }


    private void printOrderedByPage() {
        Pageable pageable = PageRequest.of(0,3);
        Page<Customer> customerPage1 = repository.findAllOrderByName(pageable);
        LOGGER.info("page size={}, current number={} , total page={}, total components={}",customerPage1.getSize(),
                customerPage1.getNumber(),customerPage1.getTotalPages(),customerPage1.getTotalElements());

        customerPage1.getContent().forEach(customer -> { LOGGER.info("[{}],{}",customerPage1.getNumber(),customer); });
        Page<Customer> customerPage2 = repository.findAllOrderByName(customerPage1.nextPageable());
        customerPage2.getContent().forEach(customer -> { LOGGER.info("[{}],{}",customerPage2.getNumber(),customer); });
    }
}

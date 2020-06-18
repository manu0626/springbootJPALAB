package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Beverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BeverageRunner1 implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageRunner1.class.getSimpleName());
    @Autowired
    MyBeverageCRUDRepository repository;
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("start to test beverage repository");
        insertSomeData();
        loadAllData();
        deleteAndCheckCount();
    }

    private void loadAllData() {
        repository.findAll().forEach(beverage -> LOGGER.info("get a record==>{}",beverage));
    }

    private void insertSomeData() {
        repository.save(new
                Beverage("americano","black coffee with no milk"));
        repository.save(new Beverage("latte", "espresso with 60% milk"));
        repository.save(new Beverage("Assam black tea", "normal black tea"));
    }

    private void deleteAndCheckCount(){
        LOGGER.info("before delete , count={}", repository.count());
        repository.deleteAll();
        LOGGER.info("after delete , count={}", repository.count());
    }

}

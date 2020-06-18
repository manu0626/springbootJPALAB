package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Beverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        loadSomeData();
        loadAllData();
        loadDataByPage();
        loadDataByLike();
        loadDataByContaining();
        deleteAndCheckCount();
    }

    private void loadDataByContaining() {
        LOGGER.info("find detail with milk");
        repository.findByDetailContaining("milk").forEach(beverage -> LOGGER.info("find detail with milk:{}",beverage));
        LOGGER.info("find detail with hot");
        repository.findByDetailContaining("hot").forEach(beverage -> LOGGER.info("find detail with milk:{}",beverage));
    }


    private void loadDataByLike(){
        LOGGER.info("find hot");
        repository.findByTitleLike("hot%").forEach(beverage -> LOGGER.info("hot prefix:{}",beverage));
        repository.findByTitleLike("%latte").forEach(beverage -> LOGGER.info("end with latte:{}",beverage));
    }

    private void loadAllData() {
        LOGGER.info("find all data");
        repository.findAll().forEach(beverage -> LOGGER.info("get a record==>{}",beverage));
    }

    private void loadSomeData(){

        LOGGER.info("find all american only");
        repository.findMatchByTitle("americano").forEach(beverage -> LOGGER.info("aanericano only == >{}",beverage));
    }

    private void loadDataByPage(){
        LOGGER.info("load data by page");
        PageRequest pr1 = PageRequest.of(0,4);
        repository.findAll(pr1).forEach(beverage -> {
            LOGGER.info("#{}  page:{}",pr1.getPageNumber(),beverage);
        });
        Pageable pr2 = pr1.next();
        repository.findAll(pr2).forEach(beverage -> {
            LOGGER.info("#{}  page:{}",pr2.getPageNumber(),beverage);
        });
    }

    private void insertSomeData() {
        repository.save(new Beverage("americano","ice black coffee with no milk"));
        repository.save(new Beverage("americano","hot black coffee with no milk"));
        repository.save(new Beverage("ice latte", "espresso with 60% milk"));
        repository.save(new Beverage("hot latte", "espresso with 60% milk"));
        repository.save(new Beverage("ice Assam black tea", "normal black tea"));
        repository.save(new Beverage("hot Assam black tea", "normal black tea"));
        repository.save(new Beverage("ice milk tea", "balck tea with milk"));
        repository.save(new Beverage("hot milk tea", "balck tea with milk"));
    }

    private void deleteAndCheckCount(){
        LOGGER.info("before delete , count={}", repository.count());
        repository.deleteAll();
        LOGGER.info("after delete , count={}", repository.count());
    }

}

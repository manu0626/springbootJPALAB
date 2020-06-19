package com.chtti.springboot.demo.Demo7JPADocker.repository;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDemoRepository  extends JpaRepository<Customer, Integer> {

    //JPQL
    @Query("SELECT x FROM Customer x ORDER BY x.lastName, x.firstName")
    List<Customer> findAllOrderByName();

    @Query("SELECT x FROM Customer x ORDER BY x.lastName, x.firstName")
    Page<Customer> findAllOrderByName(Pageable pageable);
}

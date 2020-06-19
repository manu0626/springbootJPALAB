package com.chtti.springboot.demo.Demo7JPADocker.service;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;
import com.chtti.springboot.demo.Demo7JPADocker.repository.CustomerDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDemoRepository repository;

    @Override
    public List<Customer> retrieveCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(Integer customerId) {

        Optional<Customer> maybeACustomer = repository.findById(customerId);

        return maybeACustomer.get();
    }

    @Override
    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        repository.deleteById(customerId);
    }
}

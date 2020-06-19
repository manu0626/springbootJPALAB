package com.chtti.springboot.demo.Demo7JPADocker.controller;

import com.chtti.springboot.demo.Demo7JPADocker.beans.Customer;
import com.chtti.springboot.demo.Demo7JPADocker.forms.CustomerForm;
import com.chtti.springboot.demo.Demo7JPADocker.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @ModelAttribute
    CustomerForm setupForm(){
        return new CustomerForm();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@Validated CustomerForm form, BindingResult result,Model model){

        Customer customer = new Customer();
        BeanUtils.copyProperties(form,customer);
        service.saveCustomer(customer);

        return "redirect:/customers";
    }

    @RequestMapping(method = RequestMethod.GET)
    String listAll(Model model){

        model.addAttribute("customers",service.retrieveCustomers());

        return "customers/list";
    }

}

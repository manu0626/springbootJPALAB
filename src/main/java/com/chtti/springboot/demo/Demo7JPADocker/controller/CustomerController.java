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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    //方法執行前先執行
    @ModelAttribute
    CustomerForm setupForm(){
        return new CustomerForm();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@Validated CustomerForm form, BindingResult result,Model model){

        if(result.hasErrors()){
            return listAll(model);
        }

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

    @RequestMapping(value = "delete" , method = RequestMethod.POST)
    String delete(@RequestParam Integer id){

        service.deleteCustomer(id);
        return "redirect:/customers";

    }

    @RequestMapping(value = "edit" , method = RequestMethod.GET)
    String edit(CustomerForm form,@RequestParam Integer id,Model model){

        Customer customer = service.getCustomer(id);

        model.addAttribute("customer",customer);

        form.setFirstName(customer.getFirstName());
        form.setLastName(customer.getLastName());

        return "customers/edit";

    }

    @RequestMapping(value = "modify" , method = RequestMethod.POST)
    String modify(CustomerForm form,@RequestParam Integer id){
        Customer customer = service.getCustomer(id);

        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        service.updateCustomer(customer);

        return "redirect:/customers";
    }


}

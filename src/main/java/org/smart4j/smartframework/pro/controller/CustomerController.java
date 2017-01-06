package org.smart4j.smartframework.pro.controller;

import org.smart4j.smartframework.annotation.Action;
import org.smart4j.smartframework.annotation.Controller;
import org.smart4j.smartframework.annotation.Inject;
import org.smart4j.smartframework.bean.Data;
import org.smart4j.smartframework.bean.Param;
import org.smart4j.smartframework.bean.View;
import org.smart4j.smartframework.pro.model.Customer;
import org.smart4j.smartframework.pro.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016-12-29.
 */
@Controller
public class CustomerController {

   // @Inject
    private CustomerService customerService=new CustomerService();

    @Action("get:/customer")
    public View index(Param param){
        System.out.println("index 进来啦啦啦");
        List<Customer> customerList=customerService.getCustomerList();
        System.out.println("getCustomerList 好啦啦啦");
        return new View("customer.jsp").addModel("customerList",customerList);
    }

    @Action("get:/customer_show")
    public View show(Param param){
        long id=param.getLong("id");
        Customer customer= customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer",customer);
    }

    @Action("get:/customer_create")
    public View create(Param param){
        return new View("customer_create.jsp");
    }

    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String,Object> fieldMap=param.getMap();
        boolean result=customerService.createCustomer(fieldMap);
        return new Data(result);
    }

    @Action("get:/customer_edit")
    public View edit(Param param){
        long id=param.getLong("id");
        Customer customer= customerService.getCustomer(id);
        return new View("customer_edit.jsp").addModel("customer",customer);
    }

    @Action("put:/customer_edit")
    public Data editSubmit(Param param){
        long id=param.getLong("id");
        Map<String,Object> fieldMap=param.getMap();
        boolean result=customerService.updateCustomer(id,fieldMap);
        return new Data(result);
    }

    @Action("delete:/customer_edit")
    public Data delete(Param param){
        long id=param.getLong("id");
        boolean result=customerService.deleteCustomer(id);
        return new Data(result);
    }
}

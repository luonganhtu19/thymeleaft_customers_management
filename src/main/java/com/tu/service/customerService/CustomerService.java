package com.tu.service.customerService;

import com.tu.model.Customer;

import java.util.*;

public class CustomerService implements ICustomerService {
    private  static Map<Integer,Customer> customers;
    static {
        customers=new HashMap<>();
        customers.put(1,new Customer(1,"Jonh","jounh2@kkkk","HaNoi"));
        customers.put(2,new Customer(2,"Jonh111","jounh2@kkkk1","HaNoi"));
        customers.put(3,new Customer(3,"Jonh22","joun22222h2@kkkk","HaNoi"));
        customers.put(4,new Customer(4,"Jon11111111111h","jounh2@kkkk","HaNoi"));
    }
    public CustomerService(){};
    @Override
    public List<Customer> findAll() {
        ArrayList customerList= new ArrayList(customers.values());
        return customerList;
    }

    @Override
    public Customer findById(Integer id) {
        return customers.get(id);
    }

    @Override
    public void update(Integer id,Customer model) {
        customers.put(id,model);
    }

    @Override
    public void save(Customer model) {
        model.setId(setID(model)+1);
        customers.put(model.getId(),model);
    }

    @Override
    public void remove(Integer id) {
        customers.remove(id);
    }

    private int setID(Customer model){
       return Collections.max(customers.entrySet(), Map.Entry.comparingByKey()).getKey();
    }
}

package com.glodon.springboot.spring_boot_demo.dao;

import com.glodon.springboot.spring_boot_demo.domain.Customer;

import java.util.List;

public interface CustomerMapper {

    public void save(Customer customer);

    public List<Customer> findAll();

    public Customer findById(Integer id);

    public void update(Customer customer);

    public void delete(Integer id);
}

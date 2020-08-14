package com.glodon.springboot.spring_boot_demo.service.impl;

import com.glodon.springboot.spring_boot_demo.dao.CustomerMapper;
import com.glodon.springboot.spring_boot_demo.domain.Customer;
import com.glodon.springboot.spring_boot_demo.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public void save(Customer customer) {
        //判断是否添加还是修改
        if(customer.getId()!=null){
            //修改
            customerMapper.update(customer);
        }else{
            //添加
            customerMapper.save(customer);
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return customerMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        customerMapper.delete(id);
    }
}

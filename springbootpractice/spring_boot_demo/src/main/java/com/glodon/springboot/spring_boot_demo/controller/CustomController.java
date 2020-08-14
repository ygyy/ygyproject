package com.glodon.springboot.spring_boot_demo.controller;

import com.glodon.springboot.spring_boot_demo.domain.Customer;
import com.glodon.springboot.spring_boot_demo.service.CustomerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomController {
    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到input页面
     * @param model
     * @return
     */
    @RequestMapping("/input")
    public String input(Model model){
        Customer customer = new Customer();
        model.addAttribute("cust",customer);
        return "input";
    }

    /**
     * 保存或修改
     * @param customer
     * @return
     */
    @RequestMapping("/save")
    public String  save(Customer customer){
        customerService.save(customer);
        return "redirect:/customer/list";
    }
    @RequestMapping("/list")
    public String list(Model model){
        List<Customer> list = customerService.findAll();
        model.addAttribute("customer",list);
        return "succ";
    }

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    @GetMapping("/findbyId")
    public String findbyId(Integer id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("cust",customer);
        return "input";
    }

    /**
     * 根据id删除客户信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        customerService.delete(id);
        return "redirect:/customer/list";
    }

}

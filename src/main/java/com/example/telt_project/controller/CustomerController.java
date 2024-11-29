package com.example.telt_project.controller;

import com.example.telt_project.model.Customer;
import com.example.telt_project.service.CustomerService;
import com.example.telt_project.util.JwtUtil;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final JwtUtil jwtUtil;

    // Login endpoint
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Customer customer = customerService.getCustomer(email, password); // Authenticate user
        return jwtUtil.generateToken(customer.getEmail()); // Return JWT token
    }


    @GetMapping("/all")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/get")
    public Customer getCustomer(@RequestParam(name = "email") String email,
                                @RequestParam(name = "password") String password) {
        return customerService.getCustomer(email,password);
    }

    @PostMapping("/add")
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping("/delete")
    public void deleteCustomerByEmail(@RequestParam(name = "email") String email) {
        customerService.deleteCustomerByEmail(email);
    }
}

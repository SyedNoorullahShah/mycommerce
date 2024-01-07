package com.project.mycommerce.service.impl;

import com.project.mycommerce.model.Customer;
import com.project.mycommerce.repository.CustomerRepository;
import com.project.mycommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }
}

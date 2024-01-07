package com.project.mycommerce.service;

import com.project.mycommerce.model.Area;
import com.project.mycommerce.model.Customer;

import java.util.List;
import java.util.Random;

public interface OrderService {
  void createOrderAndSave(List<Area> areas, List<Customer> customers, Random random);
}

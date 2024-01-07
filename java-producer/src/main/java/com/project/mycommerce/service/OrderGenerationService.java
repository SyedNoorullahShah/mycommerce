package com.project.mycommerce.service;

import com.project.mycommerce.model.Area;
import com.project.mycommerce.model.Customer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class OrderGenerationService {

  private final OrderService orderService;
  private final AreaService areaService;
  private final CustomerService customerService;

  @PostConstruct
  public void init() {
    startOrdersGeneration();
  }

  private void startOrdersGeneration() {
    List<Area> areas = areaService.findAll();
    List<Customer> customers = customerService.findAll();
    Random random = new Random();
    Timer timer = new Timer();
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            orderService.createOrderAndSave(areas, customers, random);
          }
        },
        500,
        500);
  }
}

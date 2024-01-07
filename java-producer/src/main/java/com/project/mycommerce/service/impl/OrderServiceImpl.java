package com.project.mycommerce.service.impl;

import com.project.mycommerce.model.Area;
import com.project.mycommerce.model.Customer;
import com.project.mycommerce.model.Inventory;
import com.project.mycommerce.model.Order;
import com.project.mycommerce.model.OrderItem;
import com.project.mycommerce.repository.OrderRepository;
import com.project.mycommerce.service.InventoryService;
import com.project.mycommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final InventoryService inventoryService;

  @Override
  @Transactional
  public void createOrderAndSave(List<Area> areas, List<Customer> customers, Random random) {
    Order order = new Order();
    setArea(order, areas, random);
    setCustomer(order, customers, random);
    setOrderItems(order, random);
    setTotal(order, order.getOrderItemList());
    save(order);
  }

  private void setTotal(Order order, List<OrderItem> orderItemList) {
    if (CollectionUtils.isNotEmpty(orderItemList)) {
      double total = orderItemList.stream().mapToDouble(OrderItem::getTotal).sum();
      order.setTotal(total);
    }
  }

  // shuffles the inventory and picks the first 5 products
  private List<Inventory> getInventoryListForOrderPlacement() {
    List<Inventory> inventoryList = inventoryService.findAllWithProducts();
    Collections.shuffle(inventoryList);
    return inventoryList.subList(0, 5);
  }

  private void setOrderItems(Order order, Random random) {
    List<Inventory> inventoryList = getInventoryListForOrderPlacement();

    for (Inventory item : inventoryList) {
      if (item.getQuantity() <= 0) continue;

      OrderItem orderItem = new OrderItem();
      orderItem.setProduct(item.getProduct());
      orderItem.setUnitPrice(item.getUnitPrice());
      Integer quantity = item.getQuantity() > 5 ? random.nextInt(5) + 1 : item.getQuantity();
      orderItem.setQuantity(quantity);
      int newItemQuantity = item.getQuantity() - quantity;
      item.setQuantity(
          newItemQuantity > 0 ? newItemQuantity : 1000); // resetting quantity to 1000 in case of 0
      orderItem.setTotal(item.getUnitPrice() * quantity);
      order.addOrderItem(orderItem);
    }
  }

  private void setArea(Order order, List<Area> areas, Random random) {
    Area area = areas.get(random.nextInt(areas.size()));
    order.setArea(area);
  }

  private void setCustomer(Order order, List<Customer> customers, Random random) {
    Customer customer = customers.get(random.nextInt(customers.size()));
    order.setCustomer(customer);
  }

  public void save(Order order) {
    log.info("save:: saving order having details {}", order.toString());
    orderRepository.save(order);
  }
}

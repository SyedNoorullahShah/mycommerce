package com.project.mycommerce.service;

import com.project.mycommerce.model.Inventory;

import java.util.List;

public interface InventoryService {
  List<Inventory> findAllWithProducts();
}

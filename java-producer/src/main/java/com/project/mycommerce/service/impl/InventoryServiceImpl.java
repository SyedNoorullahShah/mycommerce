package com.project.mycommerce.service.impl;

import com.project.mycommerce.model.Inventory;
import com.project.mycommerce.repository.InventoryRepository;
import com.project.mycommerce.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  @Override
  public List<Inventory> findAllWithProducts() {
    return inventoryRepository.findAllWithProducts();
  }
}

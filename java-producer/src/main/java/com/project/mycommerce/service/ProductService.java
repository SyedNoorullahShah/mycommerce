package com.project.mycommerce.service;

import com.project.mycommerce.dto.TopProductsListDTO;
import com.project.mycommerce.model.Product;

import java.util.List;

public interface ProductService {
  List<Product> findAll();

  List<TopProductsListDTO> findTopProductsLastThirtySeconds();
}

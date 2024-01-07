package com.project.mycommerce.service.impl;

import com.project.mycommerce.dto.TopProductsListDTO;
import com.project.mycommerce.model.Product;
import com.project.mycommerce.repository.ProductRepository;
import com.project.mycommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public List<TopProductsListDTO> findTopProductsLastThirtySeconds() {
    LocalDateTime currentTimestamp = LocalDateTime.now();
    LocalDateTime pastTimestamp = currentTimestamp.minusSeconds(30);

    return productRepository.findTopProductsBetweenDateTime(
        PageRequest.ofSize(10), pastTimestamp, currentTimestamp);
  }
}

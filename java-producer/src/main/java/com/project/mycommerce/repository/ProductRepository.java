package com.project.mycommerce.repository;

import com.project.mycommerce.dto.TopProductsListDTO;
import com.project.mycommerce.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query("""
      SELECT NEW com.project.mycommerce.dto.TopProductsListDTO(oi.product.name, SUM(oi.total))
      FROM OrderItem oi
      WHERE oi.createdAt BETWEEN (:pastTimestamp) AND (:currentTimestamp)
      GROUP BY oi.product
      ORDER BY SUM(oi.total) DESC""")
  List<TopProductsListDTO> findTopProductsBetweenDateTime(Pageable pageable, LocalDateTime pastTimestamp,
                                                          LocalDateTime currentTimestamp);
}

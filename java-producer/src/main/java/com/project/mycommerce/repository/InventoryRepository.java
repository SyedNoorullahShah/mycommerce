package com.project.mycommerce.repository;

import com.project.mycommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

  @Query("""
      select i from Inventory i
      left join fetch i.product""")
  public List<Inventory> findAllWithProducts();
}

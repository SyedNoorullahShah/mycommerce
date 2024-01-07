package com.project.mycommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  private Double unitPrice;
  private Integer quantity;
  private Double total;

  @Override
  public String toString() {
    return "OrderItem{" +
        ", product=" + product.getName() +
        ", unitPrice=" + unitPrice +
        ", quantity=" + quantity +
        ", total=" + total +
        '}';
  }
}

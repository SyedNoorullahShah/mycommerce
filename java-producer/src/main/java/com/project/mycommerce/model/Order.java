package com.project.mycommerce.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`order`")
public class Order extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  private Double total;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItemList;

  public void addOrderItem(OrderItem orderItem) {
    if (CollectionUtils.isEmpty(orderItemList)) {
      orderItemList = new ArrayList<>();
    }
    orderItem.setOrder(this);
    orderItemList.add(orderItem);
  }

  @Override
  public String toString() {
    return "Order{" +
        "customer=" + customer.getName() +
        ", area=" + area.getName() +
        ", total=" + total +
        ", orderItemList=" + orderItemList +
        '}';
  }
}

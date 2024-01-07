package com.project.mycommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Area extends BaseEntity {
  private String code;
  private String name;
}

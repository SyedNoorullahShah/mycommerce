package com.project.mycommerce.repository;

import com.project.mycommerce.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
}

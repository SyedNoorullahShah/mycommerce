package com.project.mycommerce.service.impl;

import com.project.mycommerce.model.Area;
import com.project.mycommerce.repository.AreaRepository;
import com.project.mycommerce.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

  private final AreaRepository areaRepository;

  @Override
  public List<Area> findAll() {
    return areaRepository.findAll();
  }
}

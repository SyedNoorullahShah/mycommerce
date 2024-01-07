package com.project.mycommerce.service.job;

import com.project.mycommerce.dto.TopProductsListDTO;
import com.project.mycommerce.kafka.KafkaProducer;
import com.project.mycommerce.kafka.KafkaTopics;
import com.project.mycommerce.service.ProductService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendUpdatesJob implements Job {

  @Autowired private ProductService productService;

  @Autowired private KafkaProducer kafkaProducer;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    List<TopProductsListDTO> topProducts = productService.findTopProductsLastThirtySeconds();
    kafkaProducer.sendMessage(topProducts, KafkaTopics.TOP_PRODUCTS_TOPIC);
  }
}

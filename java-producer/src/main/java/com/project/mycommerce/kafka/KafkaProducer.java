package com.project.mycommerce.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public void sendMessage(Object message, String topic) {
    try {
      String jsonMessage = objectMapper.writeValueAsString(message);
      log.info(
          "sendMessage:: sending the following message on topic {}:- \n{}", topic, jsonMessage);
      kafkaTemplate.send(topic, jsonMessage);
    } catch (JsonProcessingException e) {
      log.error("sendMessage:: Failed to send message to topic {}, error: {}", topic, e);
    }
  }
}

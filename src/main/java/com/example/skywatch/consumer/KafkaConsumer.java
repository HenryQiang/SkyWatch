package com.example.skywatch.consumer;

// 导入自定义工具类，提供SM4加密解密功能（来自您之前添加的JAR包）

import com.yunwangan.utils.SM4Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class KafkaConsumer {


    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    // 声明为final确保线程安全，避免在多线程环境下出现问题
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "erkai")
    public void consume(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);

            String msgId = jsonNode.get("msgId").asText();
            String encryptedContent = jsonNode.get("content").asText();

            String decryptedContent = SM4Util.decryptData_CBC(encryptedContent);
            System.out.println("Received message: msgId: " + msgId + " Decrypted Data: " + decryptedContent);

        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }
}
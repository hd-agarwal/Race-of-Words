package org.example.wordsServer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.race_of_words.DatasetMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class WordsConsumer {
//    Logger logger = LoggerFactory.getLogger(WordsConsumer.class);

    WordsService wordsService;

    @KafkaListener(topics = "words_data")
    @Transactional(rollbackFor = Exception.class)
    public void consumeWords(@Payload DatasetMessage datasetMessage,  @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key) throws Exception {
        if (key == null) {
//            logger.warn("Message received without key");
            return;
        }
//        logger.info("Processing message with key: {}", key);
        try {
            wordsService.addAllWords(datasetMessage.getText());
            throw new Exception("some error occurred");
        } catch (Exception e) {
//            logger.error("Error occurred while trying to add words from message with key: {}", key);
            throw e;
        }
//        System.out.println("Received " + key);
//        System.out.println("\t\turl: " + datasetMessage.getUrl());
//        System.out.println("\t\ttext:" + datasetMessage.getText());
//        System.out.println("\t\tkey:" + datasetMessage.get("myKey"));
        
    }
}

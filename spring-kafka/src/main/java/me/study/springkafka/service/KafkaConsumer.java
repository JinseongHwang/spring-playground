package me.study.springkafka.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.springkafka.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    @KafkaListener(topics = Constants.TOPIC, groupId = Constants.CONSUMER_GROUP)
    public void consume(String message) throws IOException {
        log.info("Consumed message : {}", message);
    }
}

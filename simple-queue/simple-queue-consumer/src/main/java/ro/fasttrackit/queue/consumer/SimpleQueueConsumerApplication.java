package ro.fasttrackit.queue.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ro.fasttrackit.queue.dto.Msg;

@SpringBootApplication
public class SimpleQueueConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleQueueConsumerApplication.class, args);
    }

    @Bean
    Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

@Slf4j
@Service
@RabbitListener(queues = "simple-queue")
class MyListener {
//    @RabbitHandler
//    void handleMessage(String msg) {
//        log.info("Received " + msg);
//    }

    @RabbitHandler
    void handleJson(Msg msg) {
        log.info("received " + msg);
    }
}
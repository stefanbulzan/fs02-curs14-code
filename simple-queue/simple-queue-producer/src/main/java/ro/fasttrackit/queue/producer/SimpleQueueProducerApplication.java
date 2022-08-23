package ro.fasttrackit.queue.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.queue.dto.Msg;

@SpringBootApplication
public class SimpleQueueProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleQueueProducerApplication.class, args);
    }

}

@RestController
@RequiredArgsConstructor
@RequestMapping("msg")
class MsgController {
    private final RabbitTemplate rabbit;
    private final Queue simpleQueue;

    @GetMapping
    void publishMessage() {
        rabbit.convertAndSend(simpleQueue.getName(), "hello");
    }

    @GetMapping("json")
    void publishJson() {
        rabbit.convertAndSend(simpleQueue.getName(), new Msg("Hello json"));
    }
}

@Configuration
class RabbitConfig {
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Queue simpleQueue() {
        return new Queue("simple-queue");
    }
}
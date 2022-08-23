package ro.fasttrackit.exchange.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // DIRECT EXCHANGE
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("fasttrack.direct");
    }

    @Bean
    Queue directQueue() {
        return new Queue("exchange-direct-consumer");
    }

    @Bean
    Binding directBindingMyTopic(Queue directQueue, DirectExchange exchange) {
        return BindingBuilder.bind(directQueue).to(exchange).with("my-topic");
    }

    @Bean
    Binding directBindingOther(Queue directQueue, DirectExchange exchange) {
        return BindingBuilder.bind(directQueue).to(exchange).with("other");
    }

    //TOPIC EXCHANGE
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("fasttrack.topic");
    }

    @Bean
    Queue topicQueue() {
        return new Queue("exchange-topic-consumer");
    }


    //students.<studentId>
    @Bean
    Binding topicBindingStudent(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("students.#");
    }

    @Bean
    MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //FANOUT
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fasttrack.fanout");
    }

    @Bean
    Queue fanoutQueue() {
        return new Queue("exchange-fanout-consumer");
    }

    @Bean
    Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }


}

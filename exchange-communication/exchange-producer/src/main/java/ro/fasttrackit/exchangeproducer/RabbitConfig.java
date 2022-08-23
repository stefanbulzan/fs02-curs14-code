package ro.fasttrackit.exchangeproducer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("fasttrack.direct");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("fasttrack.topic");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fasttrack.fanout");
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbit = new RabbitTemplate(connectionFactory);
        rabbit.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbit;
    }
}

package ro.fasttrackit.exchangeproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.queue.dto.Msg;
import ro.fasttrackit.queue.dto.Student;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessagePublisher {
    private final TopicExchange topicExchange;
    private final DirectExchange directExchange;
    private final FanoutExchange fanoutExchange;
    private final RabbitTemplate rabbit;

    public void publishDirect(String topic) {
        log.info("publishing direct %s".formatted(topic));
        rabbit.convertAndSend(directExchange.getName(), topic, new Msg("Hello on direct topic " + topic));
    }

    public void publishTopic(String topic) {
        log.info("publishing on topic %s".formatted(topic));
        rabbit.convertAndSend(topicExchange.getName(), topic, new Student("Maria", 25));
    }

    public void publishFanout(String msg) {
        log.info("publishing on fanout %s".formatted(msg));
        rabbit.convertAndSend(fanoutExchange.getName(), "", new Msg(msg));
    }
}

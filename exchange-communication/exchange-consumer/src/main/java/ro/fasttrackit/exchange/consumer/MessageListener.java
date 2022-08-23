package ro.fasttrackit.exchange.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.queue.dto.Msg;
import ro.fasttrackit.queue.dto.Student;

@Slf4j
@Component
public class MessageListener {
    @RabbitListener(queues = "#{directQueue.name}")
    void receiveDirect(Msg msg) {
        log.info("Received message %s".formatted(msg));
    }

    @RabbitListener(queues = "#{topicQueue.name}")
    void receiveTopic(Student msg) {
        log.info("Received student %s".formatted(msg));
    }

    @RabbitListener(queues = "#{fanoutQueue.name}")
    void receiveFanout(Msg msg) {
        log.info("Received msg %s".formatted(msg));
    }

}

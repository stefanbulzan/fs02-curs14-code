package ro.fasttrackit.exchangeproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publish")
@RequiredArgsConstructor
public class MessageController {
    private final MessagePublisher messagePublisher;

    @GetMapping("direct/{topic}")
    void publishDirect(@PathVariable String topic) {
        messagePublisher.publishDirect(topic);
    }

    @GetMapping("topic/students")
    void publishStudent() {
        messagePublisher.publishTopic("students");
    }

    @GetMapping("topic/students/{id}")
    void publishStudent(@PathVariable String id) {
        messagePublisher.publishTopic("students.%s".formatted(id));
    }

    @GetMapping("fanout")
    void publishFanout(@RequestParam(required = false) String msg) {
        messagePublisher.publishFanout(msg == null ? "hello" : msg);
    }
}

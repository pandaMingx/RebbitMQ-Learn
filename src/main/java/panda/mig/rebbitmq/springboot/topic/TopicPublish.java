package panda.mig.rebbitmq.springboot.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicPublish {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // http://localhost:8080/topics
    @RequestMapping("topics")
    public String testHello(){
        rabbitTemplate.convertAndSend("topics","user.save.findAll","user.save.findAll 的消息");
        return "topics";
    }
}

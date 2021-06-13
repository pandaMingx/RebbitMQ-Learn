package panda.mig.rebbitmq.springboot.work;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkPublish {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // http://localhost:8080/work
    @RequestMapping("work")
    public String testHello(){
        rabbitTemplate.convertAndSend("work","work work");
        return "work";
    }
}

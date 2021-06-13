package panda.mig.rebbitmq.springboot.hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloPublish {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // http://localhost:8080/hello
    @RequestMapping("hello")
    public String testHello(){
        rabbitTemplate.convertAndSend("hello","hello world");
        return "hello";
    }
}

package panda.mig.rebbitmq.springboot.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectPublish {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // http://localhost:8080/direct
    @RequestMapping("direct")
    public String testHello(){
        rabbitTemplate.convertAndSend("directs","error","error 的日志信息");
        return "direct";
    }
}

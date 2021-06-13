package panda.mig.rebbitmq.springboot.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutPublish {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // http://localhost:8080/fanout
    @RequestMapping("fanout")
    public String testHello(){
        rabbitTemplate.convertAndSend("logs","","这是日志广播");
        return "fanout";
    }
}

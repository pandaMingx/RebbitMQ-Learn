package panda.mig.rebbitmq.publishsubscribe;

import com.rabbitmq.client.*;
import panda.mig.rebbitmq.utils.RebbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RebbitUtils.getConnection();

        final Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("logs","fanout");
//创建临时队列
        String queue = channel.queueDeclare().getQueue();
//将临时队列绑定exchange
        channel.queueBind(queue,"logs","");
//处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: "+new String(body));
            }
        });
    }
}

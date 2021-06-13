package panda.mig.rebbitmq.routing.direct;

import com.rabbitmq.client.*;
import panda.mig.rebbitmq.utils.RebbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RebbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("logs_direct","direct");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定队列和交换机
        channel.queueBind(queue,"logs_direct","error");

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: "+new String(body));
            }
        });
    }
}

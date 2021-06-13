package panda.mig.rebbitmq.workquene;

import com.rabbitmq.client.*;
import panda.mig.rebbitmq.utils.RebbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RebbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);//一次只接受一条未确认的消息
        channel.queueDeclare("hello",true,false,false,null);
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: "+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);//手动确认消息
            }
        });
    }
}

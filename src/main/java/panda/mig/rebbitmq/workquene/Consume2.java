package panda.mig.rebbitmq.workquene;

import com.rabbitmq.client.*;
import panda.mig.rebbitmq.utils.RebbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RebbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",true,false,false,null);
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(1000);   //处理消息比较慢 一秒处理一个消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2: "+new String(body));
            }
        });
    }
}

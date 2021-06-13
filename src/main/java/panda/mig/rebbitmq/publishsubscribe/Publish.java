package panda.mig.rebbitmq.publishsubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import panda.mig.rebbitmq.utils.RebbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publish {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RebbitUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
        channel.queueDeclare("hello",true,false,false,null);

        //声明交换机
        channel.exchangeDeclare("logs","fanout");//广播 一条消息多个消费者同时消费
//发布消息
        channel.basicPublish("logs","",null,"hello".getBytes());

//        for (int i = 0; i < 10; i++) {
//            channel.basicPublish("", "hello", null, (i+"====>:我是消息").getBytes());
//        }

        RebbitUtils.close(connection,channel);
    }
}

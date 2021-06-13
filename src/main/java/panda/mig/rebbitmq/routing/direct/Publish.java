package panda.mig.rebbitmq.routing.direct;

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

        //声明交换机  参数1:交换机名称 参数2:交换机类型 基于指令的Routing key转发
        channel.exchangeDeclare("logs_direct","direct");
        String key = "info";
        //发布消息
        channel.basicPublish("logs_direct",key,null,("指定的route key"+key+"的消息").getBytes());

        RebbitUtils.close(connection,channel);
    }
}

package panda.mig.rebbitmq.routing.topic;

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

        //声明交换机和交换机类型 topic 使用动态路由(通配符方式)
        channel.exchangeDeclare("topics","topic");
        String routekey = "user.save";//动态路由key
        //发布消息
        channel.basicPublish("topics",routekey,null,("这是路由中的动态订阅模型,route key: ["+routekey+"]").getBytes());

        RebbitUtils.close(connection,channel);
    }
}

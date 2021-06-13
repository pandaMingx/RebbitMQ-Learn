package panda.mig.rebbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RebbitUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("hello");
        connectionFactory.setPassword("hello");
        connectionFactory.setVirtualHost("hello");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }

    public static void close(Connection cn, Channel ch) throws IOException, TimeoutException {
        if(null != ch){
            ch.close();
        }
        if(null != cn){
            cn.close();
        }
    }
}

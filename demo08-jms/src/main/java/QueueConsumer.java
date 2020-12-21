import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class QueueConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.188.128:61616");
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session 参数 1.是否启动事务 2.消息确认模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建消息队列对象 点对点
        Queue queue = session.createQueue("test-queue");
        //6.创建消息生产者
        MessageConsumer messageConsumer = session.createConsumer(queue);

        //7.创建监听器，监听消息
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //8.设置键盘输入事件，防止消息未监听，就已关闭流
        System.in.read();

        //9.关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
    }
}

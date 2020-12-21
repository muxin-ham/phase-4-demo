import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工程
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.188.128:61616");
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建连接方式
        Topic topic = session.createTopic("test-topic");
        //6.创建消费zhe
        MessageConsumer consumer = session.createConsumer(topic);
        //7.设置监听，监听消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());;
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //8.设置键盘录入，....
        System.in.read();

        //9.关闭流资源
        consumer.close();
        session.close();
        connection.close();
    }
}

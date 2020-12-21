import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {
    public static void main(String[] args) throws JMSException {
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
        //6.创建生产者
        MessageProducer producer = session.createProducer(topic);
        //7.设置消息
        TextMessage textMessage = session.createTextMessage("topic连接测试");
        //8.发送消息
        producer.send(textMessage);

        //9.关闭流资源
        producer.close();
        session.close();
        connection.close();
    }
}

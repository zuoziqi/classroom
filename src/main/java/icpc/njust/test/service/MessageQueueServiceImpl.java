package icpc.njust.test.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service("MessageQueueService")
public class MessageQueueServiceImpl implements MessageQueueService {
    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
    @Override
    public void sendMessage(Destination destination, final String msg) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    @Override
    public void sendMessage(final String msg) {
        String destination = jmsTemplate.getDefaultDestinationName();
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}

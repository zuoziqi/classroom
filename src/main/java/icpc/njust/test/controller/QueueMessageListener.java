package icpc.njust.test.controller;

import com.alibaba.fastjson.JSON;
import icpc.njust.test.MessageTemplate.CommonRequest;
import icpc.njust.test.ServletListener.ContextInitializeListener;
import icpc.njust.test.service.MessageQueueService;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.util.Objects;


public class QueueMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        MessageQueueService messageQueueService= ContextInitializeListener.getMessageQueueService();
        ProxyOperator proxyOperator=ContextInitializeListener.getProxyOperator();
        JmsTemplate jmsTemplate=ContextInitializeListener.getJmsTemplate();
        if(messageQueueService==null||proxyOperator==null||jmsTemplate==null) return;
        try {
            TextMessage messageText=(TextMessage) message;
            Destination target = Objects.requireNonNull(jmsTemplate.getConnectionFactory())
                    .createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE).createQueue("classroom.Response");
            CommonRequest request= (CommonRequest) JSON.parse(messageText.getText());
            if(request==null||request.token==null) return;
            String result=proxyOperator.operator(request.methodName,request.methodArgsStrings);
            messageQueueService.sendMessage(target,result);
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}

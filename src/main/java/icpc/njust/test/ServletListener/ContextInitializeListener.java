package icpc.njust.test.ServletListener;

import com.alibaba.fastjson.JSON;
import icpc.njust.test.MessageTemplate.ModuleInfo;
import icpc.njust.test.MessageTemplate.UserPrivilege;
import icpc.njust.test.controller.ProxyOperator;
import icpc.njust.test.service.MessageQueueService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class ContextInitializeListener implements ServletContextListener {
    private static JmsTemplate jmsTemplate=null;
    private static WebApplicationContext applicationContext=null;
    private static ProxyOperator proxyOperator=null;
    private static MessageQueueService messageQueueService=null;

    public static JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public static MessageQueueService getMessageQueueService() {
        return messageQueueService;
    }

    public static WebApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static ProxyOperator getProxyOperator() {
        return proxyOperator;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationContext= WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        assert applicationContext != null;
        proxyOperator= (ProxyOperator) applicationContext.getBean("ProxyOperator");
        messageQueueService=(MessageQueueService)applicationContext.getBean("MessageQueueService");
        jmsTemplate= (JmsTemplate) applicationContext.getBean("jmsTemplate");
        Map<String,Class> mapping=proxyOperator.getProxyMapping();
        try {
            Destination target=jmsTemplate.getConnectionFactory().createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE).createQueue("Module.Registry");
            for (String key:mapping.keySet()
                 ) {
                ModuleInfo info=new ModuleInfo();
                info.authRange= UserPrivilege.SystemUser.toString();
                info.methodName=key;
                info.serviceName="classroom";
                messageQueueService.sendMessage(target, JSON.toJSONString(info));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

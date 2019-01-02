package icpc.njust.test.controller;

import icpc.njust.test.service.CheckService;
import icpc.njust.test.service.CheckServiceImpl;
import icpc.njust.test.service.ClassService;
import icpc.njust.test.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Map;


public class QueueMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }
}

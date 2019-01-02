package icpc.njust.test.service;

import javax.jms.Destination;

public interface MessageQueueService {
    void sendMessage(Destination destination, final String msg);
    void sendMessage(final String msg);
}

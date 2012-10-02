package no.officenet.example.mom;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;

@Component
public class MyEventPublisher implements EventPublisher {

	@Resource
	JmsTemplate jmsTemplate;

	@Resource
	MyService myService;

	@Resource
	Destination destination;

	@Override
	@Transactional
	public void publishEvent(final Serializable event) {
		myService.createProducerStuff("Before jms.send");
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(event);
			}
		});
		myService.createProducerStuff("After jms.send, sleeping 5s");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myService.createProducerStuff("After jms.send, waking up...");
	}
}

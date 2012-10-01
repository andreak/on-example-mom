package no.officenet.example.mom;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class MyEventConsumerImpl implements MyEventConsumer, MessageListener {

	@Resource
	MyService myService;

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				System.out.println("Processing " + objectMessage.getObject());
				myService.createConsumerStuff("Creating consumer stuff");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not ObjectMessage");
		}
	}
}
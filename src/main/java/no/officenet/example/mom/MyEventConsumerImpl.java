package no.officenet.example.mom;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class MyEventConsumerImpl implements MyEventConsumer, MessageListener {

	@Resource
	MyService myService;

	@Override
	@Transactional
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				System.out.println("Processing " + objectMessage.getObject());
				myService.createConsumerStuff("Creating consumer stuff, sleeping 5s");
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				myService.createConsumerStuff("Creating consumer stuff, waking up");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not ObjectMessage");
		}
		throw new RuntimeException("BOOOOOM");
	}
}
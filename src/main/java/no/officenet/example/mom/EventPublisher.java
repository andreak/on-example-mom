package no.officenet.example.mom;

import java.io.Serializable;

public interface EventPublisher {
	void publishEvent(Serializable event);
}
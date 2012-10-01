<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="no.officenet.example.mom.EventPublisher" %><%

	EventPublisher eventPublisher = WebApplicationContextUtils.getWebApplicationContext(application).getBean(EventPublisher.class);
	eventPublisher.publishEvent("Snirk");
%>
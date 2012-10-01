package no.officenet.example.mom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Service
@Transactional
public class MyServiceImpl implements MyService {

	@Resource
	DataSource dataSource;

	@Override
	public void createProducerStuff(String stuff) {
		new JdbcTemplate(dataSource).update("insert into producer_info(stuff) values(?)", stuff);
	}

	@Override
	public void createConsumerStuff(String stuff) {
		new JdbcTemplate(dataSource).update("insert into consumer_info(stuff) values(?)", stuff);
	}
}

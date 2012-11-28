(Requires PostgreSQL)

1. create a database with "createdb mom"
2. Fire up the application with "mvn tomcat7:run"
3. Point your browser to http://localhost:8080/mom/

Observe the following in /var/log/postgresql/postgresql-9.2-main.log:
```
2012-11-28 09:54:38 CET LOG:  execute S_1: BEGIN
2012-11-28 09:54:38 CET LOG:  execute <unnamed>: insert into producer_info(stuff) values($1)
2012-11-28 09:54:38 CET DETAIL:  parameters: $1 = 'Before jms.send'
2012-11-28 09:54:38 CET LOG:  execute <unnamed>: insert into producer_info(stuff) values($1)
2012-11-28 09:54:38 CET DETAIL:  parameters: $1 = 'After jms.send, sleeping 5s'
2012-11-28 09:54:43 CET LOG:  execute <unnamed>: insert into producer_info(stuff) values($1)
2012-11-28 09:54:43 CET DETAIL:  parameters: $1 = 'After jms.send, waking up...'
2012-11-28 09:54:43 CET LOG:  execute S_2: COMMIT
2012-11-28 09:54:43 CET LOG:  execute S_1: BEGIN
2012-11-28 09:54:43 CET LOG:  execute <unnamed>: insert into consumer_info(stuff) values($1)
2012-11-28 09:54:43 CET DETAIL:  parameters: $1 = 'Creating consumer stuff, sleeping 5s'
2012-11-28 09:54:48 CET LOG:  execute <unnamed>: insert into consumer_info(stuff) values($1)
2012-11-28 09:54:48 CET DETAIL:  parameters: $1 = 'Creating consumer stuff, waking up'
2012-11-28 09:54:48 CET LOG:  execute S_2: COMMIT
```

Why isn't anything written to AMQ-tables?

begin;

drop table if exists activemq_acks ;
drop table if exists activemq_lock ;
drop table if exists activemq_msgs ;
drop table if exists producer_info ;
drop table if exists consumer_info ;

CREATE TABLE activemq_acks (
    container character varying(250) NOT NULL,
    sub_dest character varying(250),
    client_id character varying(250) NOT NULL,
    sub_name character varying(250) NOT NULL,
    selector character varying(250),
    last_acked_id bigint,
    priority bigint DEFAULT 5 NOT NULL,
    xid bytea,
    PRIMARY KEY (container, client_id, sub_name, priority)
);

CREATE TABLE activemq_lock (
    id bigint PRIMARY KEY,
    time bigint,
    broker_name character varying(250)
);

CREATE TABLE activemq_msgs (
    id bigint PRIMARY KEY,
    container character varying(250),
    msgid_prod character varying(250),
    msgid_seq bigint,
    expiration bigint,
    msg bytea,
    priority bigint,
    xid bytea
);

CREATE INDEX activemq_msgs_cidx ON activemq_msgs USING btree (container);
CREATE INDEX activemq_msgs_eidx ON activemq_msgs USING btree (expiration);
CREATE INDEX activemq_msgs_midx ON activemq_msgs USING btree (msgid_prod, msgid_seq);
CREATE INDEX activemq_msgs_pidx ON activemq_msgs USING btree (priority);

INSERT INTO activemq_lock(id) VALUES (1);

CREATE TABLE producer_info(stuff varchar);
CREATE TABLE consumer_info(stuff varchar);

commit;

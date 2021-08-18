DROP TABLE IF EXISTS etl;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS bank;


DROP TABLE IF EXISTS etl_user_group;
DROP TABLE IF EXISTS etl_group;
DROP TABLE IF EXISTS etl_user;
DROP TABLE IF EXISTS etl_to_group;
DROP TABLE IF EXISTS shva_log_approve;
DROP TABLE IF EXISTS shva_error_record;

CREATE TABLE bank (
  id INTEGER   PRIMARY KEY ,
  name VARCHAR(40) NOT NULL
 );


 CREATE TABLE etl_user (
  id INTEGER   PRIMARY KEY auto_increment,
  username VARCHAR(40) NOT NULL,
  status  VARCHAR(10) NOT NULL
 );


 CREATE TABLE etl_group (
  id INTEGER   PRIMARY KEY auto_increment,
  groupname VARCHAR(40) NOT NULL
 );

-- CREATE TABLE etl_user_group (
--  id INTEGER   PRIMARY KEY auto_increment,
--  user_id INTEGER,
--  group_id INTEGER,
--  FOREIGN KEY (user_id) REFERENCES etl_user(id),  
--  FOREIGN KEY (group_id) REFERENCES etl_group(id)  
-- );
 
 
 
 
CREATE TABLE etl (
  id INTEGER   PRIMARY KEY auto_increment,
  bank_id INTEGER ,
  name VARCHAR(40) NOT NULL,
  description VARCHAR(40) ,
  folder VARCHAR(40) NOT NULL,
  schedule_crone VARCHAR(40) NOT NULL,
  FOREIGN KEY (bank_id) REFERENCES bank(id)  
);


--CREATE TABLE etl_to_group (
--  id INTEGER   PRIMARY KEY auto_increment,
--  etl_id INTEGER,
--  group_id INTEGER,
--  FOREIGN KEY (group_id) REFERENCES etl_group(id),  
--  FOREIGN KEY (etl_id) REFERENCES etl(id)
--);
  



CREATE TABLE transaction (
  id INTEGER   PRIMARY KEY auto_increment,
  bank_id INTEGER ,
  name VARCHAR(40) ,
  description VARCHAR(40) ,
  amount DOUBLE NOT NULL,
  created_date TIMESTAMP,
  FOREIGN KEY (bank_id) REFERENCES bank(id)
);

 

insert into   bank(id,name) value ( 10,'Leumi');
insert into   bank(id,name) value ( 12,'Poalim');

insert into   etl(id,bank_id,name,description,folder,schedule_crone) values( null,12,'Poalim_etl','Poalim',"C:\\videoturnstile\\data","20 * * * * ?");
insert into   etl(id,bank_id,name,description,folder,schedule_crone) values( null,10,'Leumi_etl','Leumi',"C:\\videoturnstile\\data","50 * * * * ?");
  
create table shva_log_approve (
		  id integer   primary key ,
		  rec_type integer not null,
		  format_code varchar(1),
		  ixx_date varchar(6),
		  ixx_time varchar(6),
		  processor_number varchar(6),
		  finish_error varchar(4),
		  atm_no varchar(4),
		  atm_seq integer,
		  tran_type integer,
		  amount float,
		  bank integer,
		  branch integer,
		  account integer,
		  card integer,
		  card_type integer,
		  response_code integer,
		  rej_reason varchar(2),
		  retain_card varchar(1),
		  cardholder_processor integer,
		  time_of_transaction integer,
		  original_tp integer,
		  reject_letter varchar(1),
		  start_of_msr varchar(38),
		  terminal_date varchar(4),
		  tp varchar(2),
		  t2_from_chip varchar(1),
		  card_reader varchar(1),
		  sc_status varchar(1),
		  t2_source varchar(1),
		  i31_field_55_len varchar(3),
		  i35_field_55_len varchar(3),
		  f39_response_code varchar(2),
		  pin_verification_code varchar(1),
		  pin_fail_code varchar(2),
		  error_code varchar(2),
		  app_reject_reason varchar(10),
		  field_num integer,
		  tp_bank_num integer,
		  tp_processor_num integer,
		  cp_bank_num integer,
		  cp_processor_num integer
		  );


		  
create table shva_error_record (
		   id bigint not null auto_increment,
		   tp integer ,
		   atm_number integer,
		   atm_seq integer,
		   transac_date integer,
		   time integer,
		   rec_type varchar(1),
		   error_stage varchar(1),
		   error_sequence integer,
		   error_code integer,
		   error_field varchar(20),
		   rejected_record varchar(250),
		   primary key (`id`)
	  
		) ;
  
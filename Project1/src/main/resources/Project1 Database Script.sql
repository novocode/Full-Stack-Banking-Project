create table customers (
	customer_id serial primary key,
	customer_username varchar(30) unique not null,
	customer_password varchar(30) not null,
	customer_first_name varchar(30) not null,
	customer_last_name varchar(30) not null,
	customer_ssn int unique null
);

create table accounts (
	account_number serial primary key,
	account_type varchar(8) not null, --set to 8 for checking
	account_balance numeric(15,2) not null,
	c_id int references customers(customer_id)
);

create table account_applications(
	application_id serial primary key,
	account_type varchar(8) not null,
	account_balance numeric(15,2) not null,
	c_id int references customers(customer_id)
);

create table customer_transactions(
	transaction_id serial primary key,
	a_id int references accounts(account_number),
	account_type varchar(8),
	transaction_type varchar(10) not null,
	transaction_amount numeric(15,2) not null,
	transfer_into_id int null	
);

create table employee (
	employee_id serial primary key,
	employee_username varchar(30) unique not null,
	employee_password varchar(30) not null,
	employee_first_name varchar(30) not null,
	employee_last_name varchar(30) not null
);

select * from customers;
select * from accounts;
select * from account_applications;
select * from customer_transactions;
select * from employee;

delete from customers;
delete from accounts;
delete from account_applications;
delete from customer_transactions;
delete from employee;

drop table customers;
drop table accounts;
drop table account_applications;
drop table customer_transactions;
drop table employee;

INSERT INTO customers (customer_username, customer_password, customer_first_name, customer_last_name, customer_ssn) 
				VALUES ('foo', 'blah', 'jim', 'tom', 123);
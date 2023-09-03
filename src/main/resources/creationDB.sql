create table Bank (
    bank_id int primary key generated by default as identity,
    bank_name varchar(50) not null unique
);

create table Bank_Account (
    account_id int primary key generated by default as identity,
    account_name varchar(50) not null unique,
    account_user_id int not null references Bank_User(user_id),
    account_bank_id int not null references Bank(bank_id),
    account_date_of_open date not null,
    account_value int not null
);

create table Bank_User (
    user_id int primary key generated by default as identity,
    user_lastname varchar(50) not null,
    user_firstname varchar(50) not null,
    user_middlename varchar(50) not null
);

create table Bank_Transaction (
    transaction_id int primary key generated by default as identity,
    transaction_type varchar(50) not null,
    transaction_bank_sender_id int not null references Bank(bank_id),
    transaction_bank_recipient_id int not null references Bank(bank_id),
    transaction_account_sender_id int not null references Bank_Account(account_id),
    transaction_account_recipient_id int not null references Bank_Account(account_id),
    transaction_date date not null,
    transaction_value int not null
);

create table Bank_Account_Receipt (
    receipt_id int primary key generated by default as identity,
    receipt_type varchar(50) not null,
    receipt_date date not null,
    receipt_bank_id int references Bank(bank_id) not null,
    receipt_account_id int references Bank_Account(account_id) not null,
    receipt_value int not null
);
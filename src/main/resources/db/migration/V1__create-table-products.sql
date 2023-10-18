create table products(

    id bigint not null auto_increment,
    bar_code varchar(50) not null,
    description varchar(100) not null,
    primary key(id)
);
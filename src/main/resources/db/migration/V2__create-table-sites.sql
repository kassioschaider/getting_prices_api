create table sites(

    id bigint not null auto_increment,
    url varchar(50) not null,
    type varchar(50),
    primary key(id)
);
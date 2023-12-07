create table seller_tags(

    id bigint not null auto_increment,
    site_id bigint not null,
    tag varchar(50) not null unique,
    description varchar(50),
    created_at datetime not null,
    active boolean not null default 1,
    primary key(id),
    constraint fk_seller_tags_site_id foreign key(site_id) references sites(id)
);
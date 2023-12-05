create table scraping_data_configs(

    id bigint not null auto_increment,
    site_id bigint not null,
    extract_price_pattern varchar(50) not null,
    type varchar(50) not null,
    unique_price_class varchar(50),
    key_to_attribute_element_to_get_price varchar(50),
    attribute_value_prefix_to_get_price varchar(50),
    key_to_attribute_element_to_get_seller_tag_name varchar(50),
    attribute_value_prefix_to_get_seller_tag_name varchar(50),
    created_at timestamp,
    active boolean not null default 1,
    primary key(id),
    foreign key (site_id) references sites(id)
);
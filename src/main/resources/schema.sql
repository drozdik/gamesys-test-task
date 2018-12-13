create table rss_item
(
   id varchar(255) primary key,
   title varchar(255) not null,
   description varchar(255) not null,
   pub_date TIMESTAMP WITH TIME ZONE,
   primary key(id)
);
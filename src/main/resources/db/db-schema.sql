create table sample
(
    id            bigint(20) auto_increment,
    title varchar null,
    primary key (id)
);

create table child
(
    id            bigint(20) auto_increment,
    sample_id bigint(20) not null,
    title varchar null,
    primary key (id)
);
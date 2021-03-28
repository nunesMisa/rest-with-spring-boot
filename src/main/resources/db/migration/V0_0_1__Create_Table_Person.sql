create table PERSON (
    ID bigserial not null,
    FIRST_NAME varchar(50) not null,
    LAST_NAME varchar(50) not null,
    ADDRESS varchar(100),
    GENDER varchar(50),
    primary key (id)
)

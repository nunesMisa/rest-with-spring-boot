CREATE TABLE IF NOT EXISTS permission (
    id bigserial not null,
    description varchar(255) DEFAULT NULL,
    primary key (id)
)
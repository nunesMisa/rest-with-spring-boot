CREATE TABLE IF NOT EXISTS users (
  id bigserial not null,
  user_name varchar(255) DEFAULT NULL,
  full_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  account_non_expired BOOLEAN DEFAULT NULL,
  account_non_locked BOOLEAN DEFAULT NULL,
  credentials_non_expired BOOLEAN DEFAULT NULL,
  enabled BOOLEAN DEFAULT NULL,
  UNIQUE(user_name),
    primary key (id)
)
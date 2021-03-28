CREATE TABLE books (
  id bigserial not null,
  author text,
  launch_date timestamp NOT NULL,
  price decimal(65,2) NOT NULL,
  title text,  
   primary key (id)  
);

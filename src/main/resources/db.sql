CREATE SCHEMA IF NOT EXISTS `storage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
Use `storage`;

create table IF NOT EXISTS task
(
  id          bigint        not null auto_increment,
  content     varchar(2048) not null,
  description varchar(2048) not null,
  primary key (id)
) engine = MyISAM;

create table IF NOT EXISTS user
(
  id       bigint       not null auto_increment,
  username varchar(255) not null,
  password varchar(255) not null,
  role     varchar(255) not null,
  primary key (id)
) engine = MyISAM;
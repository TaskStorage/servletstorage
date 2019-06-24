CREATE SCHEMA IF NOT EXISTS `storage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
Use `storage`;

create table tasks
(
  id          bigint        not null auto_increment,
  content     varchar(2048) not null,
  description varchar(2048) not null,
  primary key (id)
) engine = MyISAM;
-- 管理用ロールの追加
drop role if exists musubi_admin;
create role musubi_admin with password 'Hogepiyo';
alter role musubi_admin with superuser createrole login;
grant all on all tables in schema public to musubi_admin;

-- ロール指定
set role musubi_admin;

drop table if exists users;
create table users (
	id varchar(50) not null,
	name varchar(50) null,
	email varchar(330) null,
	password varchar(200) null,
	role int4 null,
	profile text null,
	created_at timestamp with time zone not null default current_timestamp,
	updated_at timestamp with time zone not null default current_timestamp,
	deleted_at timestamp with time zone null,
	constraint user_pkey primary key (id)
);

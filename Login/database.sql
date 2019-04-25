create database login;
use login

create table users(
    username char(32) primary key,
    passwordHash char(32) not null
);

delimiter //

create TRIGGER hashPassword BEFORE INSERT ON users FOR EACH ROW
BEGIN
    SET NEW.passwordHash=md5(NEW.passwordHash);
END; //

create TRIGGER hashPasswordOnUpdate BEFORE UPDATE ON users FOR EACH ROW
BEGIN
    SET NEW.passwordHash=md5(NEW.passwordHash);
END;//

delimiter ;
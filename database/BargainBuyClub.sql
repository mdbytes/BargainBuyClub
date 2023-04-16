-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bargain_buy_club
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `bargain_buy_club`;
CREATE SCHEMA IF NOT EXISTS `bargain_buy_club` DEFAULT CHARACTER SET utf8 ;
USE `bargain_buy_club` ;

-- -----------------------------------------------------
-- Table `bargain_buy_club`.`system_updates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`system_updates` ;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`system_updates` (
  `update_id` INT NOT NULL AUTO_INCREMENT,
  `update_date` DATE,
  `update_description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`update_id`))
ENGINE = InnoDB
COMMENT = '	';

-- -----------------------------------------------------
-- Table `bargain_buy_club`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`users` ;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(100) NOT NULL,
  `password` VARCHAR(1024) NOT NULL,
  is_admin VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `bargain_buy_club`.`stores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`stores` ;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`stores` (
  `store_id` INT NOT NULL AUTO_INCREMENT,
  `store_name` VARCHAR(45) NOT NULL,
  `store_url` VARCHAR(256) NOT NULL,
  `price_query` VARCHAR(256) NOT NULL,
  `product_name_query` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`store_id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bargain_buy_club`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`products` ;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `store_id` INT NOT NULL,
  `product_url` VARCHAR(1024) NOT NULL,
  `product_name` VARCHAR(256) NOT NULL,
  `recent_price` DOUBLE,
  `last_updated` DATE,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `Store`
    FOREIGN KEY (`store_id`)
    REFERENCES `bargain_buy_club`.`stores` (`store_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bargain_buy_club`.`alerts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`alerts` ;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`alerts` (
  `alert_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `alert_price` DOUBLE NOT NULL,
  PRIMARY KEY (`alert_id`),
  CONSTRAINT `Product`
    FOREIGN KEY (`product_id`)
    REFERENCES `bargain_buy_club`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `User`
    FOREIGN KEY (`user_id`)
    REFERENCES `bargain_buy_club`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Insert seed data into tables
-- -----------------------------------------------------

-- System updates

insert into system_updates (update_date, update_description) VALUES ('2023-04-01','Database initiation');

-- Users

insert into users (first_name, last_name, email_address, password, is_admin) values ('Martin', 'Dwyer', 'martin@mdbytes.com', '2d41206bee3804f8d36e82d892b58e37710edafdd3d281c7ae20344f56897f8d', 'true');

-- stores
insert into stores (store_name, store_url, price_query, product_name_query) values ('WebScraper API', 'https://webscraper.io', 'h4.pull-right.price', 'p.description');

-- products

insert into products (store_id, product_url,  product_name, recent_price,last_updated) values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/566','Acer Aspire ES1-572 Black, 15.6" HD, Core i5-7200U, 4GB, 500GB, Linux',0.0,'2023-04-13');
insert into products (store_id, product_url, product_name,  recent_price,last_updated) values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/508','IPS, Dual-Core 1.2GHz, 8GB, Android 4.3',0.0,'2023-04-13');
insert into products (store_id, product_url, product_name,  recent_price,last_updated) values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/579','Lenovo ThinkPad L460, 14" FHD IPS, Core i7-6600U, 8GB, 256GB SSD, Windows 10 Pro',0.0,'2023-04-13');
insert into products (store_id, product_url, product_name,  recent_price,last_updated) values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/544','Asus ROG Strix SCAR Edition GL503VM-ED115T, 15.6" FHD 120Hz, Core i7-7700HQ, 16GB, 256GB',0.0,'2023-04-13');
insert into products (store_id, product_url, product_name, recent_price,last_updated) values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/545','Asus VivoBook X441NA-GA190 Chocolate Black, 14", Celeron N3450, 4GB, 128GB SSD, Endless OS, ENG kbd',0.0,'2023-04-13');

-- alerts

insert into alerts (product_id, user_id, alert_price) values (1, 1, 300.0);
insert into alerts (product_id, user_id, alert_price) values (2, 1, 100.0);
insert into alerts (product_id, user_id, alert_price) values (3, 1, 800.0);
insert into alerts (product_id, user_id, alert_price) values (4, 1, 1400.0);
insert into alerts (product_id, user_id, alert_price) values (5, 1, 200.0);

-- ----------------------------------------------------------------------
-- Stored Procedures
-- ---------------------------------------------------------------------

-- most recent update 

DROP PROCEDURE IF EXISTS get_last_update_date;

DELIMITER $$
CREATE PROCEDURE get_last_update_date()
BEGIN 
	SELECT MAX(update_date) FROM system_updates;

END $$
DELIMITER ;


-- users
DROP PROCEDURE IF EXISTS get_users;

DELIMITER $$
CREATE PROCEDURE get_users()
BEGIN

	SELECT * FROM users;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS add_user;
DELIMITER $$
CREATE PROCEDURE add_user
(
	`first_name_param` VARCHAR(45),
    `last_name_param` VARCHAR(45),
    `email_address_param` VARCHAR(100),
    `password_param` VARCHAR(255),
    `is_admin_param` VARCHAR(45)
)
BEGIN
    INSERT INTO users
		(`first_name`, `last_name`, `email_address`, `password`, `is_admin`)
	VALUES
		(`first_name_param`, `last_name_param`, `email_address_param`, `password_param`, `is_admin_param`);

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_user_by_id;
DELIMITER $$
CREATE PROCEDURE get_user_by_id
(
	user_id_param INT
)
BEGIN


    SELECT * FROM users WHERE `user_id` = `user_id_param`;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_user_by_email;
DELIMITER $$
CREATE PROCEDURE get_user_by_email
(
	email_param VARCHAR(100)
)
BEGIN

    SELECT * FROM users WHERE email_address = email_param;

END $$

DELIMITER ;

DROP PROCEDURE IF EXISTS update_user;
DELIMITER $$
CREATE PROCEDURE update_user
(
	`user_id_param` INT,
	`first_name_param` VARCHAR(45),
    `last_name_param` VARCHAR(45),
    `email_address_param` VARCHAR(100),
    `password_param` VARCHAR(255),
    `is_admin_param` VARCHAR(45)
)
BEGIN
    UPDATE users
    SET `first_name` = `first_name_param`,
		`last_name` = `last_name_param`,
        `email_address` = `email_address_param`,
        `password` = `password_param`,
        `is_admin` = `is_admin_param`
	WHERE `user_id` = `user_id_param`;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS update_user_email;
DELIMITER $$
CREATE PROCEDURE update_user_email
(
	`user_id_param` INT,
	`email_address_param` VARCHAR(100)
)
BEGIN
    UPDATE users
    SET `email_address` = `email_address_param`
	WHERE `user_id` = `user_id_param`;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS update_user_password;
DELIMITER $$
CREATE PROCEDURE update_user_password
(
	`user_id_param` INT,
    `password_param` VARCHAR(255)
)
BEGIN
    UPDATE users
    SET `password` = `password_param`
	WHERE `user_id` = `user_id_param`;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS make_user_admin;
DELIMITER $$
CREATE PROCEDURE make_user_admin
(
	`user_id_param` INT
)
BEGIN
    UPDATE users
    SET `is_admin` = 'true'
	WHERE `user_id` = `user_id_param`;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS delete_user_by_id;
DELIMITER $$
CREATE PROCEDURE delete_user_by_id
(
	`user_id_param` INT
)
BEGIN
    DELETE FROM users WHERE `user_id` = `user_id_param`;
END $$
DELIMITER ;




DROP PROCEDURE IF EXISTS get_user_alerts;
DELIMITER $$
CREATE PROCEDURE get_user_alerts
(
	`user_id_param` INT
)
BEGIN
    SELECT * FROM alerts WHERE `user_id` = `user_id_param`;
END $$
DELIMITER ;

-- ----------------------------------------------------------------
-- stored procedures for stores
-- ----------------------------------------------------------------

DROP PROCEDURE IF EXISTS get_stores
DELIMITER $$
CREATE PROCEDURE get_stores()
BEGIN
	SELECT * FROM stores;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS add_store;
DELIMITER $$
CREATE PROCEDURE add_store
(
	store_name_param VARCHAR(45),
    store_url_param VARCHAR(256),
    price_query_param VARCHAR(256),
    product_name_query_param VARCHAR(256)
)
BEGIN
    INSERT INTO stores
		(store_name, store_url, price_query, product_name_query)
	VALUES
		(store_name_param, store_url_param, price_query_param, product_name_query_param);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_store_by_id;
DELIMITER $$
CREATE PROCEDURE get_store_by_id
(
	store_id_param INT
)
BEGIN
    SELECT * FROM stores WHERE store_id = store_id_param;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_store_by_name;
DELIMITER $$
CREATE PROCEDURE get_store_by_name
(
	store_name_param VARCHAR(45)
)
BEGIN
    SELECT * FROM stores WHERE store_name = store_name_param;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS update_store;
DELIMITER $$
CREATE PROCEDURE update_store
(
	store_id_param INT,
	store_name_param VARCHAR(45),
    store_url_param VARCHAR(256),
    price_query_param VARCHAR(256),
    product_name_query_param VARCHAR(256)
)
BEGIN
    UPDATE stores
    SET store_name = store_name_param,
		store_url = store_url_param,
        price_query = price_query_param,
        product_name_query = product_name_query_param
	WHERE store_id = store_id_param;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS delete_store_by_id;
DELIMITER $$
CREATE PROCEDURE delete_store_by_id
(
	store_id_param INT
)
BEGIN

    DELETE FROM stores WHERE store_id = store_id_param;

END $$
DELIMITER ;

users-- ----------------------------------------------------------------
-- stored procedures for products
-- ----------------------------------------------------------------

DROP PROCEDURE IF EXISTS get_products

DELIMITER $$
CREATE PROCEDURE get_products()
BEGIN

	SELECT * FROM products;

END $$
DELIMITER ;

SELECT * FROM products;



DROP PROCEDURE IF EXISTS add_product;

DELIMITER $$

CREATE PROCEDURE add_product
(
	`store_id_param` INT,
	`product_url_param` VARCHAR(1024),
    `product_name_param` VARCHAR(256),
    `recent_price_param` DOUBLE,
    `last_updated_param` DATE
)
BEGIN

    INSERT INTO products
		(`store_id`, `product_url`,`product_name`,`recent_price`,`last_updated`)
	VALUES
		(`store_id_param`, `product_url_param`,`product_name_param`,`recent_price_param`,`last_updated_param`);

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_product_by_id;
DELIMITER $$
CREATE PROCEDURE get_product_by_id
(
	product_id_param INT
)
BEGIN
    SELECT * FROM products WHERE product_id = product_id_param;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS get_product_by_url;
DELIMITER $$
CREATE PROCEDURE get_product_by_url
(
	product_url_param VARCHAR(1024)
)
BEGIN
    SELECT * FROM products WHERE product_url = product_url_param;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS update_product;
DELIMITER $$
CREATE PROCEDURE update_product
(
	`product_id_param` INT,
	`store_id_param` INT,
	`product_url_param` VARCHAR(1024),
    `product_name_param` VARCHAR(256),
    `recent_price_param` DOUBLE,
    `last_updated_param` DATE
)
BEGIN

    UPDATE products

    SET store_id = store_id_param,
		product_url = product_url_param,
        product_name = product_name_param,
        recent_price = recent_price_param,
        last_updated = last_updated_param
    
	WHERE product_id = product_id_param;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS delete_product_by_id;
DELIMITER $$
CREATE PROCEDURE delete_product_by_id
(
	product_id_param INT
)
BEGIN
    DELETE FROM products WHERE product_id = product_id_param;
END $$
DELIMITER ;


-- ----------------------------------------------------------------
-- stored procedures for alerts
-- ----------------------------------------------------------------

DROP PROCEDURE IF EXISTS get_alerts
DELIMITER $$
CREATE PROCEDURE get_alerts()
BEGIN

	SELECT * FROM alerts;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS add_alert
DELIMITER $$
CREATE PROCEDURE add_alert
(
	`product_id_param` INT,
	`user_id_param` INT,
	`alert_price_param` DOUBLE
)
BEGIN

    INSERT INTO alerts
		(`product_id`, `user_id`, `alert_price`)
	VALUES
		(`product_id_param`, `user_id_param`, `alert_price_param`);

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_alert_by_id;
DELIMITER $$
CREATE PROCEDURE get_alert_by_id
(
	alert_id_param INT
)
BEGIN

    SELECT * FROM alerts WHERE alert_id = alert_id_param;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS get_alert_by_attributes;
DELIMITER $$
CREATE PROCEDURE get_alert_by_attributes
(
	product_id_param INT,
    user_id_param INT,
    alert_price_param DOUBLE
)
BEGIN

    SELECT * FROM alerts 
    WHERE product_id = product_id_param AND user_id = user_id_param AND alert_price = alert_price_param;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS update_alert;
DELIMITER $$
CREATE PROCEDURE update_alert
(
	`alert_id_param` INT,
	`product_id_param` INT,
	`user_id_param` INT,
	`alert_price_param` DOUBLE
)
BEGIN

    UPDATE alerts

    SET product_id = product_id_param,
		user_id = user_id_param,
        alert_price = alert_price_param

	WHERE alert_id = alert_id_param;

END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS update_alert_price;
DELIMITER $$
CREATE PROCEDURE update_alert_price
(
	`alert_id_param` INT,
	`alert_price_param` DOUBLE
)
BEGIN

    UPDATE alerts

    SET alert_price = alert_price_param

	WHERE alert_id = alert_id_param;

END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS delete_alert_by_id;
DELIMITER $$
CREATE PROCEDURE delete_alert_by_id
(
	alert_id_param INT
)
BEGIN

    DELETE FROM alerts WHERE alert_id = alert_id_param;

END $$
DELIMITER ;

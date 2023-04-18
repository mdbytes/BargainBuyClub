-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bargain_buy_club
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `bargain_buy_club`;
CREATE SCHEMA IF NOT EXISTS `bargain_buy_club` DEFAULT CHARACTER SET utf8;
USE `bargain_buy_club`;

-- -----------------------------------------------------
-- Table `bargain_buy_club`.`system_updates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`system_updates`;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`system_updates`
(
    `update_id`          INT          NOT NULL AUTO_INCREMENT,
    `update_date`        DATE,
    `update_description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`update_id`)
)
    ENGINE = InnoDB
    COMMENT = '	';

-- -----------------------------------------------------
-- Table `bargain_buy_club`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`users`;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`users`
(
    `user_id`       INT           NOT NULL AUTO_INCREMENT,
    `first_name`    VARCHAR(45)   NOT NULL,
    `last_name`     VARCHAR(45)   NOT NULL,
    `email_address` VARCHAR(100)  NOT NULL,
    `password`      VARCHAR(1024) NOT NULL,
    is_admin        VARCHAR(45)   NOT NULL,
    PRIMARY KEY (`user_id`)
)
    ENGINE = InnoDB
    COMMENT = '	';


-- -----------------------------------------------------
-- Table `bargain_buy_club`.`stores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`stores`;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`stores`
(
    `store_id`           INT          NOT NULL AUTO_INCREMENT,
    `store_name`         VARCHAR(45)  NOT NULL,
    `store_url`          VARCHAR(256) NOT NULL,
    `price_query`        VARCHAR(256) NOT NULL,
    `product_name_query` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`store_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bargain_buy_club`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`products`;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`products`
(
    `product_id`   INT           NOT NULL AUTO_INCREMENT,
    `store_id`     INT           NOT NULL,
    `product_url`  VARCHAR(1024) NOT NULL,
    `product_name` VARCHAR(256)  NOT NULL,
    `recent_price` DOUBLE,
    `last_updated` DATE,
    PRIMARY KEY (`product_id`),
    CONSTRAINT `Store`
        FOREIGN KEY (`store_id`)
            REFERENCES `bargain_buy_club`.`stores` (`store_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `bargain_buy_club`.`alerts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargain_buy_club`.`alerts`;

CREATE TABLE IF NOT EXISTS `bargain_buy_club`.`alerts`
(
    `alert_id`    INT    NOT NULL AUTO_INCREMENT,
    `product_id`  INT    NOT NULL,
    `user_id`     INT    NOT NULL,
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
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

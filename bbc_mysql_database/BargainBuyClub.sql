-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bargainbuyclub
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bargainbuyclub
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bargainbuyclub` DEFAULT CHARACTER SET utf8 ;
USE `bargainbuyclub` ;

-- -----------------------------------------------------
-- Table `bargainbuyclub`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargainbuyclub`.`users` ;

CREATE TABLE IF NOT EXISTS `bargainbuyclub`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(100) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  is_admin VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `bargainbuyclub`.`stores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargainbuyclub`.`stores` ;

CREATE TABLE IF NOT EXISTS `bargainbuyclub`.`stores` (
  `store_id` INT NOT NULL AUTO_INCREMENT,
  `store_name` VARCHAR(45) NOT NULL,
  `store_url` VARCHAR(256) NOT NULL,
  `price_query` VARCHAR(256) NOT NULL,
  `product_name_query` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`store_id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bargainbuyclub`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargainbuyclub`.`products` ;

CREATE TABLE IF NOT EXISTS `bargainbuyclub`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `store_id` INT NOT NULL,
  `product_url` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `Store`
    FOREIGN KEY (`store_id`)
    REFERENCES `bargainbuyclub`.`stores` (`store_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bargainbuyclub`.`alerts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bargainbuyclub`.`alerts` ;

CREATE TABLE IF NOT EXISTS `bargainbuyclub`.`alerts` (
  `alert_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `alert_price` DOUBLE NOT NULL,
  PRIMARY KEY (`alert_id`),
  CONSTRAINT `Product`
    FOREIGN KEY (`product_id`)
    REFERENCES `bargainbuyclub`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `User`
    FOREIGN KEY (`user_id`)
    REFERENCES `bargainbuyclub`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

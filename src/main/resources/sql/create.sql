-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema airbnb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `airbnb` ;

-- -----------------------------------------------------
-- Schema airbnb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airbnb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `airbnb` ;

-- -----------------------------------------------------
-- Table `airbnb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`user` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `type` ENUM('client', 'host') NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `surname` VARCHAR(45) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`city` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`city` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `url` VARCHAR(200) NOT NULL COMMENT '',
  `img_name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`apartment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`apartment` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`apartment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `apartment_description` VARCHAR(100) NOT NULL COMMENT '',
  `type` ENUM('place', 'room', 'apartment') NOT NULL COMMENT '',
  `number_of_guests` INT NOT NULL COMMENT '',
  `price` INT NOT NULL COMMENT '',
  `user_id` INT UNSIGNED NOT NULL COMMENT '',
  `city_id` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_apartment_user1_idx` (`user_id` ASC)  COMMENT '',
  INDEX `fk_apartment_city1_idx` (`city_id` ASC)  COMMENT '',
  CONSTRAINT `fk_apartment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `airbnb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartment_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `airbnb`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`reservation` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`reservation` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `start` BIGINT(20) NOT NULL COMMENT '',
  `end` BIGINT(20) NOT NULL COMMENT '',
  `apartment_id` INT UNSIGNED NOT NULL COMMENT '',
  `user_id` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_reservation_apartment1_idx` (`apartment_id` ASC)  COMMENT '',
  INDEX `fk_reservation_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_reservation_apartment1`
    FOREIGN KEY (`apartment_id`)
    REFERENCES `airbnb`.`apartment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `airbnb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`subscriber`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`subscriber` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`subscriber` (
  `user_id` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`user_id`)  COMMENT '',
  CONSTRAINT `fk_subscriber_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `airbnb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

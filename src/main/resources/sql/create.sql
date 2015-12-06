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
CREATE SCHEMA IF NOT EXISTS `airbnb` DEFAULT CHARACTER SET utf8 ;
USE `airbnb` ;

-- -----------------------------------------------------
-- Table `airbnb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`user` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` ENUM('client', 'host') NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`city` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`city` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  `img_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`apartment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `airbnb`.`apartment` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`apartment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `apartment_description` VARCHAR(100) NOT NULL,
  `type` ENUM('place', 'room', 'apartment') NOT NULL,
  `number_of_guests` INT NOT NULL,
  `price` INT NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `city_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_apartment_user1_idx` (`user_id` ASC),
  INDEX `fk_apartment_city1_idx` (`city_id` ASC),
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
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `apartment_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_apartment1_idx` (`apartment_id` ASC),
  INDEX `fk_reservation_user1_idx` (`user_id` ASC),
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
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_subscriber_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `airbnb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

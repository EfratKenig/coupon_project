CREATE SCHEMA `coupon_management` ;

CREATE TABLE `coupon_management`.`companies` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  `EMAIL` VARCHAR(45) NULL,
  `PASSWORD` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`));
  
CREATE TABLE `coupon_management`.`customers` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(45) NULL,
  `LAST_NAME` VARCHAR(45) NULL,
  `EMAIL` VARCHAR(45) NULL,
  `PASSWORD` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `coupon_management`.`categories` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `coupon_management`.`coupons` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT NULL,
  `CATEGORY_ID` INT NULL,
  `TITLE` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  `START_DATE` DATE NULL,
  `END_DATE` DATE NULL,
  `AMOUNT` INT NULL,
  `PRICE` DOUBLE NULL,
  `IMAGE` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_idx` (`COMPANY_ID` ASC) VISIBLE,
  INDEX `FK_CATEGORIES_idx` (`CATEGORY_ID` ASC) VISIBLE,
  CONSTRAINT `FK_COMAPIES`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `coupon_management`.`companies` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CATEGORIES`
    FOREIGN KEY (`CATEGORY_ID`)
    REFERENCES `coupon_management`.`categories` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `coupon_management`.`customers_vs_coupons` (
  `CUSTOMER_ID` INT NOT NULL,
  `COUPON_ID` INT NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`, `COUPON_ID`),
  INDEX `FK_COUPONS_idx` (`COUPON_ID` ASC) VISIBLE,
  CONSTRAINT `FK_CUSTOMERS`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `coupon_management`.`customers` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COUPONS`
    FOREIGN KEY (`COUPON_ID`)
    REFERENCES `coupon_management`.`coupons` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `addresses`
(
    `address_id`              int         NOT NULL AUTO_INCREMENT,
    `street`          varchar(32) NOT NULL,
    `building_number` int         NOT NULL,
    `flat_number`     int         NOT NULL,
    `person_id`       int         NOT NULL,
    PRIMARY KEY (`address_id`),
    CONSTRAINT `addresses_ibfk_1`
        FOREIGN KEY (`address_id`)
            REFERENCES `persons`(`person_id`)
            ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT=1
  DEFAULT CHARSET = utf8mb4;




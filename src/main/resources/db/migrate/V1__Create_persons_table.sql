CREATE TABLE `persons`
(
    `person_id`         int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(32) NOT NULL,
    `surname`    varchar(32) NOT NULL,
    `patronymic` varchar(32) NOT NULL,
    PRIMARY KEY (`person_id`)

) ENGINE = InnoDB
  AUTO_INCREMENT=1
  DEFAULT CHARSET = utf8mb4;
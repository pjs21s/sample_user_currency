DROP DATABASE IF EXISTS sparta;
CREATE DATABASE sparta DEFAULT CHARSET = utf8 COLLATE =utf8_bin;

USE sparta;

DROP TABLE IF EXISTS currency;
CREATE TABLE `currency` (
            `exchange_rate` decimal(38,2) DEFAULT NULL,
            `created_at` datetime(6) DEFAULT NULL,
            `id` bigint NOT NULL AUTO_INCREMENT,
            `updated_at` datetime(6) DEFAULT NULL,
            `currency_name` varchar(255) DEFAULT NULL,
            `symbol` varchar(255) DEFAULT NULL,
            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS exchange;
CREATE TABLE `exchange` (
                            `amount_in_krw` decimal(38,2) DEFAULT NULL,
                            `created_at` datetime(6) DEFAULT NULL,
                            `currency_id` bigint DEFAULT NULL,
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `updated_at` datetime(6) DEFAULT NULL,
                            `user_id` bigint DEFAULT NULL,
                            `amount_after_exchange` varchar(255) DEFAULT NULL,
                            `status` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKkb7wq7crkyi506xlbae6cxjao` (`currency_id`),
                            KEY `FK3eb1wcn8bb9v5md8v8p3c2fe` (`user_id`),
                            CONSTRAINT `FK3eb1wcn8bb9v5md8v8p3c2fe` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                            CONSTRAINT `FKkb7wq7crkyi506xlbae6cxjao` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `created_at` datetime(6) DEFAULT NULL,
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `updated_at` datetime(6) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
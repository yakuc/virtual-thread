CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `login_id` VARCHAR(128) NOT NULL,
  `password` VARCHAR(64) NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_id_UNIQUE` (`login_id` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `articles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(255) NOT NULL,
  `body` TEXT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` INT NOT NULL,
  `update_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_articles_users_idx` (`create_user_id` ASC) VISIBLE,
  INDEX `fk_articles_users1_idx` (`update_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_articles_users`
    FOREIGN KEY (`create_user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articles_users1`
    FOREIGN KEY (`update_user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `body` TEXT NOT NULL,
  `article_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` INT NOT NULL,
  `update_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_articles1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_comments_users1_idx` (`create_user_id` ASC) VISIBLE,
  INDEX `fk_comments_users2_idx` (`update_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comments_articles1`
    FOREIGN KEY (`article_id`)
    REFERENCES `articles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`create_user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users2`
    FOREIGN KEY (`update_user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `caches` (
  `id` VARBINARY(16) NOT NULL,
  `key` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `value` BLOB NOT NULL,
  `version` SMALLINT NOT NULL DEFAULT 1,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
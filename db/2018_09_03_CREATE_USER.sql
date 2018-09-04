CREATE TABLE `exam_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `pass_word` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `reg_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `exam_question_bank` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255) NULL DEFAULT NULL COMMENT '题目',
	`answer` VARCHAR(255) NULL DEFAULT NULL COMMENT '答案',
	`true_answer` VARCHAR(255) NULL DEFAULT NULL COMMENT '正确答案',
	`jie_xi` VARCHAR(255) NULL DEFAULT NULL COMMENT '解释',
	`note` VARCHAR(255) NULL DEFAULT NULL COMMENT '说明',
	`question_bank_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '题库ID',
	PRIMARY KEY (`id`)
)
COMMENT='题库'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `exam_roll` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`roll_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '卷',
	`question_bank_id` VARCHAR(32) NULL DEFAULT '' COMMENT '题库',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

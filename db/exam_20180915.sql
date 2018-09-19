CREATE TABLE `exam_question_bank_ya` (
  `question_bank_id` varchar(32) NOT NULL COMMENT '题库ID',
  `question_bank_ya_id` varchar(32) NOT NULL DEFAULT '' COMMENT '本期押题ID',
  PRIMARY KEY (`question_bank_ya_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='押题';


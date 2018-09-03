CREATE TABLE `exam_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `pass_word` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `reg_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


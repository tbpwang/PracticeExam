DROP DATABASE IF EXISTS db_health;
CREATE DATABASE db_health;

DROP TABLE IF EXISTS db_health.user;
CREATE TABLE db_health.user (
  id        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK',
  username  VARCHAR(255) NOT NULL UNIQUE
  COMMENT '用户名',
  password  VARCHAR(255) NOT NULL
  COMMENT '密码',
  telephone VARCHAR(255) NOT NULL
  COMMENT '联系电话',
  address   VARCHAR(255) NOT NULL
  COMMENT '用户地址',
  isAdmin   BOOLEAN      DEFAULT FALSE
  COMMENT '是否管理员身份'
)
  COMMENT '用户表';

SHOW COLUMNS FROM db_health.user;

INSERT INTO db_health.user (username, password, telephone, address, isAdmin)
VALUES ('admin', '123', '123456789', 'cumtb', TRUE);

INSERT INTO db_health.user (username, password, telephone, address)
VALUES ('test', '123', '1350000000', 'cumtb');

SELECT *
FROM db_health.user;

DROP TABLE IF EXISTS db_health.article;
CREATE TABLE db_health.article (
  id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK',
  title    VARCHAR(255)  NOT NULL
  COMMENT '文章标题',
  abstract VARCHAR(2048) NOT NULL
  COMMENT '文章摘要',
  content  MEDIUMTEXT    NOT NULL
  COMMENT '文章内容',
  createDate     TIMESTAMP DEFAULT current_timestamp
  COMMENT '发布时间'
)
  COMMENT '文章表';

INSERT INTO db_health.article (title, abstract, content) VALUES ('坚持运动能改善肌肉缓解松弛','摘要坚持运动能改善肌肉缓解松弛','正文坚持运动能改善肌肉缓解松弛');

SELECT *
FROM db_health.article;
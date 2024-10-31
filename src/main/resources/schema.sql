-- 建立短網址資料表
CREATE TABLE IF NOT EXISTS `shorturl_data` (
  `shortUrl` VARCHAR(15) NOT NULL COMMENT '短網址' PRIMARY KEY,
  `originalUrl` TEXT NOT NULL COMMENT '原始網址',
  `createAt` VARCHAR(30) NOT NULL COMMENT '建立時間',
  `clickCount` INT COMMENT '點擊次數' DEFAULT 0
);
create database studentchoosecourses default character set utf8;
GRANT all privileges ON studentchoosecourses.* TO  'studentchoosecourses'@'%' IDENTIFIED BY 'studentchoosecourses';
flush privileges;
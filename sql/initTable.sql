drop table t_student_user;
CREATE TABLE t_student_user (
  id varchar(255) NOT NULL COMMENT 'id',
  status int(255) DEFAULT NULL COMMENT 'status',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  user_name varchar(255) DEFAULT NULL COMMENT 'user name',
  user_pwd varchar(255) DEFAULT NULL COMMENT 'password',
  student_identity varchar(255) DEFAULT NULL COMMENT 'student identity',
  email varchar(255) DEFAULT NULL COMMENT 'email',
  first_name varchar(255) DEFAULT NULL COMMENT 'first name',
  last_name varchar(255) DEFAULT NULL COMMENT 'last name',
   PRIMARY KEY (id)
)  DEFAULT CHARSET=utf8 COMMENT='student user';



CREATE TABLE t_course_info
(
    id           varchar(255) NOT NULL COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    course_name    varchar(1000) comment 'course name',
    total_students int comment 'student count',
    avg_gpa        double comment 'avg gpa'
) comment = 'course info';
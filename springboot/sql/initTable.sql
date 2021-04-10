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


drop table t_subject_info;
CREATE TABLE t_subject_info
(
    id           varchar(255) NOT NULL COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    subject_name    varchar(1000) comment 'subject name'
) comment = 'subject info';

CREATE TABLE t_student_interest_info
(
    id           varchar(255) NOT NULL COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    subject_id    varchar(50) comment 'subject name',
    student_id    varchar(50) comment 'student name',
    interest_level  int
) comment = 'interest info';


CREATE TABLE t_major_requirement
(
    id      varchar(255) primary key COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    major_name    varchar(1000) comment 'major name',
    course_name    varchar(1000) comment 'course name',
    course_section    varchar(1000) comment 'course section',
    credits_need_in_section    int comment 'credits need in section'
) comment = 'major requirement';


CREATE TABLE t_student_choose_course_record
(
    id      varchar(255) primary key COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    student_id    varchar(1000) comment 'student id',
    course_name    varchar(1000) comment 'course name',
    which_year    int comment 'which year',
    semester   varchar(50) comment 'semester'
) comment = 'student choose course record';


drop table t_course_student_rate;
CREATE TABLE t_course_student_rate
(
    id      varchar(255) primary key COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    course_name    varchar(200) comment 'course name',
    student_id    varchar(200) comment 'student id',
    rate decimal(3,1) comment 'rate'
) comment = 't_course_student_rate';



CREATE TABLE t_course_student_tag
(
    id      varchar(255) primary key COMMENT 'id',
    status       int(255) DEFAULT NULL COMMENT 'status',
    create_time  datetime DEFAULT NULL COMMENT 'create time',
    update_time  datetime DEFAULT NULL COMMENT 'update time',
    course_name    varchar(200) comment 'course name',
    student_id    varchar(200) comment 'student id',
    tag varchar(200) comment 'tag'
) comment = 't_course_student_tag';


1.courseStudentRate
课程id  用户id  打分



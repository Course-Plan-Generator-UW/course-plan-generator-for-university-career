anyone regist can login this webapp

student_user:
id
create_time
update_time
status
user_name
user_pwd
student_identity
email
first_name
last_name


browser  <-->  webapp

http request contains:
1. method type: get post
get: query
post：push data
2.url:   http://ip:port/webappName/controllerName/{otherInfo}
get  /studentUser/queryPage?pageInfo    query one page
get  /studentUser/queryById?id=1   query one studentUser by id=1
post /studentUser/insertOne  body with json:{userName:xxx,userPwd:xxxx,xx... }
post /studentUser/updateById  body with json:{id:xx,...}
get  /studentUser/deleteById?id=1


method+url ---->  xxxxController.xxxMethod

XController.m1() call  XService.m2()  XDao.m3()


@RestController
//Controller|RestController 表示当前类的方法可以对外提供服务 浏览器可以访问
//这是一个Component Spring会扫描加载 管理这个类
@RequestMapping("user")
//表示客户端访问此类中的方法的时候 http://ip:port/应用/user/xxxx.do
@Slf4j
//自动生成log变量 用于记录日志
@CrossOrigin(origins = "*", maxAge = 3600)
//允许跨域访问

注册
1.输入密码
2.后台对密码加密 md5
3.存储

验证：
1.输入密码
2.后台对密码加密 md5
3.查看数据库中的密码是否一样

https://  加密

http:// 不安全 明文


学校 -> 专业 -> 课程


学生 选 专业； 选 课程


select term_code, subject_code,t4.name subject_name,t3.number course_number,t2.name course_name
from subject_memberships t1
left outer join course_offerings t2 on t1.course_offering_uuid = t2.uuid
left outer join course t3 on t2.course_uuid = t3.id
left outer join subject t4 on t1.subject_code = t4.code
where t1.subject_code = '102' and t2.term_code = 1112
order by course_number


有空的时候，进行逻辑梳理：
有哪些核心功能？每个核心功能，需要哪些数据，数据分布在哪些表中 还缺少哪些数据


兴趣表中：
1.发请求得到所有学科  都默认值为没有选择
学科id_levelId
2.发请求获取某学生的所有兴趣信息  选择对应的值

学生已选课程录入：
界面上的操作：
学生登陆
学生输入三个查询条件：年 学期 课程名
模糊查询
选中其中的一个，输入分数，
添加到已选课程

传送给后端的数据：
课程id 分数（后台会补充学生id）年 学期


保存的时候：


添加学生





目的：为选课服务
考虑到能毕业
1.手动选择 毕业专业（如果学生已有就不用选择了）
2.已选课程列表 自动从数据库查询
3.从数据库可以查到 还有哪些学科缺多少学分








前提：
一个课程 只有一个学期



1.courseStudentRate
课程id  用户id  打分

2.courseStudentTag
课程id 用户id 标签

3.
courseInfo中添加avgRate

课程课程id和用户id 可以获取rate值
根据课程id获取标签集合

提供








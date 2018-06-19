# 1、 SpringBoot 介绍 #

----------

## 1.1 SpringBoot特点： ##
 - 化繁为简，简化配置
 - 备受关注，下一代框架
 - 微服务的入门级微框架

# 2、 第一个SpringBoot 应用 #

----------

## 2.1 三种启动方式 ##

1. IDEA中

		`Application --> run`

2. 切换到项目目录：
	
		`mvn spring-boot:run`

3.  切换到项目目录：
  
		mvn install
		cd target
		java -jar xxx-0.0.1-SNAPSHOP.jar

# 3、项目属性配置 #

----------


## 3.1 配置文件的选择 ##

### 3.1.1 application.properties配置 ###
    server.prot=8080  --指定端口
	server.servlet.context-path=/girl  --项目路径
### 3.1.2 application.yml 配置（推荐，有天然的树状结构） ###
	server:
  		port: 8082
  		servlet:context-path: /girl

	
ps:

1. 冒号后必须有空格（语法设置）

2. springBoot2.0写法 server.servlet.context-path

3. springBoot1.0写法 server.context-path

## 3.2 引用配置中的值 ##

### 3.2.1 使用@Value注解 配置文件的注入 ###

> application.yml 中
    
	server:
  		port: 8080
	cupSize: B
	age: 18
	content: "cupSize: ${cupSize}, age: ${age}"

> HelloController 中

     @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

### 3.3.2 使用@ConfigurationProperties注解 ###

> application.yml （对配置的分组） 中
    
	server:
  		port: 8080
	girl:   
  		cupSize: B
  		age: 18

> 新建GirlProperties实体类 中



    @Component      // 不添加该注解无法Autowired
	@ConfigurationProperties(prefix = "girl")
	public class GirlProperties {
 
	    private String cupSize;
	
	    private Integer age;
	
	    public String getCupSize() {
	        return cupSize;
	    }
	
	    public void setCupSize(String cupSize) {
	        this.cupSize = cupSize;
	    }
	
	    public Integer getAge() {
	        return age;
	    }
	
	    public void setAge(Integer age) {
	        this.age = age;
	    }
	}
>
    @Value  配置文件的注入
	// 配置文件的分组
	@Component
	@ConfigurationProperties

## 3.3 多环境配置文件 ##
> application.yml 配置 (使用测试环境)：
>	
  	spring:
      profiles:
        active: dev


> application-dev.yml 测试环境配置：
>
    server:
  		port: 8080
	girl:
  		cupSize: B
  		age: 18

	
> application-prod.yml 生产环境配置：
>
    server:
  		port: 8081
	girl:
  		cupSize: F
  		age: 18



> ps:
> 命令行启动项目时指定配置文件：

	mvn install
	cd target
	java -jar girl-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

# 4、Controller的使用 #


- @Controller 处理http请求

- @RestController 组合注解（spring4之后新加的注解，返回json，等同于@Controller 与 @ResponseBody的组合）
- @RequestMapping 配置URL映射

## 4.1  RequestMapping ##
### 4.1.1 多url访问同一个页面 ###

使用集合方法上@RequestMapping(value = {"/sayhello","/sayhi"}

### 4.1.2 给整个类添加一个URL  ###
类上面加@RequestMapping("/hello")\
 
访问方法时，

拼接使用http://localhost:8080/hello/sayhello 
或者 http://localhost:8080/hello/sayhi

### 4.1.3 带参数到url ###

#### 4.1.3.1 不带问好到url ####

@PathVariable("id")   --> /say/{id} 或者 /{id}/say

    // method = RequestMethod.POST 可以不加，get/post 都可以获取到,(不建议)
	@RequestMapping(value = "/say/{id}", method = RequestMethod.POST)
    public String say(@PathVariable("id") Integer id){

		return "id:" + id;
    }
访问：http://localhost:8080/hello/say/234 

或者 http://localhost:8080/hello/234/say 

#### 4.1.3.2 带问好带url ####

@RequestParam("id")

	@RequestMapping(value = "/say", method = RequestMethod.POST)
    public String say(@RequestParam("id") Integer myId){
   
        return "id:" + myId;
    }


访问：http://localhost:8080/hello/say?id=123

使用http://localhost:8080/hello/say 访问 报 400
使用：http://localhost:8080/hello/say?id=  --> id = null




 	//@RequestMapping(value = "/say", method = RequestMethod.GET)
	@GetMapping(value = "/say")
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId){
        
        return "id:" + myId;
    }

	required： 是否必传 

	defaultValue: 默认值

	组合注解：
	
	@RequestMapping(value = "/say", method = RequestMethod.GET) 等价于 @GetMapping(value = "/say")

	@RequestMapping(value = "/say", method = RequestMethod.POST) 等价于 @PostMapping(value = "/say")

# 5、数据库操作 #

## 5.1 Spring-Data-Jpa ##

   JPA(Java Persistence API) ：

   定义了一系列对象持久化的标准，目前实现这一规范的产品Hibernate、TopLink等

	新增两个依赖

	<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jpa</artifactId>
    </dependency>

	在application.yml中新增以下配置，测试以及生产环境也都可以用

    spring:
  		profiles:
    		active: dev
	  	datasource:
		    driver-class-name: com.mysql.jdbc.Driver
		    url: jdbc:mysql://127.0.0.1:3306/dbgirl
		    username: root
		    password: root
		  jpa:
		    hibernate:
		      ddl-auto: update  #常用create/update
		      naming:
		          physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
		          database-platform: org.hibernate.dialect.MySQL5InnoDBDialect  #不加这句则默认为myisam引擎解决事务不回滚问题
		    show-sql: true


	update : 程序重启表若存在，更新
	create : 程序重启表若存在，删掉重建


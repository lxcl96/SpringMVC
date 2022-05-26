

# 一、SpringMVC简介

### 1、什么是MVC

MVC是一种软件架构的思想，将软件按照模型、视图、控制器来划分

M：Model，模型层，指工程中的JavaBean，作用是处理数据

JavaBean分为两类：

- 一类称为实体类Bean：专门存储业务数据的，如 Student、User 等
- 一类称为业务处理 Bean：指 Service 或 Dao 对象，专门用于处理业务逻辑和数据访问。

V：View，视图层，指工程中的html或jsp等页面，作用是与用户进行交互，展示数据

C：Controller，控制层，指工程中的servlet，作用是接收请求和响应浏览器

MVC的工作流程：
用户通过视图层发送请求到服务器，在服务器中请求被Controller接收，Controller调用相应的Model层处理请求，处理完毕将结果返回到Controller，Controller再根据请求处理的结果找到相应的View视图，渲染数据后最终响应给浏览器

### 2、什么是SpringMVC

SpringMVC是Spring的一个后续产品，是Spring的一个子项目

SpringMVC 是 Spring 为表述层开发提供的一整套完备的解决方案。在表述层框架历经 Strust、WebWork、Strust2 等诸多产品的历代更迭之后，目前业界普遍选择了 SpringMVC 作为 Java EE 项目表述层开发的**首选方案**。

> 注：三层架构分为表述层（或表示层）、业务逻辑层、数据访问层，表述层表示前台页面和后台servlet

### 3、SpringMVC的特点

- **Spring 家族原生产品**，与 IOC 容器等基础设施无缝对接
- **基于原生的Servlet**，通过了功能强大的**前端控制器DispatcherServlet**，对请求和响应进行统一处理
- 表述层各细分领域需要解决的问题**全方位覆盖**，提供**全面解决方案**
- **代码清新简洁**，大幅度提升开发效率
- 内部组件化程度高，可插拔式组件**即插即用**，想要什么功能配置相应组件即可
- **性能卓著**，尤其适合现代大型、超大型互联网项目要求

# 二、HelloWorld

### 1、开发环境

IDE：idea 2021.3

构建工具：maven3.6.1

服务器：tomcat8

Spring版本：5.3.1

### 2、创建maven工程

##### a>添加web模块

##### b>打包方式：war

##### c>引入依赖

```xml
<dependencies>
    <!-- SpringMVC -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.1</version>
    </dependency>

    <!-- 日志 -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>

    <!-- ServletAPI -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>

    <!-- Spring5和Thymeleaf整合包 -->
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
        <version>3.0.12.RELEASE</version>
    </dependency>
</dependencies>
```

注：由于 Maven 的传递性，我们不必将所有需要的包全部配置依赖，而是配置最顶端的依赖，其他靠传递性导入。

![images](img\img001.png)

### 3、配置web.xml

注册SpringMVC的前端控制器DispatcherServlet

##### a>默认配置方式 (spring配置文件位置默认，名称默认)

此配置作用下，SpringMVC的配置文件默认位于WEB-INF下，默认名称为web.xml中\<servlet-name>的值-servlet.xml，例如，以下配置所对应SpringMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml

```xml
<!-- 配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理 -->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <!--
        设置springMVC的核心控制器所能处理的请求的请求路径
        /所匹配的请求可以是/login或.html或.js或.css方式的请求路径
        但是/不能匹配.jsp请求路径的请求
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

##### b>扩展配置方式

可通过init-param标签设置SpringMVC配置文件的位置和名称，通过load-on-startup标签设置SpringMVC前端控制器DispatcherServlet的初始化时间

```xml
<!-- 配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理 -->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 通过初始化参数指定SpringMVC配置文件的位置和名称 -->
    <init-param>
        <!-- contextConfigLocation为固定值 -->
        <param-name>contextConfigLocation</param-name>
        <!-- 使用classpath:表示从类路径查找配置文件，例如maven工程中的src/main/resources -->
        <param-value>classpath:springMVC.xml</param-value>
    </init-param>
    <!-- 
 		作为框架的核心组件，在启动过程中有大量的初始化操作要做
		而这些操作放在第一次请求时才执行会严重影响访问速度
		因此需要通过此标签将启动控制DispatcherServlet的初始化时间提前到服务器启动时
	-->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <!--
        设置springMVC的核心控制器所能处理的请求的请求路径
        /所匹配的请求可以是/login或.html或.js或.css方式的请求路径
        但是/不能匹配.jsp请求路径的请求
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

> 注：
>
> \<url-pattern>标签中使用/和/*的区别：
>
> /所匹配的请求可以是/login或.html或.js或.css方式的请求路径，但是/不能匹配.jsp请求路径的请求
>
> 因此就可以避免在访问jsp页面时，该请求被DispatcherServlet处理，从而找不到相应的页面
>
> /*则能够匹配所有请求，例如在使用过滤器时，若需要对所有请求进行过滤，就需要使用/\*的写法

### 4、创建请求控制器

由于前端控制器对浏览器发送的请求进行了统一的处理，但是具体的请求有不同的处理过程，因此需要创建处理具体请求的类，即请求控制器

请求控制器中每一个处理请求的方法成为控制器方法

因为SpringMVC的控制器由一个POJO（普通的Java类）担任，因此需要通过@Controller注解将其标识为一个控制层组件，交给Spring的IoC容器管理，此时SpringMVC才能够识别控制器的存在

```java
@Controller
public class HelloController {
    
}
```

### 5、创建springMVC的配置文件

```xml
<!-- 自动扫描包 -->
<context:component-scan base-package="com.atguigu.mvc.controller"/>

<!-- 配置Thymeleaf视图解析器 -->
<bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
    <property name="order" value="1"/>
    <property name="characterEncoding" value="UTF-8"/>
    <property name="templateEngine">
        <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
            <property name="templateResolver">
                <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
    
                    <!-- 视图前缀 -->
                    <property name="prefix" value="/WEB-INF/templates/"/>
    
                    <!-- 视图后缀 -->
                    <property name="suffix" value=".html"/>
                    <property name="templateMode" value="HTML5"/>
                    <property name="characterEncoding" value="UTF-8" />
                </bean>
            </property>
        </bean>
    </property>
</bean>

<!-- 
   处理静态资源，例如html、js、css、jpg
  若只设置该标签，则只能访问静态资源，其他请求则无法访问
  此时必须设置<mvc:annotation-driven/>解决问题
 -->
<mvc:default-servlet-handler/>

<!-- 开启mvc注解驱动 -->
<mvc:annotation-driven>
    <mvc:message-converters>
        <!-- 处理响应中文内容乱码 -->
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <property name="defaultCharset" value="UTF-8" />
            <property name="supportedMediaTypes">
                <list>
                    <value>text/html</value>
                    <value>application/json</value>
                </list>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```

### 6、测试HelloWorld

##### a>实现对首页的访问

在请求控制器中创建处理请求的方法

```java
// @RequestMapping注解：处理请求和控制器方法之间的映射关系
// @RequestMapping注解的value属性可以通过请求地址匹配请求，/表示的当前工程的上下文路径
// localhost:8080/springMVC/
@RequestMapping("/")
public String index() {
    //设置视图名称
    return "index";
}
```

##### b>通过超链接跳转到指定页面

在主页index.html中设置超链接

```html
<!DOCTYPE html>
<!-- thymeleaf名称空间必须添加-->
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
    <h1>首页</h1>
    <a th:href="@{/hello}">HelloWorld</a><br/>
</body>
</html>
```

在请求控制器中创建处理请求的方法

```java
@RequestMapping("/hello")
public String HelloWorld() {
    return "target";
}
```

### 7、总结

浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理。前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制器中@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面

# 三、@RequestMapping注解

## 1、@RequestMapping注解的功能

从注解名称上我们可以看到，@RequestMapping注解的作用就是将请求和处理请求的控制器方法关联起来，建立映射关系。

SpringMVC 接收到指定的请求，就会来找到在映射关系中对应的控制器方法来处理这个请求。

## 2、@RequestMapping注解的位置

@RequestMapping标识一个==类==：设置映射请求的请求路径的初始信息

@RequestMapping标识一个==方法==：设置映射请求请求路径的具体信息

```java
@Controller
@RequestMapping("/test")
public class RequestMappingController {

	//此时请求映射所映射的请求的请求路径为：/test/testRequestMapping
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping(){
        return "success";
    }

}
```

## 3、@RequestMapping注解的value属性

==***必须添加，如果是数组满足其中任意一个即可***==

==@RequestMapping注解的value属性通过请求的请求地址匹配请求映射==

@RequestMapping注解的value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求

@RequestMapping注解的value属性必须设置，至少通过请求地址匹配请求映射

```html
<a th:href="@{/testRequestMapping}">测试@RequestMapping的value属性-->/testRequestMapping</a><br>
<a th:href="@{/test}">测试@RequestMapping的value属性-->/test</a><br>
```

```java
@RequestMapping(
    	//如果是数组满足其中任意一个即可
        value = {"/testRequestMapping", "/test"}
)
public String testRequestMapping(){
    return "success";
}
```

>==如果不满足value属性：报错代码404==
>
> HTTP状态 404 - 未找到

## 4、@RequestMapping注解的method属性

==***默认get和post请求都接收，如果是数组满足其中任意一个即可***==

==@RequestMapping注解的method属性通过请求的请求方式（get或post）匹配请求映射==

@RequestMapping注解的method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求

若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错405：Request method 'POST' not supported

```html
<a th:href="@{/test}">测试@RequestMapping的value属性-->/test</a><br>
<form th:action="@{/test}" method="post">
    <input type="submit">
</form>
```

```java
@RequestMapping(
        value = {"/testRequestMapping", "/test"},
    	//如果是数组满足其中任意一个即可 默认post和get请求都接收
        method = {RequestMethod.GET, RequestMethod.POST}
)
public String testRequestMapping(){
    return "success";
}
```

`每次都写参数比较麻烦，spring对不同请求方式定义了相应的注解： `

+ `处理GET请求，method = "RequestMethod.GET" 对应注解===>@GetMapping(请求路径)`
+ `处理POST请求，method = "RequestMethod.POST" 对应注解===>@PostMapping(请求路径)`
+ `处理PUT请求，method = "RequestMethod.PUT" 对应注解===>@PutMapping(请求路径)`
+ `处理DELETE请求，method = "RequestMethod.DELETE" 对应注解==>@DeleteMapping(请求路径)`

==如果即写了method，又写了注解，注解方式的失效，只有method的生效==

```java
@RequestMapping(value = {"/testRequestMapping","/test"},method = { RequestMethod.POST})  
@GetMapping("/testRequestMapping") //失效 放在RequestMapping也是失效
public String success() {
    return "success";
}
```

***好处：***根据同一个路径的不同请求可以处理不同的业务逻辑（如：查询就是get。更新就是post）

***常用的请求方式为：GET、POST、PUT、DELETE，但是目前浏览器只支持GET和POST请求，如果form标签下提交方式为其他请求则被浏览器默认设为GET请求方式。***

==如果一定要发送PUT或其他请求，则需要通过Spring提供的过虑器HiddenHttpMethodFilter，这个会在restful部分讲到==

> ==如果不满足method属性：报错代码405==
>
> Request method 'POST' not supported

## 5、@RequestMapping注解的params属性

表示请求的参数，根据请求参数来获取请求。

==***如果params属性为数组，表示所有的条件都需要满足才能匹配上***==

```java
/*	请求参数格式如下：
    params = {"username"} 表示请求中必须含有关建字（key）username，对属性值（value）不做要求
    params = {"!username"} 表示请求中不能含有关建字（key）username
    params = {"username=admin"} 表示请求中必须含有关建字（key）username，且属性值（value）必须为admin
    params = {"username!=admin"} 表示请求中必须含有关建字（key）username，且属性值（value）不能为为admin （实际情况是，除了username!=admin的其他所有访问都可以，包括没有username）
   
 */
```

```html
<!-- 小知识 参数拼接两种方式-->
<!-- 方式1：使用? -->
<a th:href="@{/hello/testRequestMapping?username=admin&passwd=123}">get请求</a>
<!-- 方式2：使用()  【thymeleaf有效，别的地方不确定】-->
<a th:href="@{/hello/testRequestMapping(username=admin,passwd=123)}">get请求</a>
```

> ==如果不满足params属性：报错代码400==
>
> Parameter conditions "username, password!=123456" not met for actual request parameters: username={admin}, password={123456}

## 6、@RequestMapping注解的headers属性

根据请求头进行映射匹配

```java
/*	请求头参数格式如下：
    headers = {"header"} 表示请求头中必须含有关建字（key）username，对属性值（value）不做要求
    headers = {"!header"} 表示请求头中不能含有关建字（key）username
    headers = {"username=admin"} 表示请求头中必须含有关建字（key）username，且属性值（value）必须为admin
    headers = {"username!=admin"} 表示请求头中必须含有关建字（key）username，且属性值（value）不能为为admin （实际情况是，除了header!=admin的其他所有访问都可以，包括没有username）
   
 */
```

> ==如果不满足headers属性：报错代码404==
>
> HTTP状态 404 - 未找到

## 7、SpringMVC支持ant风格的路径

==给@RequestMapping的value属性用==

*特殊字符不好使： / 和 ?*

`?：表示任意的单个字符`   

`*：表示任意的0个或多个字符`

`**:表示任意的0层或多层目录` 如 /a\*\*a/ 此时\*，没有特殊含义

> 在使用\*\*时，只能使用  /\*\*/xxx的方式，\*\*只能单独写

## 8、SpringMVC支持路径中的占位符（重点）

`地址参数原始方式：/deleteUser?id=1`

`地址参数restful方式：/deleteUser/1`

`例如：原始方式：userSpringMVCToController?id=1  `

`rest方式：user/springMVC/to/Controller/1`

SpringMVC路径中的占位符常用于restful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中（传参），就可以在相应的@ResquestMapping注解的value属性中通过占位符{xxx}表示传输的数据xxx为你任意取的名字，便于获取，再通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参。

```java
//{}就表示为占位符 表示这是前端传来的值而不是地址,随便起个名字叫id 【占位符必有值】
//多个参数 必须用/分割
@RequestMapping("/testPath/{id}/{username}")   //路径下写了占位符则前端必须传递，否则无法匹配
//给参数加上注解@PathVariable  自动注入属性
public String testPath(@PathVariable("id") Integer id,@PathVariable("username") String username) {
    System.out.println("id:" + id);
    System.out.println("username:" + username);
    return "success";
}

//注：可以匹配到/testPath/1 但匹配不到/testPath  即占位符不能为空
```



# 四、SpringMVC获取请求参数

## 方法1：通过原生ServletAPI获取参数

原理：前端处理器DispatcherServlet调用控制期响应方法时，会根据方法的参数自动注入属性。这其中就包括HttpServletRequest request。【直接使用，不需要加上注解】




























































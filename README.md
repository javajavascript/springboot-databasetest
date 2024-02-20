Spring Boot Notes

-------------------------------------------------------------

Project Setup - Personal

Make sure Sring Boot version, JDK version, and MyBatis version are all compatible!

The error message will never tell you that the version is wrong or incompatible! You have to check these yourself, and people just figure it out with experience

This kind of problem doesn't happen in JavaScript Node JS because the console clearly tells you when a package dependency version is outdated or depreciated. Spring Boot doesn't say anything!

-------------------------------------------------------------

Framework Important Note

Application Context Error usually referes to dependency or configuartion or version issue

Spring Boot has a lot of options, JDBC, MyBatis, JPA, Hibernate, H2

If you follow tutorials, make sure they are using the same setup! For example below:

Tutorial 1 uses JPA and MySQL Workbench
https://www.youtube.com/watch?v=4LZKnegAm4g

Tutorial 2 uses H2
https://www.javaguides.net/2021/07/crud-junit-tests-for-spring-data-jpa.html

You cannot combine the code from these! They run on different setups!

This problem doesn't normally happen with JavaScript because everyone uses Node JS and Express JS, so the setup is the same. But Spring Boot is a much bigger framework, there are many options, and people will use different setups.

-------------------------------------------------------------

MySQL Workbench

In order to run MySQL Workbench, you need to install MySQL Server! These are 2 separate things!

MySQL Workbench: https://dev.mysql.com/downloads/workbench/
MySQL Community Server: https://dev.mysql.com/downloads/mysql/

MySQL Workbench is a GUI for MySQL Server
MySQL Server is the actual database

MySQL Workbench Example

https://www.youtube.com/watch?v=4LZKnegAm4g
9:10 and 14:40

create database fullstack;
show databases;
use fullstack;
show tables;
desc user; (description user)
select * from user;

Click on Schemas on left side bottom, refresh, and you will see the database

-------------------------------------------------------------

Apache Maven

Maven is a build tool, it allows for mvn clean and mvn install

The pom.xml (project object model) comes from maven. This is like package.json from Node JS.

Maven manages dependencies

Maven is a project management tool, it allows you to build, test, and package projects

However, Maven is not a java compiler, that is separate

Recall that when you create a new Spring project, Maven and Groovy are the build options
https://start.spring.io/

-------------------------------------------------------------

Spring vs Spring Boot

Spring makes you manually define dependencies in pom.xml
Spring does not come with embedded servers like tomcat

Spring Boot comes with a starter pom.xml file
Spring Boot comes with embedded servers like tomcat

-------------------------------------------------------------

Spring Bean

POJO = plain old java object
@Component = Spring bean

Spring bean = Java class managed by Spring
Spring beans can be managed in xml or java

Spring bean XML example:

<bean id="myBean" class="com.example.MyBean">
    <property name="message" value="Test"/>
</bean>

Name is the instance variable name
Value is the value of the instance variable, set through a Java setter

Spring bean java example:

public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}


@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        MyBean bean = new MyBean();
        bean.setMessage("Test");
        return bean;
    }
}

-------------------------------------------------------------

Autowired

Autowired creates an instances of a class into another class that depends on it (example: creates instance of a car class into a driver class)

Autowired is an annotation used for automatic dependency injection. It allows Spring to automatically inject dependencies into a Spring bean, eliminating the need for explicit bean configuration in the XML or Java configuration files.

With Autowired, it looks like this:

// Car.java
public class Car {
    private String model;

    // Constructor
    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

// Driver.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {
    private Car car;

    // Constructor injection using @Autowired
    @Autowired
    public Driver(Car car) {
        this.car = car;
    }

    public void drive() {
        System.out.println("Driving " + car.getModel());
    }
}

@Autowired is doing this

        Car car = new Car("Toyota");
        Driver driver = new Driver(car);

-------------------------------------------------------------

Controller, Service, DAO

The primary goal of these 3 layers is separation of concern, so we can test everything (back end to front end data transfer, data manipulation, data fetching) separately, quick explanation: 

Controller = closest to UI, determines URL mappings, transfers data from back end to front end, Postman tests this, sometimes called API layer because front end calls this

Service = business logic/layer, prepares and manipulates the data, example would be filtering, calls APIs from other microservices because it is business logic

DAO = data access object, sometimes called repository layer, fetches from database using JDBC or MyBatis (SQL XML approach)

Model = separate from the 3 layers but in used in all 3, general directory/class to determine fields and getters/setters of object

Why doesn't JavaScript Node JS use controller, service dao?

Controller, service, DAO are synchronous, while Node JS is asynchronous

Node JS is single threaded, while spring boot is multi threaded

This next part is a guess. Because spring boot is multithreaded, we can run multiple back end operations, so we separate into controller, service, dao very clearly. Because Node JS is single threaded, we use async await to run one operation at a time, which is why we group the URL mapping, filtering, and data fetching in one call. Single threaded makes us focus on single operations from beginning to end, which is why they are grouped.

Node JS has Express JS middleware to handle the service/business logic

Explanation with examples:

Controller = closest to UI, determines URL mappings, transfers data from back end to front end, Postman tests this, sometimes called API layer because front end calls this

  //get all posts
  @GetMapping("/posts")
  public List<Post> getPosts(){
    //calling child class getPosts() method of PostServiceImpl using postService(parent).
    return this.postService.getPosts();
  }


Service = business logic/layer, prepares and manipulates the data, example would be filtering, Calls APIs from other microservices because it is business logic. We can also filter in DAO through SQL statements, but the service layer allows for separation of concern, if we change our filter rules, we only change service, not DAO. Also, you can test data filtering separately from data fetching.

@Service
public class PostServiceImpl implements PostService {
  //storing data temporary…
  List<Post> list;
  //adding constructor of List<Post>
  public PostServiceImpl() {
  // adding data into this constructor.
  list = new ArrayList<>();
  list.add(new Post(1, "first post", "Hey, this is my first post"));
  list.add(new Post(2, "second post", "Hey,this is my second post"));
  }
  @Override
  public List<Post> getPosts() {
  // TODO Auto-generated method stub
  return list;
  }
}

DAO = data access object, sometimes called repository layer, fetches from database using JDBC or MyBatis (SQL XML approach)

-------------------------------------------------------------

Tomcat

Tomcat is an embedded server or web server for Spring Boot apps to run on. The JavaScript equivalent would be Node HTTP Module or Express JS, since they all run the app on a port. Tomcat defaults to port 8080 and Node/Express default to port 3000. However, the biggest difference is that you can code with Node HTTP Module and Express, but you cannot code with Tomcat.

-------------------------------------------------------------

Annotations

What is @RestController annotation in Spring Boot?

The @RestController is a stereotype annotation. It adds @Controller and @ResponseBody annotations to the class. We need to import org.springframework.web.bind.annotation package in our file, in order to implement it.

What is @RequestMapping annotation in Spring Boot?

The @RequestMapping annotation is used to provide routing information. It tells to the Spring that any HTTP request should map to the corresponding method. We need to import org.springframework.web.annotation package in our file.

What is @RestController annotation in Spring Boot?
@RestController is a Spring Boot annotation used to create RESTful web services.

What is @RequestMapping annotation in Spring Boot?
@RequestMapping is a Spring Boot annotation used to map a URL request to a controller method.

-------------------------------------------------------------

Mircoservices with Spring Boot

Design and develop each microservice as a separate Spring Boot application, encapsulating a specific business capability.
Use lightweight communication mechanisms like REST or messaging for inter-service communication.
Implement service discovery and registration using tools like Netflix Eureka or HashiCorp Consul.
Apply fault tolerance and resilience patterns like circuit breakers (Hystrix) and distributed tracing (Sleuth) for better reliability.
Deploy and manage microservices using containerization platforms like Docker and orchestration tools like Kubernetes.


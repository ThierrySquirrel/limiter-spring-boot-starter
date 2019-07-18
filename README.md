#   limiter-spring-boot-starter

limiter SpringBoot  Edition

[English](./README.md)

Support function：
- [x] Single-machine current limiting
- [x] Cluster Current Limitation

## Tips：
    Each time a node is restarted or added, the initial number fills redis, of course, when the capacity is not maximized.    
    Specific flow restriction parameters, please cooperate with pressure testing tools to optimize, to achieve the QPS you need.  
    If the current limit is successful, the code block with @LimitTraffic annotation will be executed, otherwise it will not be executed to facilitate user-defined schemes, such as service protection degradation, etc.
        
##  Quick Start

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <artifactId>limiter-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>1.0.1-RELEASE</version>
        </dependency>
```

### configuration file
 
 ```properties
 ## application.properties
spring.redis.host= #您redis的地址
spring.redis.port= #您redis的端口号
 ```
 
#   Start limiter

 ```java
 @SpringBootApplication
 @EnableLimiter
 public class DemoApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }  
 }
 ```
 
 #  Current Limitation
 
 ```java
 @TokenLimitedTraffic
 public class Hello {
 	@LimitTraffic(initialQuantity = 2333, maximumCapacity = 3222, addedQuantity = 2333)
 	public String hello() {
 		return "world";
 	}
 }
 ```
 
 #  Custom operation

```java
@RestController
public class World {
	@Resource
	private Hello hello;

	@GetMapping("/world")
	public String world() {
		String hello = this.hello.hello();
		boolean empty = StringUtils.isEmpty(hello);
		if (empty) {
			return "service degradation";
		}
		return hello;
	}
}
``` 
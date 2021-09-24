#   limiter-spring-boot-starter

限流   SpringBoot 版

[English](./README.md)

支持功能：
- [x] 限流  
- [x] 限制服务  

## 提示:
  限流之前,请先压测好服务QPS,进行准确限流.  
  准确限流,保障服务不会因为过高QPS导致服务关闭或重启,服务集群时更为健壮.  
  限流操作,应该分为事务性操作,和非事务性操作,分开限流,通常两者QPS差距较大.  
  限制服务,用于可能产生延迟或高出错率的服务,保障服务的安全性  

##  Quick Start

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <artifactId>limiter-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.1.0.7-RELEASE</version>
        </dependency>
```
 
#   启动Limiter

 ```java
 @SpringBootApplication
 public class LimiterApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }  
 }
 ```
 
 #  限流
 
 ```java
@Slf4j
@Component
public class LimitFallback {
    public String limit(String limit) {
        log.error (limit);
        return limit;
    }
}

 @RestController
 public class limitController {
    @LimitTraffic (limitName = "limit", permitsPerSecond = 2000, fallbackClass = LimitFallback.class, fallbackMethod = "limit")
 	public String limit(@RequestParam("limit") String limit) {
 		return limit;
 	}
 }
 ```

 #  限制服务
 
  ```java
 @Slf4j
 @Component
 public class LimitedServiceFallback {
     public String limitedService(String limitedService) {
         log.error (limitedService);
         return limitedService;
     }
 }
 
  @RestController
  public class LimitedServiceController {
     @LimitedService (fallbackClass = LimitedServiceFallback.class, fallbackMethod = "limitedService")
  	public String limitedService(@RequestParam("limitedService") String limitedService) {
  		return limitedService;
  	}
  }
  ```
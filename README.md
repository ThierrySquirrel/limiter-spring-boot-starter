#   limiter-spring-boot-starter

limiter SpringBoot  Edition

[中文](./README_zh_CN.md)

Support function：
- [x] Limit Traffic
- [x] Limited Service

## Tips:
  Before Current Limiting, Please Press And Measure The QPS For Accurate Current Limiting  
  Accurate Flow Restriction Ensures That The Service Will Not Be Shut Down Or Restarted Due To Excessive QPS, And The Service Cluster Is More Robust  
  The Current Limiting Operation Should Be Divided Into Transactional Operation And Non Transactional Operation, And The QPS Gap Between Them Is Usually Large  
  Limit Service, For Service That May Cause Delay Or High Error Rate, To Ensure The Security Of Services  
      
##  Quick Start

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <artifactId>limiter-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.1.0.5-RELEASE</version>
        </dependency>
```

#   Start Limiter

 ```java
 @SpringBootApplication
 public class LimiterApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }  
 }
 ```
 
 #  Limit Traffic
 
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

 #  Limited Service
 
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
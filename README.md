# comma

### About 
This is a one stop library for spring mvc-application.

it supports:
- custom commaException handling (Inline and REST)
- thrift server/client (with connection pool and caching)
- swagger customization (SwaggerDiscoverable)
- Message Resource with locale
- kafka (publisher/subscriber)
- logging (AOP logging at class and method level)


### How to use?
   - Maven Project
       ```java
        <!-- Add this dependency in your pom.xml -->
           
           <dependency>
               <groupId>org.ebenso</groupId>
               <artifactId>comma</artifactId>
                   <version>0.0.1-SNAPSHOT</version>
           </dependency>
       ```

- Release version

    ```java
    <!-- Add this dependency in your pom.xml -->
    
    <dependency>
        <groupId>org.ebenso</groupId>
        <artifactId>comma</artifactId>
            <version>0.0.1</version>
    </dependency>
    ```

- Gradle Project
    ```java
    <!-- Add this dependency in your build.gradle -->
    
    implementation('org.ebenso:comma')
    
    ```

## Built With

* [Java](https://docs.oracle.com/en/java/)
* [Thrift](https://thrift.apache.org/docs/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [comma(acp-comma)]()



## Authors

[Kumar Harsha](https://github.com/eigenharsha)

## Questions?

If you have any questions about the framework, or something doesn't work as expected,
please [submit an issue here](https://github.com/eigenharsha/comma/issues/new).

## How to contribute?

Fork the repository, make changes, submit a pull request.
We promise to review your changes same day and apply to
the `master` branch, if they look correct.

Please run Maven build before submitting a pull request:

```
$ mvn clean install -Pqulice
```
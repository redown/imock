# imock

## repository
```xml
<dependency>
    <groupId>com.github.redown</groupId>
    <artifactId>imock</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Api
### addPerson
|key|name|type|lenght|value|
|--- |--- |--- |--- |--- |
|input|||||
|name|Name|String|[8-12]|tom|
|age|Age|int|[18,60]|20|
|birth|Birth|String|[8,8]|20000101|
|output|||||

### queryPerson
|key|name|type|lenght|value|
|--- |--- |--- |--- |--- |
|input|||||
|name|Name|String|[8-12]|tom|
|output|||||
|name|Name|String|[8-12]|tom|
|age|Age|int|[18,60]|20|
|birth|Birth|String|[8,8]|20000101|


## Service
- host: 0.0.0.0
- prot: 9381

## Code
### IApiEntity/IApiField
```java
@IApiEntity(name = "Person Input")
public class PersonInput {

    @IApiField(name="Name", length = {8, 16}, alias = "userName")
    String name;

    @IApiField(name = "Age", range = {18, 60})
    int age;
}
```

### IApi/IMethod
```java
@IApi(name = "Person Service")
public interface PersonService {
    @IApiMethod(name = "addPerson")
    PersonOutput addPerson(PersonInput input);

    @IApiMethod(name = "qaueryPerson")
    PersonOutput queryPerson(PersonInput input);
}
```

### IApiScan/IMockServer
```java
@IApiScan(service={"com.github.redown.imock.xexample.service"})
@IMockServer(port = 9381, hander = TimeServerHandler.class)
public class ExampleServer {
    public static void main(String[] args) {
        MockServer.run(ExampleServer.class, args);
    }

}
```


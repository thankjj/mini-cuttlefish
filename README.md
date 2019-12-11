# spring-cloud整合eureka和hystrix

#### 介绍
springcloud整合eureka和hystrix，实现模块间的调用，及模块出错的熔断器调用

#### 软件架构

--cuttlefish
  --cuttlefish-eureka-server 充当注册中心服务器，各个微服务模块如用户模块都注册到其注册中心
  --cuttlefish-user 充当服务提供者角色，提供用户服务，即提供接口调用
  --cuttlefish-user-api 充当接口暴露与调用的角色，使得服务提供方、消费方可以进行
  --cuttlefish-wechat 充当服务消费者角色，对服务进行消费，即对接口进行调用




#### 使用说明

1.  启动cuttlefish-eureka-server
2.  启动cuttlefish-user和cuttlefish-wechat
3.  访问 http://localhost/user/test 进行校验，期间可以通过更改用户服务提供方（cuttlefish-user模块）的方法进行验证

```
@RestController
@RequestMapping(value="/user")
public class UserController implements UserApi {
    @Override
    public String test() {
        // 添加1/0，方法调用会报错，这时候便会触发熔断器，程序保持正常
//        System.out.println("aaa"+1/0);
        return "你好，这里是spring-cloud-producer响应结果，消息发送成功";
    }
}
```





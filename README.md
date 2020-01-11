## 介绍

#### 产品介绍
一款简约时尚阅读类小程序——墨鱼，追求极致内容、极致简约，适合广大年轻群体，土味情话、三行情书、
内涵段子等等，应有尽有。后期可以考虑进行定制化表白、回忆等服务，将他与她的故事印记在小程序，进入墨鱼，
就可以是进入一段回忆。

#### 技术背景
墨鱼整体架构采用Spring Cloud，Spring Cloud是一系列框架的有序集合。
利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，
如服务发现注册、配置中心、消息总线、负载均衡、熔断器、数据监控等，
都可以用Spring Boot的开发风格做到一键启动和部署。  

SpringCloud主要框架有：  
&emsp;1）服务发现——Netflix Eureka  
&emsp;2）服务调用——Netflix Feign  
&emsp;3）熔断器——Netflix Hystrix  
&emsp;4）服务网关——Netflix Zuul  
&emsp;5）分布式配置——Spring Cloud Config  
&emsp;6）消息总线——Spring Cloud Bus  
&emsp;（详情见：https://blog.csdn.net/qq_36092584/article/details/101536347）

目前已经整合Eureka、Feign和Hystrix，接下来先把功能进行完善，后续顶上其他组件


## 技术架构

#### 总体
--cuttlefish  
&emsp;--cuttlefish-eureka-server 充当注册中心服务器，各个微服务模块如用户模块都注册到其注册中心  
&emsp;--cuttlefish-user 用户模块服务器；充当服务提供者角色，提供用户服务，即提供接口调用  
&emsp;--cuttlefish-user-api 充当接口暴露与调用的角色，使得服务提供方、消费方可以进行  
&emsp;--cuttlefish-content 内容模块服务器；充当服务提供者角色，提供用户服务，即提供接口调用  
&emsp;--cuttlefish-content-api 充当接口暴露与调用的角色，使得服务提供方、消费方可以进行  
&emsp;--cuttlefish-wechat 微信小程序模块服务器；充当服务消费者角色，对服务进行消费，即对接口进行调用  
&emsp;--cuttlefish-wechat-ui 微信小程序UI界面  
&emsp;--cuttlefish-admin 后台管理模块服务器；充当服务消费者角色，对服务进行消费，即对接口进行调用  
&emsp;--cuttlefish-admin-ui 后台管理UI界面  

#### redis
主要用作缓存，包括缓存分类信息，用户信息，点赞信息，浏览信息，token信息等。
另外通过设置缓存失效时间控制token过期，优化某用户对同一文章反复点赞与取消，
改善浏览次数计量方式，约定时间内同一用户，同一IP再次浏览不增加浏览次数。
下面是简单的一个用法，主要是引入redisTemplate进行缓存操作。需要注意采用redisTemplate
由于其默认的序列化方式，缓存的key头会自动带上一串字符，
如： "\xac\xed\x00\x05t\x00\x19key_xxx"，但是不影响。解决方法也有可以
不采用默认的序列化方式，用StringRedisTemplate。

~~~
涉及常见操作：
redisTemplate.hasKey(key) 缓存中是否存在该key
redisTemplate.delete(key) 删除缓存中的key

redisTemplate.expire(key, 60, TimeUnit.SECONDS) 设置60s后缓存失效
redisTemplate.getExpire(key) 获取缓存失效时间，缓存失效后其值小于0

redisTemplate.opsForValue().set(key, value) 常规对象缓存，value可以是任何类型数据
redisTemplate.opsForValue().increment(key) 缓存key的value为数值型，自增1
redisTemplate.opsForValue().decrement(key) 缓存key的value为数值型，自减1

redisTemplate.opsForList().leftPushAll(key, list) 将list集合放入list缓存中
redisTemplate.opsForList().range(key, 0, -1) 从缓存中取出该list所有数据

redisTemplate.opsForSet().add(key, value) 往set缓存中加数据
redisTemplate.opsForSet().isMember(key, value) 判断set缓存中是否已经存在value
~~~
说明redis默认会采用快照RDB的持久化方式，即关掉服务器后缓存数据会读取快照文件进行缓存加载

#### 定时任务
采用Spring3.0后自带的一个定时任务器----Scheduled定时任务器，简单，无侵入。
这边采用定时任务将缓存中的一些数据定时持久化入库。用法如下：
~~~
@Component
@Configuration
@EnableScheduling
public class ContentScheduledTask {
    @Scheduled(cron = "每隔x秒执行一次：*/59 */1 * * * ? ")
    private void contentThumbPersist(){
        System.out.println("----定时任务持久化操作----" );
    }
}
~~~

## 项目启动

1.  启动cuttlefish-eureka-server
2.  启动cuttlefish-user和cuttlefish-content
3.  启动cuttlefish-wechat和cuttlefish-admin
4.  在微信开发者工具打开cuttlefish-wechat-ui，注意小程序需要手动修改./config/api.js

    ````
    const BaseUrl = 'http://192.168.201.8/wechat/';
    ````
    
    （工具下载：https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html）
    
5.  在VSCode打开cuttlefish-admin-ui

## 项目进展

目前，整体项目才完成小程序基本的功能；后台管理端界面整体架构已经有了，需要增加相应接口






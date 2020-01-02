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






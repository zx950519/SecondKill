# 秒杀系统实践

![](http://ww1.sinaimg.cn/large/005L0VzSgy1g35j335xo9j30n607s74e.jpg)  
[![experimental](http://badges.github.io/stability-badges/dist/experimental.svg)](http://github.com/badges/stability-badges)  
## :computer: 架构
![](http://ww1.sinaimg.cn/large/005L0VzSgy1g35im55pqzj313c08lq5d.jpg)  
本项目基于imooc教程以及网上若干博客资源，实现了一个简单的秒杀系统。其中，使用到了如下关键技术：  
- SpringBoot
- Redis
- MySQL
- RabbitMQ
- Tomcat
- Nginx
- Linux

## :wrench: 环境&安装&使用
本节对代码的开发环境、服务器环境、代码部署以及使用做简要说明
#### 开发环境
本套代码使用Win10系统+IDEA开发，JDK版本为8.0，SpringBoot版本为1.5.10.RELEASE。其他各组件的版本信息请移步pom.xml。

#### 服务器环境
本项目将MySQL、Redis、RabbitMQ安装在服务器环境下。服务器的操作系统为CentOS7。  
具体操作步骤可参考：http://note.youdao.com/noteshare?id=6bd987c21be47ce4381a82a2a8b5d1b0&sub=B8463E9BB46A479B9AE3D09D925A612F  
#### 代码部署
下载本仓库内的文件，放入新建的IDEA项目中，做简要配置即可使用。如果部署jar包或war包，需额外在操作系统中安装Tomcat并启动。  

##### 使用
1. 启动IDEA项目  
2. 打开浏览器，输入测试地址： http://服务器ip:8080/login/to_login  
3. 输入登陆信息(13000000000， 123456)  

如果需要重置数据库，请访问http://服务器ip:8080/miaosha/reset  

## :coffee: 压测分析
建设中......  

## :watermelon: 致谢
建设中......  

## :memo: 声明
建设中......  

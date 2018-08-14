Ipooleth应用层部署运维文档


1 前端页面(ipooleth-web)


1.1 环境准备


NodeJS 8.10



1.2 编译

npm run build:testing
npm run build:production

1.3 部署目录

/data/app/ipooleth-web


2 后端转义api服务(ipooleth-api)


2.1 项目结构


Ipooleth-common-utils
Ipooleth-data
Ipooleth-manage-api
Ipooleth-platform-api



2.2 环境准备


Maven3.5+
Mongodb3+
Redis3.2+
Jdk1.8+
Tomcat8+



2.3 配置修改


2.3.1 测试环境

/ipooleth-api/ipooleth-platform-api/src/main/resources/DEV

redis 配置修改(application.properties)

spring.redis.host={机主IP地址}
spring.redis.password={密码}
mongodb 配置修改(application.properties)

spring.data.mongodb.uri=mongodb://{用户}:{官能}@{机主IP地址}:{端口}/ipooleth?AutoConnectRetry=true

2.3.2 生产环境

/ipooleth-api/ipooleth-platform-api/src/main/resources/PD

redis 配置修改(application.properties)

spring.redis.host={机主IP地址}
spring.redis.password={密码}
mongodb 配置修改(application.properties)

spring.data.mongodb.uri=mongodb://{用户}:{官能}@{机主IP地址}:{端口}/ipooleth?AutoConnectRetry=true

2.4 构建编译


编译

mvn -f ./ipooleth-api/ipooleth-platform-api/pom.xml -PPD install

部署包路径

./ipooleth-api/ipooleth-platform-api/target/ipooleth-platform-api_0.0.1.war


2.5 部署上线

将正在运行的tomcat 停止

./tomat8/bin shutdown.sh
重命名包

mv /ipooleth-api/ipooleth-platform-api/target/ipooleth-platform-api_0.0.1.war /ipooleth-api/ipooleth-platform-api/target/ipooleth-platform-api.war
清除上个版本

rm -rf  ./tomat8/webapp/*
将构建后的包部署到tomcat

cp /ipooleth-api/ipooleth-platform-api/target/ipooleth-platform-api_0.0.1.war ./tomat8/webapp/
启动 tomcat

./tomat8/bin startup.sh

2.6 上线检查

http://{IP}:{端口}/ipooleth-platform-api/swagger-ui.html


3 Jenkins配置


安装NodeJS
安装Maven

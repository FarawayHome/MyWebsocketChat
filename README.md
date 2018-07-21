
# 1. 项目背景
该项目是学校通信软件开发实训课程的一门作业，要求完成一个聊天系统，但要求有至少一个亮点，无论是功能上的还是技术上的。 我擅长Java Web开发，因此决定在技术上做一个亮点，使用WebSocket。
# 2. 技术背景
1. wbsocket是HTML5开始提供的一种在单个 TCP 连接上进行全双工通讯的协议。 在WebSocket 
API中，浏览器和服务器只需要做一个握手的动作，然后，浏览器和服务器之间就形成了一条快速通道。两者之间就直接可以数据互相传送。相比传统的ajax通信，真正的实现了服务器给客户端推送消息。 
2. SSM(spring+springmvc+mybatis)略 

# 3. 目录结构
- config 存放websocket的配置程序
- controller 存放websocket 处理程序和主程序处理程序
- resources 存放ssm配置文件
- mapper mybatis逆向生成的文件
- student.sql 数据库文件
- webapp 前端程序
# 4. 运行结果
1. 用户登录
![login](https://github.com/FarawayHome/MyWebsocketChat/blob/master/login.png)
2. 聊天界面
![chat](https://github.com/FarawayHome/MyWebsocketChat/blob/master/chat.png)

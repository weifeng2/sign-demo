demo工程
测试地址 http://localhost:8081/sign-demo/demo.do

1.定时任务每30秒从redis中获取JAVA代码，进行编译后存储在内存中，具体见DemoServlet的init方法
2.在http://localhost:8081/sign-demo/demo.do这个请求中，调用延签调整为从内存中加载class信息后，通过反射
调用指定的方法。


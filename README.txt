打包命令: $ mvn clean package -Dmaven.test.skip=true

common模块：  公共类
app模块:      api和backstage
job模块:      自动任务

#ref: http://blog.csdn.net/catoop/article/details/50588851
通过参数指定一个外部配置文件，以 demo.jar 为例，方法如下：
java -jar demo.jar --spring.config.location=/opt/config/application.properties

notes:
<th:block th:text="${application.static}"/>
<link rel="stylesheet" href="/assets/css/bootstrap.css" th:href="@{${application.static}+'/assets/css/bootstrap.css'}" />
<script th:inline="javascript">
    var apiUrl = /*[[@{/journal}]]*/;	// api URL
</script>
<script th:src="${application.static + '/components/jquery/dist/jquery.js'}"></script>

https://spring.io/guides/topicals/spring-security-architecture/

sha256sum:
123 => a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3

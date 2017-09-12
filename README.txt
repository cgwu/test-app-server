打包命令: $ mvn clean package -Dmaven.test.skip=true
nohup mvn spring-boot:run &

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

<span th:text="${@systemParamService.add(100,22)}"></span>

<!--/**/-->

<link rel="stylesheet" type="text/css" href="DataTables-1.10.15/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="AutoFill-2.2.0/css/autoFill.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="Buttons-1.4.0/css/buttons.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="ColReorder-1.3.3/css/colReorder.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="FixedColumns-3.2.2/css/fixedColumns.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="FixedHeader-3.1.2/css/fixedHeader.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="KeyTable-2.3.0/css/keyTable.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="Responsive-2.1.1/css/responsive.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="RowGroup-1.0.0/css/rowGroup.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="RowReorder-1.2.0/css/rowReorder.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="Scroller-1.4.2/css/scroller.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="Select-1.2.2/css/select.bootstrap.min.css"/>

<script type="text/javascript" src="jQuery-2.2.4/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="JSZip-3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="pdfmake-0.1.27/build/pdfmake.min.js"></script>
<script type="text/javascript" src="pdfmake-0.1.27/build/vfs_fonts.js"></script>
<script type="text/javascript" src="DataTables-1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="DataTables-1.10.15/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="AutoFill-2.2.0/js/dataTables.autoFill.min.js"></script>
<script type="text/javascript" src="AutoFill-2.2.0/js/autoFill.bootstrap.min.js"></script>
<script type="text/javascript" src="Buttons-1.4.0/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="Buttons-1.4.0/js/buttons.bootstrap.min.js"></script>
<script type="text/javascript" src="Buttons-1.4.0/js/buttons.colVis.min.js"></script>
<script type="text/javascript" src="Buttons-1.4.0/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="Buttons-1.4.0/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="Buttons-1.4.0/js/buttons.print.min.js"></script>
<script type="text/javascript" src="ColReorder-1.3.3/js/dataTables.colReorder.min.js"></script>
<script type="text/javascript" src="FixedColumns-3.2.2/js/dataTables.fixedColumns.min.js"></script>
<script type="text/javascript" src="FixedHeader-3.1.2/js/dataTables.fixedHeader.min.js"></script>
<script type="text/javascript" src="KeyTable-2.3.0/js/dataTables.keyTable.min.js"></script>
<script type="text/javascript" src="Responsive-2.1.1/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" src="Responsive-2.1.1/js/responsive.bootstrap.min.js"></script>
<script type="text/javascript" src="RowGroup-1.0.0/js/dataTables.rowGroup.min.js"></script>
<script type="text/javascript" src="RowReorder-1.2.0/js/dataTables.rowReorder.min.js"></script>
<script type="text/javascript" src="Scroller-1.4.2/js/dataTables.scroller.min.js"></script>
<script type="text/javascript" src="Select-1.2.2/js/dataTables.select.min.js"></script>



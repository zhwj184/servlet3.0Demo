servlet3.0Demo
==============

servlet3.0对应的新特性可以参考 http://zhwj184.iteye.com/admin/blogs/1745426

servlet3.0Demo  这个工程是eclipse j2ee 4.2版本下的web工程目录结构：
1.主要有几部分的代码
   可插拔的Web框架 web.xml web-fragment.xml的模块化
2.注解
   filter,contextListener,servlet，listener的示例；
3.异步化示例
4.文件上传示例

fragment2对应的jar包META-INFO下的web-fragment.xml的内容：

<?xml version="1.0" encoding="GB18030"?>
<web-fragment version="3.0" xmlns="http://java.sun.com/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd">

	<name>fragment2</name>
	<!--
	<ordering>
		<after>
			<others />
		</after>
		<before>
			<name>fragment1</name>
		</before>
	</ordering> -->

	<filter>
		<filter-name>myplugginFilter2</filter-name>
		<filter-class>com.filter.pluggin.MyplugginFilter2</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>myplugginFilter2</filter-name>
		<url-pattern>/plugin2/*</url-pattern>
	</filter-mapping>
	
	<servlet>
		<servlet-name>myplugginSerlvet2</servlet-name>
		<servlet-class>com.servlet.pluggin.MyplugginServlet2</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>myplugginSerlvet2</servlet-name>
		<url-pattern>/plugin2/*</url-pattern>
	</servlet-mapping>
</web-fragment>


fragment1对应的jar包META-INFO下的web-fragment.xml的内容：
<?xml version="1.0" encoding="GB18030"?>
<web-fragment version="3.0" xmlns="http://java.sun.com/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd">

	<name>fragment1</name>
	<ordering>
		<after>
			<others />
		</after>
		<before>
			<name>fragment2</name>
		</before>
	</ordering>

	<filter>
		<filter-name>myplugginFilter</filter-name>
		<filter-class>com.filter.pluggin.MyplugginFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>myplugginFilter</filter-name>
		<url-pattern>/plugin/*</url-pattern>
	</filter-mapping>
	
	<servlet>
		<servlet-name>myplugginSerlvet</servlet-name>
		<servlet-class>com.servlet.pluggin.MyplugginSerlvet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>myplugginSerlvet</servlet-name>
		<url-pattern>/plugin/*</url-pattern>
	</servlet-mapping>
</web-fragment>


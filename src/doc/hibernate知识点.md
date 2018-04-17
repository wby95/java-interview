# hibernate

 # hibernate 五个核心接口 对持久化对象的CRUD&事务的控制
 
 |Session|SessionFactory|Transaction|Query|Configuration|
 | :--------   | :--------  | :--------  |  :--------  |  :--------  | 
 |负责执行被持久化对象的CRUD操作（与数据库交互），非线程安全|负责初始化hibernate,充当数据存储源的代理，并负责创建session,一个项目通常只需要一个SessionFactory(多数据库时候，每一个数据库指定一个SessionFactory)|Transaction负责事务的相关的操作|Query&Criteria接口负责执行各种数据库的查询，可以使用HQL,SQL两种表达方式|Configuration接口负责配置并且启动hibernate，创建SessionFactory对象|
 
 # 创建Configuration
 
  Xml文件（hibernate.cfg.xml）
  
  Configuration cfg = new Configuration().configure();

# 构建SessionFactory
  
  
  针对单个数据库映射关系经过编译后的内存镜像，是线程安全的。
   
  SessionFactory 对象一旦构造完毕，即被赋予特定的配置信息
  
  SessionFactory是生成Session的工厂
  
  构造 SessionFactory 很消耗资源，一般情况下一个应用中只初始化一个 SessionFactory 对象。
  
  Hibernate4 新增了一个 ServiceRegistry 接口，所有基于 Hibernate 的配置或者服务都必须统一向这个 ServiceRegistry  注册后才能生效
  
 
   |  Hibernate4 中创建 SessionFactory 的步骤  |
   | :--------   |
   | Configuration configuration = new Configuration().configure();  //创建configuration 对象|
   | ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); //创建 serviceRegistry对象|
   | SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry)|

 
 # SessionFactory负责初始化hibernate,充当数据存储源的代理,整合可以这样写
 
 ```
 	<!-- 配置 C3P0 数据源 -->
 	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
 		<property name="user" value="${jdbc.user}"></property>
 		<property name="password" value="${jdbc.password}"></property>
 		<property name="driverClass" value="${jdbc.driverClass}"></property>
 		<property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
 	</bean>
 	
 	<!-- 配置 SessionFactory -->
 	<bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
 		<property name="dataSource" ref="dataSource"></property>
 
 		<property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
 
 		<property name="mappingResources">
 			<list>
 				<value>com/wby/user/entity/User.hbm.xml</value>
 				<value>com/wby/brand/entity/Brand.hbm.xml</value>
 				<value>com/wby/phone/entity/Phone.hbm.xml</value>
 				<value>	com/wby/phonesize/entity/Phonesize.hbm.xml</value>
 
 			</list>
 		</property>
 	</bean>
 
 ```
 
 # session
 
 通常来说，每一个session实例和一个数据库事务绑定，也就是每一次执行一个数据库事务，都应该创建一个session,session共用一个SessionFactory.
 
 1.Session session = sessionFactory.openSession();
 
 2.创建好session后，通过对session对对象进行持久化操作。
   
 3.session.close()
  
   - ##使用Session操作对象
   
   |  对象的几种状态  |
   | :--------      |
   |临时状态  不处于session缓存中，在数据库中也没有对应的记录|
   |持久化 处于session缓存中，若在数据库中已经有和其对应的记录, 持久化对象和数据库中的相关记录对应|
   |游离状态 不再处于 Session 缓存中，OID 不为 null，虽然有对应的数据库记录但是他已经无法执行数据库相关操作|
   |删除状态 不再处于 Session 缓存中，在数据库中没有和其 OID 对应的记录 |
   
   ![对象的几种状态.JPG](对象的几种状态.JPG)
   
  
# 加载一个对象到内存 get() & load()

   | get()    | load()|
   | :--------   | :--------      |
   |get()会立即加载对象，立即检索|load()若不使用对象，不会立即加载，而是返回一个代理对象，延迟检索|
   |若数据表中没有对应的记录，get()返回null|若数据表中没有对应的记录,load()返回异常|
   ||load()会抛出懒加载异常，当提前关闭session[在需要初始化代理类对象之前关闭session]|
 
 
# 保存对象
   
   | save()    | persist()|
   | :--------   | :--------      |
   |使临时对象变为持久化对象||
   |为对象分配ID||
   |在flush缓存的时候，会发生insert语句|也会执行insert语句|
   |在save方法之前保存的id是无效的|在persist前有id会抛出异常|
   |持久化对象的id不能被更改||
   
# update
   
   若更新一个持久化对象，无需显示调用session.update(),因为在调用Transaction 的commit()之前会执行session的flush()方法。
   
   update 如果涉及到触发器的问题时候，常常需要设置:class 节点设置 select-before-update="true"
   
   当一个对象为游离对象的时候，需要显示调用更新  游离状态：提前关闭了session ，这时候，数据库有对应记录，但是不存在session中。像一个人不在公司，出差去了，这时候不在受公司的约束（session的管理），但是仍然是公司的一员。
   
   无论要更新的游离对象和数据库中的记录是否一样这时候都会发送update()，因为这时候并不知道数据库的记录是怎么样的。
   
   
# 日期属性的配置
   
   java   java.util.Date & java.util.calendar
   
   - java.util.Date(父类)
     - ------->  (子类) java.sql.Date  Date[yyyy-MM-dd]
     - -------> (子类)  java.sql.Time TIME[hh:mm:ss]
     - -------> (子类)  java.sql.Time TIMESTAMP[yyyy-MM-dd hh:mm:ss]
   
   建议将持久化类的属性设置为java.util.Date，因为java.util.Date是三个java.sql.时间 的父类，他可以兼容sql中的三种方式
   
  
  
# hinernate 实体关系的映射关系

   ## 双向多对一  .xml
   
   <many-to-one  name="多关联一那一端的属性名" class="一那一端的全类名" column="一那一端在多的一端对应数据表的外键" />
   
   
   <set name="属性值" table="告诉是关联的那一张表">
     <key column="那张表关联外键的名字">
     <one-to-many class="全类名"   />
   </set>
                   
   一般来说，一的一端要放弃维护 inverse="true"
   
   
   ## 双向多对一   注解
   
   @manyToOne
   @joinColumn(name="外键名")  
   
   
    
  
   
   
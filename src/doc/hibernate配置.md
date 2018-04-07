# hibernate的常用配置使用

# 实体类上的注解

@Entity 注解将一个类声明为一个实体bean。

@Table(name="表名")

# 主属性

@Id

@GeneratedValue(generator="_native") 用于定义主键生成策略

@GenericGenerator(name="_native",strategy="native")

# 主属性的另一种写法 

@Id    必须，定义了映射到数据库表的主键的属性，一个实体只能有一个属性被映射为主  键，置于 getXxxx() 前。

@GeneratedValue(strategy=GenerationType,generator="") - 可选，用于定义主键生成策略。

属性：

Strategy - 表示主键生成策略，取值有：

GenerationType.AUTO - 根据底层数据库自动选择（默认），若数据库支持自动增长类型，则为自动增长。

GenerationType.INDENTITY - 根据数据库的Identity字段生成，支持DB2、MySQL、MS、SQL Server、SyBase与HyperanoicSQL数据库的Identity类型主键。

GenerationType.SEQUENCE - 使用Sequence来决定主键的取值，适合Oracle、DB2等支持Sequence的数据库，一般结合@SequenceGenerator使用。

# 非主属性

@Column(length=50)

@Lob
@Column(columnDefinition="TEXT") 可选，表示该字段在数据库中的实际类型。通常ORM框架可以根    据属性类型自动判断数据库中字段的类型，但是对于Date类型仍无法确定数据      库中字段类型究竟是 DATE,TIME还是 TIMESTAMP. 此外 ,String 的默认映射类型为VARCHAR, 如果要将 String 类型映射到特定数据库的 BLOB或 TEXT字段类型，该属性非常有用。

# 关系配置

@OneToOne、@OneToMany、@ManyToOne、ManyToMany的共有属性：

fetch - 配置加载方式。取值有

Fetch.EAGER -  及时加载，多对一默认是Fetch.EAGER 

Fetch.LAZY - 延迟加载，一对多默认是Fetch.LAZY

cascade - 设置级联方式，取值有：

CascadeType.PERSIST - 保存

CascadeType.REMOVE - 删除

CascadeType.MERGE - 修改

CascadeType.REFRESH - 刷新

CascadeType.ALL - 全部

targetEntity - 配置集合属性类型，如：@OneToMany(targetEntity=Book.class)

 

@JoinColumn - 可选，用于描述一个关联的字段。

@JoinColumn和@Column类似，介量描述的不是一个简单字段，而是一个关联字段，例如描述一个 @ManyToOne 的字段。

 

属性：

name - 该字段的名称，由于@JoinColumn描述的是一个关联字段，如ManyToOne, 则默认的名称由其关联的实体决定。

例如，实体 Order 有一个user 属性来关联实体 User, 则 Order 的 user 属性为一个外键 ,

其默认的名称为实体User的名称 + 下划线 + 实体User的主键名称

 ## 用户表&订单
 
用户表：
 
@OneToMany(targetEntity=Order.class,cascade=CascadeType.ALL)

@JoinColumn(name="userId",updatable=false)

订单：

@ManyToOne
@JoinColumn(name="userId",updatable=false)



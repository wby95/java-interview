# hibernate的配置使用

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

GenerationType.INDENTITY - 根据数据库的Identity字段生成，支持DB2、MySQL、MS、SQL Server、SyBase与HyperanoicSQL数据库的Identity                                          类型主键。

GenerationType.SEQUENCE - 使用Sequence来决定主键的取值，适合Oracle、DB2等支持Sequence的数据库，一般结合@SequenceGenerator使用。
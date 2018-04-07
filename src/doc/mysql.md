# mysql
- ### sql执行的顺序
     1. from子句组装来自不同数据源的数据
     2. where子句基于指定的条件对记录进行筛选
     3. group by子句将数据划分为多个分组
     4. 使用聚集函数进行计算
     5. 使用having子句筛选分组
     6. select计算所有的表达式
     7. 使用order by对结果进行排序

- ### 单表查询
 
   #### 查询所有字段
      select * from 表名;
   #### 查询指定字段
      select 字段名 from 表名;
   #### 查询指定数据
      select * from 表名 where 字段='';
   #### 带in关键字的查询
      selct * from 表名 where 字段 in('','',...);
   #### 带between and的范围查询
      select * from 表名 where 字段[not] between 取值 and 取值
   #### 带like的字符匹配查询
      select * from 表名 where 字段 like '%取值%';
   #### 带_的字段匹配查询,m为开头，n为结尾的三个字段
      select * from 表名 where 字段 like 'm_n';
   #### 用is null关键字查询空值
      select * from 表名 where 字段 is null;
   #### 带and的多条件查询
      select * from 表名 where 条件1 and 条件2 and [...];
   #### 带or的多查询条件查询 
      select * from 表名 where 条件1 or 条件2 or [...];
   #### 带distinct关键字去除结果中重复的行
      select distinct 字段名 from 表名
   #### 用order by关键字对结果的查询
      select * from 表名 where 字段 order by 字段[asc|desc];
   #### 用group by关键字分组查询
      select * from 表名 group by 字段   ：将数据划分到不同的组中，实现对记录的分组查询，查询结果只是显示每组的第一条记录。
   #### 用group by&group_concat()函数一起上使用 关键字分组查询
      select group_concat(字段) from 表名 group by 字段    ：group_concat(字段) 可以将分组中的所有字段值都显示出来。
   #### 用limit限制查询结果的数量关键字分组查询
      select * from 表名  order by 字段 limit 3
      select * from 表名  order by 字段 limit 1,2  :参数1是开始读取的第一条记录(从0开始)，参数2是查询的记录数；


 - ### 聚合函数查询
 
    #### count()
       select count(*) from 表名  ：count(*) 返回的是选择集合中的总函数；除*以外的任何参数，返回所选择集合中非null值的行数；   
    #### sum()
       select sum(字段) from 表名；
    #### avg()
       select avg(字段) from 表名；
    #### max()
       select max(字段) from 表名；
    #### min()
       select min(字段) from 表名；

      
 - ### 连接查询
    #### 内连接查询
      - 内连接也叫连接，是最早的一种连接。还可以被称为普通连接或者自然连接，内连接是从结果表中删除与其他被连接表中没有匹配行的所有行，所以内连接可能会丢失信息。
    #### 外连接查询
      - 左外连接(select 字段名 from 表1 left join 表2 on 表1.属性=表2属性)，左外连接是指左表中的所有数据分别与右表中的每一条数据进行连接组合，返回的结果除了内连接的数据以外，还包括一些不符合条件的数据，并在右表的相应的列中添加null值。
      - 右外连接(select 字段名 from 表1 right join 表2 on 表1.属性=表2属性)
   
   
 - ### 子查询
 
    #### 带[not] in的子查询
       select * from 表1 where 字段名 in(select 字段名 from 表2 where 字段名)  ：子查询的返回结果集是值的列表，比较的运算符就必须用in运算符代替。in运算符可以检查结果集是否存在某个特定的值，如果检测成功，就执行外部的查询。
    #### 带比较运算符的子查询  
       select 字段 from 表1 where 字段 >=( select 字段 from 表1 where 字段=取值 )
    #### 带[not] exists的子查询
       select * from 表1 where exists(select 字段名 from 表2 where 字段=取值 )   :使用exists关键字时，内部查询语句不返回查询的记录，而是返回一个真价值。如果内部查询语句查询到满足条件的记录，就返回一个true，否则就返回false,当返回true,外部查询语句进行查询，当返回值是false，外部的查询语句不再进行查询或者查询不出任何记录。
    #### 带 any 的子查询   
       select 字段 from 表1 where 字段<any( select 字段 from 表1 )       <any:表示小于所有值&>=all：表示大于等于所有值 ，any关键字表示满足其中的任意一个条件，使用any关键字时，只要满足内层的查询语句返回的结果中的任何一个，就可以通过该条件来执行外层查询语句。
    #### 带 all 的子查询    
       all 关键字需要满足内查询语句返回的所有结果，才可以执行外层查询语句
    #### 带 union [all]的子查询   
       select 字段一 from 表1
       union
       select 字段一 from 表2  :合并后将所有结果都合并到了一起，并且除了重复值 union all没去重
  
 - ### 取别名&赋值
       as & into
 
 - ### 使用正则表达式
       select * from 表明 where 字段名 regexp 'a*c';  :c字母之前可能出现0或者更多的字母a
     
     |模式字符|含义|
     | :------ | :------| 
     |^|匹配以特定字符或者字符串开头的记录|
     |$|匹配以特定字符或者字符串结尾的记录|
     |.|匹配字符串的任意一个字符|
     |[]|匹配字符集合|
     |^|[^c-z]包含了c-z字符以外的记录|
     |*|匹配0+字符|
     |+|至少出现过一个|
     |字符串{}|a{3}字段连续出现3次a字符的记录|
     |字符串{m,n}|匹配字符串出现至少m次，最多n次|
     

 - ### 数据库&数据表的操作
 
     #### 创建数据库
        create database 数据库名字;
     #### 查看数据库
        show databases;
     #### 选择数据库
        use 数据库名字；
     #### 删除数据库
        drop database 数据库名字;
     #### 创建数据表
        create [temporary] table [if not exists] 数据表名(列名1 属性,列名2 属性...);
     #### 查看数据表
        describe 数据表名 [列名]; 
        show columns from 数据表名
     #### 修改表结构
        alter table 数据表名 add 字段名 属性
                            modify 字段名 属性
                            change column 字段名 新字段名 属性;
      ### 重命名表
        rename table 数据表名1 to 数据库表名2;  
      #### 删除数据表
        drop table 数据表名;
        
 - ### 索引（类比于书的目录）
    
    1. 索引是一种将数据库中单列或者多列的值进行排序的结构，通过索引不但可以提高查询速度，还可以降低服务器的负载。
    2. 但是创建索引和维护需要消费时间，并且该消费与数据量大小成正比；另外索引需要占用物理空间，给数据的维护造成
    很多麻烦。
    3. 整体来说，索引可以提高查询速度，但是会影响用户操作数据库的插入操作，因为向有索引的表中插入记录
    时，数据库系统会按照索引进行排序，所以用户可以删除索引后，插入数据，当数据的插入操作完成后，可以
    重新创建索引。
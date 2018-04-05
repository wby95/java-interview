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
 
   ####查询所有字段
      select * from 表名;
   ####查询指定字段
      select 字段名 from 表名;
   ####查询指定数据
      select * from 表名 where 字段='';
   ####带in关键字的查询
      selct * from 表名 where 字段 in('','',...);
   ####带between and的范围查询
      select * from 表名 where 字段[not] between 取值 and 取值
   ####带like的字符匹配查询
      select * from 表名 where 字段 like '%取值%';
   ####带_的字段匹配查询,m为开头，n为结尾的三个字段
      select * from 表名 where 字段 like 'm_n';
   ####用is null关键字查询空值
      select * from 表名 where 字段 is null;
   ####带and的多条件查询
      select * from 表名 where 条件1 and 条件2 and [...];
   ####带or的多查询条件查询 
      select * from 表名 where 条件1 or 条件2 or [...];
   ####带distinct关键字去除结果中重复的行
      select distinct 字段名 from 表名
   ####用order by关键字对结果的查询
      select * from 表名 where 字段 order by 字段[asc|desc];
   
    
      
      
  
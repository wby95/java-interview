# 数据库 
  - MySql的逻辑架构
  - CRUD
  - 三大范式
  - ACID
    - 原子性(atomicity): 一个事物必须被视为一个不可分割的最小工作单元，整个事物中的操作要么全部提交成功，要么全部失败回滚，对于一个事物来说，不可能只执行其中的一部分操作，这就是的原子性。
    -  一致性(consistency): 数据库总是从一个一致性的状态转到另一个事务的一致性状态。
        
        |由于事物的一致性，所以如果这时候执行完第三系统突然奔溃，支票账户也不会损失100，因为事物还没提交|
         | :-----   | 
         |1 start transaction  ;|
         | 2 select * from checking where id=1;|
         | 3 update checking set balance=balance-100 where id=1;|
         |  4 update savings set balance=balance+100 where id=1;|
         |  5 commit;|
      
     - 隔离性(isolation):一个事物所做的修改在最终提交前，对其他事物是不可见的。在前面的例子中，如果执行到第3，此时有另一个账户汇款，则其看见的支票账户得余额并没有被减去100。
     
     - 持久性(durability): 一旦事物提交，则其所做的修改就会永远保存在数据库中。
  - 事物的隔离级
    - read uncommitted(未提交读)：事物中的修改，即使没有提交，对其他事物也是可见的。事物可以读取未提交的数据(在实际的开发中一般很少使用)
    - read committed(提交读)：一个事物的开始直到提交之前，所做的任何修改对其他事物都是不可见的。这个级别也叫做不可重复读，因为两次的执行同样的查询，可能会得到不一样的结果。
    - repeatable read(可重复读)：该级别保证了同一个事物中，多次读取同样记录的结果是一致的。但理论上还是没有解决幻读。
    - serializable可串行化：是最高的隔离级别，它通过强制事物串行执行，serializable会在每一行的数据加锁，所以可能导致大量的超时和锁争用的问题。(实际开发少用)
   - ANSI SQL隔离级别
   
      |  隔离级别       | 脏读可能性    |  不可重复读  | 幻读可能性    |  加锁读  |
      | :--------  :| :-----:   | :----: | :-----:   | :----: |
      |  读未提交        | Yes      |   Yes    |Yes|No|
      |  读已提交        | No      |   Yes    |Yes|No|
      |  可重复读       | No      |   No   |Yes|No|
      | 可串行化|No|No|No|Yes|
       - 脏读：读取了未提交的数据
       - 不可重复读：前后读取数据不一致
       - 幻读：指的是在某个事物在读取某个范围记录时，另一个事务又在该范围内插入了新的记录，当之前的事物再次读取该范围的记录时，会产生幻行。
       InnoDB和XtraDB存储引擎通过多版本并发控制(MVCC)解决了幻读的问题。 T1 : select * from user where id=1;
                                                                  T2 :  insert into user('id','username')values(1,'wby');
                                                                  T1:  insert into user('id','username')values(1,'wby');这时候T1在插入的时候就会出现错误，因为记录已经存在
                                                                 
                                                                  
       
 - CAP
  
 
# springboot知识总结

- Spring Data JPA(Java Persistence API)

```
1 使用O/R映射 实现数据访问  O/R即将领域模型类和数据库的表进行映射，通过操作操作对象而实现操作数据库的能力

2  JPA O/R映射的标准规范，所谓的规范即只定义标准规则（如注解，接口），不提供好实现，提供商可以按照标准规范
来实现，而使用者只需要按照规范中的定义来使用方法即可。

```

- 定义数据访问层

```
public interface PersonRepository extends JpaRepository<Person,Long>{
   
   //定义数据访问操作的方法

}

```

- 定义查询方法

   - 常规查询
  
```

public interface PersonRepository extends JpaRepository<Person,Long>{
   
   //定义数据访问操作的方法
   
   /**
   *   1. findBy 等价于 fing / read / readBy / query/ queryBy / get / getBy
   *
   **/
   
   List<Person> findByName(String name)   //select p from Person p where p.name=?1
   
   List<Person> findByNameLike（String name） // select p from Person p where p.name like ?1
   
   List<Person> findByNameAndAddress（String name,String address）select p from Person p where p.name=?1 and p.address=?2
}



```
    
    
   - 限制结果数的查询
    
 ```

public interface PersonRepository extends JpaRepository<Person,Long>{
   
   //定义数据访问操作的方法
   
   /**
   *    获得符合结果的前10条记录
   *
   **/
   List<Person> findFirst10ByName(String name) 
   
   /**
   *    获得符合结果的前30条记录
   *
   **/
   List<Person> findTop30ByName(String name)
   
   
   
 }



```

   - 使用JPA的@NamedQuery查询(采用了我们自定义的查询)
   
```
   @Entity
   @NamedQuery(name="Person.findByName",
      query="select p from Person p where p.name=?1")
   public class Person{
   
   }
   
   public interface PersonRepository extends JpaRepository<Person,Long>{
      
       List<Person> findByName(String name) 
      
   }
   


```

   - @Query查询
   
 ```
 
 public interface PersonRepository extends JpaRepository<Person,Long>{
        @Query("elect p from Person p where p.name=?1")
        List<Person> findByName(String name) 
       
    }





```
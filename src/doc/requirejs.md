# RequireJs 基础

* [一、RequiredJs 简介](#RequiredJs简介)
* [二、RequiredJs 方法](#RequiredJs方法)  
* [三、baseUrl&data-main区别](#baseUrl&data-main区别) 

 ##### RequireJs简介

   * 异步加载文件，模块化开发：一个文件就是一个模块。
  
 ##### RequireJs方法
   * define:定义模块
   
      ![模块定义.PNG](模块定义.PNG)
   * require：加载模块 
   
      ![RequiredJs加载模块.PNG](RequiredJs加载模块.PNG)
   * RequireJs使用了head.appendChild()将每一个依赖加载为一个script标签。
   
 ##### baseUrl&data-main区别  
   * 原始写法
   
   ![原始写法.PNG](原始写法.PNG)
   * data-main写法
   
   ![data-main写法.PNG](data-main写法.PNG)
   * config方式
   
   ![config方式.PNG](config方式.PNG)
     
 
 
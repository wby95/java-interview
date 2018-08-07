# Angular

- http://www.runoob.com/angularjs/angularjs-tutorial.html

-  常用命令

  |命令|解释|
  | :--------  | :--------  |
  |ng-app=" "|定义angularJS的使用范围|
  |ng-init="变量=值;变量='值'"|2、ng-init="变量=值;变量='值'"  初始化变量的值，有多个变量时，中间用分号隔开|
  |ng-model="变量"|义变量名|
  |ng-bind="变量"|绑定变量名，获取该变量的据这里的变量就是第3条的变量名。但是一般都用双重花括号来获取变量的值，比如：{{变量}}|

- Scope & Event

  |Scope|Event|
  | :--------  | :--------  |
  |Scope 是Angular的内置对象，$Scope来获得。在Scope中定义的数据是数据模型，可以在{{数据模型}}，在视图上获得|所有不同的Scope之间要相互交互的话需要通过事件|
  |作用范围是：页面声明ng-controller标签元素的作用范围|
  
-   模块&路由定义

```

var actionApp=angular.module('actionApp',['ngRoute']); //定义模块，并且依赖路由模块 ngRoute
actionApp.config(['$routeProvider',function($routeProvider){
  $routeProvider.when('/open',{
       controller:'View1Controller',
       templateUrl:'views/view1.html'
  }).when....


}]);


actionApp.controller('View1Controller',['$rootScope','$scope','$http',
    function($rootScope,$scope,$http){
       $scope.seach=functon(){
           personName=$scope.personName;
           $http.get('search',{
               params:{personName:personName}
          }).success(functon(data)
              $scope.person=data;
          });
       
       };
  
}]);





```
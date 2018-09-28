# XHR 简介

 > XmlHttpRequest对象发出HTTP请求，得到服务端返回的数据再处理，它为服务请求&
 解析服务端响应提供了流畅的接口
 
 ```
   var xhr;
   if(window.xmlHttpRequest){
       xhr=new XmlHttpRequest();
   }else{
       xhr=new ActiveObject('Microsoft.xmlHTTP');
   }
   
   xhr.open(GET&POST,url,同步&异步)
   
   xhr.send(null);
   
   if(xhr.readyState==4){
      if(xhr.status==200){
          result.innerHTML+=xhr.responseText;
      }
   }
 
 ```
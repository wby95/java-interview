# json 基础

 ### 概念
   - ##### SONObject的数据是用 {  } 来表示的，son对象，就是一个键对应一个值，使用的是大括号{ }，如：{key:value}，而JSONArray，顾名思义是由JSONObject构成的数组，用  [ { } , { } , ......  , { } ]  来表示
 
   - #####  Json对象中添加的是键值对，JSONArray中添加的是Json对象
   
   - #####  首先把字符串转成 JSONArray对象  JSONArray json = JSONArray.fromObject(str );
   
   - #####  在使用json-lib包中JSONArray rows=JSONArray.fromObject(userList, jsonConfig)时，可能出现以下几种情况：     
   - 1（字段过滤）如果需要在转化过程中去除某些字段，则需要定义一些Excludes字段，具体使用如下
   ```
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<User> userList=userService.findUserList(s_user, pageBean);
		long total=userService.getUserCount(s_user);
		JsonConfig jsonConfig=new JsonConfig();
		
	   //（字段过滤）如果需要在转化过程中去除某些字段，则需要定义一些Excludes字段，具体使用如下
		jsonConfig.setExcludes(new String[]{"orderList"});
		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows=JSONArray.fromObject(userList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
   ```
   - 2 （Date类型转化）JavaBean出现Date格式时，转化成json时会出现将其转化为：{"date":5,"day":4,"hours":16,"minutes":26,"month":4,"seconds":31,"time":1452086791290,"timezoneOffset":-480,"year":116}，这个不易处理，如果需要将Date转换为我们认识的“yyyy-MM-dd”格式，则需要自行创建时间转换器，并实现json-lib中的JsonValueProcessor接口，实现该接口中的两个方法（processArrayValue和processObjectValue）：
   ```
    	public String list()throws Exception{
    		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
    		List<User> userList=userService.findUserList(s_user, pageBean);
    		long total=userService.getUserCount(s_user);
    		JsonConfig jsonConfig=new JsonConfig();
    		jsonConfig.setExcludes(new String[]{"orderList"});
    		
    		//JavaBean出现Date格式时
    		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
    		
    		JSONArray rows=JSONArray.fromObject(userList, jsonConfig);
    		JSONObject result=new JSONObject();
    		result.put("rows", rows);
    		result.put("total", total);
    		ResponseUtil.write(ServletActionContext.getResponse(), result);
    		return null;
    	}
    	
    	
    	
    	import java.text.SimpleDateFormat;
        
        import net.sf.json.JsonConfig;
        import net.sf.json.processors.JsonValueProcessor;
        
        /**
         * json-lib 日期处理类
         * @author Administrator
         *
         */
        public class DateJsonValueProcessor implements JsonValueProcessor{
        
        	private String format;  
        	
            public DateJsonValueProcessor(String format){  
                this.format = format;  
            }  
            
        	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        		// TODO Auto-generated method stub
        		return null;
        	}
        
        	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        		if(value == null)  
                {  
                    return "";  
                }  
                if(value instanceof java.sql.Timestamp)  
                {  
                    String str = new SimpleDateFormat(format).format((java.sql.Timestamp)value);  
                    return str;  
                }  
                if (value instanceof java.util.Date)  
                {  
                    String str = new SimpleDateFormat(format).format((java.util.Date) value);  
                    return str;  
                }  
                  
                return value.toString(); 
        	}
        
        }
   ```  
   
   - 3（防止自包含）转换的对象包含自身对象，或者对象A下面挂了对象B，对象B下面又挂了对象A，如果不设置取消环形结构，则那么会抛异常："There is a cycle in the hierarchy!"
   解决方法1：
 ```
   　　在调用JSONObject.fromObject(bean,cfg)时，自定义JsonConfig：
    JsonConfig cfg = new JsonConfig();
    cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    然后将cfg对象传入fromObject方法中，这样，对象B下面挂的对象A就会被置为NULL。
   ```
   解决方法2：
   ```
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Product> productList=productService.findProductList(s_product, pageBean);
		long total=productService.getProductCount(s_product);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		
		
		jsonConfig.registerJsonValueProcessor(ProductBigType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductSmallType.class));
		
		
		JSONArray rows=JSONArray.fromObject(productList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	
	
	import java.beans.PropertyDescriptor;
    import java.lang.reflect.Method;
    
    import net.sf.json.JSONObject;
    import net.sf.json.JsonConfig;
    import net.sf.json.processors.JsonValueProcessor;
    
    /**
     * 解决对象级联问题
     * @author Administrator
     *
     */
    public class ObjectJsonValueProcessor implements JsonValueProcessor{
    
    	/**
    	 * 保留的字段
    	 */
    	private String[] properties;
    
    	/**
    	 * 处理类型
    	 */
    	private Class<?> clazz;
    
    	/**
    	 * 构造方法 
    	 * @param properties
    	 * @param clazz
    	 */
    	public ObjectJsonValueProcessor(String[] properties,Class<?> clazz){
    		this.properties = properties;
    		this.clazz =clazz;
    	}
    
    	public Object processArrayValue(Object arg0, JsonConfig arg1) {
    		// TODO Auto-generated method stub
    		return null;
    	}
    
    	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
    		PropertyDescriptor pd = null;
    		Method method = null;
    		StringBuffer json = new StringBuffer("{");
    		try{
    			for(int i=0;i<properties.length;i++){
    				pd = new PropertyDescriptor(properties[i], clazz);
    				method = pd.getReadMethod();
    				String v = String.valueOf(method.invoke(value));
    				json.append("'"+properties[i]+"':'"+v+"'");
    				json.append(i != properties.length-1?",":"");
    			}
    			json.append("}");
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    		return JSONObject.fromObject(json.toString());
    	}
    
    }

```


 ### easyUI这样获取Json的内嵌数据   
  https://blog.csdn.net/u014049880/article/details/54897006
 
 ```
 <%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
 <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
 <script type="text/javascript">
 
 	function searchProduct(){
 		$("#dg").datagrid('load',{
 			"s_product.name":$("#s_productName").val()
 		});
 	}
 	
 	function formatProPic(val,row){
 		return "<img width=100 height=100 src='${pageContext.request.contextPath}/"+val+"'>";
 	}
 
 	function formatSmallTypeId(val,row){
 		return row.smallType.id;
 	}
 	
 	function formatSmallTypeName(val,row){
 		return row.smallType.name;
 	}
 	
 	function formatBigTypeId(val,row){
 		return row.bigType.id;
 	}
 	
 	function formatBigTypeName(val,row){
 		return row.bigType.name;
 	}
 	
 	function formatHot(val,row){
 		if(val==1){
 			return "是";
 		}else{
 			return "否";
 		}
 	}
 	
 	function formatSpecialPrice(val,row){
 		if(val==1){
 			return "是";
 		}else{
 			return "否";
 		}
 	}
 	
 </script>
 </head>
 <body style="margin:1px;">
 	<table id="dg" title="商品管理" class="easyui-datagrid"
 	 fitColumns="true" pagination="true" rownumbers="true"
 	 url="product_list.action" fit="true" toolbar="#tb">
 	 <thead>
 	 	<tr>
 	 		<th field="cb" checkbox="true" align="center"></th>
 	 		<th field="id" width="50" align="center">编号</th>
 	 		<th field="proPic" width="150" align="center" formatter="formatProPic">商品图片</th>
 	 		<th field="name" width="150" align="center">商品名称</th>
 	 		<th field="price" width="50" align="center">价格</th>
 	 		<th field="stock" width="50" align="center">库存</th>
 	 		<th field="smallType.id" width="100" align="center" formatter="formatSmallTypeId" hidden="true">所属商品小类id</th>
 	 		<th field="smallType.name" width="100" align="center" formatter="formatSmallTypeName">所属商品小类</th>
 	 		<th field="bigType.id" width="100" align="center" formatter="formatBigTypeId" hidden="true">所属商品大类id</th>
 	 		<th field="bigType.name" width="100" align="center" formatter="formatBigTypeName">所属商品大类</th>
 	 		<th field="hot" width="50" align="center" formatter="formatHot">是否热卖</th>
 	 		<th field="specialPrice" width="50" align="center" formatter="formatSpecialPrice">是否特价</th>
 	 		<th field="description" width="50" align="center" hidden="true">描述</th>
 	 		<th field="hotTime" width="50" align="center" hidden="true">设置热卖时间</th>
 	 		<th field="specialPriceTime" width="50" align="center" hidden="true">设置特价时间</th>
 	 	</tr>
 	 </thead>
 	</table>
 	
 	<div id="tb">
 		<div>
 			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 			<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-hot" plain="true">设置为热卖</a>
 			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-special" plain="true">设置为特价</a>
 		</div>
 		<div>
 			&nbsp;商品名称：&nbsp;<input type="text" id="s_productName" size="20" onkeydown="if(event.keyCode==13) searchProduct()"/>
 			<a href="javascript:searchProduct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 		</div>
 	</div>
 </body>
 </html>
 
 ```
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
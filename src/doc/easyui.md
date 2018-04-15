# easyui
  
  ### ComboBox（下拉列表框）  当上一个下拉列框选择后，下一个下拉列框显示对应的信息
  ```
  <tr>
  	 <td>所属大类：</td>
  	 <td colspan="4">
  	 //valueField:基础数据值名称绑定到该下拉列表框。
  	 //textField::基础数据字段名称绑定到该下拉列表框。
  	 //通过URL加载远程列表数据。
  	 	<input class="easyui-combobox" id="bName" name="product.bigType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productBigType_comboList.action'"/>
  	 </td>
  </tr>
  <tr>
  	 <td>所属小类：</td>
  	 <td colspan="4">
  	 	<input class="easyui-combobox" id="sName" name="product.smallType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productSmallType_comboList.action'"/>
  	 </td>
  </tr>
  
  
  $(function(){
  		$("#bName").combobox({
  		//在用户选择列表项的时候触发。
  			onSelect:function(record){
  				$("#sName").combobox("reload","productSmallType_comboList.action?s_productSmallType.bigType.id="+record.id);
  				$("#sName").combobox("setValue","");
  			}
  		});
  	});
  	
  	
  	import java.util.List;
    
    import javax.annotation.Resource;
    
    import net.sf.json.JSONArray;
    import net.sf.json.JSONObject;
    import net.sf.json.JsonConfig;
    
    import org.apache.struts2.ServletActionContext;
    import org.springframework.stereotype.Controller;
    
    import com.java1234.entity.ProductBigType;
    import com.java1234.service.ProductBigTypeService;
    import com.java1234.util.ResponseUtil;
    import com.opensymphony.xwork2.ActionSupport;
    
    @Controller
    public class ProductBigTypeAction extends ActionSupport{
    
    	/**
    	 * 
    	 */
    	private static final long serialVersionUID = 1L;
    	
    	@Resource
    	private ProductBigTypeService productBigTypeService;
    	
    	public String comboList()throws Exception{
    		JSONArray jsonArray=new JSONArray();
    		JSONObject jsonObject=new JSONObject();
    		jsonObject.put("id", "");
    		jsonObject.put("name", "请选择...");
    		jsonArray.add(jsonObject);
    		List<ProductBigType> productBigTypeList=productBigTypeService.findAllBigTypeList();
    		JsonConfig jsonConfig=new JsonConfig();
    		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
    		JSONArray rows=JSONArray.fromObject(productBigTypeList, jsonConfig);
    		jsonArray.addAll(rows);
    		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
    		return null;
    	}
    
    }

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.java1234.entity.ProductSmallType;
import com.java1234.service.ProductSmallTypeService;
import com.java1234.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductSmallTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductSmallType s_productSmallType;

	public ProductSmallType getS_productSmallType() {
		return s_productSmallType;
	}

	public void setS_productSmallType(ProductSmallType s_productSmallType) {
		this.s_productSmallType = s_productSmallType;
	}

	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	public String comboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<ProductSmallType> productSmallTypeList=productSmallTypeService.findProductSmallTypeList(s_productSmallType);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"bigType","productList"});
		JSONArray rows=JSONArray.fromObject(productSmallTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}

}

  	 
  
  ```
  ### easyui主页面一般的布局
  
  ```
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">



// 添加一个未选中状态的选项卡面板
$('#tt').tabs('add',{
	title: '新选项卡面板',
	selected: false
	//...
});




	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
	<table style="padding: 5px" width="100%">
		<tr>
			<td width="50%">
				<img src="${pageContext.request.contextPath}/images/bglogo.png"/>
			</td>
			<td valign="bottom" align="right" width="50%">
				<font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${currentUser.userName }</font>
			</td>
		</tr>
	</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用易买网后台管理系统</font></div>
		</div>
	</div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="用户管理" data-options="selected:true,iconCls:'icon-user'" style="padding: 10px">
			<a href="javascript:openTab('用户管理','userManage.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理用户</a>
		</div>
		
		<div title="商品管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
			<a href="javascript:openTab('商品管理','productManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品大类</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品小类</a>
		</div>
		<div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理订单</a>
		</div>
		<div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理留言</a>
		</div>
		<div title="公告管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理公告</a>
		</div>
		<div title="新闻管理"  data-options="iconCls:'icon-news'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理新闻</a>
		</div>
		<div title="标签管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理标签</a>
		</div>
		<div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px">
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			<a href="" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
		</div>
	</div>
</div>
<div region="south" style="height: 25px;padding: 5px;" align="center">

</div>
</body>
</html>



```

### CRUD
```

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    
    
    //模糊查询
	var url;
	function searchUser(){
		$("#dg").datagrid('load',{
		//使用一些参数查询数据。
			"s_user.userName":$("#s_userName").val()
		});
	}
	//删除
	function deleteUser(){
	    //返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		//存放所有行的id在一个数组
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		//数组间的id用逗号隔开
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("user_deleteUser.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
					//"json" 使得result为一个object对象
				},"json");
			}
		});
		
	}
	
	//添加
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		url="user_saveUser.action";
	}
	
	
	function saveUser(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($("#sex").combobox("getValue")==""){
					$.messager.alert("系统提示","请选择性别");
					return false;
				}
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败");
					return;
				}
			}
		});
	}
	//修改
	function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
		$("#trueName").val(row.trueName);
		$("#userName").val(row.userName);
		$("#password").val(row.password);
		$("#sex").combobox("setValue",row.sex);
		$("#birthday").datebox("setValue",row.birthday);
		$("#dentityCode").val(row.dentityCode);
		$("#email").val(row.email);
		$("#mobile").val(row.mobile);
		$("#address").val(row.address);
		url="user_saveUser.action?user.id="+row.id;
	}
	
	function resetValue(){
		$("#trueName").val("");
		$("#userName").val("");
		$("#password").val("");
		$("#sex").combobox("setValue","");
		$("#birthday").datebox("setValue","");
		$("#dentityCode").val("");
		$("#email").val("");
		$("#mobile").val("");
		$("#address").val("");
	}
	
	function closeUserDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</head>
<body style="margin:1px;">
	<table id="dg" title="用户管理" class="easyui-datagrid"
	 fitColumns="true" pagination="true" rownumbers="true"
	 url="user_list.action" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="50" align="center">编号</th>
	 		<th field="trueName" width="100" align="center">真实姓名</th>
	 		<th field="userName" width="100" align="center">用户名</th>
	 		<th field="password" width="100" align="center">密码</th>
	 		<th field="sex" width="50" align="center">性别</th>
	 		<th field="birthday" width="100" align="center">出生日期</th>
	 		<th field="dentityCode" width="150" align="center">身份证</th>
	 		<th field="email" width="100" align="center">邮件</th>
	 		<th field="mobile" width="100" align="center">联系电话</th>
	 		<th field="address" width="100" align="center">收货地址</th>
	 	</tr>
	 </thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
			<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 570px;height:300px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>真实姓名：</td>
	 				<td><input type="text" id="trueName" name="user.trueName" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>用户名：</td>
	 				<td><input type="text" id="userName" name="user.userName" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>密码：</td>
	 				<td><input type="text" id="password" name="user.password" class="easyui-validatebox" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>性别：</td>
	 				<td>
	 					<select class="easyui-combobox" id="sex" name="user.sex" style="width: 154px;" editable="false" panelHeight="auto">
	 						<option value="">请选择性别</option>
	 						<option value="男">男</option>
	 						<option value="女">女</option>
	 					</select>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>出生日期：</td>
	 				<td><input type="text" id="birthday" name="user.birthday" class="easyui-datebox" editable="false" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>身份证：</td>
	 				<td><input type="text" id="dentityCode" name="user.dentityCode" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>邮件：</td>
	 				<td><input type="text" id="email" name="user.email" class="easyui-validatebox" validType="email" required="true"/></td>
	 				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	 				<td>联系电话：</td>
	 				<td><input type="text" id="mobile" name="user.mobile" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>收货地址：</td>
	 				<td colspan="4">
	 					<input type="text" id="address" name="user.address" class="easyui-validatebox" required="true" style="width: 350px;"/>
	 				</td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
```
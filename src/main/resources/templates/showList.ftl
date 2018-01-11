<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="EasyUI/themes/default/easyui.css" rel="stylesheet" />
    <link href="EasyUI/themes/icon.css" rel="stylesheet" />
     <script src="EasyUI/jquery.min.js"></script>
    <script src="EasyUI/jquery.easyui.min.js"></script>
</head>
	<#list list as dept>
  		${dept.deptno}
  		${dept.dname}
  		${dept.loc}
  					<br/>
	</#list>
<body>

	<div class="easyui-dialog" style="width:400px;height:200px"
    data-options="title:'My Dialog',collapsible:true,iconCls:'icon-ok',onOpen:function(){}">
        dialog content.
</div>

	<table class="easyui-datagrid" title="Basic DataGrid" style="width:500px;height:250px"
			  data-options="fitColumns:false,singleSelect:true"  >
    <thead>
		<tr style="width:auto" >
			<th data-options="field:'deptno'">Deptno</th>
			<th data-options="field:'dname'">Dname</th>
			<th data-options="field:'loc'">Loc</th>
		</tr>
    </thead>

    <tbody>
	<#list list as dept>
		<tr>
  		
			<td>${dept.deptno}</td><td>${dept.dname}</td><td>${dept.loc}</td>
		</tr>
		
	</#list>
	</tbody>
</table>
	
	
	
</body>
</html>
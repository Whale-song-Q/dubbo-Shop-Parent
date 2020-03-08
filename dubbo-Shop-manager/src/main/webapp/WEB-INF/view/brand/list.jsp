<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div>
	<input id="BrandName" value="${mohu}"/>
	<button type="button" class="btn btn-primary" onclick="query()">
   	查询 </button>
   	
   	<button type="button" class="btn btn-primary" onclick="delBatch()">
   	批量删除</button>
   	
   	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
   	添加 </button>
   	
   	
   	
</div>
 <table class="table">
	
	<tr> 
		<th>id <input type="checkbox" id="allSel" onchange="selectedAll()">  
		<button type="button" class="btn btn-info btn-sm" onclick="selAll(1)">全选</button>
		<button type="button" class="btn btn-info btn-sm" onclick="selAll(2)">全不选</button>
		<button type="button" class="btn btn-info btn-sm" onclick="selAll(3)">反选</button>
		 </th>
		<th>名称</th>
		<th>首字母</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="bran">
		<tr>
			<td><input type="checkbox" name="ids" value="${bran.id}" onchange="selectedOne()"> ${bran.id}</td>
			<td>${bran.name}</td>
			<td>
			${bran.firstChar}
			</td>
			<td>
				<button type="button" class="btn btn-danger" onclick="delbran(${bran.id})">删除</button>
				<button type="button" class="btn btn-warning" onclick="openupdateBrand(${bran.id})">修改</button>
			</td>
			
		</tr>
	</c:forEach>
	
	<!-- pageInfo -->
	<tr>
		<td colspan="3">
			<jsp:include page="../common/page.jsp"></jsp:include>
		</td>
	</tr>
</table> 

<!-- 添加方法Modal框 -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">添加品牌</h5>
         
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        	
      </div>
      <div class="modal-body">
        	<form id="addBrand">
        		 <input type="hidden" name="id" id="upId">
        		 <div class="form-group">
    				<label for="specName">品牌名称</label>
    				<input type="text" class="form-control" name="name" id="addName" placeholder="请输入品牌的名称">
<!--     				<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small> -->
  				</div>
  				<div class="form-group form-group-proper">
    				<label for="inputAddress">首字母</label>
    				<input type="text" name="firstChar" class="form-control" id="firstChar" placeholder="请输入品牌的大写首字母">
  				</div>
  				
    			
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="addBrand()">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- 修改方法Modal框 -->
<div class="modal fade" id="updateBrand" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="updateBrandLabel">修改品牌</h5>
         
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        	
      </div>
      <div class="modal-body">
        	<form id="updBrand">
        		 <input type="hidden" name="id" id="upIds">
        		 <div class="form-group">
    				<label for="specName">品牌名称</label>
    				<input type="text" class="form-control" name="name" id="updaName" placeholder="请输入品牌的名称">
<!--     				<small id="specNamelHelp" class="form-text text-muted">请输入修改的品牌名称</small> -->
  				</div>
  				<div class="form-group form-group-proper">
    				<label for="inputAddress">首字母</label>
    				<input type="text" name="firstChar" class="form-control" id="updafirstChar" placeholder="请输入品牌的大写首字母">
  				</div>
  				
    			
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="updaBrand()">提交</button>
      </div>
    </div>
  </div>
</div>



  
<script type="text/javascript">
	
	/**
	查询
	*/
	function query(){
	var url="/brand/list?mohu="+$("#BrandName").val();
	$("#main").load(url);
	}
	
	/**
	* 分页 
	*/
	function goPage(pagenum){
		
		var url="/brand/list?mohu="+$("#BrandName").val()+'&page='+pagenum;
		$("#main").load(url);
	}
	
	/**
	* 刷新 而且保持查询条件和页码
	*/
	function refresh(){
		
		var url="/brand/list?mohu="+$("#BrandName").val()+'&page=${pageInfo.pageNum}';
		$("#main").load(url);
	}
	
	
	function addBrand(){
		
		 $('#staticBackdrop').modal('hide');
		//addbrand
		var formData = new FormData($("#addBrand")[0]);
		$.ajax({url:"/brand/addBrand",
			 // dataType:"json",
			  data:formData,
			  // 让jQuery 不要再提交数据之前进行处理
			  processData : false,
			  // 提交的数据不能加消息头
			  contentType : false,
			  // 提交的方式 
			  type:"post",
			  // 成功后的回调函数
			  success:function(data){
				 if(data=="success"){
					 
					 alert("数据提交成功")
					
					 
				 }else{
					 alert("数据保存失败")
				 }
				 
			  }
			  })
		
		
		
		
	}
	//添加窗口的复位
	function resetAddForm(){
/* 		$(".form-group-proper").each(function(){
			$(this).remove();
		}) */
/* 		addindex=1; */
		$("#specName").val("")
		
	}
	
	// 给添加规格模态框增加显示成成功后的事件  
	$('#staticBackdrop').on('shown.bs.modal', function (e) {
		  // do something...
/* 		  alert("添加模态框") */
		resetAddForm();
	})
	
	// 给添加规格模态框增加关闭以后的事件  
	$('#staticBackdrop').on('hidden.bs.modal', function (e) {
		  // do something...
		refresh();
	})
	
	// 给修改规格模态框增加关闭以后的事件  
/* 	$('#staticBackdropUpdate').on('hidden.bs.modal', function (e) {
		  // do something...
		refresh();
	})	
	 */
	
		/**
		* 删除单挑记录
		*/
		function delbran(id){
		
		
		var idsOne= new Array();
		idsOne.push(id);
			if(confirm("您确认删除该条数据么？下标为："+idsOne)){
				$.post("/brand/delAllbrand",{ids:idsOne},function(data){
					if(data="success"){
						alert("删除成功")
						refresh();
					}else{
						alert("删除失败")
					}
					
				});
			}else{
				return;
			}
		}
	 
	 
	 
	 
	 
	
	function delBatch(){
		
		if($("[name=ids]:checked").length<=0){
			alert("没有数据被选中，不能删除")
			return;
		}
		
		// 组织删除的数据
		var delIds  = new Array();
		$("[name=ids]:checked").each(function(){
			delIds.push($(this).val());
		})
		alert("待删除元素为"+delIds)
		
		if(confirm("您确认删除这些数据么？")){
			$.post("/brand/delAllbrand",{ids:delIds},function(data){
				if(data="success"){
					alert("删除成功")
					refresh();
				}else{
					alert("删除失败")
				}
				
			});
		}else{
			return;
		}
		
		
	}
	
	
	
	//修改
	
	
			//修改模态框显示
	function openupdateBrand(id){
		resetUpdateForm(id);
	}
	
	
	function updaBrand(){
		
		 $('#updateBrand').modal('hide');
		 
		var formData = new FormData($("#updBrand")[0]);
		$.ajax({url:"/brand/doUpdatebrand",
			 // dataType:"json",
			  data:formData,
			  // 让jQuery 不要再提交数据之前进行处理
			  processData : false,
			  // 提交的数据不能加消息头
			  contentType : false,
			  // 提交的方式 
			  type:"post",
			  // 成功后的回调函数
			  success:function(data){
				 if(data=="success"){
					 alert("数据提交成功")
				 }else{
					 alert("数据保存失败")
				 }
				 
			  }
			  })
		
		
		
		
		
	}
	
	
	
	
		function resetUpdateForm(id){

       //发送ajax回显数据
       $.ajax({ 
    	   url:"/brand/toUpdatebrand",
    	   data:{id:id},
    	   type:"post",
    	   success:function(data){
    		   
    		   if(data!=null){
     			   $("#upIds").val(data.id) 
    			   $("#updaName").val(data.name)
    			   $("#updafirstChar").val(data.firstChar)
    		   }else{
    			   
    			   alert("回显数据为空！") 
    		   }  
    	   }
   
       })
       //弹出回显的修改窗口
       $('#updateBrand').modal('show');
	}
		
		
		
		
		
		
		
		// 给修改品牌模态框增加关闭以后的事件(刷新main)
		$('#updateBrand').on('hidden.bs.modal', function (e) {
			  // do something...
			refresh();
		})
		

	
	
	
	/**
		点击全选的checkbox
	*/
	function selectedAll(){
	var checked = $("#allSel").prop("checked")
	// 让每个checkbox 都等于 总的checkbox
	$("[name=ids]").each(function(){
			$(this).prop("checked",checked)
		}
	)
	}
	
	/**
	* 修改一个checkbox
	*/
	function selectedOne(){
	// 判断是否所有的都被选中了
	var allSelected = $("[name=ids]").length==$("[name=ids]:checked").length;
	//设置全选的框
	$("#allSel").prop("checked",allSelected)
	}
	
	/**
	点击三个按钮
	*/
	function selAll(flag){
	
		if(flag==1){
		//全选
		$("[name=ids]").each(function(){
			$(this).prop("checked",true)
		}
		)
	}
	
	if(flag==2){
		//全不选
		$("[name=ids]").each(function(){
			$(this).prop("checked",false)
		})
	}
	if(flag==3){
		//反选
		$("[name=ids]").each(function(){
			var ch = !$(this).prop("checked")
			$(this).prop("checked",ch)
		}
		)
	} 
	// 判断是否所有的都被选中了
	var allSelected = $("[name=ids]").length==$("[name=ids]:checked").length;
	//设置全选的框
	$("#allSel").prop("checked",allSelected)
	
	}
	
	
	
	
	
	
	
</script> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/insert.jsp" %>
<%
 pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>

<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
 <link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<!--  标题-->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-success" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-bordered" id="emps_table">
					<thead>
						<tr>
							<th>#</th>
							<th>name</th>
							<th>gender</th>
							<th>email</th>
							<th>部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
              <div class="col-md-6" id="page_info_area">
              </div>
              <div class="col-md-6 col-md-offset-8" id="page_nav_area">
              </div>
          </div>
     </div>
</div>

<script type="text/javascript">
	$(function(){
		//去首页
		to_page(1);
	});
	
	function to_page(pn){
		$.ajax({
			url:"${APP_PATH}/emps",
			data:"num="+pn,
			type:"GET",
			success:function(result){
				//console.log(result);
				//1、解析并显示员工数据
				build_emps_table(result);
				//2、解析并显示分页信息
				build_page_info(result);
				//3、解析显示分页条数据
				build_page_nav(result);
			}
		});
	}
	
	function build_emps_table(result){
		//清空table表格
		$("#emps_table tbody").empty();
		var emps = result.extend.pageInfo.list;
		$.each(emps,function(index,item){
			/* var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>"); */
			var empId= $("<td></td>").append(item.empId);
			var empName = $("<td></td>").append(item.empName);
			var gender = $("<td></td>").append(item.gender=='M'?"男":"女");
			var email = $("<td></td>").append(item.email);
			var deptName = $("<td></td>").append(item.department.deptName);
			/**
			<button class="">
								<span class="" aria-hidden="true"></span>
								编辑
							</button>
			*/
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
			//为编辑按钮添加一个自定义的属性，来表示当前员工id
			/* editBtn.attr("edit-id",item.empId); */
			var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			//为删除按钮添加一个自定义的属性来表示当前删除的员工id
			/* delBtn.attr("del-id",item.empId); */
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			//var delBtn = 
			//append方法执行完成以后还是返回原来的元素
			$("<tr></tr>")
				.append(empId)
				.append(empName)
				.append(gender)
				.append(email)
				.append(deptName)
				.append(btnTd)
				.appendTo("#emps_table tbody");
		});
	}
	//解析显示分页信息
	//定义全局变量
	var totalRecord;
	var currentPage;
	function build_page_info(result){
		$("#page_info_area").empty();
		$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+
				result.extend.pageInfo.pages+"页,总"+
				result.extend.pageInfo.total+"条记录");
		totalRecord = result.extend.pageInfo.pages;
		currentPage = result.extend.pageInfo.pageNum;
	} 
	
	//解析显示分页条，点击分页要能去下一页....
	function build_page_nav(result){
		//page_nav_area
		$("#page_nav_area").empty();
		var ul = $("<ul></ul>").addClass("pagination");
		
		//构建元素
		var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
		if(result.extend.pageInfo.hasPreviousPage == false){
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		}else{
			//为元素添加点击翻页的事件
			firstPageLi.click(function(){
				to_page(1);
			});
			prePageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum -1);
			});
		}
		
		
		
		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
		var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		if(result.extend.pageInfo.hasNextPage == false){
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		}else{
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum +1);
			});
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
		}
		
		
		
		//添加首页和前一页 的提示
		ul.append(firstPageLi).append(prePageLi);
		//1,2，3遍历给ul中添加页码提示
		$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
			
			var numLi = $("<li></li>").append($("<a></a>").append(item));
			if(result.extend.pageInfo.pageNum == item){
				numLi.addClass("active");
			}
			numLi.click(function(){
				to_page(item);
			});
			ul.append(numLi);
		});
		//添加下一页和末页 的提示
		ul.append(nextPageLi).append(lastPageLi);
		
		//把ul加入到nav
		var navEle = $("<nav></nav>").append(ul);
		navEle.appendTo("#page_nav_area");
	}
	
	//点击新增按钮弹出模态框。
	$("#emp_add_modal_btn").click(function(){
		//清除表单数据（表单完整重置（表单的数据，表单的样式））
		reset_form("#empAddModal form");
		//$("")[0].reset();
		//发送ajax请求，查出部门信息，显示在下拉列表中
		getDepts("#empAddModal select");
		//弹出模态框
		$("#empAddModal").modal({
			backdrop:"static"
		});
	});
	//清空表单样式及内容
	function reset_form(ele){
		$(ele)[0].reset();
		//清空表单样式
		$(ele).find("*").removeClass("has-error has-success");
		$(ele).find(".help-block").text("");
	}
	
	function getDepts(ele){
		  //清空之前的值
        $(ele).empty();
		  //发送ajax请求获取部门信息数据
		$.ajax({
			url:"${APP_PATH}/depts",
			type:"GET",
			success:function(result){
				//console.log(result);
				$.each(result.extend.depts,function(){
					
					var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
					optionEle.appendTo(ele);
				}); 
			}
		})
	}
	
	//点击保存，保存员工。
	$("#emp_save_btn").click(function(){
		//1、模态框中填写的表单数据提交给服务器进行保存
		//1、先对要提交给服务器的数据进行校验
		if(!validate_add_form()){
			return false;
		}; 
		
		//1、判断之前的ajax用户名校验是否成功。如果成功。
		if($(this).attr("ajax-va")=="error"){
			return false;
		} 
		
		//2、发送ajax请求保存员工
		$.ajax({
			url:"${APP_PATH}/saveEmp",
			type:"POST",
			data:$("#empAddModal form").serialize(),
			success:function(result){
				//alert(result.msg);s
				if(result.code == 200){
					//员工保存成功；
					//1、关闭模态框
					$("#empAddModal").modal('hide');
					
					//2、来到最后一页，显示刚才保存的数据
					//发送ajax请求显示最后一页数据即可
					to_page(totalRecord);
				}else{
					//显示失败信息
					//console.log(result);
					//有哪个字段的错误信息就显示哪个字段的；
					if(undefined != result.extend.errorField.email){
						//显示邮箱错误信息
						//show_validate_msg("#email_add_input", "error", result.extend.errorField.email);
					}
					if(undefined != result.extend.errorField.empName){
						//显示员工名字的错误信息
						//show_validate_msg("#empName_add_input", "error", result.extend.errorField.empName);
					}
				} 
			}
		});
	});
	//校验表单数据
	function validate_add_form(){
		//获取要校验的数据，并使用正则表达式进行校验
		//1.用户名校验
		//1.1获取表单数据，用户名
		var empName=$("#empName_add_input").val();
		//1.2.编写校验规则，可以参考jQuery文档
		var regName=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		//1.3引用正则表达式的test方法测试字符串是否符合要求
		if(!regName.test(empName)){
			//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
			show_validate_msg("#empName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#empName_add_input", "success", "");
		}
		//2.邮箱校验
		var email=$("#email_add_input").val();
		var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(!regEmail.test(email)){
			//alert("邮箱格式不正确");
			show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
			return false;
		}else{
			show_validate_msg("#email_add_input", "success", "");
		}
		return true;
	}
	
	//显示校验结果的提示信息
	function show_validate_msg(ele,status,msg){
		//清除当前元素的校验状态
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if("success"==status){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		}else if("error" == status){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}
	
	//检验用户名是否可用
	$("#empName_add_input").change(function(){
		//发送ajax请求验证用户名是否可用
		var empName=this.value;
		$.ajax({
			url:"${APP_PAHT}/checkUser",
			data:"empName="+empName,
			type:"POST",
			success:function(result){
				if(result.code==200){
					show_validate_msg("#empName_add_input", "success", "用户名可用");
					//给保存按钮添加一个属性
					$("#emp_save_btn").attr("ajax-va","success");
				}else{
					show_validate_msg("#empName_add_input", "error", "用户名不可用");
					$("#emp_save_btn").attr("ajax-va","error");
				}
			}
		});
	});
</script>
</body>
</html>
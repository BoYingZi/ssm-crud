package com.boy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boy.bean.Employee;
import com.boy.service.EmployeeService;
import com.boy.utils.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 检查用户名是否可用
	 * @param userName
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public Msg checkUser(@RequestParam("empName") String userName) {
		boolean b = employeeService.checkUser(userName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	@RequestMapping(value="/saveEmp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map=new HashMap<String, Object>();
//			若校验失败，应该返回校验失败的信息，展示在前端模态框中
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println("错误的字段名"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorField", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
			
		}
	}
/*	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="num",defaultValue="1") Integer pageNum,
			@RequestParam(value="size",defaultValue="5") Integer pageSize,Model model) {
		PageHelper.startPage(pageNum, pageSize);
//		这得到的是总数据，无分页信息
		List<Employee> list = employeeService.getAllEmpsWithDept();
//		将查询到的所有数据封装到pageInfo属性中
		PageInfo pageInfo=new PageInfo(list, 5);
		model.addAttribute("pageInfo", pageInfo);
		return "list";
	}*/
	
	/*@RequestMapping("/emps")
	@ResponseBody
	public PageInfo getEmpsWithJson(@RequestParam(value="num",defaultValue="1") Integer pageNum,
			@RequestParam(value="size",defaultValue="5") Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
//		这得到的是总数据，无分页信息
		List<Employee> list = employeeService.getAllEmpsWithDept();
//		将查询到的所有数据封装到pageInfo属性中
		PageInfo pageInfo=new PageInfo(list, 5);
		return pageInfo;
	}*/
	
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="num",defaultValue="1") Integer pageNum,
			@RequestParam(value="size",defaultValue="5") Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
//		这得到的是总数据，无分页信息
		List<Employee> list = employeeService.getAllEmpsWithDept();
//		将查询到的所有数据封装到pageInfo属性中
		PageInfo pageInfo=new PageInfo(list, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	
}

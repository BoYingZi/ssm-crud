package com.boy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boy.bean.Employee;
import com.boy.bean.EmployeeExample;
import com.boy.bean.EmployeeExample.Criteria;
import com.boy.dao.EmployeeMapper;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getAllEmpsWithDept(){
		List<Employee> list = employeeMapper.selectByExampleWithDept(null);
		return list;
	}

	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
		
	}
	/**
	 * 检验用户是否可用
	 * @param userName
	 * @return
	 */
	public boolean checkUser(String userName) {
		EmployeeExample example=new EmployeeExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEmpNameEqualTo(userName);
		long count = employeeMapper.countByExample(example);
		//如果记录数==0表示可用
		return count==0;
	}

}

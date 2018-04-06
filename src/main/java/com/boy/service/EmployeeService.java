package com.boy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boy.bean.Employee;
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

}

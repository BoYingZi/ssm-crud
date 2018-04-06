package com.boy.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boy.bean.Employee;
import com.boy.dao.EmployeeMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class EmployeeMapperTest {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	public void testCountByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
	}

	@Test
	public void testInsertSelective() {
		EmployeeMapper mappers = sqlSession.getMapper(EmployeeMapper.class);
		String uid = UUID.randomUUID().toString().substring(1, 5);
		for (int i = 0; i < 1000; i++) {
			mappers.insertSelective(new Employee(null, uid,"M" , uid+"jerry@qq.com", 1));
		}
	}
		

	@Test
	public void testSelectByExample() {
		
	}

	@Test
	public void testSelectByPrimaryKey() {
		employeeMapper.selectByPrimaryKey(1);
	}

	@Test
	public void testSelectByExampleWithDept() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByPrimaryKeyWithDept() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByExampleSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}

}

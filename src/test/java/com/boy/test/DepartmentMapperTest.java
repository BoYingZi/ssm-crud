package com.boy.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boy.bean.Department;
import com.boy.dao.DepartmentMapper;
/**
 * 测试DepartmentMapper接口
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class DepartmentMapperTest {
	@Autowired
	private DepartmentMapper departmentMapper;

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertSelective() {
		//departmentMapper.insertSelective(new Department(null, "数据部"));
		departmentMapper.insertSelective(new Department(null, "研发部"));
	}

}

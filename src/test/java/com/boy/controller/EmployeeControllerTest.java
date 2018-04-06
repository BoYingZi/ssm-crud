package com.boy.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.boy.bean.Employee;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class EmployeeControllerTest {
	@Autowired
	private WebApplicationContext context;
	
	MockMvc mvc;
	@Before
	public void initMock() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void test() throws Exception {
//		ģ�������õ�����ֵ
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("num", "1");
		map.add("size", "5");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/emps").params(map)).andReturn();
//		����ɹ��󣬻�ȡ�������е����ݽ�����֤
		MockHttpServletRequest request = result.getRequest();
		PageInfo<Employee> pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("��ǰҳ��"+pi.getPageNum());
		System.out.println("��ҳ��"+pi.getPages());
		System.out.println("�ܼ�¼��"+pi.getTotal());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		int[] num = pi.getNavigatepageNums();
		for (int i = 0; i < num.length; i++) {
			System.out.println(" "+i);
		}
		System.out.println("��ȡԱ������");
		List<Employee> list = pi.getList();
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}

}

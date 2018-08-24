package zyq.mybatis.simple.mapper;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import zyq.mybatis.simple.model.Country;

public class CountryMapperTest {

	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void init() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Country> list = sqlSession.selectList("selectAll");
			printCountryList(list);
		}finally {
			sqlSession.close();
		}
	}
	
	public void printCountryList(List<Country> list) {
		for(Country country : list) {
			System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
		}
	}
}

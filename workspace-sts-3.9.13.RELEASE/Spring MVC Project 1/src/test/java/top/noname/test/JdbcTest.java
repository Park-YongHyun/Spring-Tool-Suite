package top.noname.test;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JdbcTest extends TestTemplate {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	// 연결 테스트
	@Test
	public void connectionTest() {
		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Connection connection = sqlSession.getConnection();
			log.info("@@@ sqlSession: " + sqlSession);
			log.info("@@@ connection: " + connection);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

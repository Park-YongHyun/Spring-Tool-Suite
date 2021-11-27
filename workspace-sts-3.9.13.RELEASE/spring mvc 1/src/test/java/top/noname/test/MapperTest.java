package top.noname.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.mapper.TestMapper;

public class MapperTest extends TestTemplate {
	@Autowired
	private TestMapper mapper;

	@Test
	public void test() {
		log.info("@@@ result: " + mapper.test());
	}
}

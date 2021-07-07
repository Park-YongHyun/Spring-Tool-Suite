package top.noname.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTemplate {
	protected static Logger log;

	public TestTemplate() {
		log = LoggerFactory.getLogger(getClass());
	}

	@Before
	public void start() {
		log.info("@@@ start");
	}

	@After
	public void end() {
		log.info("@@@ end");
	}
}

package kr.ac.smu;

import java.net.URI;
import java.net.URL;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.ac.smu.cs.comnet.service.UserService;
import kr.ac.smu.cs.comnet.vo.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class test {
	@Autowired
	private UserService service;
	@Autowired
	private WebApplicationContext ctx;
	@Autowired
	private PasswordEncoder encoder;
	private MockMvc mvc;
	private Logger log=LoggerFactory.getLogger(test.class);
	@Before
	public void setup() {
		mvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	@Test
	public void tests() throws Exception{
		URI url=new URI("/login");
		log.info(mvc.perform(MockMvcRequestBuilders.post(url)
				.param("username", "201611011").param("password", "1111")).andReturn().getModelAndView().getViewName());
	}
}

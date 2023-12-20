package com.pcwk.ehr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;

import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	final Logger LOG = LogManager.getLogger(UserDaoJUnitTest.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	List<UserVO> users;
	UserVO searchVO;
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ setUp                                                   │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");			
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		users = Arrays.asList(
				new UserVO("p1-01", "이상무99-01", "4444_1", "jamesol1@paran.com", "010-1772-7952"),
				new UserVO("p1-02", "이상무99-01", "4444_2", "jamesol2@paran.com", "010-2772-7952"),
				new UserVO("p1-03", "이상무99-01", "4444_3", "jamesol3@paran.com", "010-3772-7952"),
				new UserVO("p1-04", "이상무99-01", "4444_4", "jamesol4@paran.com", "010-4772-7952"),
				new UserVO("p1-05", "이상무99-01", "4444_5", "jamesol5@paran.com", "010-5772-7952")
			);
		searchVO = new UserVO();
		searchVO.setUserId("p1");
	}
	
	public UserVO doSelectOne(UserVO inVO) throws Exception{
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ doSelectOne                                             │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		//UserVO inVO = users.get(0);
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/user/doSelectOne.do")
				.param("userId",                  inVO.getUserId());
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ result                                                  │"+result);
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		
		UserVO outVO = new Gson().fromJson(result, UserVO.class);
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ outVO                                     │"+outVO);
		LOG.debug("└───────────────────────────────────────────┘");		
		assertNotNull(outVO);
		
		return outVO;
	}
	//@Ignore
	@Test
	public void doUpdate() throws Exception{
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ doUpdate                                                  │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		UserVO inVO = users.get(0);
		String upStr ="_U";
		int upNum =100;
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.post("/user/doUpdate.do")
				.param("userId", inVO.getUserId())
				.param("password", inVO.getPassword())
				.param("username", inVO.getUsername())
				.param("email", inVO.getEmail()+upStr)
				.param("userTel", inVO.getUserTel());
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ result                                                  │"+result);
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		
		MessageVO messageVO =new Gson().fromJson(result, MessageVO.class);
		 //assertEquals(String.valueOf(1), messageVO.getMsgId());
		 LOG.debug("┌─────────────────────────────────────────────────────────┐");
		 LOG.debug("│ messageVO                                               │"+messageVO);
		 LOG.debug("└─────────────────────────────────────────────────────────┘");
	 
	}
//	@Ignore
	@Test
	public void addAndGet() throws Exception {
		//1.데이터 삭제
		//2.데이터 추가
		//3.데이터 검색
		
		//1.
		doDelete(users.get(0));
		doDelete(users.get(1));
		doDelete(users.get(2));
		doDelete(users.get(3));
		doDelete(users.get(4));
		
		//2.
		doSave(users.get(0));
		doSave(users.get(1));
		doSave(users.get(2));
		doSave(users.get(3));
		doSave(users.get(4));
		
		//3.
		isSameUser(users.get(0), doSelectOne(users.get(0)));
		isSameUser(users.get(1), doSelectOne(users.get(1)));
		isSameUser(users.get(2), doSelectOne(users.get(2)));
		isSameUser(users.get(3), doSelectOne(users.get(3)));
		isSameUser(users.get(4), doSelectOne(users.get(4)));
		
	}
	private void isSameUser(UserVO userVO, UserVO outVO) {
		assertEquals(userVO.getUserId(), outVO.getUserId());
		assertEquals(userVO.getPassword(), outVO.getPassword());
		assertEquals(userVO.getUsername(), outVO.getUsername());
		assertEquals(userVO.getEmail(), outVO.getEmail());//email
		assertEquals(userVO.getUserTel(), outVO.getUserTel());
		

	}
	
	
	public void doDelete(UserVO inVO) throws Exception{
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ doDelete                                                │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		//UserVO inVO = users.get(0);
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/user/doDelete.do")
				.param("userId",                  inVO.getUserId());
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ result                                                  │"+result);
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		
		 MessageVO messageVO =new Gson().fromJson(result, MessageVO.class);
		 //assertEquals(String.valueOf(1), messageVO.getMsgId());
		 LOG.debug("┌─────────────────────────────────────────────────────────┐");
		 LOG.debug("│ messageVO                                               │"+messageVO);
		 LOG.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	public void doSave(UserVO inVO) throws Exception{
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ doSave                                                  │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		//UserVO inVO = users.get(0);
		
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.post("/user/doSave.do")
				.param("userId", inVO.getUserId())
				.param("password", inVO.getPassword())
				.param("username", inVO.getUsername())
				.param("email", inVO.getEmail())
				.param("userTel", inVO.getUserTel());
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ result                                                  │"+result);
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		
		MessageVO messageVO =new Gson().fromJson(result, MessageVO.class);
		 assertEquals(String.valueOf(1), messageVO.getMsgId());
		 LOG.debug("┌─────────────────────────────────────────────────────────┐");
		 LOG.debug("│ messageVO                                               │"+messageVO);
		 LOG.debug("└─────────────────────────────────────────────────────────┘");
	}
	

	@Test
	public void bean() {
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ webApplicationContext                                   │"+webApplicationContext);
		LOG.debug("│ mockMvc                                                 │"+mockMvc);
		LOG.debug("└─────────────────────────────────────────────────────────┘");			
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
	}
	
	

}
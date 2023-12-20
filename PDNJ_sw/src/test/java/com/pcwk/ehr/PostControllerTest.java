package com.pcwk.ehr;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;
import java.sql.SQLException;
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
import org.springframework.context.ApplicationContext;
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
import com.pcwk.ehr.user.dao.PostDao;
import com.pcwk.ehr.user.domain.PostVO;
import com.pcwk.ehr.user.domain.UserVO;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostControllerTest {
	final Logger LOG = LogManager.getLogger(getClass());
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	PostDao dao;
	
	MockMvc mockMvc;
	List<PostVO> posts;
	PostVO searchVO;
	
//	ApplicationContext context;
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ setUp                                                   │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		dao = context.getBean("postDao", PostDao.class);
		int seq = dao.getNextval();
		posts = Arrays.asList(new PostVO(seq, "post3", "thanks", "", "p1"));
		searchVO = new PostVO();
		searchVO.setPostId(seq);
	}
	
	@Test
	public void doUpdate() throws Exception{
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│doUpdate()              │");
		LOG.debug("└────────────────────────┘");
		PostVO inVO = posts.get(0);
		
		String upStr = "_U";
		int upNum    = 100;
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/doSave.do")
				.param("postId", inVO.getPostId() + "")
				.param("title", inVO.getTitle())
				.param("postContent", inVO.getPostContent())
				.param("postDate", inVO.getPostDate() + "")
				.param("userId", inVO.getUserId());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());//is2xxSuccessful()
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│result                  │"+result);
		LOG.debug("└────────────────────────┘");
		
		//Gson -> VO
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│messageVO               │"+messageVO);
		LOG.debug("└────────────────────────┘");
		
		
	}
	
	@Ignore
	@Test
	public void addAndGet() throws Exception {
		//1.데이터 삭제
		//2.등록
		//3.한건 조회
		
		//1
		doDelete(posts.get(0));
		
		//2
		doSave(posts.get(0));
		
		//3
		//PostVO outVO01 = doSelectOne(posts.get(0));
		isSameUser(posts.get(0), doSelectOne(posts.get(0)));
	}
	
	private void isSameUser(PostVO postVO, PostVO outVO) {
		assertThat(postVO.getPostId(), is(outVO.getPostId()));
		assertThat(postVO.getTitle(), is(outVO.getTitle()));
		assertThat(postVO.getPostContent(), is(outVO.getPostContent()));
		// assertThat(postVO.getPostDate(), is(outVO.getPostDate()));
		assertThat(postVO.getUserId(), is(outVO.getUserId()));
	}
	
	public PostVO doSelectOne(PostVO inVO) throws Exception {
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│doSelectOne()           │");
		LOG.debug("└────────────────────────┘");
//		UserVO inVO = users.get(0);
		//url + 호출방식(get) + param(userId)
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/doSelectOne.do")//get
				.param("postId",    	inVO.getPostId()+"");
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│result                  │"+result);
		LOG.debug("└────────────────────────┘");
		
		PostVO outVO = new Gson().fromJson(result, PostVO.class);
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│outVO                   │"+outVO);
		LOG.debug("└────────────────────────┘");
		assertNotNull(outVO);
		
		return outVO;
	}
	
	public void doDelete(PostVO inVO) throws Exception{
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│doDelete()              │");
		LOG.debug("└────────────────────────┘");
		//UserVO inVO = users.get(0);
		
		//url + 호출방식(get) + param(userId)
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/doDelete.do")//get
				.param("userId",    	inVO.getUserId());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│result()                │"+result);
		LOG.debug("└────────────────────────┘");
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
//		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│messageVO               │"+messageVO);
		LOG.debug("└────────────────────────┘");
		
	}
//	@Test
	public void doSave(PostVO inVO) throws Exception {
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ doSave                                                  │");
		LOG.debug("└─────────────────────────────────────────────────────────┘");
//		PostVO inVO = users.get(0);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/doSave.do")
				.param("postId", inVO.getPostId() + "")
				.param("title", inVO.getTitle())
				.param("postContent", inVO.getPostContent())
				.param("postDate", inVO.getPostDate() + "")
				.param("userId", inVO.getUserId());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ result                                                  │" + result);
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│messageVO               │"+messageVO);
		LOG.debug("└────────────────────────┘");
		
	}
	@Test
	public void bean() {
		LOG.debug("┌─────────────────────────────────────────────────────────┐");
		LOG.debug("│ beans()                                                 │");
		LOG.debug("│ webApplicationContext                                   │" + webApplicationContext);
		LOG.debug("│ mockMvc                                                 │" + mockMvc);
		LOG.debug("└─────────────────────────────────────────────────────────┘");
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
	}
}
package com.pcwk.ehr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.user.dao.PostDao;
import com.pcwk.ehr.user.domain.PostVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) // f2로 이름 복사 사용 권장
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostDaoJUnitTest {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	PostDao dao;
	//
	PostVO postVO01;

	// getCount 조회에 사용
	PostVO searchVO;
	// ApplicationContext context;

	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		// sh03_01/src/main/java/applicationContext.xml
		// context = new GenericXmlApplicationContext("applicationContext.xml");

		int seq = dao.getNextval();
		// 등록//_:한글자 의미
		postVO01 = new PostVO(seq, "post3", "thanks", "날짜 사용하지 안음", "p1");
		// getCount 조회에 사용 LIKE
		searchVO = new PostVO();
		searchVO.setPostId(seq);
		LOG.debug("===============");
		LOG.debug("=context=" + context);
		LOG.debug("dao=" + dao);
		LOG.debug("===============");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=tearDown=");
	}

	@Ignore
	@Test
	public void doRetrieve() throws SQLException {
		LOG.debug("===============");
		LOG.debug("=doRetrieve()=");
		LOG.debug("===============");

		searchVO.setPageSize(10);
		searchVO.setPageNo(1);
		searchVO.setSearchDiv("10");
		searchVO.setSearchWord("p1");

		List<PostVO> list = dao.doRetrieve(this.searchVO);
		assertEquals(3, list.size());
	}

	@Ignore
	@Test
	public void getAll() throws SQLException {
		// 1. 데이터 삭제
		// 2. 데이터 입력
		// 3. 건수 확인
		// 4. getAll()
		// 5. 3건
		// 6. 데이터 비교
		LOG.debug("===============");
		LOG.debug("=getAll()=");
		LOG.debug("===============");

		// 1.
		dao.doDelete(postVO01);

		assertEquals(0, dao.getCount(searchVO));

		// 2.
		int flag = dao.doSave(postVO01);

		// 3.
		assertEquals(1, flag);
		assertEquals(1, dao.getCount(searchVO));

		// 4.
		List<PostVO> list = dao.getAll(searchVO);

		// 5.
		assertEquals(1, list.size());

		for (PostVO vo : list) {
			LOG.debug(vo);
		}

		isSameUser(postVO01, list.get(0));

	}

	// setUp()
	// getFailure()
	// tearDown()
	// expected=예외, 예외가 발생하면 성공
	@Ignore
	@Test(expected = EmptyResultDataAccessException.class)
	public void getFailure() throws Exception {
		LOG.debug("===============");
		LOG.debug("=getFailure=");
		LOG.debug("===============");

		// 1.데이터삭제
		// 2.한건조회

		// 1.
		dao.doDelete(postVO01);

		postVO01.setUserId("unknown id");

		// 2.
		dao.doSelectOne(postVO01);

	}

	//@Ignore
	@Test(timeout = 30000) // 3초안에 , long 1/1000초
	public void addAndGet() throws SQLException {
		// 1. 데이터 삭제
		// 2. 등록
		// 3. 한 건 조회

		// 1.
		dao.doDelete(postVO01);

		// 2.
		int flag = dao.doSave(postVO01);
		int count = dao.getCount(searchVO);
		assertThat(flag, is(1));
		assertThat(count, is(1));

//		if(1==flag) {
//			LOG.debug("등록 성공!");
//		}else {
//			LOG.debug("등록 실패!");
//		}

		PostVO outVO01 = dao.doSelectOne(postVO01);

		assertNotNull(outVO01);// Not Null이면 true

//		if(null != outVO) {
//			LOG.debug("단건조회 성공!");
//		}else {
//			LOG.debug("단건조회 실패!");
//		}

		// 데이터 동일성 테스트//DB열 필요 없어짐
		isSameUser(postVO01, outVO01);
		LOG.debug("postVO01:" + postVO01);
		LOG.debug("outVO01:" + outVO01);

	}

	@Ignore
	@Test
	public void update() throws SQLException {
		// 1. 데이터 삭제
		// 2. 데이터 입력
		// 3. 등록 데이터 조회
		// 4. 조회된 데이터를 수정
		// 5. update
		// 6. 수정 데이터 조회
		// 7. 비교

		LOG.debug("===============");
		LOG.debug("=update()=");
		LOG.debug("===============");

		// 1.
		dao.doDelete(postVO01);

		assertEquals(0, dao.getCount(searchVO));

		// 2.
		dao.doSave(postVO01);
		assertEquals(1, dao.getCount(searchVO));

		// 3.
		PostVO getVO = dao.doSelectOne(postVO01);
		isSameUser(getVO, postVO01);

		// 4.
		String upStr = "_U";
		int upInt = 10;

		getVO.setTitle(getVO.getTitle() + upStr);
		getVO.setPostContent(getVO.getPostContent() + upStr);

		// 5.
		int flag = dao.doUpdate(getVO);
		assertEquals(1, flag);

		// 6.
		PostVO vsVO = dao.doSelectOne(getVO);

		// 7.
		isSameUser(vsVO, getVO);

	}

	private void isSameUser(PostVO postVO, PostVO outVO) {
		assertThat(postVO.getPostId(), is(outVO.getPostId()));
		assertThat(postVO.getTitle(), is(outVO.getTitle()));
		assertThat(postVO.getPostContent(), is(outVO.getPostContent()));
		// assertThat(postVO.getPostDate(), is(outVO.getPostDate()));
		assertThat(postVO.getUserId(), is(outVO.getUserId()));

	}

}
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

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) // f2로 이름 복사 사용 권장
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoJUnitTest {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	UserDao dao;
	
	// 다건 등록
	UserVO userVO01;

	// getCount 조회에 사용
	UserVO searchVO;
	// ApplicationContext context;

	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		// sh03_01/src/main/java/applicationContext.xml
		// context = new GenericXmlApplicationContext("applicationContext.xml");

		// 등록//_:한글자 의미
		userVO01 = new UserVO("p1", "pdnj2", "pdnj", "email", "01012345678");

		// getCount 조회에 사용 LIKE
		searchVO = new UserVO("p1", "", "", "", "");

		LOG.debug("===============");
		LOG.debug("=context=" + context);
		LOG.debug("dao=" + dao);
		LOG.debug("===============");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=tearDown=");
	}
	
	@Test
	public void idDuplicatecheck() throws SQLException{
		//1. 데이터 삭제
		//2. 데이터 입력
		//3. idCheck
		
		//1.
		dao.doDelete(userVO01);
		assertEquals(0, dao.getCount(searchVO));//
		
		//2.
		int flag = dao.doSave(userVO01);
		
		//3.
		assertEquals(1, flag);
		assertEquals(1, dao.getCount(searchVO));
		
		//idCheck : id 있는 경우
		int idCheckCnt = dao.idDuplicateCheck(userVO01);
		assertEquals(1, idCheckCnt);
		
		//idCheck : id 없는 경우
		userVO01.setUserId("unknow_user");
		idCheckCnt = dao.idDuplicateCheck(userVO01);
		assertEquals(0, idCheckCnt);
		
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

		List<UserVO> list = dao.doRetrieve(this.searchVO);
		assertEquals(1, list.size());
	}
	
	@Ignore
	@Test
	public void getAll()throws SQLException{
		//1. 데이터 삭제
		//2. 데이터 입력
		//3. 건수 확인
		//4. getAll() 전체 데이터 조회
		//6. 데이터 비교
		LOG.debug("===============");
		LOG.debug("=getAll()=");
		LOG.debug("===============");
		
		//1.
		dao.doDelete(userVO01);
		assertEquals(0, dao.getCount(searchVO));//3건 예상했는데 0건
		
		//2.
		int flag = dao.doSave(userVO01);
		
		//3.
		assertEquals(1, flag);
		assertEquals(1, dao.getCount(searchVO));
		
		//4.
		List<UserVO> list = dao.getAll(searchVO);
		
		//5.
		assertEquals(3, list.size());
		
		for(UserVO vo :list) {
			LOG.debug(vo);
		}
		
		isSameUser(userVO01, list.get(0));
		
		
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
		dao.doDelete(userVO01);

		assertEquals(0, dao.getCount(searchVO));

		// 2.
		dao.doSave(userVO01);
		assertEquals(1, dao.getCount(searchVO));

		// 3.
		UserVO getVO = dao.doSelectOne(userVO01);
		isSameUser(getVO, userVO01);

		// 4.
		String upStr = "_U";

		getVO.setPassword(getVO.getPassword() + upStr);
		getVO.setUsername(getVO.getUsername() + upStr);

		// 5.
		int flag = dao.doUpdate(getVO);
		assertEquals(1, flag);

		// 6.
		UserVO vsVO = dao.doSelectOne(getVO);

		// 7.
		isSameUser(vsVO, getVO);

	}

	@Ignore
	@Test(expected = EmptyResultDataAccessException.class)
	public void getFailure() throws Exception {
		LOG.debug("===============");
		LOG.debug("=getFailure=");
		LOG.debug("===============");

		// 1.데이터삭제
		// 2.한건조회

		// 1.
		dao.doDelete(userVO01);

		userVO01.setUserId("unknown id");

		// 2.
		dao.doSelectOne(userVO01);

	}

	@Ignore
	@Test(timeout = 3000) // 3초안에 , long 1/1000초
	public void addAndGet() throws SQLException {
		// 1. 데이터 삭제
		// 2. 등록
		// 3. 한 건 조회

		// 1.
		dao.doDelete(userVO01);

		// 2.
		int flag = dao.doSave(userVO01);
		int count = dao.getCount(searchVO);
		assertThat(flag, is(1));
		assertThat(count, is(1));

		UserVO outVO01 = dao.doSelectOne(userVO01);

		assertNotNull(outVO01);

		// 데이터 동일성 테스트//DB열 필요 없어짐
		isSameUser(userVO01, outVO01);

	}

	private void isSameUser(UserVO userVO, UserVO outVO) {
		assertThat(userVO.getUserId(), is(outVO.getUserId()));
		assertThat(userVO.getPassword(), is(outVO.getPassword()));
		assertThat(userVO.getUsername(), is(outVO.getUsername()));
		assertThat(userVO.getEmail(), is(outVO.getEmail()));
		assertThat(userVO.getUserTel(), is(outVO.getUserTel()));

	}

}
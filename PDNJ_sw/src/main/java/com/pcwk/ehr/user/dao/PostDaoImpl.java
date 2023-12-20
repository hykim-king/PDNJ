package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.user.domain.PostVO;

@Repository
public class PostDaoImpl implements PostDao {

	final Logger LOG = LogManager.getLogger(getClass());

	final String NAMESPACE = "com.pcwk.ehr.post";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public PostDaoImpl() {
	}

	@Override
	public List<PostVO> getAll(PostVO inVO) throws SQLException {
		List<PostVO> outList = new ArrayList<PostVO>();

		LOG.debug("1.param 	\n" + inVO.toString());

		String statement = NAMESPACE + DOT + "getAll";
		LOG.debug("2.statement 	\n" + statement);

		outList = this.sqlSessionTemplate.selectList(statement, inVO);
		for (PostVO vo : outList) {
			LOG.debug(vo);
		}

		return outList;
	}

	@Override
	public int doUpdate(PostVO inVO) throws SQLException {
		int flag = 0;

		LOG.debug("1.param \n" + inVO.toString());

		String statement = NAMESPACE + DOT + "doUpdate";
		LOG.debug("2.statement \n" + statement);

		flag = this.sqlSessionTemplate.update(statement, inVO);
		LOG.debug("3.flag \n" + flag);
		return flag;
	}

	@Override
	public int getCount(PostVO inVO) throws SQLException {
		int count = 0;

		LOG.debug("1.param \n" + inVO.toString());

		String statement = NAMESPACE + DOT + "getCount";
		LOG.debug("2.statement \n" + statement);

		count = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3.count \n" + count);

		return count;
	}

	@Override
	public int doDelete(PostVO inVO) throws SQLException {
		int flag = 0;

		LOG.debug("1.param \n" + inVO.toString());

		String statement = NAMESPACE + DOT + "doDelete";
		LOG.debug("2.statement \n" + statement);

		flag = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("3.flag \n" + flag);

		return flag;
	}

	@Override
	public PostVO doSelectOne(PostVO inVO) throws SQLException, EmptyResultDataAccessException {
		PostVO outVO = null;

		LOG.debug("1.param \n" + inVO.toString());

		String statement = NAMESPACE + DOT + "doSelectOne";
		LOG.debug("2.statement \n" + statement);

		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3.outVO \n" + outVO.toString());

		return outVO;
	}

	@Override
	public int doSave(PostVO inVO) throws SQLException {
		int flag = 0;

		LOG.debug("1.param \n" + inVO.toString());

		String statement = this.NAMESPACE + DOT + "doSave";
		LOG.debug("2.statement \n" + statement);

		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("3.flag \n" + flag);

		return flag;
	}

	@Override
	public List<PostVO> doRetrieve(PostVO inVO) throws SQLException {
		List<PostVO> outList = new ArrayList<PostVO>();

		LOG.debug("1 param \n" + inVO.toString());

		String statement = NAMESPACE + DOT + "doRetrieve";
		LOG.debug("2.statment \n" + statement);

		outList = this.sqlSessionTemplate.selectList(statement, inVO);

		for (PostVO vo : outList) {
			LOG.debug(vo);
		}

		return outList;
	}

	// 시퀀스
	@Override
	public int getNextval() throws SQLException {
		int no = 0;

		String statement = this.NAMESPACE + DOT + "getNextval";
		LOG.debug("2.statement \n" + statement);

		no = this.sqlSessionTemplate.selectOne(statement);
		LOG.debug("3.count \n" + no);

		return no;
	}

}
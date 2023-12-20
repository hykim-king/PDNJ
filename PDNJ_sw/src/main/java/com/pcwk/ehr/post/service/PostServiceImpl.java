package com.pcwk.ehr.post.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.user.dao.PostDao;
import com.pcwk.ehr.user.domain.PostVO;

@Service
public class PostServiceImpl implements PostService {

	final Logger LOG = LogManager.getLogger(PostServiceImpl.class);

	@Autowired
	private PostDao postDao;

	@Resource(name = "dummyMailSender")
	private MailSender mailSender;

	public PostServiceImpl() {
	}

	@Override
	public int doUpdate(PostVO inVO) throws SQLException {
		return postDao.doUpdate(inVO);
	}

	@Override
	public int getCount(PostVO inVO) throws SQLException {
		return postDao.getCount(inVO);
	}

	@Override
	public int doDelete(PostVO inVO) throws SQLException {
		return postDao.doDelete(inVO);
	}

	@Override
	public PostVO doSelectOne(PostVO inVO) throws SQLException, EmptyResultDataAccessException {
		return postDao.doSelectOne(inVO);
	}

	@Override
	public int doSave(PostVO inVO) throws SQLException {
		return postDao.doSave(inVO);
	}

	@Override
	public List<PostVO> doRetrieve(PostVO inVO) throws SQLException {
		return postDao.doRetrieve(inVO);
	}

}
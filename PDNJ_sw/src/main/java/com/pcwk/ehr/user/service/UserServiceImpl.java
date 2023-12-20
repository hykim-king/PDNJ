package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.UserVO;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	final Logger LOG = LogManager.getLogger(getClass());
	
	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMEND_COUNT_FOR_GOLD = 30;

	@Autowired
	private UserDao userDao;

	@Resource(name = "dummyMailSender")
	private MailSender mailSender;

	public UserServiceImpl() {
	}

	@Override
	public List<UserVO> doRetrieve(UserVO inVO) throws SQLException {
		return userDao.doRetrieve(inVO);
	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
		return userDao.doSelectOne(inVO);
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		return userDao.doDelete(inVO);
	}

	@Override
	public int getCount(UserVO inVO) throws SQLException {
		return userDao.getCount(inVO);
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		return userDao.doUpdate(inVO);
	}

	@Override
	public List<UserVO> getAll(UserVO inVO) throws SQLException {
		return userDao.getAll(inVO);
	}

	@Override
	public int idDuplicateCheck(UserVO inVO) throws SQLException {
		
		return userDao.idDuplicateCheck(inVO);
	}

}

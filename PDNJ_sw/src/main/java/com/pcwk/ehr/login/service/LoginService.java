package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.user.domain.UserVO;

public interface LoginService {
	
	/**
	 * 데이터 베이스 ID, Password Check
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	int loginCheck(UserVO inVO) throws SQLException;
	
	/**
	 * 단건조회 //LoginDaoImpl
	 * @param inVO
	 * @return
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException;

}
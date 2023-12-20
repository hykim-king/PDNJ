package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.user.domain.PostVO;

public interface PostDao extends WorkDiv<PostVO> {

	List<PostVO> getAll(PostVO inVO) throws SQLException;

	int doUpdate(PostVO inVO) throws SQLException;

	int getCount(PostVO inVO) throws SQLException;

	int doDelete(PostVO inVO) throws SQLException;

	int getNextval() throws SQLException;

	PostVO doSelectOne(PostVO inVO) throws SQLException, EmptyResultDataAccessException;

	int doSave(PostVO inVO) throws SQLException;

}

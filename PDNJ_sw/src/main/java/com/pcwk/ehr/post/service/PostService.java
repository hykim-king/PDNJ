package com.pcwk.ehr.post.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.user.domain.PostVO;

public interface PostService {
	
	public List<PostVO> doRetrieve(PostVO inVO) throws SQLException;
	
	public int doUpdate(PostVO inVO) throws SQLException;
	
	public int getCount(PostVO inVO) throws SQLException;
	
	public int doDelete(final PostVO inVO) throws SQLException;
	
	public PostVO doSelectOne(PostVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	public int doSave(PostVO inVO) throws SQLException;
	
}
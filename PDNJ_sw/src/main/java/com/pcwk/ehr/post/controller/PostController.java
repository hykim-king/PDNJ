package com.pcwk.ehr.post.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.post.service.PostService;
import com.pcwk.ehr.user.domain.PostVO;

@Controller
@RequestMapping("post")
public class PostController {
	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	PostService postService;

	@RequestMapping(value = "/moveToReg.do", method = RequestMethod.GET)
	public String moveToReg() throws SQLException {
		String view = "post/post_reg";
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│ moveToReg()            │");
		LOG.debug("└────────────────────────┘");

		return view;
	}

	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET)
	public String doRetrieve(PostVO searchVO, HttpServletRequest req, Model model) throws SQLException {
		String view = "post/post_list";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doRetrieve()                              │ searchVO:" + searchVO);
		LOG.debug("└───────────────────────────────────────────┘");

		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"));
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"));
		searchVO.setSearchDiv(searchDiv);
		searchVO.setSearchWord(searchWord);

		String pageSize = StringUtil.nvl(req.getParameter("pageSize"), "10");
		String pageNo = StringUtil.nvl(req.getParameter("pageNo"), "1");

		// 브라우저에서 숫자 : 문자로 들어 옴
		long tPageNo = Long.parseLong(pageNo);
		long tPageSize = Long.parseLong(pageSize);

		if (0 == tPageNo) {
			searchVO.setPageNo(1);
		} else {
			searchVO.setPageNo(tPageNo);
		}

		if (0 == tPageSize) {
			searchVO.setPageSize(10);
		} else {
			searchVO.setPageSize(tPageSize);
		}

		LOG.debug("pageSize              :" + searchVO.getPageSize());
		LOG.debug("pageNo              :" + searchVO.getPageNo());

		LOG.debug("searchDiv              :" + searchDiv);
		LOG.debug("searchWord              :" + searchWord);

		LOG.debug("searchVO:" + searchVO);

		List<PostVO> list = this.postService.doRetrieve(searchVO);

		// 화면에 데이터 전달
		model.addAttribute("list", list);

		// 검색 조건
		model.addAttribute("searchVO", searchVO);

		return view;
	}

	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(PostVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│doUpdate()              │inVO:" + inVO);
		LOG.debug("└────────────────────────┘");

		int flag = postService.doUpdate(inVO);//
		String message = "";
		if (1 == flag) {
			message = inVO.getPostId() + "가 수정 되었습니다.";
		} else {
			message = inVO.getPostId() + "수정 실패";
		}
		MessageVO messageVO = new MessageVO(flag + "", message);// package com.pcwk.ehr.cmn;
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString              :" + jsonString);

		return jsonString;
	}

	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(PostVO inVO, HttpServletRequest req, Model model)
			throws SQLException, EmptyResultDataAccessException {
		String view = "post/post_mod";
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│ doSelectOne()          │ inVO:" + inVO);
		LOG.debug("└────────────────────────┘");

		String PostId = req.getParameter("PostId");
		LOG.debug("PostId              :" + PostId);

		PostVO outVO = this.postService.doSelectOne(inVO);
		LOG.debug("outVO              :" + outVO);

		model.addAttribute("outVO", outVO);
		return view;
	}

	@RequestMapping(value = "/doDelete.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(PostVO inVO, HttpServletRequest req) throws SQLException {
		String jsonString = "";
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│doDelete()              │inVO:" + inVO);
		LOG.debug("└────────────────────────┘");

		String PostId = req.getParameter("PostId");
		LOG.debug("PostId              :" + PostId);

		int flag = postService.doDelete(inVO);//
		String message = "";

		if (1 == flag) {
			message = inVO.getPostId() + "가 삭제 되었습니다.";

		} else {
			message = inVO.getPostId() + "삭제 실패";
		}
		MessageVO messageVO = new MessageVO(String.valueOf(flag), message);
		jsonString = new Gson().toJson(messageVO);

		LOG.debug("jsonString:" + jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/doSave.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSave(PostVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("┌────────────────────────┐");
		LOG.debug("│doSave()                │inVO:" + inVO);
		LOG.debug("└────────────────────────┘");

		int flag = postService.doSave(inVO);
		String message = "";

		if (1 == flag) {
			message = inVO.getPostId() + "가 등록 되었습니다.";
		} else {
			message = inVO.getPostId() + "등록 실패";
		}

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString:" + jsonString);

		return jsonString;
	}
}
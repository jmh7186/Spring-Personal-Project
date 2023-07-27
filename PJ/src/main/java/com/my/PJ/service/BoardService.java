package com.my.PJ.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.my.PJ.mapper.BoardMapper;
import com.my.PJ.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper mapper;
	
	public BoardPageList pageList(int currentPage, int countPerPage) {
		int totalPage = 1;
		int totalCount = mapper.totalCount();
		int startPage;
		int endPage;
		int endRow = currentPage * countPerPage;
		
		if (totalCount%countPerPage!=0) {
			totalPage = totalCount/countPerPage+1;
		} else {
			totalPage = totalCount/countPerPage;
		}
		if ( currentPage/5<1 ) {
			startPage=1;
			endPage=5;
		}else {
			startPage=currentPage-(currentPage%5);
			endPage=startPage+5;
		}
		if(endPage>totalPage){
			endPage=totalPage;
		}
		if(totalPage == currentPage) {
			countPerPage = totalCount%countPerPage;
		}
		if (countPerPage<10) {
			endRow = mapper.totalCount();
		}
		BoardPageList pl = new BoardPageList(totalCount, countPerPage, totalPage, startPage, endPage, currentPage, mapper.findPage(endRow, countPerPage));
		return pl;
	}
	
	public List<BoardVO> findList(String keyword, String kind) {
		return mapper.findList(keyword, kind);
	}

	public BoardVO findOneById(int idx) {
		return mapper.findOneById(idx);
	}

	public int viewcountUp(int idx) {
		return mapper.viewcountUp(idx);
	}
	
	public int insert(String writeid, String title, String content, MultipartFile mf) {
		BoardVO b = new BoardVO();
		b.setWriteid(writeid);
		b.setTitle(title);
		b.setContent(content);
		try {
			String uploadDir = "C:/Users/admin/Documents/workspace-spring-tool-suite-4-4.19.0.RELEASE/PJ/src/main/webapp/uploadFiles/";
			String filename = mf.getOriginalFilename();
			mf.transferTo(new File(uploadDir+filename));
			b.setFilename(filename);
			return mapper.insert(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapper.insert(b);
	}

	public int updateById(HttpServletRequest request) {
		return mapper.updateById(request);
		
	}

	public int deleteById(int idx) {
		return mapper.deleteById(idx);
	}

	public int replyInsert(HttpServletRequest request) {
		return mapper.replyInsert(request);
	}
}

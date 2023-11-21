package com.my.PJ.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.my.PJ.mapper.BoardMapper;
import com.my.PJ.vo.BoardVO;

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

		if (totalCount % countPerPage != 0) {
			totalPage = totalCount / countPerPage + 1;
		} else {
			totalPage = totalCount / countPerPage;
		}
		if (currentPage / 5 < 1) {
			startPage = 1;
			endPage = 5;
		} else {
			startPage = currentPage - (currentPage % 5);
			endPage = startPage + 5;
		}
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		if (totalPage == currentPage) {
			countPerPage = totalCount % countPerPage;
		}
		if (countPerPage < 10) {
			endRow = mapper.totalCount();
		}
		BoardPageList pl = new BoardPageList(totalCount, countPerPage, totalPage, startPage, endPage, currentPage,
				mapper.findPage(endRow, countPerPage));
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
		if ( mf!=null && !(mf.isEmpty()) ) {
			try {
				String uploadDir = "C:/Users/JoEngie/git/Spring-Personal-Project/PJ/src/main/resources/static/uploadFiles/";
				String filename = mf.getOriginalFilename();
				mf.transferTo(new File(uploadDir + filename));
				b.setFilename(filename);
				mapper.insert(b);
				return b.getIdx(); // 등록된 게시글 IDX번호를 리턴함
			} catch (IOException e) {
				e.printStackTrace();
				mapper.insert(b);
				return b.getIdx();
			}
		}
		mapper.insert(b);
		return b.getIdx();
	}

	public int update(int idx, String writeid, String title, String content, String file, MultipartFile mf) {
		BoardVO b = new BoardVO();
		b.setIdx(idx);
		b.setWriteid(writeid);
		b.setTitle(title);
		b.setContent(content);
		b.setFilename(file);
		if ( mf!=null && !(mf.isEmpty()) ) {
			try {
				String uploadDir = "C:/Users/JoEngie/git/Spring-Personal-Project/PJ/src/main/resources/static/uploadFiles/";
				String originalFilename = mf.getOriginalFilename();
				mf.transferTo(new File(uploadDir + originalFilename));
				b.setFilename(originalFilename);
				mapper.update(b);
				return b.getIdx(); // 수정된 게시글 IDX번호를 리턴함
			} catch (IOException e) {
				e.printStackTrace();
				mapper.update(b);
				return b.getIdx();
			}
		}
		mapper.update(b);
		return b.getIdx();
	}

	public int deleteById(int idx) {
		return mapper.deleteById(idx);
	}

	public int replyInsert(BoardVO b) {
		return mapper.replyInsert(b);
	}

}

package com.my.PJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.PJ.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Mapper
public interface BoardMapper {
	
	public List<BoardVO> findAll();
	
	public BoardVO findOneById(int idx);
	
	public int deleteById(int idx);
	
	public List<BoardVO> findPage (int endRow, int countPerPage);
	
	public int totalCount();

	public int insert(BoardVO b);

	public int updateById(HttpServletRequest request);

	public int replyInsert(HttpServletRequest request);
	
	public List<BoardVO> findList(String keyword, String kind);
	
	public int totalFindCount(String keyword);
	
	public int viewcountUp (int idx);
}

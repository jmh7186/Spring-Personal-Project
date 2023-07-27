package com.my.PJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.PJ.vo.MemberVO;

@Mapper
public interface MemberMapper {
	public int insert(MemberVO m);

	public List<MemberVO> findAll();

	public MemberVO findById(String mid);

	public int updateById(MemberVO m);

	public int deleteById(String mid);
}

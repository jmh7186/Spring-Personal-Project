package com.my.PJ.service;

import java.util.List;

import com.my.PJ.vo.PhonebookVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhonebookPageList {
	int totalCount; //전체게시물 수 
	int countPerPage; //페이지당 글의 수
	int totalPage; //전체페이지 수
	int startPage; //시작페이지
	int endPage; //끝페이지
	int currentPage;  //현재페이지
	List<PhonebookVO> list;
}

package com.my.PJ.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.PJ.service.*;
import com.my.PJ.vo.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class FrontController {
	
	@Autowired
	MemberService mbservice;
	
	@Autowired
	PhonebookService pbservice;
	
	@Autowired
	BoardService boservice;

	@GetMapping(value = { "/", "/index" })
	public ModelAndView mvIndex() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "home/main.jsp");
		mv.addObject("asidepage", "home/aside.jsp");
		mv.setViewName("index");
		return mv;
	}
	////////////////////////////////////////////////////////////////////////////////로그인/로그아웃
	@GetMapping("/member/login")
	public ModelAndView mvLogin() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "/member/login.jsp");
		mv.setViewName("index");
		return mv;
	}

	@PostMapping("/member/loginProc")
	public ModelAndView mvLoginProc(String id,String pwd, HttpSession request) {
		ModelAndView mv = new ModelAndView();
		MemberVO m = mbservice.findById(id);
		
		if(m!=null && m.getMid().equals(id) && m.getMpwd().equals(pwd)) {
			mv.addObject("mainpage", "/member/loginProc.jsp");
			mv.addObject("result",1);
			request.setAttribute("id", id);
		}else {
			mv.addObject("mainpage", "/member/loginProc.jsp");
			mv.addObject("result",0);
		}
		mv.setViewName("index");
		return mv;
	}
	@GetMapping("/logoutProc")
	public ModelAndView mvLogoutProc(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		session.invalidate();
		mv.addObject("mainpage", "home/main.jsp");
		mv.setViewName("index");
		return mv;
	}
	////////////////////////////////////////////////////////////////////////////////로그인/로그아웃
	////////////////////////////////////////////////////////////////////////////////멤버
	@GetMapping("/member/view")
	public ModelAndView mvMemberView(String id) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "member/viewmember.jsp");
		mv.addObject("member", mbservice.findById(id));
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/member/deleteProc")
	public ModelAndView mvMemberDeleteProc() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "member/deleteProc.jsp");
		mv.setViewName("index");
		return mv;
	}

	@PostMapping("/member/updateProc")
	public ModelAndView mvUpdateProc(String mid, String mpwd, String mhp, String madr) {
		ModelAndView mv = new ModelAndView();
		MemberVO m = new MemberVO(mid,mpwd,mhp,madr);
		
		int result = mbservice.updateById(m);
		mv.addObject("mainpage", "member/updateProc.jsp");
		mv.addObject("result", result);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/signup/contract")
	public ModelAndView mvContract() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "member/contract.jsp");
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/signup")
	public ModelAndView mvSignup() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "member/signup.jsp");
		mv.setViewName("index");
		return mv;
	}

	@PostMapping("/signup/signupProc")
	public ModelAndView mvSignupProc(String id, String pwd, String hp, String adr) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainpage", "member/signupProc.jsp");
		int result = mbservice.insert(new MemberVO(id,pwd,hp,adr));
		
		if(result==1) {
			mv.addObject("mainpage", "member/welcome.jsp");
			mv.addObject("id", id);
		}else {
			mv.addObject("mainpage", "member/signupProc.jsp");
			mv.addObject("result", result);
		}
		mv.setViewName("index");
		return mv;
	}
	////////////////////////////////////////////////////////////////////////////////멤버
	////////////////////////////////////////////////////////////////////////////////지도
	@GetMapping("/map")
	public ModelAndView map(ModelAndView mv) {
		mv.addObject("mainpage", "map/map.jsp");
		mv.setViewName("index");
		return mv;
	}
	////////////////////////////////////////////////////////////////////////////////지도
	////////////////////////////////////////////////////////////////////////////////전화번호부
//	@GetMapping(value = {"/phonebook/pagelist/{page}","/phonebook/pagelist"})
//	public ModelAndView pbList(ModelAndView mv, @PathVariable(required = false, value = "page") Integer page) {
//		if(page==null) {
//			page=1;
//		}
//		mv.addObject("mainpage", "phonebook/pageList.jsp");
//		mv.addObject("pblist", pbservice.pageList(page));
//		mv.setViewName("index");
//		return mv;
//	}
//	
//	@GetMapping("/phonebook/view")
//	public ModelAndView pbView(ModelAndView mv, @RequestParam("idx") String idx) {
//		mv.addObject("p", pbservice.findById(idx));
//		mv.addObject("mainpage", "phonebook/view.jsp");
//		mv.setViewName("index");
//		return mv;
//	}
//	
//	@PutMapping("/phonebook/update")
//	@ResponseBody
//	public int pbUpdate(@RequestBody PhonebookVO p) {
//		return pbservice.updateById(p);
//	}
//	
//	@GetMapping("/phonebook/insert")
//	public ModelAndView pbInsert(ModelAndView mv) {
//		mv.addObject("mainpage", "phonebook/insert.jsp");
//		mv.setViewName("index");
//		return mv;
//	}
//	
//	@PostMapping("/phonebook/insertProc")
//	@ResponseBody
//	public int pbInsertProc(@RequestBody PhonebookVO p) {
//		return pbservice.insert(p);
//	}
//	
//	@DeleteMapping("/phonebook/delete")
//	@ResponseBody
//	public int pbDelete(@RequestBody String idx) {
//		return pbservice.deleteById(idx);
//	}
	//////////////////////////////////////////////////////////////////////////////전화번호부
	//////////////////////////////////////////////////////////////////////////////채팅
	@GetMapping("/chat")
	public ModelAndView pbInsertProc(ModelAndView mv) {
		mv.addObject("mainpage", "chat/chat.jsp");
		mv.setViewName("index");
		return mv;
	}
	//////////////////////////////////////////////////////////////////////////////채팅
	//////////////////////////////////////////////////////////////////////////////게시판
	
	@GetMapping("/board/pagelist")
	public ModelAndView boList(ModelAndView mv, @RequestParam(name = "page", defaultValue = "1") int currentPage) {
		mv.addObject("mainpage","board/pageList.jsp");
		mv.addObject("pagelist",boservice.pageList(currentPage, 10));
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/board/findone")
	public ModelAndView boFindOnd(ModelAndView mv, @RequestParam("idx") int idx) {
		boservice.viewcountUp(idx);
		mv.addObject("b", boservice.findOneById(idx));
		mv.addObject("mainpage","board/findOne.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/board/writeform")
	public ModelAndView boInsert(ModelAndView mv) {
		mv.addObject("mainpage","board/writeForm.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	@PostMapping("/board/writeProc")
	public ModelAndView boInsertProc(	ModelAndView mv,
										@RequestPart("writeid") String writeid,
										@RequestPart("title") String title,
										@RequestPart("content") String content,
										@RequestPart(name = "file", required = false) MultipartFile mf) {
		int _idx = boservice.insert(writeid,title,content,mf);
		mv.setViewName("redirect:/board/findone?idx="+_idx);
		return mv;
	}
	
	@PostMapping("/board/updateProc")
	public ModelAndView boUpdateProc(	ModelAndView mv,
										@RequestPart("idx") String idx,
										@RequestPart("writeid") String writeid,
										@RequestPart("title") String title,
										@RequestPart("content") String content,
										@RequestPart(name = "file", required = false) MultipartFile mf) {
		int _idx = boservice.update(Integer.parseInt(idx),writeid,title,content,mf);
		mv.setViewName("redirect:/board/findone?idx="+_idx);
		return mv;
	}
	
	@GetMapping("/board/deleteProc")
	public ModelAndView boDeleteProc(ModelAndView mv, @RequestParam int idx) {
		boservice.deleteById(idx);
		mv.setViewName("redirect:/board/pagelist");
		return mv;
	}
	//////////////////////////////////////////////////////////////////////////////게시판
	//////////////////////////////////////////////////////////////////////////////국가별 안전정보
	@GetMapping("/overseas")
	public ModelAndView boDeleteProc(ModelAndView mv, @RequestParam(required = false) String pageNo, @RequestParam(required = false) String title) {
		mv.addObject("mainpage","overseas/safetyinfo.jsp");
		if(pageNo==null) mv.addObject("pageNo",1);
		else mv.addObject("pageNo",pageNo);
		mv.addObject("title",title);
		mv.setViewName("index");
		return mv;
	}
	//////////////////////////////////////////////////////////////////////////////국가별 안전정보
	//////////////////////////////////////////////////////////////////////////////갤러리
	@GetMapping("/gallary")
	public ModelAndView gallaryView(ModelAndView mv) {
		File dir = new File("C:/Users/admin/git/Spring-Personal-Project/PJ/src/main/resources/static/uploadPics/");
		File[] files = dir.listFiles();
		List<String> lis = new ArrayList<String>();
		for(File f:files) {
			lis.add(f.getName());
		}
		mv.addObject("mainpage","overseas/gallary.jsp");
		mv.addObject("pics",lis);
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/gallary/upload")
	public String gallaryUpload() {
		return "overseas/gallaryupload";
	}
	
	@PostMapping("/gallary/uploadproc")
	@ResponseBody
	public int gallaryUploadProc(ModelAndView mv, @RequestPart("pic") MultipartFile mf) {
		if ( mf!=null && !(mf.isEmpty()) ) {
			try {
				String uploadDir = "C:/Users/admin/git/Spring-Personal-Project/PJ/src/main/resources/static/uploadPics/";
				LocalDate ld = LocalDate.now();
				String filename = ld.toString() +"_"+ mf.getOriginalFilename();
				mf.transferTo(new File(uploadDir + filename));
				return 1;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	//////////////////////////////////////////////////////////////////////////////갤러리
}

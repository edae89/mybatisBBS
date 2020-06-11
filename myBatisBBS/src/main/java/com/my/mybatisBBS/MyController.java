package com.my.mybatisBBS;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.DTO.MemberDTO;
import com.my.mapper.MapperDAO;
import com.my.service.ControllerToModel;
import com.my.service.MyTestService;

@Controller // bean을 주입하는 방식. 이때 ControllerToModel타입으로 주입
public class MyController {
	@Autowired
	ControllerToModel tomodel;
	
	@RequestMapping("bbsConlist")
	public String list(Model model) {
//		MyTestService k = new MyTestService();
		model.addAttribute("list", tomodel.selectAll());
		//System.out.println("DB에서 가져온 튜플 수 :"+DTOs.size());
		return "bbs/list";
	}
	
//	@RequestMapping("del")
//	public String listDelete(Model model) {
//		mapper = sqlSession.getMapper(MapperDAO.class);
//		mapper.deleteAll();
//		ArrayList<MemberDTO> DTOs = mapper.selectAll();
//		model.addAttribute("list", DTOs);
//		System.out.println("DB에서 가져온 튜플 수 :"+DTOs.size());
//		return "list";
//	}
	
	@RequestMapping("bbsConinput")
	public String inputform() {
		return "bbs/input";
	}
	
	@RequestMapping("bbsConinputprocess")
	public String inputDB(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println(id+"/"+name);
		tomodel.insert(id, name);
		//mybatis에 접근하여 전달
		
		return "redirect:bbs/list";
	}
	
	@RequestMapping("bbsCondel")	//방법1 HttpServletRequest
	public String del(HttpServletRequest request){
		String id = request.getParameter("id");
		tomodel.del(id);
		return "redirect:bbs/list";
	}
	
	
	@RequestMapping("bbsConmodify") //방법2 어노테이션 RequestParam
	public String mod(@RequestParam("id") String id, Model model) {
		model.addAttribute("userInfo", tomodel.selectOne(id));
		return "bbs/modify";
	}
	
	@RequestMapping("bbsConmodifyprocess_bak") //방법3 객체로 받기
	public String modp(@ModelAttribute MemberDTO user) {
		System.out.println(user.getId()+"/"+user.getName());
		tomodel.modify(user);
		return "redirect:bbs/list";
	}
	
	@RequestMapping("bbsConmodifyprocess")
	public String modpHash(HttpServletRequest request) {
		HashMap<String,String> h = new HashMap();
		h.put("id",request.getParameter("id"));
		h.put("name", request.getParameter("name"));
		tomodel.modifyhash(h);
		
		return "redirect:bbs/list";
	}
	
	
}

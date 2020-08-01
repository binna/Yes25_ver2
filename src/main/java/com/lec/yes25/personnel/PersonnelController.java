package com.lec.yes25.personnel;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lec.yes25.common.C;
import com.lec.yes25.personnel.command.EmpListCommand;

@Controller
@RequestMapping("/personnel")
public class PersonnelController implements Username{

	// 컨트롤러는 서버가 가동될 때 생성되며, 스프링 컨테이너에 생성이 된다 .
	//MyBatis  -> setter 만들어서 autowired 하기 
	private SqlSession sqlSession;
		
	public PersonnelController() {
		super();
		System.out.println("PersonnelController() 생성");
	}

	@Autowired  
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		C.sqlSession = sqlSession;
	}

	@GetMapping("/main")
	public void personnelMain(HttpServletRequest request, Model model) {
		System.out.println("----------------   PersonnelController 입니다  " + username + " -----------------------\n" 
					+ "personnel/main 경로로...");

		model.addAttribute("username", username);
		new EmpListCommand().execute(model);
	}

	@GetMapping("/commutelist")
	public void personnelClist() {
		System.out.println("personnel/commutelist 경로로...");
	}

	@GetMapping("/login")
	public void loginInput(String error, String logout, Model model) {

		System.out.println("error : " + error); // 로그인 실패했을 때 담기는 에러 메시지임
		System.out.println("logout : " + logout);
		System.out.println("personnel/login 경로로... ----> 여기는 로그인화면!!!!");

		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Accout(계정 다시 확인하라우)");
		}

		if (logout != null) {
			model.addAttribute("logout", "LogOut!!!!!!");
		}
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public void logoutGet() {
		System.out.println("logouttttttt");
	}

}

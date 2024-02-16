package controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.jhta.model2.annotation.Controller;
import kr.co.jhta.model2.annotation.RequestMapping;
import kr.co.jhta.model2.constant.HttpMethod;
import service.UserService;
import vo.User;

@Controller
public class HomeController {
	
	// 값을 담는 객체는 절대로 멤버변수에 정의하면 안되고 매번 새로 생성해야 한다.
	// 기능을 제공하는 객체는 멤버변수로 1개 생성(모든 메소드가 접근 가능), 읽기전용
	// 메소드안에서 생성하면 메소드 안에서만 사용가능
	// final : 값을 절대로 변경할 수 없음
	private final UserService userService = new UserService();
	
	@RequestMapping(path = "/home.do")
	public String home(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// home.jsp로 내부이동
		return "home.jsp";
	}
	
	@RequestMapping(path = "/login.do", method = HttpMethod.GET)
	public String loginform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "loginform.jsp";
	}
	
	@RequestMapping(path = "/login.do", method = HttpMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		try {
			User user = userService.login(id, password);
			// getSession()는 getSession(true)와 같다. 
			// 요청메세지에 세션아이디가 없으면, 세션객체를 새로 생성해서 반환한다.
			// 요청메세지에 세션아이디가 있지만 세션아이디에 해당하는 세션객체가 타임아웃 경과로 이미 파괴되었다면 세션객체를 새로 생성해서 반환한다.
			// 요청메세지에 세션아이디가 있고, 세션아이디에 해당하는 세션객체가 발견되면 그 세션객체를 반환한다.
			HttpSession session = req.getSession();
			session.setAttribute("LOGIN_USER", user);
			
			return "redirect:home.do";
		} catch (Exception ex) {
			return "redirect:login.do?error=fail";
		}
	}
	
	@RequestMapping(path = "/logout.do")
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// getSession(false)
		// 요청메세지에 세션아이디가 없으면, null을 반환한다.
		// 요청메세지에 세션아이디가 있지만 세션아이디에 해당하는 세션객체가 타임아웃 경과로 이미 파괴되었다면 null을 반환한다.
		// 요청메세지에 세션아이디가 있고, 세션아이디에 해당하는 세션객체가 발견되면 그 세션객체를 반환한다.
		HttpSession session = req.getSession(false);
		if (session != null) {
			// invalidate() : 세션을 무효화 시키는 메소드
			session.invalidate();
		}
		
		return "redirect:home.do";
	}	
	
	// 회원가입 버튼 클릭 -> GET 방식, register.do를 요청
	@RequestMapping(path = "/register.do", method = HttpMethod.GET)
	public String form(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		return "registerform.jsp";
	}
	
	@RequestMapping(path = "/register.do", method = HttpMethod.POST)
	public String register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 요청파라미터값 조회하기
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		
		/* Console 창에 출력하기
		System.out.println("아이디: " + id);
		System.out.println("비밀번호: " + password);
		System.out.println("이름: " + name);
		System.out.println("전화번호: " + tel);
		System.out.println("이메일: " + email); */
		
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setTel(tel);
		user.setEmail(email);
		
		userService.register(user);
		
		// 재요청 URL을 보냄. 외부이동
		return "redirect:home.do";
	}
} 
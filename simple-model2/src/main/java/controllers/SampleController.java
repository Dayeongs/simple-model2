package controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Department;
import vo.Employee;

@WebServlet(urlPatterns = "/sample")
public class SampleController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 뷰에 전달할 값을 요청객체에 속성으로 저장하기
		Department department = new Department(10, "영업1팀", "02)1234-5678", "070-1111-2222");
		request.setAttribute("dept", department);
		
		Employee employee = new Employee();
		employee.setId(1001);
		employee.setName("김유신");
		employee.setTel("010-1200-1239");
		employee.setEmail("kim@gmail.com");
		employee.setDept(department);
		request.setAttribute("emp", employee);
		
		/* 뷰에 전달할 값을 요청객체에 지정하기
		request.setAttribute("id", 10000);
		request.setAttribute("name", "김유신");
		request.setAttribute("tel", "010-1234-5678"); */
		
		// 뷰로 내부이동하기
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/sample.jsp");
		// forward(): 내부이동 시켜주는 메소드
		dispatcher.forward(request, response);
		
	}
	
} 
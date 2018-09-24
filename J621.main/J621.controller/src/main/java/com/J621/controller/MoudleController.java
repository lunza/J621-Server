package com.J621.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/J621/common")
@EnableScheduling
public class MoudleController {
	
	
	@RequestMapping(value = "/toPage", method = RequestMethod.GET)
	//@ResponseBody
	public void toPage(@RequestParam(value = "pageName", required = true) String pageName,
			HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("/WEB-INF/jsp/"+pageName+".jsp");
		//request.getRequestDispatcher("/WEB-INF/jsp/"+pageName+".jsp").forward(request, response);
		//return pageName;
		
	}
	

}

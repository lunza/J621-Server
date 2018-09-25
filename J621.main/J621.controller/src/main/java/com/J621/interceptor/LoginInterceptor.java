package com.J621.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.J621.vo.J621User;

public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * Handler执行完成之后调用这个方法
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc)
			throws Exception {

	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * Handler执行之前调用这个方法
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截器执行");
		// 获取请求的URL
		String url = request.getRequestURI();
		System.out.println(url);
		
		// 获取Session
		J621User user = (J621User)request.getSession().getAttribute("user");
		System.out.println(user);
		if (user != null) {
			System.out.println("放行");
			return true;
		}
		// 不符合条件的，跳转到登录界面
		request.getRequestDispatcher("login.jsp").forward(request, response);
		System.out.println("拦截");
		return false;
	}

}

package ky.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ky.entity.TSysUser;

public class PowerCheckFilter implements Filter {
	
	String[] excludedPageArray = null;

	public void destroy() {
		System.out.println("destroy");
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse reponse = (HttpServletResponse) arg1;
		TSysUser user = (TSysUser)request.getSession().getAttribute("user");
		String url = request.getRequestURI();
		System.out.println("[PowerCheckFilter]url:" + url);
		if(user == null){
			//没有登录时,不能访问除登录以外的action
			if(url.endsWith(".action") && url.indexOf("login")<0 && url.indexOf("verifycode")<0){
				request.getSession().setAttribute("toLogin", "yes");
				reponse.sendRedirect(request.getContextPath());
			}
			//没有登录时,不能访问upload目录和file目录的文件
			if(url.indexOf("upload")>=0 || url.indexOf("file")>=0){
				request.getSession().setAttribute("toLogin", "yes");
				reponse.sendRedirect(request.getContextPath());
			}
		}
		
		chain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("[PowerCheckFilter]init...");
	}

}

package com.fangzhizun.cowxgzh.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CheckoutDataSource {


	@Pointcut("execution(* com.fangzhizun.cowxgzh.service..*.*(..))")
	public void domain(){

	}

	@Before("domain()")
	public void checkout(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String company =(String) request.getSession().getAttribute("companyName");

		System.out.println(company);
		if(company == null){
			company = request.getParameter("co");
		}
		DataSourceContextHolder.setDbType(company);
		System.out.println("当前数据库："+company);
	}
}

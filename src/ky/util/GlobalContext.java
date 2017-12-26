package ky.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GlobalContext implements ApplicationContextAware {
	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		context = contex;
	}
	public static ApplicationContext getContext() {
		return context;
	}
	public final static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	public final static Object getBean(String beanName, Class<?> requiredType) {
		return context.getBean(beanName, requiredType);
	}
}
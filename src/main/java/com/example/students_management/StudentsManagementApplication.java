package com.example.students_management;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

import javax.naming.AuthenticationException;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
public class StudentsManagementApplication {

	private static Logger log = LoggerFactory.getLogger(StudentsManagementApplication.class);

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public void handlerException(AuthenticationException exception) {
		log.debug("{} was thrown", exception.getClass(), exception);
	}

	@Bean
	public Realm realm() {
		// uses 'classpath:shiro_users.properties' by default
		PropertiesRealm realm = new PropertiesRealm();
		return realm;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		chainDefinition.addPathDefinition("/**", "authcBasic");
		return chainDefinition;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentsManagementApplication.class, args);
	}

	//Shiro使@RequestMapping失效，调用404异常
	@Bean
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setUsePrefix(true);
		return defaultAdvisorAutoProxyCreator;
	}

}

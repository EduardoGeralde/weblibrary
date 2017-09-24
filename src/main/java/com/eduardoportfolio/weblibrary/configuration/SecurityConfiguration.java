package com.eduardoportfolio.weblibrary.configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Has to be placed above the class that is responsible for effectively controls the access rules. This annotation
//load another components as well, like SecurityExpressionHandler (Evaluate specific languages of security control)
//and WebInvocationPrivilegeEvaluator (responsible to verify if the user has access for the address)
@EnableWebSecurity
//This class inherits all the infrastructure ready to begin our security configurations 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

}

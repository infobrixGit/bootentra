// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.example.bootentra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * AADWebSecurityConfigurer (AADWSC) is an extension of Spring's WebSecurityConfigurer (WSC).
 * 
 * You must extend AADWSC to define your own custom configuration in the configure() method.
 * Be sure to call super.configure() first. This will set up all of your AuthN/AuthZ properly.
 * 
 * You may omit this by not extending the AADWSC class.
 * 
 * If you don't extend AADWSC or WSC, AAD boot starter will create a DefaultAADWebSecurityConfigurerAdapter
 * bean automatically, and define its own default http.authorizeRequests() rule (authorize ALL requests).
 * 
 * See DefaultAADWebSecurityConfigurerAdapter in com.azure.spring.aad.webapp.AADWebAppConfiguration
 */

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends AADWebSecurityConfigurerAdapter{
//  @Value( "${app.protect.authenticated}" )
//  private String[] protectedRoutes;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//    // use required configuration from AADWebSecurityAdapter.configure:
//    super.configure(http);
//    // add custom configuration:
//    http.authorizeRequests()
//      .antMatchers(protectedRoutes).authenticated()     // limit these pages to authenticated users (default: /token_details)
//      .antMatchers("/**").permitAll();                  // allow all other routes.
//    }
//}
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {

  @Value( "${app.protect.authenticated}" )
  private String[] protectedRoutes;
  /**
   * Add configuration logic as needed.
   */
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeRequests()
            .requestMatchers("/", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login(withDefaults());
      return http.build();
  }
}
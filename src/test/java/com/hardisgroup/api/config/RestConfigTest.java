package com.hardisgroup.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Test Rest configuration class.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hardisgroup.api.*")
public class RestConfigTest {

}

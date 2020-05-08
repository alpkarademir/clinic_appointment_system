package com.config;


import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }
	
	@Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{};
    }

    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        super.logger.info("Initializing servlet configuration...");
        return new Class[]{AppConfig.class};
    }
}
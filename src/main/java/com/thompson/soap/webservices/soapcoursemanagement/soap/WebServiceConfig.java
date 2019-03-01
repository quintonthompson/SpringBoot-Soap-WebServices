package com.thompson.soap.webservices.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig {
    //MessageDispatcherServlet
        //Application context
        //url -> /ws/*

    //Defined a servlet to handle all the requests and mapped to /ws/*
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
    }

    // /ws/courses.wsdl
    //course-details.xsd
    //Use the course-details to create the WSDL

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema){
            DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        //CoursePort
        definition.setPortTypeName("CoursePort");
        //Namespace - "http://www.quintonthompson.com/courses"
        definition.setTargetNamespace("http://www.quintonthompson.com/courses");
        // /ws
        definition.setLocationUri("/ws");
        //schema
        definition.setSchema(coursesSchema);
            return definition;
    }

    //This creates a schema to help define the WSDL
    @Bean
    public XsdSchema coursesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }

}

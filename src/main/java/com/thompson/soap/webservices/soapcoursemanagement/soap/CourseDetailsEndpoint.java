package com.thompson.soap.webservices.soapcoursemanagement.soap;

import com.thompson.coursedetails.CourseDetails;
import com.thompson.coursedetails.GetCourseDetailsRequest;
import com.thompson.coursedetails.GetCourseDetailsResponse;
import com.thompson.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.thompson.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    @PayloadRoot(namespace = "http://www.quintonthompson.com/courses",localPart = "GetCourseDetailsRequest")//if request has these names it will map to this method
    @ResponsePayload //This method will contain the response payload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        Course course = service.findById(request.getId());
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        response.setCourseDetails(courseDetails);
        return response;
    }
}


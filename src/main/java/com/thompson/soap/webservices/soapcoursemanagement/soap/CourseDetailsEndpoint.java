package com.thompson.soap.webservices.soapcoursemanagement.soap;

import com.thompson.coursedetails.*;
import com.thompson.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.thompson.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    @PayloadRoot(namespace = "http://www.quintonthompson.com/courses",localPart = "GetCourseDetailsRequest")//if request has these names it will map to this method
    @ResponsePayload //This method will contain the response payload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
        Course course = service.findById(request.getId());

        return mapCourseDetails(course);
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    @PayloadRoot(namespace = "http://www.quintonthompson.com/courses",localPart = "GetAllCourseDetailsRequest")//if request has these names it will map to this method
    @ResponsePayload //This method will contain the response payload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){
        List<Course> courses = service.findAllCourses();

        return mapAllCourseDetails(courses);
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

        //GetAllCoursesResponse has a list because the max occurs are unbounded
        //since response is now an array defined in GetAllCoursesResponse class we each map to response
        for(Course course : courses){
            CourseDetails mapCourse = mapCourse(course);
            response.getCourseDetails().add(mapCourse);
        }
        return response;
    }

    @PayloadRoot(namespace = "http://www.quintonthompson.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request){
       int status = service.deleteById(request.getId());

       DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
       response.setStatus(status);
       return response;
    }
}


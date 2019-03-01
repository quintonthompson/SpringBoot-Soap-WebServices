package com.thompson.soap.webservices.soapcoursemanagement.soap;

import com.thompson.coursedetails.CourseDetails;
import com.thompson.coursedetails.GetCourseDetailsRequest;
import com.thompson.coursedetails.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {
    //method
    //input-GetCourseDetailsRequest
    //output-GetCourseDetailsResponse

    @PayloadRoot(namespace = "http://www.quintonthompson.com/courses",localPart = "GetCourseDetailsRequest")//if request has these names it will map to this method
    @ResponsePayload //This method will contain the response payload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("This is a wonderful course!");
        response.setCourseDetails(courseDetails);
        return response;
    }
}


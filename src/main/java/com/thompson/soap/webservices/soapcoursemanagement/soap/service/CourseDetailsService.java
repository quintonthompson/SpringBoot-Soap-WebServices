package com.thompson.soap.webservices.soapcoursemanagement.soap.service;

import com.thompson.soap.webservices.soapcoursemanagement.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static{
        Course course1 = new Course(1,"Spring", "10 steps");
        courses.add(course1);

        Course course2 = new Course(2,"Spring MVC", "10 examples");
        courses.add(course2);

        Course course3 = new Course(3,"Spring Boot", "6k students");
        courses.add(course3);

        Course course4 = new Course(4,"Maven", "Most popular maven course");
        courses.add(course4);
    }

    public Course findById(int id){
        for (Course course : courses){
            if(course.getId() == id ){
                return course;
            }
        }
        return null;
    }

    public List findAllCourses(){
        return new ArrayList(courses);
    }

    public int deleteById(int id) {
        //Using iterator to avoid concurrent modification
        synchronized (courses) {
        Iterator<Course> iterator = courses.iterator();
            while (iterator.hasNext()) {
                Course course = iterator.next();
                if (course.getId() == id) {
                    iterator.remove();
                    return 1;
                }
            }
            return 0;
        }
    }

}

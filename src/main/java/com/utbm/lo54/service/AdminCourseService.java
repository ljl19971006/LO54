package com.utbm.lo54.service;

import com.utbm.lo54.bean.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface AdminCourseService {


    public Course saveone(Course c);//create a new course

    @Transactional
	public String deleteCourse(int id);//delete a course

}

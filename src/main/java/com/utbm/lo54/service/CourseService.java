package com.utbm.lo54.service;


import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.CourseSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
//import com.us.example.bean.User;

/**
 * The Interface CourseService.
 */
public interface CourseService {


	public List<CourseSession> getCourseByWord(String word);

	public CourseSession getCourseById(Long id);

	public List<CourseSession> getall();

	public CourseSession saveone(CourseSession courseSession);
//	public CourseSession getCourseByCourseId(long id);

	public List<CourseSession> getCourseByDate(String date) ;

	public List<CourseSession> getCourseByDateAndLocation(String date, String location);

	public List<CourseSession> getCourseSessionOutofdate(String date);
	@Transactional
	public String updateCourseSessionInClientNull(int cs_id);
	@Transactional
	public void deleteCourseSessionById(Long cs_id);

	public CourseSession saveCourseSessioninfo(CourseSession courseSession);

	public Course saveCourseInfo(Course course);



}

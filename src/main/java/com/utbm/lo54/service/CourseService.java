package com.utbm.lo54.service;


import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.CourseSession;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
//import com.us.example.bean.User;

/**
 * The Interface StudentService.
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

	public String updateCourseSessionInClientNull(int cs_id);

	public boolean deleteCourseSessionById(int cs_id);

	public CourseSession saveCourseSessioninfo(CourseSession courseSession);

	public Course saveCourseInfo(Course course);
//	public students saveone(students students);
//	@Transactional
//	public String deleteUser(String cas,String mdl);
//	@Transactional
//	public String deleteUser(Long id);
//
//	public students findChem(Long id);
//
//	public List<students> getAll();
//
//	students getUserByInchikey(String inchikey);
////
//	students getUserBySmiles(String smiles);
//
//	students getUserById(Long id);
//
//	String UpdateUser(students students);
}

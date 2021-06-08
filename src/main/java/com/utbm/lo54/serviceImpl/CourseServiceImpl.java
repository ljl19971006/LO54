package com.utbm.lo54.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.CourseSession;
import com.utbm.lo54.dao.CourseJpaDao;
import com.utbm.lo54.dao.CourseSessionJpaDao;
import com.utbm.lo54.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseSessionJpaDao courseSessionJpaDao;
    @Autowired
    private CourseJpaDao courseJpaDao;


    @Override
    public List<CourseSession> getCourseByWord(String w) {
        return courseSessionJpaDao.findByCourse_Title(w);
    }

    @Override
    public CourseSession getCourseById(Long id){
        return courseSessionJpaDao.findById(id);
    }
//
//    @Override
//    public CourseSession getCourseByCourseId(long id) {
//        return courseSessionJpaDao.findByCourseId(id);
//    }

    @Override
    public CourseSession saveone(CourseSession courseSession) {
        return courseSessionJpaDao.save(courseSession);
    }

    @Override
    public List<CourseSession> getall() {
        return courseSessionJpaDao.findAllByCourse();
    }

    @Override
    public List<CourseSession> getCourseByDate(String date)  {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = simpleDateFormat.parse(date);
//        System.out.println(JSON.toJSON(date1));
        System.out.println(date);
        return courseSessionJpaDao.findByStartDate(date);
    }

    @Override
    public List<CourseSession> getCourseByDateAndLocation(String date, String location) {

        return courseSessionJpaDao.findByStartDateAndLocationCity(date,location);

    }

    @Override
    public List<CourseSession> getCourseSessionOutofdate(String date){
        return courseSessionJpaDao.findOutofdate(date);
    }

    @Override
    public String updateCourseSessionInClientNull(int cs_id){
        courseSessionJpaDao.checkid1andsetNull(cs_id);
        courseSessionJpaDao.checkid2andsetNull(cs_id);
        return "updating";
    }

    @Override
    public void deleteCourseSessionById(Long cs_id){
         courseSessionJpaDao.deleteCourseSessionById(cs_id);
    }

    @Override
    public CourseSession saveCourseSessioninfo(CourseSession courseSession){
        return courseSessionJpaDao.saveCourseSession(courseSession);
    }

    @Override
    public Course saveCourseInfo(Course course){
        return courseJpaDao.saveCourse(course);
    }
}

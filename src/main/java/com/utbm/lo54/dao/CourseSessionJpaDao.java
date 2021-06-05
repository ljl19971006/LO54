package com.utbm.lo54.dao;


import com.utbm.lo54.bean.Client;
import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * The Interface UserJpaDao.
 * @author abel
 */
public interface CourseSessionJpaDao extends JpaRepository<CourseSession, Long> {

    CourseSession findById(Long id);

    @Query(value = "select  * from course_session c inner join course co on c.course_id=co.id "
            ,nativeQuery = true)
    List<CourseSession> findAllByCourse();
//    @Query(value = "SELECT  * from course_session c where c.start_date=?1", nativeQuery = true)
//    CourseSession findByStartDate(String date);
    List<CourseSession> findByStartDate(String date);

    @Query(value = "select  * from course_session c inner join course co on c.course_id=co.id " +
            "where co.title like?1",nativeQuery = true)
    List<CourseSession> findByCourse_Title(String code);



    @Query(value = "select  * from course_session c inner join location l on c.location_id=l.id " +
            "where c.start_date=?1 and l.city=?2",nativeQuery = true)
    List<CourseSession> findByStartDateAndLocationCity(String date, String location);




}

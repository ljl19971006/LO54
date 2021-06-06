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

    List<CourseSession> deleteCourseSessionByEndDate(String date);

    @Query(value = "select  * from course_session c inner join location l on c.location_id=l.id " +
            "where c.start_date=?1 and l.city=?2",nativeQuery = true)
    List<CourseSession> findByStartDateAndLocationCity(String date, String location);

    @Query(value = "select * from course_session where start_date < #{date}",nativeQuery = true)
    List<CourseSession> findOutofdate(String date);

    @Query(value = "update client set course_Session_Id1 = Null where course_Session_Id1 = #{cs_id}",nativeQuery = true)
    public String checkid1andsetNull(int cs_id);

    @Query(value = "update client set course_Session_Id2 = Null where course_Session_Id2 = #{cs_id}",nativeQuery = true)
    public String checkid2andsetNull(int cs_id);

    @Query(name = "deleteById",value = "delete from course_session where id=#{cs_id};",nativeQuery = true)
    public boolean deleteById(int cs_id);

    @Query(name = "saveCourseSession",value = "insert INTO client (id,isfull,max,course_id,location_id,end_date,start_date,current_number) " +
            "values (#{id},#{isfull},#{max},#{course_id},#{location_id},#{end_date},#{start_date},#{current_number})" +
            "ON DUPLICATE KEY UPDATE isfull = #{isfull},max = #{max},course_id = #{course_id},location_id = #{location_id}," +
            "end_date = #{end_date},start_date =#{start_date},current_number = #{current_number};",nativeQuery = true)
    public CourseSession saveCourseSession(CourseSession courseSession);
}

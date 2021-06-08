package com.utbm.lo54.dao;


import com.utbm.lo54.bean.Client;
import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * The Interface CourseJpaDao.
 */
public interface CourseJpaDao extends JpaRepository<Course, Long> {

    List<Course> findByTitleLike(String word);

    Course findById(int id);

    @Query(value="insert INTO course (id,title,code) values (#{id},#{title},#{code} " +
            "ON DUPLICATE KEY UPDATE title = #{title},code = #{code}",nativeQuery = true)
    Course saveCourse(Course course);




}

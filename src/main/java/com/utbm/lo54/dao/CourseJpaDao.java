package com.utbm.lo54.dao;


import com.utbm.lo54.bean.Client;
import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * The Interface UserJpaDao.
 * @author abel
 */
public interface CourseJpaDao extends JpaRepository<Course, Long> {

    List<Course> findByTitleLike(String word);

    Course findById(int id);




}

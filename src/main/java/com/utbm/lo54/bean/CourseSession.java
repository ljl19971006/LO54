package com.utbm.lo54.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
//many to one
@Entity
@Table(name = "course_session")
public class CourseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "start_date")
    private String startDate;


    @Column(name = "end_date")
    private String endDate;


    @Column(name = "max")
    private int max;


    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name="isfull")
    private boolean isfull;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseSession(boolean isfull) {
        this.isfull = isfull;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getStartDate() {
        return startDate;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;}

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isIsfull() {
        return isfull;
    }

    public void setIsfull(boolean isfull) {
        this.isfull = isfull;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CourseSession(){}


}

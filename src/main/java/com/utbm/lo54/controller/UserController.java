package com.utbm.lo54.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import com.utbm.lo54.bean.Client;
import com.utbm.lo54.bean.Course;

import com.utbm.lo54.bean.CourseSession;
import com.utbm.lo54.service.ClientService;
import com.utbm.lo54.service.CourseService;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import javax.xml.crypto.Data;
import javax.xml.ws.soap.Addressing;


@Controller
//@RequestMapping(value = "/request")
public class UserController {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserController.class));

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClientService clientService;


    @GetMapping("getAllClient")
    @ResponseBody
    List<Client> getAllClient() {
        return clientService.getAllClient();
    }


    /**
     * 通过spring data jpa 调用方法
     * api :localhost:8099/byKeyWord?k=JAVA
     */
//完善大小写转化处理
    @GetMapping("byKeyWord")
    @ResponseBody
    List<CourseSession> getCourseByKeyWord(String k) {
        String word = "%" + k + "%";
        return courseService.getCourseByWord(word);
    }

    //api :localhost:8099/byword?word=JAVA
    @GetMapping("byword")
//    @ResponseBody
    String getCoursebykeyword(@RequestParam(value = "word") String word, HttpSession session) {

        String k = "%" + word + "%";
        List<CourseSession> selectsession = courseService.getCourseByWord(k);
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "motcle";
    }

    @GetMapping("index")
//    @ResponseBody
    String initial(HttpSession session) {
        removeCourseSessionOutOfDate();
        List<CourseSession> selectsession = courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "hello";
    }

    @GetMapping("motcle")
//    @ResponseBody
    String motcle(HttpSession session) {
        List<CourseSession> selectsession = courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "motcle";
    }

    @GetMapping("dateandlocation")
//    @ResponseBody
    String searchdateandlocation(HttpSession session) {
        List<CourseSession> selectsession = courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "dateandlocation";
    }


    @GetMapping("dateindex")
//    @ResponseBody
    String dateindex(HttpSession session) {
        List<CourseSession> selectsession = courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "datesearch";
    }


    //http://localhost:8099/byDate?byDate=2021-05-08%2018:15:00
    @GetMapping("byDate")
//    @ResponseBody
    String getCourseByDate(@RequestParam(value = "byDate") String byDate, HttpSession session) {
        List<CourseSession> selectsession = courseService.getCourseByDate(byDate);
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "datesearch";
    }

    //http://localhost:8099/date?date=2021-05-08%2018:15:00
    @RequestMapping(value = "date")
    @ResponseBody
    List<CourseSession> searchbydate(@RequestParam(value = "date") String byDate, HttpSession session) {

        return courseService.getCourseByDate(byDate);
    }

    //    @GetMapping("removeCourseSessionOutofdate")
//    @ResponseBody
    void removeCourseSessionOutOfDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datestring = dateFormat.format(date);
        System.out.println(datestring);
        List<CourseSession> CS_list = courseService.getCourseSessionOutofdate(datestring);

        for (CourseSession courseSession : CS_list) {
            long cs_id = courseSession.getId();
            System.out.println(courseSession.toString());
            courseService.updateCourseSessionInClientNull((int) cs_id);
            courseService.deleteCourseSessionById(cs_id);
            System.out.println("Remove finished");
        }
    }

    @RequestMapping("saveCourseSessionInfo")
    public CourseSession saveCourseSessionInfo(@RequestBody CourseSession courseSession) {
        return courseService.saveCourseSessioninfo(courseSession);
    }

    @RequestMapping("saveCourseInfo")
    public Course saveCourseInfo(@RequestBody Course course) {
        return courseService.saveCourseInfo(course);
    }


    @GetMapping("bydateandlocation")
//    @ResponseBody
    String getCourseByDateandlocation(@RequestParam(value = "date") String date,
                                      @RequestParam(value = "location") String location,
                                      HttpSession session) {
        List<CourseSession> selectsession = courseService.getCourseByDateAndLocation(date, location);
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()) {
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "dateandlocation";
    }

    //http://localhost:8099/byDateAndLocation?date=2021-05-08 18:15:00&location=Belfort
    @GetMapping("byDateAndLocation")
    @ResponseBody
    List<CourseSession> getCourseByDateAndLocation(String date, String location) {
        return courseService.getCourseByDateAndLocation(date, location);
    }


    @RequestMapping(value = "/inscrire")
    public String createShop(Model model, @RequestParam String name, @RequestParam String prenom,
                             @RequestParam String adresse, @RequestParam String telephone, @RequestParam Long coursesessionid) {
        Client client1 = null;
        try {
            client1 = clientService.getClientBy4infos(name, prenom, adresse, telephone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (client1==null)
        {
            client1=new Client();
            client1.setLastName(name);
            client1.setFirstName(prenom);
            client1.setAddress(adresse);
            client1.setPhone(telephone);
            client1.setCourse_Session_Id1(coursesessionid);
            clientService.saveClientInfo(client1);
            CourseSession c = courseService.getCourseById(coursesessionid);
            c.setCurrentNumber(c.getCurrentNumber()+1);
            courseService.saveone(c);
            return "inscrire_success";
        }
        System.out.println(client1.toString());
        CourseSession c = courseService.getCourseById(coursesessionid);
        if (client1.getCourse_Session_Id1() == null) {
            client1.setCourse_Session_Id1(coursesessionid);
            clientService.saveClientInfo(client1);
            c.setCurrentNumber(c.getCurrentNumber() + 1);
            if (c.getCurrentNumber() == c.getMax())
                c.setIsfull(true);
            courseService.saveone(c);
            System.out.println(client1.toString());
            return "inscrire_success";
        } else if (client1.getCourse_Session_Id2() == null && client1.getCourse_Session_Id1() != coursesessionid) {
            client1.setCourse_Session_Id2(coursesessionid);
            clientService.saveClientInfo(client1);
            c.setCurrentNumber(c.getCurrentNumber() + 1);
            if (c.getCurrentNumber() == c.getMax())
                c.setIsfull(true);
            courseService.saveone(c);
            System.out.println(client1.toString());
            return "inscrire_success";
        } else {
            return "inscrire_failure";
        }

    }
}



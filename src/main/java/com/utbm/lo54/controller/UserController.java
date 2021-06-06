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
    List<Client> getAllClient(){ return clientService.getAllClient();}

    @GetMapping("removeCourseSessionOutofdate")
    @ResponseBody
    String removeCourseSessionOutofdate(String date){
        List<CourseSession> CS_list = courseService.getCourseSessionOutofdate(date);
        for (CourseSession courseSession : CS_list){
            long cs_id = courseSession.getId();
            courseService.updateCourseSessionInClientNull((int)cs_id);
            courseService.deleteCourseSessionById((int)cs_id);
            System.out.println("Remove finished");
        }
        return "index";
    }

    @RequestMapping("saveCourseSessionInfo")
    public CourseSession saveCourseSessionInfo(@RequestBody CourseSession courseSession){
        return courseService.saveCourseSessioninfo(courseSession);
    }

    @RequestMapping("saveCourseInfo")
    public Course saveCourseInfo(@RequestBody Course course){
        return courseService.saveCourseInfo(course);
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
        List<CourseSession> selectsession= courseService.getCourseByWord(k);
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()){
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists",selectsession);
        return "motcle";
    }

    @GetMapping("index")
//    @ResponseBody
    String initial(HttpSession session) {
        List<CourseSession> selectsession= courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()){
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists",selectsession);
        return "hello";
    }

    @GetMapping("motcle")
//    @ResponseBody
    String motcle(HttpSession session) {
        List<CourseSession> selectsession= courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()){
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "motcle";
    }

    @GetMapping("dateandlocation")
//    @ResponseBody
    String searchdateandlocation(HttpSession session) {
        List<CourseSession> selectsession= courseService.getall();
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()){
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists", selectsession);
        return "dateandlocation";
    }



//    @RequestMapping("/hello")
//    public String hello(Model m) {
//        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
//        return "hello";  //视图重定向hello.jsp
//    }


//    @GetMapping("byId")
//    @ResponseBody
//    CourseSession getCourseByDate(Integer id) {
//        return courseService.getCourseById(id);
//    }


    @GetMapping("dateindex")
//    @ResponseBody
    String dateindex(HttpSession session) {
        List<CourseSession> selectsession=courseService.getall();
        for (CourseSession courseSession : selectsession) {
           if (courseSession.isfull()){
               selectsession.remove(courseSession);
           }
        }
        session.setAttribute("courselists",selectsession);
        return "datesearch";
    }


    //http://localhost:8099/byDate?byDate=2021-05-08%2018:15:00
    @GetMapping("byDate")
//    @ResponseBody
    String getCourseByDate(@RequestParam(value = "byDate") String byDate, HttpSession session) {
        List<CourseSession> selectsession= courseService.getCourseByDate(byDate);
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()){
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists",selectsession);
        return "datesearch";
    }
    //http://localhost:8099/date?date=2021-05-08%2018:15:00
    @RequestMapping(value = "date")
    @ResponseBody
    List<CourseSession> searchbydate(@RequestParam(value = "date") String byDate, HttpSession session) {

        return courseService.getCourseByDate(byDate);
    }

//    @RequestMapping(value="/byDate")
//    public String index(@PathVariable String date, Model model){
//        List<CourseSession> courseSessionList = courseService.getCourseByDate(date);
//        model.addAttribute("courselists", courseSessionList);
//        System.out.println("封装的model="+model);
//        return "byDate";
//    }


    @GetMapping("bydateandlocation")
//    @ResponseBody
    String getCourseByDateandlocation(@RequestParam(value = "date") String date,
                                      @RequestParam(value = "location") String location,
                                      HttpSession session) {
        List<CourseSession> selectsession= courseService.getCourseByDateAndLocation(date,location);
        for (CourseSession courseSession : selectsession) {
            if (courseSession.isfull()){
                selectsession.remove(courseSession);
            }
        }
        session.setAttribute("courselists",selectsession);
        return "dateandlocation";
    }

    //http://localhost:8099/byDateAndLocation?date=2021-05-08 18:15:00&location=Belfort
    @GetMapping("byDateAndLocation")
    @ResponseBody
    List<CourseSession> getCourseByDateAndLocation(String date, String location) {
        return courseService.getCourseByDateAndLocation(date, location);
    }



    @RequestMapping(value = "/inscrire")
    public String createShop(Model model,@RequestParam String name, @RequestParam String prenom,
                            @RequestParam String adresse, @RequestParam String telephone,@RequestParam Long coursesessionid){
        Client client1=clientService.getClientBy4infos(name,prenom, adresse,telephone);
        CourseSession c=courseService.getCourseById(coursesessionid);
        if ( client1.getCourse_Session_Id1()==null){
            client1.setCourse_Session_Id1(coursesessionid);
            clientService.saveClientInfo(client1);
            c.setCurrentNumber(c.getCurrentNumber()+1);
            if (c.getCurrentNumber()==c.getMax())
                c.setIsfull(true);
            courseService.saveone(c);
            System.out.println(client1.toString());
            return "inscrire_success";
        }
        else if (client1.getCourse_Session_Id2()==null&&client1.getCourse_Session_Id1()!=coursesessionid)
        {
            client1.setCourse_Session_Id2(coursesessionid);
            clientService.saveClientInfo(client1);
            c.setCurrentNumber(c.getCurrentNumber()+1);
            if (c.getCurrentNumber()==c.getMax())
                c.setIsfull(true);
            courseService.saveone(c);
            System.out.println(client1.toString());
            return "inscrire_success";
        }
        else {
            return "inscrire_failure";
        }

    }




//
//    @RequestMapping(value = "/inscrire",method =RequestMethod.POST)
//    String UpdateUser(Client client) {
//        clientService.saveone(client);
//        return "success";
//    }
}

    //    @RequestMapping(value = "/index",method = RequestMethod.GET)
//    public ModelAndView getAllUser(Model model){
//        List<Reguchem> reguchemList= userService.getAll();
//        model.addAttribute("list:",reguchemList);
//        model.addAttribute("title：","用户管理");
//        return new ModelAndView("users/index","userModel",model);
//
//    }

//
//
//    @RequestMapping(value = "/byKeyWord", method = RequestMethod.GET)
//    @ResponseBody
//    public List<course> getCoursebykw(String kw){
//        return courseService.getCourseByWord(kw);
//    }
//
//    @RequestMapping(value = "/byDate", method = RequestMethod.GET)
//    @ResponseBody
//    public List<course> GetCourseByDate(String date){
//        List<course> courselist = courseService.getCourseByDate(date);
//        return courselist;
//        }
//
//    @RequestMapping(value = "/bydateandsite", method = RequestMethod.GET)
//    @ResponseBody
//    public List<course> GetCoursebyds(String  date, String site){
//        List<course> courseList = courseService.getCourseByDateAndLocation(date,site);
//        return courseList;
//    }
//
//
////    @RequestMapping(value = "/bymdl", method = RequestMethod.GET)
////    @ResponseBody
////    public ResponseEntity<Object> getUserbymdl(HttpServletRequest request) {
////        Map<String, Object> map = CommonUtil.getParameterMap(request);
////        String mdl = (String) map.get("mdl");
////        return new ResponseEntity<>(userService.getUserByMdl(mdl), HttpStatus.OK);
////    }
//
//    @RequestMapping(value = "/bySmiles", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<Object> getUserbySmiles(HttpServletRequest request) {
//        Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String Smiles = (String) map.get("Smiles");
//        return new ResponseEntity<>(userService.getUserBySmiles(Smiles), HttpStatus.OK);
//    }
////
//    @RequestMapping(value = "/byInchikey", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<Object> getUserbyInchikey(HttpServletRequest request) {
//        Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String Inchikey = (String) map.get("Inchikey");
//        return new ResponseEntity<>(userService.getUserByInchikey(Inchikey), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/bycasandmdl", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<Object> getUserbycasandmdl(HttpServletRequest request) {
//        Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String mdl = (String) map.get("mdl");
//        String cas = (String) map.get("cas");
//        return new ResponseEntity<>(userService.getUserByCasAndMdl(cas, mdl), HttpStatus.OK);
//    }
//
//
//    @PostMapping("add1")
//    @ResponseBody
//    String addUser1(student student){
//       student.setCreate_time(new Date());
//       userService.saveone(student);
//        return "success";
//    }
//


//    @GetMapping("byKeyWord")
////    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    @ResponseBody
//    //传入必要参数
//    student addUser(@RequestParam(value="cas",required=false)String cas, @RequestParam(value="mdl",required=false)String mdl, @RequestParam(value="name_cn",required=false)String name_cn, @RequestParam(value="name_en",required=false)String name_en, @RequestParam(value="chemical_sort",required=false)String chemical_sort, @RequestParam(value="risk_sug",required=false)String risk_sug, @RequestParam(value="description",required=false)String description, @RequestParam(value="inspection_agency_id",required=true)String inspection_agency_id, @RequestParam(value="regulated_sort",required=true)String regulated_sort, @RequestParam(value="restrict_reason",required=true)String restrict_reason, @RequestParam(value="source",required=false)String source, @RequestParam(value="inchikey",required=true)String inchikey, @RequestParam(value="smiles",required=true)String smiles) {
//        student student = new student(cas,mdl,name_cn,name_en,chemical_sort,risk_sug,description,inspection_agency_id,regulated_sort,restrict_reason,source,inchikey,smiles);
//        student.setCreate_time(new Date());
//        student student1 = userService.saveone(student);
//        return student1;
//    }

    //    @Transactional
//    @GetMapping("del")
//    ResponseEntity<Object> delUser(HttpServletRequest request){
//       Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String cas = (String) map.get("cas");
//        String mdl = (String) map.get("mdl");
//
//        return new ResponseEntity<>(userService.deleteUser(cas,mdl),HttpStatus.OK);
//    }

//
//    @GetMapping("delbyid")
//    @ResponseBody
//    String delUserById(Long id) {
//         return userService.deleteUser(id);
//    }


//
//    @GetMapping("updatexxx")
////    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    @ResponseBody
//    student UpdateUser(Long id, String cas, String mdl) {
//        student student = userService.findChem(id);
//        student.setMdl(mdl);
//        student.setCas(cas);
//        student.setUpdate_time(new Date());
//        student student1 = userService.saveone(student);
//        return student1;
//
//          @GetMapping("update")
//          private Reguchem updateUser(Long id,String nickName){
//                  Reguchem user = userServiceImpl.findUser(id);
//                  user.setNickname(nickName);
//                  userService.updateUser(user);
//                  return Reguchem;
//    }
//    public ResponseEntity<Object> add(HttpServletRequest request) {
//        Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String mdl = (String) map.get("mdl");
//        String cas = (String) map.get("cas");
//        Reguchem reguchem = new Reguchem();
//        reguchem.setMdl(mdl);
//        reguchem.setCas(cas);
//        return new ResponseEntity<>( userService.Saveone(reguchem), HttpStatus.OK);
//    }



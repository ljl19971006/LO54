package com.utbm.lo54.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.Client;
import com.utbm.lo54.bean.CourseSession;
import com.utbm.lo54.service.CourseService;
import com.utbm.lo54.service.ClientService;
import com.utbm.lo54.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;


@Controller
//@RequestMapping(value = "/request")
public class UserController {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserController.class));

    @Autowired
    private CourseService courseService;
    private ClientService clientService;
    // private ClientService clientService;
    /**
     * 通过spring data jpa 调用方法
     * api :localhost:8099/request/bycas?cas=111
     *
     */
//完善大小写转化处理
    @GetMapping("byKeyWord")
    @ResponseBody
    List<Course> getCourseByKeyWord(String k) {
        String word="%"+k+"%";
        return courseService.getCourseByWord(word);
    }


    @GetMapping("byId")
    @ResponseBody
    CourseSession getCourseByDate(Integer id)  {
        return courseService.getCourseById(id);
    }

    @GetMapping("byDate")
    @ResponseBody
    List<CourseSession> getCourseByDate(String date)  {
        return courseService.getCourseByDate(date);
    }

    //http://localhost:8099/byDateAndLocation?date=2021-05-08 18:15:00&location=Belfort
    @GetMapping("byDateAndLocation")
    @ResponseBody
    List<CourseSession> getCourseByDateAndLocation(String date, String location)  {

        return courseService.getCourseByDateAndLocation(date,location);
    }
    //////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/getAllClient")
    List<Client> getAllClient(){ return clientService.getAllClient();}

    @RequestMapping("saveClientInfo")
    Client saveClientInfo(@RequestBody Client client){
        return clientService.saveClientInfo(client);
    }

    @DeleteMapping("/deleteById")
    Boolean deleteById(@RequestParam("Id") int Id){ return clientService.deleteClientById(Id);}



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
//    @PostMapping("update")
//    @ResponseBody
//    String UpdateUser(student student) {
//            student.setUpdate_time(new Date());
//        userService.saveone(student);
//        return "success";
    }

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



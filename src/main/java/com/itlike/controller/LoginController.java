package com.itlike.controller;

import com.itlike.pojo.employee;
import com.itlike.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Api(value="用户controller",tags={"用户操作接口"})
@Controller
public class LoginController {

    @Autowired
    DataSource dataSource;
    @Resource
    private userService userService;
    @RequestMapping("/hello")
    public String hello(){
        return "hello"+dataSource;
    }

    @PostMapping("/userLogin")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        int num=0;
        num = userService.selectUser(username,password);
        if(num<=0){
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }else {
            session.setAttribute("loginUser",num);
            return "redirect:main";
        }
    }

    @ApiOperation(value="获取所有用户信息",notes="注意问题点",httpMethod="GET")
    @RequestMapping("/main")
    public String getAllUser(Model model){
        model.addAttribute("userList",userService.getAllUser());
        return "main";
    }

    //新增界面
    @ApiOperation(value="新增界面",notes="注意问题点",httpMethod="GET")
    @RequestMapping("/add")
    public String insertUser(){
        System.out.println("add");
        return "add";
    }
    //新增
    @ApiOperation(value="新增用户",notes="注意问题点",httpMethod="POST")
    @PostMapping("/insertEmployee")
    public String insertUser(employee employee){
        userService.insertEmployee(employee);
        System.out.println("insertEmployee");
        return "redirect:main";
    }
    //编辑界面 回显数据
    @ApiOperation(value="回显用户数据",notes="注意问题点",httpMethod="GET")
    @ApiImplicitParams({@ApiImplicitParam(name="id",value="用户id",dataType="Integer", paramType = "path")})
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id,Model model){
        System.out.println("editPage");
        employee employee = userService.selectEmployee(id);
        model.addAttribute("employee",employee);
        return "add";
    }
    //编辑  通过有没有employee对象 判断是否有id 有id  put请求 没有id post请求
    @ApiOperation(value="编辑用户",notes="注意问题点",httpMethod="PUT")
    @PutMapping("/insertEmployee")
    public String editEmployee(employee employee){
        userService.updateEmployee(employee);
        System.out.println("editEmployee");
        return "redirect:main";
    }

    //删除
    @RequestMapping(value = "/del/{id}")
    @ResponseBody
    public void deleteEmployee(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteEmployee");
        userService.deleteEmployee(id);
        request.getRequestDispatcher("/main").forward(request,response);
//        return "redirect:getAllUser";
    }
    @InitBinder/*date是 import java.util.Date*/
    public void InitBinder(WebDataBinder dataBinder){
        dataBinder.registerCustomEditor(Date.class,new PropertyEditorSupport(){
            public void  setAsText(String value){
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            public String getAsText(){
                return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
            }
        });
    }

}

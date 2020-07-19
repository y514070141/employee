package com.itlike.service;

import com.itlike.mapper.employeeMapper;
import com.itlike.pojo.employee;
import com.itlike.util.md5Util;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Resource
    private employeeMapper employeeMapper;
    @Override
    public List<employee> getAllUser() {
        return employeeMapper.getAllUser();
    }

    @Override
    public void insertEmployee(employee employee) {
        //springboot 自带的md5加密
//        String password=DigestUtils.md5DigestAsHex(employee.getPassword().getBytes(StandardCharsets.UTF_8));
//        employee.setPassword(password);

        //自己写一个md5工具类
//        String password=md5Util.code(employee.getPassword());
//        employee.setPassword(password);

        //md5Hash加密
//        Md5Hash md5Hash=new Md5Hash(employee.getPassword(),"月",2);
//        employee.setPassword(md5Hash.toString());
        //注意 前两个数据库查得到
        employeeMapper.insertEmployee(employee);
    }

    @Override
    public employee selectEmployee(Integer id) {
        employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer selectUser(String username, String password) {
        return employeeMapper.selectUser(username,password);
    }

    @Override
    public void updateEmployee(employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }
}

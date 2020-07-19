package com.itlike.service;

import com.itlike.pojo.employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userService {
    List<employee> getAllUser();

    void insertEmployee(employee employee);

    employee selectEmployee(Integer id);

    void deleteEmployee(Integer id);

    Integer selectUser(String username, String password);

    void updateEmployee(employee employee);
}

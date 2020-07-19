package com.itlike.mapper;

import com.itlike.pojo.employee;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
//extends Mapper<employee>
public interface employeeMapper extends Mapper<employee> {
    List<employee> getAllUser();

    void insertEmployee(employee employee);

    int selectUser(@Param("username") String username, @Param("password") String password);
}

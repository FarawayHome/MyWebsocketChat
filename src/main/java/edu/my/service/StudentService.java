package edu.my.service;

import edu.my.pojo.Student;

/**
 * @Auther: shi
 * @Date: 2018/6/29 23:01
 * @Description:
 */
public interface StudentService {
    boolean login(String name, String password);


    boolean register(String name, String password);
}

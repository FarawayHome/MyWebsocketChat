package edu.my.service.impl;

import edu.my.dao.StudentMapper;
import edu.my.pojo.Student;
import edu.my.pojo.StudentExample;
import edu.my.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shi
 * @Date: 2018/6/29 23:04
 * @Description:
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public boolean login(String name, String password) {
        StudentExample example = new StudentExample();
        example.createCriteria().andStuNameEqualTo(name);
        List user = studentMapper.selectByExample(example);
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean register(String name, String password) {
        Student user = new Student();
        user.setStuName(name);
        user.setStuPassword(password);
        studentMapper.insert(user);
        return true;
    }
}

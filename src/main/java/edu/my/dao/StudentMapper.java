package edu.my.dao;

import edu.my.pojo.Student;
import edu.my.pojo.StudentExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer stuID);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer stuID);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
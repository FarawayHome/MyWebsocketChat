package edu.my.test;


import com.alibaba.fastjson.JSON;
import edu.my.pojo.vo.OnlineStudents;

import java.util.ArrayList;

/**
 * @Auther: shi
 * @Date: 2018/6/30 11:55
 * @Description:
 */
public class JsonTest {
    public static void main(String[] args) {
        ArrayList<String> username = new ArrayList<String>();
        username.add(0, "123");
        username.add(1, "234");
        OnlineStudents onlineStudents = new OnlineStudents();
        onlineStudents.setType("3");
        onlineStudents.setUsernames(username);
        String text = JSON.toJSONString(onlineStudents);
        System.out.println(text);
    }
}

package com.example.demo.empApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmpDetailsService {
    @Autowired
    JdbcTemplate jdbct;

    public List<Map<String, Object>> select(String pid) throws UserException {

        String q1 = "select * from Project_details where pId=?";
        List<Map<String, Object>> ans = jdbct.queryForList(q1, pid);

        // for (Map<String, Object> row : ans) {
        //     for (Map.Entry<String, Object> entry : row.entrySet()) {
        //         System.out.println(entry.getKey() + ": " + entry.getValue());
        //     }
        //     System.out.println("----");
        // }

        if (ans.size() == 0) {
            System.out.println("no project");
            throw new UserException("no project in the project table with this id");
        }

        String q2 = "select eid from Emp where eid in (select empId from Project_details where pId=?)";
        List<Map<String, Object>> ids = jdbct.queryForList(q2, pid);

        // for (Map<String, Object> row : ids) {
        //     for (Map.Entry<String, Object> entry : row.entrySet()) {
        //         System.out.println(entry.getKey() + ": " + entry.getValue());
        //     }
        //     System.out.println("----");
        // }

        List<Integer> list = new ArrayList<>();
        for (Map<String, Object> m : ids) {
            for (Map.Entry<String, Object> entry : m.entrySet()) {
                list.add(((Number) entry.getValue()).intValue());
            }
        }

        // for (int i = 0; i < list.size(); i++) {
        //     System.out.println(list.get(i));
        // }

        if (list.size() == 0) {
            System.out.println("no project is assigned to emp");
            throw new UserException("no project is assigned to emp");
        }

        ans.get(0).put(pid, list);
        // System.out.println(list);
        return ans;
    }
}

class UserException extends Exception {
    public UserException(String s) {
        super(s);
    }
}

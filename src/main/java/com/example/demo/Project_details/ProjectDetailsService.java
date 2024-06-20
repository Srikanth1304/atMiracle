package com.example.demo.Project_details;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectDetailsService {
    @Autowired
    JdbcTemplate conn;

    @Transactional
    public int add(ProjectDetails p) {
        int empId = p.getEmpId();
        int pId = p.getPid();
        LocalDate assignedDate = p.getAssignedDate();
        int teamLead = p.getTeamLead();
        String assignedBy = p.getAssignedBy();
        
        String empCheckQuery = "select * from Emp where eid = ?";
        @SuppressWarnings("deprecation")
        List<ProjectDetails> a = conn.query(empCheckQuery, new Object[]{empId}, new BeanPropertyRowMapper<>(ProjectDetails.class));

        //String role= "select * from "

        if (!a.isEmpty() && ((pId == 1 && "6".equals(assignedBy) && teamLead == 2) || 
                             (teamLead == 3 && pId == 2 && "7".equals(assignedBy)))) {
            String insertSql = "Insert Into Project_details(empId, pId, assignedDate, teamLead, assignedBy) " + 
                               "values(?, ?, ?, ?, ?)";
            return conn.update(insertSql, empId, pId, assignedDate, teamLead, assignedBy);
        } else {
            return -1;
        }
    }   
}
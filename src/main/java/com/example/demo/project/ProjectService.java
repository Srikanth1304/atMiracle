package com.example.demo.project;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class ProjectService {
    private static final int MAX_LENGTH = 10;
    @Autowired
    private JdbcTemplate conn;
    public int addProject(Projects p) {
        int pId = p.getpId();
        String client = p.getClient();
        String createdBy;
        int assignedBy=p.getAssignedBy();
        LocalDate startDate = p.getStartDate();
        LocalDate endDate = p.getEndDate();
        int HR = p.getHR();
        int PM = p.getPM();


        //if assignedBy ---> roleOfE (retrive)
        // int roleId="select roleOfE from Emp where eId=?" ;
        //if roleId is 3 or 1 -->insert
        //retrive role where roles.roleId==roleId and keep it in createdBy;
    String roleOfESql = "SELECT roleOfE FROM Emp WHERE eid = ?";
    String roleOfE = conn.queryForObject(roleOfESql, String.class, assignedBy);

    // Check if roleOfE is "1" or "3"
    String add="";
    if ("1".equals(roleOfE) || "3".equals(roleOfE)) {
        if("1".equals(roleOfE)){
            add="Admin";
        }
        else{
            add="HR";
        }
        // Calculate the duration in months between startDate and endDate
        Period period = Period.between(p.getStartDate(), p.getEndDate());
        int duration = period.getYears() * 12 + period.getMonths();

        int c1 = 0, c2 = 0;
        String hr = "", pm = "";

        String countHrQuery = "SELECT COUNT(*) FROM Emp WHERE eid = ?";
        c1 = conn.queryForObject(countHrQuery, Integer.class, HR);

        String countPmQuery = "SELECT COUNT(*) FROM Emp WHERE eid = ?";
        c2 = conn.queryForObject(countPmQuery, Integer.class, PM);
        System.out.println(c1);
        System.out.println(c2);
        if (c1 > 0 && c2 > 0) {
            String hrQuery = "SELECT workmail FROM Emp WHERE eid = ?";
            hr = conn.queryForObject(hrQuery, String.class, HR);

            String pmQuery = "SELECT workmail FROM Emp WHERE eid = ?";
            pm = conn.queryForObject(pmQuery, String.class, PM);
            if (hr.length() > MAX_LENGTH) {
                hr = hr.substring(0, MAX_LENGTH);   
            }
            if (pm.length() > MAX_LENGTH) {
                pm = pm.substring(0, MAX_LENGTH);
            }
        } else {
            System.out.println("Enter the details of HR and Project manager in the employee table");
            return -998;
        }
        // Insert into Projects table
        String insertSql = "INSERT INTO Pro (pId, client,createdBy, startDate, endDate, duration, HR, PM) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return conn.update(insertSql, p.getpId(), p.getClient(), add , p.getStartDate(), p.getEndDate(), duration, hr, pm); 
      } else {
        // User does not have a valid role, do not insert
        return 0;
    }
}

}

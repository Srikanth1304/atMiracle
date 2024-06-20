package com.example.demo.say;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    @Autowired
    private JdbcTemplate conn;

    public int add(Employee e) {
            String firstName = e.getFirstName();
            String lastName = e.getLastName();
            String depart = e.getDepart();
            String phone = e.getPhone();
            LocalDateTime joiningDate = generateJoiningDate();
            String gmailId = e.getGmailId();
            String workLocation = e.getWorkLocation();
            int pincode = e.getPincode();
            int salary = e.getSalary();
            String roleOfE = e.getRoleOfE();
            String createdBy = e.getCreatedBy();
            String password = generatePassword(firstName, lastName);
            String workMail = generateWorkMail(firstName, lastName);

            String adminCheckSql = "SELECT * FROM Emp WHERE roleOfE = 1 AND workMail = ? ";
            @SuppressWarnings("deprecation")
            List<Employee> admins = conn.query(adminCheckSql,new Object[]{createdBy}, new BeanPropertyRowMapper<>(Employee.class));
            
            if (!admins.isEmpty()) {
              String roleCheckSql = "SELECT roleId FROM roles WHERE role= ?";
              @SuppressWarnings("deprecation")
              Integer roleId = conn.queryForObject(roleCheckSql, new Object[]{roleOfE}, Integer.class);
              String sql = "INSERT INTO Emp (firstName, lastName, depart, phone, joiningDate, gmailId, workMail, password, workLocation, pincode, salary, roleOfE, createdBy) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
              return conn.update(sql, firstName, lastName, depart, phone, joiningDate, gmailId, workMail, password, workLocation, pincode, salary, roleId.toString(), createdBy);
            } 
            else {
                // Invalid
                return 0;
            }
        
    }
    private LocalDateTime generateJoiningDate() {
        return LocalDateTime.now();
    }

    private String generatePassword(String firstName, String lastName) {
        String first = firstName.toLowerCase();
        String f = first.substring(first.length() - 2);
        String l = lastName.substring(lastName.length() - 2);
        LocalDateTime localDateTime = LocalDateTime.now();
        int hours = localDateTime.getHour();
        int minutes = localDateTime.getMinute();
        String formattedHours = String.format("%02d", hours);
        String formattedMinutes = String.format("%02d", minutes);
        return f + l.toUpperCase() + "&" + formattedHours + formattedMinutes;
    }

    private String generateWorkMail(String firstName, String lastName) {
        String first = firstName.toLowerCase();
        return first.charAt(0) + lastName.toLowerCase();
    }

    public List<Employee> select(Employee e) {
        StringBuilder b = new StringBuilder("SELECT * FROM Emp WHERE 1=1");
        if (e.getFirstName() != null && !e.getFirstName().isEmpty()) {
            b.append(" AND firstName='").append(e.getFirstName()).append("'");
        }
        if (e.getFirstName() == null && e.getLastName() != null ) {
            b.append(" AND lastName='").append(e.getLastName()).append("'");
        }
        if (e.getFirstName() == null && e.getLastName() == null  && e.getDepart() != null ) {
            b.append(" AND depart='").append(e.getDepart()).append("'");
        }

        String sql = b.toString();
        return conn.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

}



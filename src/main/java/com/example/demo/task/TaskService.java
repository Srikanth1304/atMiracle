package com.example.demo.task;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    JdbcTemplate conn;

    public int add(Task t) {
        int taskId = t.getTaskId();
        String taskName = t.getTaskName();
        int assignedBy = t.getAssignedBy();
        int assignedTo = t.getAssignedTo();
        LocalDate assignedDate = LocalDate.now();
        int durationH = t.getDurationH();
        LocalDate startDate = t.getStartDate();
        LocalDate endDate = endDateCal(startDate, durationH);
        String status =knowStat(startDate, assignedDate, endDate);
        String description = t.getDescription();

        String empCheck="Select count(*) from Emp where eid=?";
        @SuppressWarnings("deprecation")
        int fempCheck=conn.queryForObject(empCheck,new Object[]{assignedTo},Integer.class);

        String checkLead = "select roleOfE from Emp where eid=?";
        @SuppressWarnings("deprecation")
        String str = conn.queryForObject(checkLead, new Object[]{assignedBy }, String.class);
        int rId=Integer.parseInt(str);

        String s="SELECT role from roles where roleId=?";
        @SuppressWarnings("deprecation")
        String role=conn.queryForObject(s, new Object[]{rId}, String.class);

        if ("lead".equals(role) && fempCheck>0) {
            String sql = "INSERT INTO Task (taskId, taskName, assignedBy, assignedTo, assignedDate, durationH, startDate, endDate, status, description) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return conn.update(sql, taskId, taskName, assignedBy, assignedTo, assignedDate, durationH, startDate, endDate, status, description);
        } else {
            return -1;
        }
    }

    private String knowStat(LocalDate startDate, LocalDate assignedDate, LocalDate endDate) {
        String status = "";
        if (assignedDate.isAfter(startDate) && assignedDate.isBefore(endDate)) {
            status = "On going";
        } else if (assignedDate.isBefore(startDate)) {
            status = "Not started";
        } else {
            status = "Completed";
        }
        return status;
    }

    private LocalDate endDateCal(LocalDate startDate, int durationH) {
        int gap = durationH / 8;
        if (durationH % 8 > 0) {
            gap = gap + 1;
        }
        return startDate.plusDays(gap);
    }
}

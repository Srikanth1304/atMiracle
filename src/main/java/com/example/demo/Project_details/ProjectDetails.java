package com.example.demo.Project_details;

import java.time.LocalDate;

public class ProjectDetails {
    private int empId;
    private int pid;
    private int teamLead;

    public int getTeamLead() {
        return teamLead;
    }
    public void setTeamLead(int teamLead) {
        this.teamLead = teamLead;
    }
    private LocalDate assignedDate;
    private String assignedBy;
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public LocalDate getAssignedDate() {
        return assignedDate;
    }
    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
    public String getAssignedBy() {
        return assignedBy;
    }
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

}

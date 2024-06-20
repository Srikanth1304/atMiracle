package com.example.demo.project;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class Projects {
    private int pId;
    private String client;
    private String createdBy;
    private int assignedBy;
    public int getAssignedBy() {
        return assignedBy;
    }
    public void setAssignedBy(int assignedBy) {
        this.assignedBy = assignedBy;
    }
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;
    private int HR;
    private int PM;
    
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy= createdBy;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getHR() {
        return HR;
    }
    public void setHR(int hR) {
        HR = hR;
    }
    public int getPM() {
        return PM;
    }
    public void setPM(int pM) {
        PM = pM;
    }


}

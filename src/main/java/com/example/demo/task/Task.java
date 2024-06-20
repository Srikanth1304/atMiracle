package com.example.demo.task;

import java.time.LocalDate;

public class Task {
    private int taskId;
    private String taskName;
    private int assignedBy;
    private int assignedTo;
    private LocalDate assignedDate;
    private int durationH;
    private LocalDate startDate;
    private String status;
    private String description;
    private LocalDate endDate;


    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public int getAssignedBy() {
        return assignedBy;
    }
    public void setAssignedBy(int assignedBy) {
        this.assignedBy = assignedBy;
    }
    public int getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }
    public LocalDate getAssignedDate() {
        return assignedDate;
    }
    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
    public int getDurationH() {
        return durationH;
    }
    public void setDurationH(int durationH) {
        this.durationH = durationH;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

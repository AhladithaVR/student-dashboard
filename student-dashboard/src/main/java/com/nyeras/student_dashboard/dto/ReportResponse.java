package com.nyeras.student_dashboard.dto;

public class ReportResponse {

    private long totalStudents;
    private long totalTasks;
    private long completedTasks;
    private long pendingTasks;
    private long totalAttendance;

    public ReportResponse() {
    }

    public ReportResponse(long totalStudents,
                          long totalTasks,
                          long completedTasks,
                          long pendingTasks,
                          long totalAttendance) {
        this.totalStudents = totalStudents;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.pendingTasks = pendingTasks;
        this.totalAttendance = totalAttendance;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public long getPendingTasks() {
        return pendingTasks;
    }

    public long getTotalAttendance() {
        return totalAttendance;
    }
}
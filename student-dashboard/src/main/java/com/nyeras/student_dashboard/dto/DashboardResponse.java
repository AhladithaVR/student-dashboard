package com.nyeras.student_dashboard.dto;

public class DashboardResponse {

    private long totalStudents;
    private long totalTasks;
    private long totalAttendance;
    private long presentCount;
    private long absentCount;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalStudents, long totalTasks,
                             long totalAttendance, long presentCount,
                             long absentCount) {
        this.totalStudents = totalStudents;
        this.totalTasks = totalTasks;
        this.totalAttendance = totalAttendance;
        this.presentCount = presentCount;
        this.absentCount = absentCount;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public long getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(long totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public long getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(long presentCount) {
        this.presentCount = presentCount;
    }

    public long getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(long absentCount) {
        this.absentCount = absentCount;
    }
}
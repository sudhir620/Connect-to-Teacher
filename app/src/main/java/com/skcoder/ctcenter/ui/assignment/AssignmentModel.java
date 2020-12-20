package com.skcoder.ctcenter.ui.assignment;

public class AssignmentModel {
    String assignmentTitle, assignmentUrl, department, semester;

    public AssignmentModel() {
    }

    public AssignmentModel(String assignmentTitle, String assignmentUrl, String department, String semester) {
        this.assignmentTitle = assignmentTitle;
        this.assignmentUrl = assignmentUrl;
        this.department = department;
        this.semester = semester;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentUrl() {
        return assignmentUrl;
    }

    public void setAssignmentUrl(String assignmentUrl) {
        this.assignmentUrl = assignmentUrl;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}

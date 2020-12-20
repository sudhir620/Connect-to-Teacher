package com.skcoder.ctcenter.ui.notes;

public class NotesModel {
    String department, notesTitle, notesUrl, semester;

    public NotesModel() {
    }

    public NotesModel(String department, String notesTitle, String notesUrl, String semester) {
        this.department = department;
        this.notesTitle = notesTitle;
        this.notesUrl = notesUrl;
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesUrl() {
        return notesUrl;
    }

    public void setNotesUrl(String notesUrl) {
        this.notesUrl = notesUrl;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}

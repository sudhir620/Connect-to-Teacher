package com.skcoder.ctcenter.ui.syllabus;

public class SyllabusModel {
    String semester, syllabusUrl;

    public SyllabusModel() {
    }

    public SyllabusModel(String semester, String syllabusUrl) {
        this.semester = semester;
        this.syllabusUrl = syllabusUrl;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSyllabusUrl() {
        return syllabusUrl;
    }

    public void setSyllabusUrl(String syllabusUrl) {
        this.syllabusUrl = syllabusUrl;
    }
}

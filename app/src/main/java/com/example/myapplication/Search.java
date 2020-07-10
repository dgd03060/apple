package com.example.myapplication;

public class Search {

    int courseID; //강의 고유 번호
    String courseUniversity; // 학부 혹은 대학원
    int courseYear; //해당년도
    String courseTerm; //해당학기
    String courseArea; //강의 영역
    String courseMajor; //해당학과
    String courseGrade; //해당학년
    String courseTitle; //강의 제목
    String courseCredit; //강의학점
    String courseDivide; // 강의 분반
    String coursePersonnel; //강의 제한 인원
    String courseProfessor; //강의교수
    String courseTime; //강의시간대
    String courseRoom; //

    public Search(int courseID, String courseUniversity, int courseYear, String courseTerm, String courseArea, String courseMajor, String courseGrade, String courseTitle, String courseCredit, String courseDivide, String coursePersonnel, String courseProfessor, String courseTime, String courseRoom, String courseImage) {
        this.courseID = courseID;
        this.courseUniversity = courseUniversity;
        this.courseYear = courseYear;
        this.courseTerm = courseTerm;
        this.courseArea = courseArea;
        this.courseMajor = courseMajor;
        this.courseGrade = courseGrade;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        this.courseDivide = courseDivide;
        this.coursePersonnel = coursePersonnel;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
        this.courseImage = courseImage;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    String courseImage; // }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseUniversity() {
        return courseUniversity;
    }

    public void setCourseUniversity(String courseUniversity) {
        this.courseUniversity = courseUniversity;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    public String getCourseArea() {
        return courseArea;
    }

    public void setCourseArea(String courseArea) {
        this.courseArea = courseArea;
    }

    public String getCourseMajor() {
        return courseMajor;
    }

    public void setCourseMajor(String courseMajor) {
        this.courseMajor = courseMajor;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseDivide() {
        return courseDivide;
    }

    public void setCourseDivide(String courseDivide) {
        this.courseDivide = courseDivide;
    }

    public String getCoursePersonnel() {
        return coursePersonnel;
    }

    public void setCoursePersonnel(String coursePersonnel) {
        this.coursePersonnel = coursePersonnel;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

}
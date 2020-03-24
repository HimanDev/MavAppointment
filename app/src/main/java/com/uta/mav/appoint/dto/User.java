package com.uta.mav.appoint.dto;

public class User {

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGegreeType() {
        return gegreeType;
    }

    public void setGegreeType(String gegreeType) {
        this.gegreeType = gegreeType;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLastNameInitial() {
        return lastNameInitial;
    }

    public void setLastNameInitial(String lastNameInitial) {
        this.lastNameInitial = lastNameInitial;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String dept, gegreeType, major, lastNameInitial, studentId, phoneNo, email;
}

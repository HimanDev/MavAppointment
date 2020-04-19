package com.uta.mav.appoint.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
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

    private String dept;
    private String degreeType;
    private String major;
    private String lastNameInitial;
    private String studentId;
    private String phoneNo;
    private String email;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public void setUserFromJson(JSONObject resBody) {
        try {
            role=resBody.getString("role");
            degreeType=resBody.getString("degree_type");
            studentId=resBody.getString("student_id");
            phoneNo=resBody.getString("phone");
            lastNameInitial=resBody.getString("last_name_initial");
            email=resBody.getString("email");

        }catch (JSONException e){

        }
    }

    public interface UserResponse{
         void addUser(User user);
    }
}

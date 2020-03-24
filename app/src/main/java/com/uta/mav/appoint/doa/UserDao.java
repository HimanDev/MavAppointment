package com.uta.mav.appoint.doa;

import java.util.List;

public interface UserDao {
    List<String> getDepatments();
    List<String> getDegreeType();
    List<String> getmajors(String departmentKey);
}

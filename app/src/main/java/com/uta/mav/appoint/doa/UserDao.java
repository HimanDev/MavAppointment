package com.uta.mav.appoint.doa;

import java.util.List;

public interface UserDao {
    List<String> getDepartments();
    List<String> getDegreeType();
    List<String> getMajors(String key);
}

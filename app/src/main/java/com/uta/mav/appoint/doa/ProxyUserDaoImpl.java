package com.uta.mav.appoint.doa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyUserDaoImpl implements UserDao {

    static List<String> departments=new ArrayList<>();;
    static List<String> degreetype=new ArrayList<>();;
    static Map<String,List<String>> majors=new HashMap<>();

    static{
        departments.add("ARCH");
        departments.add("CSE");
        departments.add("MAE");
        departments.add("MATH");

        degreetype.add("BACHELOR'S");
        degreetype.add("MASTER'S");

        majors.put("ARCH",new ArrayList<String>(Arrays.asList("Architecture","Interior Design","Landscape Architecture")));
        majors.put("CSE",new ArrayList<String>(Arrays.asList("Computer Engineering","Computer Science","Software Engineering")));
        majors.put("MAE",new ArrayList<String>(Arrays.asList("Aerospace Engineering","Mechanical Engineering")));
        majors.put("MATH",new ArrayList<String>(Arrays.asList("Mathematics")));
    }


    @Override
    public List<String> getDepatments() {
        return departments;
    }

    @Override
    public List<String> getDegreeType() {
        return degreetype;
    }

    @Override
    public List<String> getmajors(String key) {
        return majors.get(key);
    }
}

package com.uta.mav.appoint.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.uta.mav.appoint.R;
import com.uta.mav.appoint.doa.ProxyUserDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    ProxyUserDaoImpl proxyUserDao;

    Spinner deptSpinner;
    Spinner degreeTypeSpinner;
    Spinner majorSpinner;

    ArrayAdapter<String> degreeTypeAdapter;
    ArrayAdapter<String> deptAdapter;
    ArrayAdapter<String> majorAdapter;

    static final String DEPARTMENT_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/department";
    static final String DEGREE_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/degree";
    static final String MAJOR_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/major";
    static final String USER_POST_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/user/student";

    public void handleRegister(View view)
    {
        //Build post body in the following format:
        /*{
            "department": "MAE",
            "degree": "Bachelors",
            "major": "Mechanical Engineering",
            "initial": "A",
            "student_id": "1001078486",
            "email": "student.name@mavs.uta.edu",
            "phone": "123-456-7890"
          }
        */
        String deptStr = deptSpinner.getSelectedItem().toString();
        String degStr = degreeTypeSpinner.getSelectedItem().toString();
        String majorStr = majorSpinner.getSelectedItem().toString();
        String initialStr = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        String idStr = ((EditText)findViewById(R.id.studentIdEditText)).getText().toString();
        String emailStr = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String phoneStr = ((EditText)findViewById(R.id.phoneEditText)).getText().toString();

        String postBody="{\n" +
                "    \"department\": \"" + deptStr + "\",\n" +
                "    \"degree\": \"" + degStr + "\",\n" +
                "    \"major\": \"" + majorStr + "\",\n" +
                "    \"initial\": \"" + initialStr + "\",\n" +
                "    \"student_id\": \"" + idStr + "\",\n" +
                "    \"email\": \"" + emailStr + "\",\n" +
                "    \"phone\": \"" + phoneStr + "\"\n" +
                "}";

        //Send post
        proxyUserDao.buildPostRequest(USER_POST_REQUEST, postBody);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerIds();
        proxyUserDao = new ProxyUserDaoImpl();

        ProxyUserDaoImpl.ClientTask deptTask = new ProxyUserDaoImpl.ClientTask(new ProxyUserDaoImpl.ClientTask.AsyncResponse() {
            @Override
            public void processFinish(ProxyUserDaoImpl.ClientTask.Result result) {
                loadDepartments();
            }
        }, DEPARTMENT_REQUEST);
        deptTask.execute(DEPARTMENT_REQUEST);

        ProxyUserDaoImpl.ClientTask degTask = new ProxyUserDaoImpl.ClientTask(new ProxyUserDaoImpl.ClientTask.AsyncResponse() {
            @Override
            public void processFinish(ProxyUserDaoImpl.ClientTask.Result result) {
                loadDegreeType();
            }
        }, DEGREE_REQUEST);
        degTask.execute(DEGREE_REQUEST);

        ProxyUserDaoImpl.ClientTask majorTask = new ProxyUserDaoImpl.ClientTask(new ProxyUserDaoImpl.ClientTask.AsyncResponse() {
            @Override
            public void processFinish(ProxyUserDaoImpl.ClientTask.Result result) {
                loadMajor();

                String majorKey = deptSpinner.getSelectedItem().toString();
                List<String> majorList = proxyUserDao.getMajors(majorKey);
                if(majorList != null) {
                    majorAdapter.addAll(majorList);
                    majorAdapter.notifyDataSetChanged();
                }
            }
        }, MAJOR_REQUEST);
        majorTask.execute(MAJOR_REQUEST);
    }

    private void loadMajor() {
        final Context context=this;
        majorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,new ArrayList<String>());
        majorSpinner.setAdapter(majorAdapter);
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptSpinner.setSelection(position);
                majorAdapter.clear();
                String majorKey = deptAdapter.getItem(position);
                List<String> majorList = proxyUserDao.getMajors(majorKey);
                if(majorList != null) {
                    majorAdapter.addAll(majorList);
                    majorAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void loadDegreeType() {
        degreeTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proxyUserDao.getDegreeType());
        degreeTypeSpinner.setAdapter(degreeTypeAdapter);

    }

    private void registerIds(){
        deptSpinner=(Spinner)findViewById(R.id.deptSpinner);
        degreeTypeSpinner=(Spinner)findViewById(R.id.degreeTypeSpinner);
        majorSpinner=(Spinner)findViewById(R.id.majorSpinner);
    }

    private void loadDepartments() {
        deptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proxyUserDao.getDepartments());
        deptSpinner.setAdapter(deptAdapter);
    }
}

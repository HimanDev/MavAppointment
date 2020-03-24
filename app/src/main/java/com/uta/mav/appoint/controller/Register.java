package com.uta.mav.appoint.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.uta.mav.appoint.R;
import com.uta.mav.appoint.doa.ProxyUserDaoImpl;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    ProxyUserDaoImpl proxyUserDao;

    Spinner deptSpinner;
    Spinner degreeTypeSpinner;
    Spinner majorSpinner;

    ArrayAdapter<String> degreeTypeAdapter;
    ArrayAdapter<String> deptAdapter;
    ArrayAdapter<String> majorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerIds();
        proxyUserDao=new ProxyUserDaoImpl();
        loadDepartments();
        loadDegreeType();
        loadMajor();
    }

    private void loadMajor() {
        final Context context=this;
        majorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,new ArrayList<String>());
        majorSpinner.setAdapter(majorAdapter);
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                majorAdapter.clear();
                String majorKey=deptAdapter.getItem(position);
                majorAdapter.addAll(proxyUserDao.getmajors(majorKey));
                majorAdapter.notifyDataSetChanged();
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
        deptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proxyUserDao.getDepatments());
        deptSpinner.setAdapter(deptAdapter);


    }
}

package com.uta.mav.appoint.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.uta.mav.appoint.dto.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    public static final String LOGIN_URL="https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/user";
    RequestQueue queue;
    public LoginService(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void validateUser(final String email, final String password, final User.UserResponse userResponse){

        Log.d("Error.ResponseDev", email+"  "+password);
        Map<String, String>  params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);
        JSONObject jsonObject=new JSONObject(params);


        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL,
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject resBody=response.getJSONObject("body");
                    Log.d("Error.ResponseDev",resBody.toString());
                    User user=new User();
                    user.setUserFromJson(resBody);
                    userResponse.addUser(user);

                }catch (JSONException e){
                    Log.d("Error.ResponseDev",e.toString());
                    userResponse.addUser(null);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.ResponseDev", error.toString());

            }
        });

       queue.add(postRequest);
    }



}



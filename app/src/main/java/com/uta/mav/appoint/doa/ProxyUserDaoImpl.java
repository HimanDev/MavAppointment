package com.uta.mav.appoint.doa;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProxyUserDaoImpl implements UserDao
{
    static List<String> departments=new ArrayList<>();
    static List<String> degreetype=new ArrayList<>();
    static Map<String,List<String>> majors=new HashMap<>();
    static final String DEPARTMENT_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/department";
    static final String DEGREE_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/degree";
    static final String MAJOR_REQUEST = "https://063o0cw25i.execute-api.us-west-2.amazonaws.com/test/major";

    static public class ClientTask extends AsyncTask<String, Void, ClientTask.Result>
    {
        boolean finished = false;
        String reqStr;
        Request request = null;
        public AsyncResponse delegate = null;

        public interface AsyncResponse {
            void processFinish(Result result);
        }

        public ClientTask(AsyncResponse delegate, String reqStr){
            this.delegate = delegate;
            this.reqStr = reqStr;
        }

        @Override
        protected void onPostExecute(Result result) {
            delegate.processFinish(result);
            this.finished = true;
        }

        void setRequest(String reqStr) {
            this.request = new Request.Builder().url(reqStr).build();
        }

        public class Result {
            public String resultValue;
            public Exception exception;
            public Result(String resultValue) {
                this.resultValue = resultValue;
            }
            public Result(Exception exception) {
                this.exception = exception;
            }
        }

        protected Result doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Result result = null;
            try {
                setRequest(reqStr);
                Response response = client.newCall(request).execute();
                if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                ResponseBody responseBody = response.body();
                String responseStr = responseBody.string();
                try{

                    JSONObject myResponse = new JSONObject(responseStr);

                    switch(reqStr)
                    {
                        case DEPARTMENT_REQUEST:
                            JSONArray depts = myResponse.getJSONObject("body").getJSONArray("list");
                            for(int i = 0; i < depts.length(); ++i)
                            {
                                departments.add(depts.getString(i));
                            }

                            break;

                        case DEGREE_REQUEST:
                            JSONArray degrees = myResponse.getJSONObject("body").getJSONArray("list");
                            for(int i = 0; i < degrees.length(); ++i)
                            {
                                degreetype.add(degrees.getString(i));
                            }
                            break;

                        case MAJOR_REQUEST:
                            JSONArray majorData = myResponse.getJSONObject("body").getJSONArray("list");
                            for(int i = 0; i < majorData.length(); ++i)
                            {
                                JSONArray element = majorData.getJSONArray(i);
                                List<String> arrList = majors.get(element.getString(1));
                                if(arrList == null)
                                {
                                    arrList = new ArrayList<String>();
                                }
                                arrList.add(element.getString(0));
                                majors.put(element.getString(1), arrList);
                            }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                result = new Result(responseStr);
                response.close();
            } catch (IOException e) {
                result = new Result(e);
            }

            return result;
        }
    }

    @Override
    public List<String> getDepartments() { return departments; }

    @Override
    public List<String> getDegreeType() {
        return degreetype;
    }

    @Override
    public List<String> getMajors(String key) {
        return majors.get(key);
    }

    public void buildPostRequest(String postUrl, String postBody)
    {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }
}

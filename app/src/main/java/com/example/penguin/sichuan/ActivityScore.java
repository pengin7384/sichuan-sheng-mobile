package com.example.penguin.sichuan;

/**
 * Created by Penguin on 2018-03-18.
 */


import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/*
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ActivityScore extends Activity {
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_score);
        parent = (LinearLayout)findViewById(R.id.dynamicArea);
        /*
        String postURL = "http://1.233.127.192:8080/SachunServer/rank.jsp"; // jsp

        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(postURL);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();
            InputStream is = resEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            is.close();
            String recv = sb.toString();
            String[] result = recv.split(":");  // jsp

            for(int i=1; i<=20; i+=2) {
                if(result[i].equals("finish")) {
                    break;
                }
                TextView txt = new TextView(this);
                txt.setText("" + ((i / 2) + 1) + "                         " + result[i] + "                              "+ result[i+1]);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(600,32,0,0);
                txt.setLayoutParams(lp);
                parent.addView(txt);
            }
        }catch(IOException e1){
            Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }*/


    }
}

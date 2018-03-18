package com.example.penguin.sichuan;

/**
 * Created by Penguin on 2018-03-18.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
/*
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ActivityStage extends Activity {
    String id;
    ImageButton btn[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_stage);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this, id+" Login", Toast.LENGTH_SHORT).show();

        btn = new ImageButton [6];

        btn[0] = (ImageButton)findViewById(R.id.imageButton1);
        btn[0].setClickable(false);
        btn[1] = (ImageButton)findViewById(R.id.imageButton2);
        btn[1].setClickable(false);
        btn[2] = (ImageButton)findViewById(R.id.imageButton3);
        btn[2].setClickable(false);
        btn[3] = (ImageButton)findViewById(R.id.imageButton4);
        btn[3].setClickable(false);
        btn[4] = (ImageButton)findViewById(R.id.imageButton5);
        btn[4].setClickable(false);
        btn[5] = (ImageButton)findViewById(R.id.imageButton6);
        btn[5].setEnabled(true);
        btn[5].setClickable(true);

        /*
        String postURL = "http://1.233.127.192:8080/SachunServer/getStage.jsp"; // jsp 주소

        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(postURL);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("id",id));

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
            String[] result = recv.split(":");  // jsp로부터 들어온 응답 데이터를 ':' 단위로 자른다.

            int clearStage = Integer.parseInt(result[1]);
            for(int i=0; i<5; i++) {

                if (i <= clearStage) {
                    btn[i].setEnabled(true);
                    btn[i].setClickable(true);

                    btn[i].setBackgroundResource(R.drawable.stage1 + i);
                }
            }
        }catch(IOException e1) {
            Toast.makeText(this, "JSP Error", Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }*/
        for(int i=0; i<5; i++) {
            btn[i].setEnabled(true);
            btn[i].setClickable(true);
            btn[i].setBackgroundResource(R.drawable.stage1 + i);
        }
    }

    public void onClick(View v) {

        Intent intent = new Intent(this, ActivityPlay.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        if(v == findViewById(R.id.imageButton1)) {
            Toast.makeText(this, "Stage 1", Toast.LENGTH_SHORT).show();
            intent.putExtra("stage",1);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        else if(v == findViewById(R.id.imageButton2)) {
            Toast.makeText(this, "Stage 2", Toast.LENGTH_SHORT).show();
            intent.putExtra("stage",2);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        else if(v == findViewById(R.id.imageButton3)) {
            Toast.makeText(this, "Stage 3", Toast.LENGTH_SHORT).show();
            intent.putExtra("stage",3);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        else if(v == findViewById(R.id.imageButton4)) {
            Toast.makeText(this, "Stage 4", Toast.LENGTH_SHORT).show();
            intent.putExtra("stage",4);
            intent.putExtra("id",id);

            startActivity(intent);
        }
        else if(v == findViewById(R.id.imageButton5)) {
            Toast.makeText(this, "Stage 5", Toast.LENGTH_SHORT).show();
            intent.putExtra("stage",5);
            intent.putExtra("id",id);
            startActivity(intent);
        }
        else if(v == findViewById(R.id.imageButton6)) {
            Toast.makeText(this, "Stage 6 (TimeAttck)", Toast.LENGTH_SHORT).show();
            intent.putExtra("stage",6);
            intent.putExtra("id",id);
            startActivity(intent);
        }
    }
}

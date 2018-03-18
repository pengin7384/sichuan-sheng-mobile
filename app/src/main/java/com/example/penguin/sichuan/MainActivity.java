package com.example.penguin.sichuan;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity {
    ImageButton btnStart, btnRank, btnEnter, btnRegister;
    TextView textView,textView2;
    EditText editTextID, editTextPW;
    String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnStart = (ImageButton)findViewById(R.id.imageButtonStart);
        btnRank = (ImageButton)findViewById(R.id.imageButtonRank);
        btnEnter = (ImageButton)findViewById(R.id.imageButtonEnter);
        btnRegister = (ImageButton)findViewById(R.id.imageButtonRegister);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        editTextID = (EditText)findViewById(R.id.editTextID);
        editTextPW = (EditText)findViewById(R.id.editTextPW);

        id = "test10";
    }

    public void onClickStart(View v) {
        textView.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        editTextID.setVisibility(View.VISIBLE);
        editTextPW.setVisibility(View.VISIBLE);
        btnEnter.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.VISIBLE);
    }

    public void onClickRank(View v) {
        Intent intent = new Intent(this, ActivityScore.class);
        startActivity(intent);
    }

    public void onClickEnter(View v) {
        String id = editTextID.getText().toString();
        String pw = editTextPW.getText().toString();


        if(id.isEmpty() || pw.isEmpty()) {
            Toast.makeText(this, "아이디 또는 비밀번호를 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }


        //테스트를 위한 자동 로그인
        Intent intent = new Intent(this, ActivityStage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("id","admin");
        startActivity(intent);
        Toast.makeText(this, "Offline mode", Toast.LENGTH_SHORT).show();
        return;

        /*
        if(id.equals("admin") || pw.equals("123")) {
            Intent intent = new Intent(this, ActivityStage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id","admin");
            startActivity(intent);
            Toast.makeText(this, "Offline mode", Toast.LENGTH_SHORT).show();
            return;
        }


        String postURL = "http://1.233.127.192:8080/SachunServer/login.jsp"; // jsp 주소

        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(postURL);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("id",id));
            params.add(new BasicNameValuePair("pw",pw));

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
            String[] result = recv.split(":");  // JSP로 부터 들어온 데이터를 : 기준으로 잘라준다

            if(result.length>1) {
                if (result[1].equals("fail")) {    // ����
                    Toast.makeText(this, "Login fail", Toast.LENGTH_SHORT).show();
                } else if (result[1].equals("success")) { // �α��οϷ�
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, ActivityStage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        }catch(IOException e1){
            Toast.makeText(this, "JSP disconnected", Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }
        */

    }

    public void onClickRegister(View v) {
        String id = editTextID.getText().toString();
        String pw = editTextPW.getText().toString();

        if(id.isEmpty() || pw.isEmpty()) {
            Toast.makeText(this, "check id or pw", Toast.LENGTH_SHORT).show();
            return;
        }
/*
        String postURL = "http://1.233.127.192:8080/SachunServer/register.jsp";

        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(postURL);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("id",id));
            params.add(new BasicNameValuePair("pw",pw));

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
            String[] result = recv.split(":");

            if(result.length>1) {
                if (result[1].equals("overlap")) {
                    Toast.makeText(this, "overlap", Toast.LENGTH_SHORT).show();
                } else if (result[1].equals("success")) {
                    Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show();
                }
            }
        }catch(IOException e1){
            Toast.makeText(this, "JSP disconnected", Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }*/
    }
}


package com.example.penguin.sichuan;

/**
 * Created by Penguin on 2018-03-18.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import org.apache.http.protocol.HTTP;
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ActivityPlay extends Activity {
    String id;
    int stage;
    Data data;
    GridLayout myGrid;
    ImageButton[] btns;
    int Col, Row;
    int MAX_X, MAX_Y;
    int start_x, start_y;
    int dest_x, dest_y;
    int old_id;
    Context context;
    PathTimer pathTimer2;
    Boolean press;
    TextView txt,txt2,txt3,txt4,txt5,txt6;
    ProgressBar progressBar;
    ///////////////////////////
    boolean delete;
    boolean fin;
    boolean warn;
    boolean excep;
    MediaPlayer mp_delete;
    MediaPlayer mp_delete2;
    MediaPlayer[]mp_combo;
    MediaPlayer mpBgm;
    MediaPlayer mpItem, mpItem2, mpItem3;
    MediaPlayer mpGo, mpClear, mpOver, mpWarn;
    //////////////////////////

    public void onDestroy() {

        super.onDestroy();
        mpBgm.stop();
        fin = true;

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_play);
        fin = false;
        warn = false;
        excep = false;
        Intent intent = getIntent();
        stage = intent.getIntExtra("stage", 0);
        id = intent.getStringExtra("id");
        if(stage == 0) {
            Toast.makeText(this, "Stage Load Error", Toast.LENGTH_SHORT).show();
            return;
        }

        data = new Data();


        data.loadStage(stage);


        data.map.mixData();
        //data.loadStage(1);
        txt = (TextView)findViewById(R.id.textView);
        txt2 = (TextView)findViewById(R.id.textView2);
        txt3 = (TextView)findViewById(R.id.textView3);
        txt4 = (TextView)findViewById(R.id.textView4);
        txt5 = (TextView)findViewById(R.id.textView5);
        txt6 = (TextView)findViewById(R.id.textView6);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        progressBar.setMax(data.map.getTime());
        progressBar.setProgress(data.map.getTime());


        context = this;
        press = false;
        myGrid = (GridLayout) findViewById(R.id.mygrid);
        Col = myGrid.getColumnCount();
        Row = myGrid.getRowCount();
        btns = new ImageButton[Col * Row];
        MAX_X = Col + 2;
        MAX_Y = Row + 2;


        for (int y = 0; y < Row; y++) {
            for (int x = 0; x < Col; x++) {
                ImageButton btn = new ImageButton(this);
                btn.setScaleType(ImageView.ScaleType.CENTER_CROP);
                btn.setBackgroundColor(Color.TRANSPARENT);
                //btn.setPadding(1, 1, 1, 1);
                btn.setPadding(1, 1, 1, 1);
                //LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(100, 140);
                //btn.setLayoutParams(Params);
                btn.setId((y * Col) + x);
                View.OnClickListener onClick;
                btn.setOnClickListener(mClickListener);
                btns[y * Col + x] = btn;



                //btn.setLayoutParams(new RelativeLayout.LayoutParams(86, 91));
                btn.setLayoutParams(new RelativeLayout.LayoutParams(86, 91));

                btn.setAdjustViewBounds(true);
                //btn.setMinimumWidth(100);
                //btn.setMinimumHeight(100);

                btn.setScaleType(ImageView.ScaleType.FIT_XY);


                if (data.map.getPosData(x + 1, y + 1) == 100) {
                    btn.setVisibility(View.INVISIBLE);
                }
                else if (data.map.getPosData(x + 1, y + 1) > 100 && data.map.getPosData(x + 1, y + 1) <= 110) {
                    btn.setImageResource(R.drawable.card_1 + (data.map.getPosData(x + 1, y + 1)%100));
                }
                else if (data.map.getPosData(x + 1, y + 1) == 150) {
                    btn.setImageResource(R.drawable.card_red);
                }
                else if (data.map.getPosData(x + 1, y + 1) == 160) {
                    btn.setImageResource(R.drawable.card_blue);
                }
                else if (data.map.getPosData(x + 1, y + 1) == 151) {
                    btn.setImageResource(R.drawable.card_keyhole_r);
                }
                else if (data.map.getPosData(x + 1, y + 1) == 161) {
                    btn.setImageResource(R.drawable.card_keyhole_b);
                }
                else if (data.map.getPosData(x + 1, y + 1) == 170) {
                    btn.setImageResource(R.drawable.card_win);
                }
                else if (data.map.getPosData(x + 1, y + 1) == 999) {
                    btn.setImageResource(R.drawable.blockcard);
                }
                btn.setBackgroundResource(0);


                myGrid.addView(btn);
            }
        }

        mp_combo = new MediaPlayer[6];
        delete = false;
        mp_delete = MediaPlayer.create(this, R.raw.delete1);
        mp_delete2 = MediaPlayer.create(this, R.raw.delete2);

        for(int i=0; i<6; i++) {
            mp_combo[i] = MediaPlayer.create(this, R.raw.combo1+i);
        }

        mpItem = MediaPlayer.create(this, R.raw.hint);
        mpItem2 = MediaPlayer.create(this, R.raw.shuffle);
        mpItem3 = MediaPlayer.create(this, R.raw.hammer);
        mpGo = MediaPlayer.create(this, R.raw.readygo);
        mpClear = MediaPlayer.create(this, R.raw.clear);
        mpOver = MediaPlayer.create(this, R.raw.timeover);
        mpBgm = MediaPlayer.create(this, R.raw.bgm3);
        mpWarn = MediaPlayer.create(this, R.raw.warning);
        mpGo.start();
        mpBgm.setLooping(true);
        mpBgm.start();
        handle.sendEmptyMessage(1);
        updateView();
    }

    Handler handle = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if (data.map.getTime() > 0 && fin==false) {
                        data.map.decTime();
                        txt3.setText(String.valueOf(progressBar.getProgress()-1));
                        progressBar.setProgress(data.map.getTime());
                        if(data.map.getTime() < 10 && warn == false) {
                            warn = true;
                            mpWarn.start();
                        }
                        if(fin==false)
                            sendEmptyMessageDelayed(1, 1000);
                    }
                    else if(data.map.getTime() == 0 && fin==false) {
                        if(stage <= 5) {
                            loseEvent();
                        }
                        else {
                            rankEvent();
                        }
                    }
            }
        }
    };


    public void updateView() {
        txt.setText(String.valueOf(data.map.totalCard()));
        txt2.setText(String.valueOf(data.map.totalPossible()));
        txt3.setText(String.valueOf(data.map.getTime()));
        txt4.setText(String.valueOf(data.map.getItem()));
        txt5.setText(String.valueOf(data.map.getItem2()));
        txt6.setText(String.valueOf(data.map.getItem3()));
    }

    public void mix() {
        data.map.mixData();

        if(data.map.totalCard()==0) {
            return;
        }
        if(data.map.totalPossible() > 0) {
            for (int y = 0; y < Row; y++) {
                for (int x = 0; x < Col; x++) {
                    int id = y * Col + x;


                    if (data.map.getPosData(x + 1, y + 1) == 100) {
                        btns[id].setVisibility(View.INVISIBLE);
                    }
                    else if (data.map.getPosData(x + 1, y + 1) > 100 && data.map.getPosData(x + 1, y + 1) <= 110) {
                        btns[id].setImageResource(R.drawable.card_1 + (data.map.getPosData(x + 1, y + 1) % 100));
                    }
                    else if (data.map.getPosData(x + 1, y + 1) == 150) {
                        btns[id].setImageResource(R.drawable.card_red);
                    }
                    else if (data.map.getPosData(x + 1, y + 1) == 160) {
                        btns[id].setImageResource(R.drawable.card_blue);
                    }
                    else if (data.map.getPosData(x + 1, y + 1) == 151) {
                        btns[id].setImageResource(R.drawable.card_keyhole_r);
                    }
                    else if (data.map.getPosData(x + 1, y + 1) == 161) {
                        btns[id].setImageResource(R.drawable.card_keyhole_b);
                    }
                    else if (data.map.getPosData(x + 1, y + 1) == 170) {
                        btns[id].setImageResource(R.drawable.card_win);
                    }
                    else if (data.map.getPosData(x + 1, y + 1) == 999) {
                        btns[id].setImageResource(R.drawable.blockcard);
                    }
                }
            }
        }
        else {
            mix();
        }


    }

    public void hintEffect(int pos_x, int pos_y) {
        int id = (pos_y-1) * Col + pos_x-1;

        if (data.map.getPosData(pos_x, pos_y) > 100 && data.map.getPosData(pos_x, pos_y) <= 110) {
            btns[id].setImageResource(R.drawable.selected_1 + data.map.getPosData(pos_x, pos_y) % 100);
        }
        else if (data.map.getPosData(pos_x, pos_y) == 150) {
            btns[id].setImageResource(R.drawable.selected_red);
        }
        else if (data.map.getPosData(pos_x, pos_y) == 160) {
            btns[id].setImageResource(R.drawable.selected_blue);
        }
        else if (data.map.getPosData(pos_x, pos_y) == 170) {
            btns[id].setImageResource(R.drawable.selected_win);
        }
    }


    public void onClickItem(View v) {

        if(v == findViewById(R.id.textViewItem1)) {
            //Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
            if(data.map.getItem() > 0) {
                if (data.map.totalPossible() == 0) {
                    Toast.makeText(this, "There is no answer", Toast.LENGTH_SHORT).show();
                    return;
                }
                mpItem.start();
                data.map.setItem(data.map.getItem() - 1);
                //cView.hintPlay();
                int[][] tempMap = data.map.getData();
                Path[] temp = data.map.hint();
                hintEffect(temp[0].getX(), temp[0].getY());
                hintEffect(temp[1].getX(), temp[1].getY());

            /*
            cView.hintEffect(temp[0].getX(), temp[0].getY(), tempMap);
            cView.hintEffect(temp[1].getX(), temp[1].getY(), tempMap);
            cView.setRemainItemHud();*/
            }
        }
        else if(v == findViewById(R.id.textViewItem2)) {
            if(data.map.getItem2() > 0) {
                if(data.map.totalCard() > 0) {
                    //Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                    mpItem2.start();
                    mix();
                    data.map.setItem2(data.map.getItem2() - 1);
                }
            }

        }
        else if(v == findViewById(R.id.textViewItem3)) {
            if(data.map.getItem3() > 0) {
                //Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show();
                data.map.setItem3En(true);
                data.map.setItem3(data.map.getItem3() - 1);
            }
        }

        updateView();
    }

    public void rankEvent() {
        mpClear.start();
        mpBgm.stop();
        String postURL = "http://1.233.127.192:8080/SachunServer/update.jsp"; // jsp 주소
        for (int y = 0; y < Row; y++) {
            for (int x = 0; x < Col; x++) {
                int id = Col * y + x;

                btns[id].setImageResource(R.drawable.card_back);
                btns[id].setClickable(false);
            }
        }

        /*
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(postURL);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("id",id));
            params.add(new BasicNameValuePair("record",String.valueOf(data.map.getScore())));

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
            Toast.makeText(this, "" + id +"님 "+data.map.getScore()+"점수 랭킹등록완료", Toast.LENGTH_SHORT).show();
        }catch(IOException e1){
            Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }*/

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ActivityStage.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }, 3000);


    }

    public void finishEvent() {
        // Clear
        mpClear.start();
        mpBgm.stop();
        fin = true;
        if (stage >= 1 && stage <= 5) {
            String postURL = "http://1.233.127.192:8080/SachunServer/updateStage.jsp"; // jsp 주소
/*
            try{
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(postURL);
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("id",id));
                params.add(new BasicNameValuePair("stage",String.valueOf(stage)));

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

            }catch(IOException e1){
                Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
                e1.printStackTrace();
            }*/

            Toast.makeText(this, "Stage" + stage + " Clear!", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), ActivityStage.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }, 3000);
        }

    }

    public void loseEvent() {
        mpBgm.stop();
        mpOver.start();
        if (stage >= 1 && stage <= 5) {
            Toast.makeText(this, "Lose!", Toast.LENGTH_SHORT).show();
            for (int y = 0; y < Row; y++) {
                for (int x = 0; x < Col; x++) {
                    int id = Col * y + x;

                    btns[id].setImageResource(R.drawable.card_back);
                    btns[id].setClickable(false);
                }
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), ActivityStage.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }, 3000);
        }
        else if(stage == 6) { // TimeAttack
            Intent intent = new Intent(getApplicationContext(), ActivityStage.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }


    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();

            int pos_x = id % Col;
            int pos_y = id / Col;

            pos_x++;
            pos_y++;

            Log.v("Test", "select (" + pos_x +","+pos_y +")");


            if (data.map.checkItem3En()) {
                int temp_x = pos_x;
                int temp_y = pos_y;


                if (data.map.getPosData(temp_x, temp_y) == 151
                        || data.map.getPosData(temp_x, temp_y) == 161
                        || data.map.getPosData(temp_x, temp_y) == 999
                        || data.map.getPosData(temp_x, temp_y) == 100
                        || data.map.getPosData(temp_x, temp_y) == 170
                        || data.map.getPosData(temp_x, temp_y) == 150
                        || data.map.getPosData(temp_x, temp_y) == 160)
                    return;

                Path temp2 = data.map.deleteCardLikeThis(temp_x, temp_y);


                if (temp2 != null) {
                    int temp_x2 = temp2.getX();
                    int temp_y2 = temp2.getY();

                    clearCard(temp_x, temp_y, temp_x2, temp_y2);
                    if(data.map.totalCard() == 0) {
                        finishEvent();
                    }
                    mpItem3.start();
                    data.map.setItem3En(false);
                }

                return;
            }


            if (press == false) {
                start_x = pos_x;
                start_y = pos_y;

                if (data.map.getPosData(start_x, start_y) == 151
                        || data.map.getPosData(start_x, start_y) == 161
                        || data.map.getPosData(start_x, start_y) == 999
                        || data.map.getPosData(start_x, start_y) == 100)
                    return;

                old_id = id;
                press = true;

                hintEffect(pos_x, pos_y);
            } else {
                dest_x = pos_x;
                dest_y = pos_y;


                if (data.isCorrect(start_x, start_y, dest_x, dest_y)) {
                    if(delete == true) {
                        mp_delete.start();
                        delete = false;
                    }
                    else
                    {
                        mp_delete2.start();
                        delete = true;
                    }
                    Log.v("Test","Possible");

                    int backup = data.map.getPosData(start_x, start_y);
                    clearCard(start_x, start_y, dest_x, dest_y);
                    pathEffect(data.map.getPathData(), data.map.getPathMax());

                    updateView();

                    if (data.map.totalCard() == 0 || (data.map.getMode() == 1 && backup == 170)) {
                        finishEvent();
                    } else if (backup == 150) { // ���� ���� ���Ž�
                        excep = true;
                        for (int y = 1; y <= 10; y++) {    // ���� �ʿ�
                            for (int x = 1; x <= 20; x++) {
                                if (data.map.getPosData(x, y) == 151) {
                                    data.map.setPosData(x, y, 100);
                                    removeCard(x, y);
                                }
                            }
                        }
                    } else if (backup == 160) { // �Ķ��� ���� ���Ž�
                        excep = true;
                        for (int y = 1; y <= 10; y++) {    // ���� �ʿ�
                            for (int x = 1; x <= 20; x++) {
                                if (data.map.getPosData(x, y) == 161) {
                                    data.map.setPosData(x, y, 100);
                                    removeCard(x, y);
                                }
                            }
                        }
                    }
                    updateView();
                } else {
                    if (data.map.getPosData(start_x, start_y) > 100 && data.map.getPosData(start_x, start_y) <= 110) {
                        btns[old_id].setImageResource(R.drawable.card_1 + data.map.getPosData(start_x, start_y) % 100 );
                    }
                    else if (data.map.getPosData(start_x, start_y) == 150) {
                        btns[old_id].setImageResource(R.drawable.card_red);
                    }
                    else if (data.map.getPosData(start_x, start_y) == 160) {
                        btns[old_id].setImageResource(R.drawable.card_blue);
                    }
                    else if (data.map.getPosData(start_x, start_y) == 170) {
                        btns[old_id].setImageResource(R.drawable.card_win);
                    }
                }
                press = false;
            }
        }




    };


    public void removeCard(int x, int y) {
        data.map.setPosData(x, y, 100);
        btns[((y-1)*Col) + x-1].setVisibility(View.INVISIBLE);
    }

    public void clearCard(int x, int y, int x2, int y2) {
        data.map.setPosData(x, y, 100);
        data.map.setPosData(x2, y2, 100);
        btns[((y-1)*Col) + x-1].setVisibility(View.INVISIBLE);
        btns[((y2-1)*Col) + x2-1].setVisibility(View.INVISIBLE);
        data.addCombo(2000);
        if(data.getCombo()>1) {
            mp_combo[data.getCombo()-2].start();
        }
        data.map.addScore(150*data.getCombo());
        if(data.map.totalPossible() == 0) {
            if(excep==true) {
                excep = false;
                return;
            }
            mpItem2.start();
            mix();
        }
    }

    public void pathEffect(Path[] pathData, int pathMax) {
        int index;
        Timer pathTimer;
        ImageButton temp;
        if (pathMax > 2) {
            for (int i = 1; i < pathMax - 1; i++) {
                temp = btns[(pathData[i].getY() - 1) * Col + pathData[i].getX() - 1];
                //temp.setBackgroundDrawable(getResources().getDrawable(R.drawable.clearline_1 + (pathData[i].getDirection()) - 1)); // ?? ???
                temp.setImageResource(R.drawable.clearline_1 + (pathData[i].getDirection()) - 1);
                temp.setVisibility(View.VISIBLE);
            }
            pathTimer = new Timer();
            pathTimer2 = new PathTimer(pathData, pathMax);
            pathTimer.schedule(pathTimer2, 100);
        }
    }


    public class PathTimer extends TimerTask {
        Path[] pathData;
        int pathMax;

        PathTimer(Path[] pathData, int pathMax) {
            this.pathData = pathData;
            this.pathMax = pathMax;
        }

        @Override
        public void run() {
            Message msg = handler.obtainMessage();
            handler.sendMessage(msg);
        }

        final Handler handler = new Handler()  {

            public void handleMessage(Message msg)  {
                for (int i = 1; i < pathMax - 1; i++) {
                    btns[(pathData[i].getY() - 1) * Col + pathData[i].getX() - 1].setVisibility(View.INVISIBLE);
                }
            }

        };
    }
}
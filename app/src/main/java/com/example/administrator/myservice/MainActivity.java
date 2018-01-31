package com.example.administrator.myservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=((TextView) findViewById(R.id.ll));
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OtherActivity.class));
            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Socket socket=new Socket("10.38.1.171",4000);
                    BufferedReader bR=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final String line=bR.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("来自服务器的数据："+line);
                        }
                    });

                    bR.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

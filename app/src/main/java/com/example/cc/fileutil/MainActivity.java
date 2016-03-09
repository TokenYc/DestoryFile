package com.example.cc.fileutil;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiongbull.jlog.JLog;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tvDir;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDir = (TextView) findViewById(R.id.tv_dir);
        tvDir.setText(FileUtil.APP_FOLDER);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final File file = new File(FileUtil.APP_FOLDER);
                if (FileUtil.deleteFolder(FileUtil.APP_FOLDER))
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Toast.makeText(MainActivity.this, "" + file.createNewFile(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

            }
        });
        thread.start();
    }
}

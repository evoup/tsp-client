package com.evoupsight.tspclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String chromosomeStr;
    Integer selectLocation;
    CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10;
    Button btn;
    String showText;
    MainActivity a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chromosomeStr = "";
        selectLocation = 0;
        box1 = (CheckBox) findViewById(R.id.checkBox);
        box2 = (CheckBox) findViewById(R.id.checkBox2);
        box3 = (CheckBox) findViewById(R.id.checkBox3);
        box4 = (CheckBox) findViewById(R.id.checkBox4);
        box5 = (CheckBox) findViewById(R.id.checkBox5);
        box6 = (CheckBox) findViewById(R.id.checkBox6);
        box7 = (CheckBox) findViewById(R.id.checkBox7);
        box8 = (CheckBox) findViewById(R.id.checkBox8);
        box9 = (CheckBox) findViewById(R.id.checkBox9);
        box10 = (CheckBox) findViewById(R.id.checkBox10);
        btn = (Button) findViewById(R.id.button);
        CheckBoxListener listener = new CheckBoxListener(this);
        box1.setOnCheckedChangeListener(listener);
        box2.setOnCheckedChangeListener(listener);
        box3.setOnCheckedChangeListener(listener);
        box4.setOnCheckedChangeListener(listener);
        box5.setOnCheckedChangeListener(listener);
        box6.setOnCheckedChangeListener(listener);
        box7.setOnCheckedChangeListener(listener);
        box8.setOnCheckedChangeListener(listener);
        box9.setOnCheckedChangeListener(listener);
        box10.setOnCheckedChangeListener(listener);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                planScheduler();
            }
        });
    }

    public void setChromosomeStr(String chromosomeStr) {
        this.chromosomeStr = chromosomeStr;
    }

    public void setSelectLocation(Integer selectLocation) {
        this.selectLocation = selectLocation;
    }

    /**
     * 行程规划排序
     */
    public void planScheduler() {
        if (selectLocation != 6) {
            Toast.makeText(this, "请选择6个要拜访的地点", Toast.LENGTH_SHORT).show();
            return;
        }
        btn.setEnabled(false);
        // 起一个异步任务来做计算
        Object[] objs = new Object[2];
        objs[0] = this;
        objs[1] = chromosomeStr;
        new CacuTask().execute(objs);
        btn.setEnabled(true);
    }

    public void setA(MainActivity a) {
        this.a = a;
    }

    public void runThread(String text) {
        this.showText = text;
        setA(this);
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(a, showText, Toast.LENGTH_LONG).show();
                        }
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}

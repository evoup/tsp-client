package com.evoupsight.tspclient;

import android.content.Intent;
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
    String caluRes;
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
        btn.setClickable(false);
        // 起一个异步任务来做计算
        Object[] objs = new Object[2];
        objs[0] = this;
        objs[1] = chromosomeStr;
        new CacuTask().execute(objs);
        btn.setEnabled(true);
        btn.setClickable(true);
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
                    if (showText.equals("正在进行云计算排序")) {
                        showResult(caluRes);
                    }
                    a.btn.setEnabled(true);
                    a.btn.setClickable(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void showResult(String res) {
        String geneStr = res.substring(res.indexOf("result:") + 7, res.indexOf("_"));
        if (geneStr != null && !geneStr.equals("")) {
            StringBuffer msg = new StringBuffer("拜访顺序：\n");
            char[] geneArray = geneStr.toCharArray();
            for (int i = 0; i < geneArray.length; i++) {
                if ("A".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtA) + "\n");
                if ("B".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtB) + "\n");
                if ("C".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtC) + "\n");
                if ("D".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtD) + "\n");
                if ("E".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtE) + "\n");
                if ("F".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtF) + "\n");
                if ("G".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtG) + "\n");
                if ("H".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtH) + "\n");
                if ("I".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtI) + "\n");
                if ("J".equals("" + geneArray[i]))
                    msg.append(getResources().getString(R.string.districtJ) + "\n");
            }
            Intent intent = new Intent();
            intent.setClass(this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("result", msg.toString());
            intent.putExtras(bundle);
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();
        } else {
            Toast.makeText(this, "排序数据错误", Toast.LENGTH_LONG).show();
        }
    }
}

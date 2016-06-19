package com.evoupsight.tspclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by evoup on 16-6-19.
 */
public class ResultActivity extends Activity {
    public TextView tv;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
        tv = (TextView) findViewById(R.id.textView2);
        tv.setTextSize(30);
        btn2 = (Button) findViewById(R.id.button2);
        Bundle bundle = this.getIntent().getExtras();
        String res = bundle.getString("result");
        tv.setText(res);

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ResultActivity.this, MainActivity.class);
                ResultActivity.this.startActivity(intent);
                ResultActivity.this.finish();
            }
        });
    }
}

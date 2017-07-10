package com.example.cj.testactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cj.testactivityforresult.util.Constants;

/**
 * 带有返回值的Activity跳转
 * <p>
 * 调用startActivityForResult方法，给定Intent和requestCode
 * 在启动的页面结束时，setResult设置返回值
 * 在接收返回值的页面中重写onActivityResult方法，匹配requestCode和resultCode
 * 这样就可以在对应的data中取出传入的数据
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.main_tv);
        btn = (Button) findViewById(R.id.main_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //参数一：请求的intent对象，参数二：requestCode请求码
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        });

    }

    //requestCode和resultCode是用来区分返回值从哪一个页面而来，并且给定到哪一个页面
    //data就是返回值携带的intent对象
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK) {
            //将数据从data中取出
            tv.setText(data.getStringExtra("name"));
        }
    }
}

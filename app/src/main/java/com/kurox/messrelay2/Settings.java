package com.kurox.messrelay2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        get();

    }

    private void get() {
     /*   //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("mailConfig",
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        MainActivity.mailTo = sharedPreferences.getString("to", "");
        MainActivity.mailSMTP = sharedPreferences.getString("SMTP", "");
        MainActivity.mailFrom = sharedPreferences.getString("from", "");
        MainActivity.mailPass = sharedPreferences.getString("pass", "");
        MainActivity.mailUser = sharedPreferences.getString("user", "");*/


        EditText t = (EditText) findViewById(R.id.mailTo);
        t.setText(MainActivity.mailTo);
        t = (EditText) findViewById(R.id.mailSMTP);
        t.setText(MainActivity.mailSMTP);
        t = (EditText) findViewById(R.id.mailForm);
        t.setText(MainActivity.mailFrom);
        t = (EditText) findViewById(R.id.mailPass);
        t.setText(MainActivity.mailPass);
        t = (EditText) findViewById(R.id.mailUser);
        t.setText(MainActivity.mailUser);
/*
        //使用toast信息提示框显示信息
        MainActivity.setOk = true;
        if (MainActivity.mailTo.length() < 1)
            MainActivity.setOk = false;
        if (MainActivity.mailSMTP.length() < 1)
            MainActivity.setOk = false;
        if (MainActivity.mailFrom.length() < 1)
            MainActivity.setOk = false;
        if (MainActivity.mailPass.length() < 1)
            MainActivity.setOk = false;
        if (MainActivity.mailUser.length() < 1)
            MainActivity.setOk = false;
        if (MainActivity.setOk)
            Toast.makeText(this, "读取数据成功", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "读取数据失败", Toast.LENGTH_LONG).show();
*/
    }

    public void set(View view) {
        EditText t = (EditText) findViewById(R.id.mailTo);
        MainActivity.mailTo = t.getText().toString();
        t = (EditText) findViewById(R.id.mailSMTP);
        MainActivity.mailSMTP = t.getText().toString();
        t = (EditText) findViewById(R.id.mailForm);
        MainActivity.mailFrom = t.getText().toString();
        t = (EditText) findViewById(R.id.mailPass);
        MainActivity.mailPass = t.getText().toString();
        t = (EditText) findViewById(R.id.mailUser);
        MainActivity.mailUser = t.getText().toString();
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = getSharedPreferences("mailConfig",
                Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("to", MainActivity.mailTo);
        editor.putString("SMTP", MainActivity.mailSMTP);
        editor.putString("from", MainActivity.mailFrom);
        editor.putString("pass", MainActivity.mailPass);
        editor.putString("user", MainActivity.mailUser);
        //提交当前数据
        editor.apply();
        //使用toast信息提示框提示成功写入数据
        Toast.makeText(this, "数据成功写入SharedPreferences！", Toast.LENGTH_LONG).show();
        finish();
    }
}

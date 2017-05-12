package com.kurox.messrelay2;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static String mailTo = "";
    public static String mailSMTP = "";
    public static String mailFrom = "";
    public static String mailUser = "";
    public static String mailPass = "";
    public static boolean setOk = false;
    public static boolean on = true;

    public static IntentFilter receiveFilter;
    public static String fullMessage = "";
    public static MessageReceiver messageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
//               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            }
        });
        //my code
        get();
        receiveFilter = new IntentFilter();
        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        messageReceiver = new MessageReceiver();
        registerReceiver(messageReceiver, receiveFilter);

        //sendEmail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //My Code
    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }


    public void get() {
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("mailConfig",
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        MainActivity.mailTo = sharedPreferences.getString("to", "");
        MainActivity.mailSMTP = sharedPreferences.getString("SMTP", "");
        MainActivity.mailFrom = sharedPreferences.getString("from", "");
        MainActivity.mailPass = sharedPreferences.getString("pass", "");
        MainActivity.mailUser = sharedPreferences.getString("user", "");

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

    }

    public void switchon(View view) {
        on = !on;
        if (on) {
            TextView sw=(TextView) findViewById(R.id.tv);
            sw.setText("ON");
            Snackbar.make(view, "服务已打开", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            TextView sw=(TextView) findViewById(R.id.tv);
            sw.setText("OFF");
            Snackbar.make(view, "服务已关闭", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
    }

}

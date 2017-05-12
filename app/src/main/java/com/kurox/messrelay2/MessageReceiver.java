package com.kurox.messrelay2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 2016/8/11.
 */
public class MessageReceiver extends BroadcastReceiver {
    private Thread newThread; //声明一个子线程


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");//提取短信消息
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        String address = messages[0].getOriginatingAddress();

        MainActivity.fullMessage = "";
        for (SmsMessage message : messages) {
            MainActivity.fullMessage += message.getMessageBody();
        }

        MainActivity.fullMessage += "  ——来自" + address;
        if ( MainActivity.on &&  MainActivity.setOk)
            sendEmail();
    }
    public void sendEmail() {


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        final String time = "自动转发 " + df.format(new Date());


        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Mail.send(MainActivity.mailSMTP, MainActivity.mailFrom, MainActivity.mailTo, time, MainActivity.fullMessage, MainActivity.mailUser, MainActivity.mailPass);
            }
        });
        newThread.start(); //启动线程
    }

}
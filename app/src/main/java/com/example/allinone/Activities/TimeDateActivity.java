package com.example.allinone.Activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeDateActivity extends AppCompatActivity {

    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView time4;
    private TextView time5;
    private TextView time6;
    private TextView time7;
    private TextView time8;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_date);

        time1=findViewById(R.id.time_1);
        time2=findViewById(R.id.time_2);
        time3=findViewById(R.id.time_3);
        time4=findViewById(R.id.time_4);
        time5=findViewById(R.id.time_5);
        time6=findViewById(R.id.time_6);
        time7=findViewById(R.id.time_7);
        time8=findViewById(R.id.time_8);

        String date = new SimpleDateFormat("dd/MM/yyyy, hh:mm a").format(new Date());
        time1.setText("1.) "+date);

        LocalDateTime now = LocalDateTime.now();
        time2.setText("2.) "+now);

        String date5 = String.valueOf(now);
        int from = date5.indexOf("T") + 1;
        int to = from + 5;
        String timeOfInterest = date5.substring(from, to);
        time3.setText("3.) How to convert #2 into required date or time form using SubString: \n"+timeOfInterest);
    }
}
//Types of Time/Date Format:
//1.) "yyyy.MM.dd G 'at' HH:mm:ss z"	== 2001.07.04 AD at 12:08:56 PDT;
//2.) "EEE, MMM d, ''yy"	== Wed, Jul 4, '01;
//3.) "h:mm a" == 12:08 PM;
//4.) "hh 'o''clock' a, zzzz" ==	12 o'clock PM, Pacific Daylight Time ;
//5.) "K:mm a, z"	== 0:08 PM, PDT;
//6.) "yyyyy.MMMMM.dd GGG hh:mm aaa" ==	02001.July.04 AD 12:08 PM;
//7.) "EEE, d MMM yyyy HH:mm:ss Z" ==	Wed, 4 Jul 2001 12:08:56 -0700;
//8.) "yyMMddHHmmssZ" ==	010704120856-0700;
//9.) "yyyy-MM-dd'T'HH:mm:ss.SSSZ"	== 2001-07-04T12:08:56.235-0700;
//10.) "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" ==	2001-07-04T12:08:56.235-07:00;
//11.) "YYYY-'W'ww-u" ==	2001-W27-3;




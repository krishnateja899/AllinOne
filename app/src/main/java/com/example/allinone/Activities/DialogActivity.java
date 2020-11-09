package com.example.allinone.Activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogActivity extends AppCompatActivity {

    private Button dialog_btn1;
    private Button dialog_btn2;
    private Button dialog_btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        dialog_btn1=findViewById(R.id.dialog_btn1);
        dialog_btn2=findViewById(R.id.dialog_btn2);


        dialog_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBox1();
            }
        });

        dialog_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBox2();
            }
        });

//        dialog_btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                dialogBox3();
//            }
//        });
    }

    private void dialogBox3() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(DialogActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                Date date = null;
                try {
                    date = fmt.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");
                String formattedTime = fmtOut.format(date);
                dialog_btn3.setText(formattedTime);
//                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                        LocalDateTime now = LocalDateTime.now();
//                        dtf.format(now)
                DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
                String dateString = dateFormat.format(new Date()).toString();

            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    private void dialogBox2() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        AlertDialog.Builder builder2 = new AlertDialog.Builder(DialogActivity.this);
        builder2.setCancelable(false);
        builder2.setTitle("Pick a car")
                .setItems(cars, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if (which==0){
                            Toast.makeText(DialogActivity.this, "Volvo", Toast.LENGTH_SHORT).show();
                        }if (which==1){
                            Toast.makeText(DialogActivity.this, "BMW", Toast.LENGTH_SHORT).show();
                        }if (which==2){
                            Toast.makeText(DialogActivity.this, "Ford", Toast.LENGTH_SHORT).show();
                        }if (which==3){
                            Toast.makeText(DialogActivity.this, "Mazda", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                ;
        AlertDialog alert12 = builder2.create();
        alert12.show();
    }

    private void dialogBox1() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DialogActivity.this);
        builder1.setMessage("Do you want to Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DialogActivity.this, "Yes Clicked!!!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DialogActivity.this, "No Clicked!!!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
package com.example.allinone.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.allinone.HelperClasses.Utils;
import com.example.allinone.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DialogActivity extends AppCompatActivity {

    private Button dialog_btn3;
    private Button dialog_btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button dialog_btn1 = findViewById(R.id.dialog_btn1);
        Button dialog_btn2 = findViewById(R.id.dialog_btn2);
        dialog_btn3 = findViewById(R.id.dialog_btn3);
        Button dialog_btn4 = findViewById(R.id.dialog_btn4);
        dialog_btn5 = findViewById(R.id.dialog_btn5);
        Button dialog_btn6 = findViewById(R.id.dialog_btn6);
        Button dialog_btn7 = findViewById(R.id.dialog_btn7);

        dialog_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox1();
            }
        });

        dialog_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox2();
            }
        });

        dialog_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox3();
            }
        });

        dialog_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.DialogClass(DialogActivity.this, "Test", "This is called from Utils", "OK");
            }
        });

        dialog_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox4();
            }
        });

        dialog_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox5();
            }
        });

        dialog_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.OpenGallery(DialogActivity.this, 1);
            }
        });
    }

    private void DialogBox1() {
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

    private void DialogBox2() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        AlertDialog.Builder builder2 = new AlertDialog.Builder(DialogActivity.this);
        builder2.setCancelable(false);
        builder2.setTitle("Pick a car")
                .setItems(cars, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if (which == 0) {
                            Toast.makeText(DialogActivity.this, "Volvo", Toast.LENGTH_SHORT).show();
                        }
                        if (which == 1) {
                            Toast.makeText(DialogActivity.this, "BMW", Toast.LENGTH_SHORT).show();
                        }
                        if (which == 2) {
                            Toast.makeText(DialogActivity.this, "Ford", Toast.LENGTH_SHORT).show();
                        }
                        if (which == 3) {
                            Toast.makeText(DialogActivity.this, "Mazda", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        ;
        AlertDialog alert12 = builder2.create();
        alert12.show();
    }

    private void DialogBox3() {
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

    private void DialogBox4() {
        DatePickerDialog datePickerDialog;
        SimpleDateFormat dateFormatter;
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        java.util.Calendar newCalendar = java.util.Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dialog_btn5.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void DialogBox5() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        edittext.setBackgroundResource(R.drawable.edittext_border);
        edittext.setPadding(30, 30, 30, 30);
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
        TextView title = new TextView(this);
        title.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        title.setTypeface(Typeface.SANS_SERIF);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 20, 0, 0);
        title.setPadding(30, 20, 0, 20);
        title.setLayoutParams(lp);
        title.setText("Enter in the text box");
        builder.setCustomTitle(title);
        builder.setCancelable(true);
        builder.setView(edittext);
        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DialogActivity.this, edittext.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton(
                "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
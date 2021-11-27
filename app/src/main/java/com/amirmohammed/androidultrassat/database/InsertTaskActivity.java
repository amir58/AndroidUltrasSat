package com.amirmohammed.androidultrassat.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.amirmohammed.androidultrassat.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class InsertTaskActivity extends AppCompatActivity {
    TextInputEditText editTextTitle;
    AutoCompleteTextView editTextDate, editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_task);

        editTextTitle = findViewById(R.id.et_task_title);
        editTextDate = findViewById(R.id.et_task_date);
        editTextTime = findViewById(R.id.et_task_time);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        editTextTime.setOnClickListener(v -> showTimePickerDialog());

        editTextDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println(v.getId());
                System.out.println(hasFocus);

                if (v.getId() == R.id.et_task_date && hasFocus) {
                    showDatePickerDialog();
                }
            }
        });

        editTextTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println(v.getId());
                System.out.println(hasFocus);

                if (v.getId() == R.id.et_task_time && hasFocus) showTimePickerDialog();
            }
        });


    }

    private void showTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        // Create a new instance of TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String realTime = (selectedHour > 12)
                        ? ( (selectedHour - 12) + ":" + selectedMinute + " PM")
                        : (selectedHour + ":" + selectedMinute + " AM");
                editTextTime.setText(realTime);
            }
        }, hour, minute, true);//Yes 24 hour time
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        final Calendar newCalendar = Calendar.getInstance();

        final DatePickerDialog StartTime = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);


                        editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                },
                newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        StartTime.show();

    }

    public void insertTask(View view) {
        String title = editTextTitle.getText().toString();

        if (title.isEmpty()) {
            editTextTitle.setError("Title required");
            return;
        }

        String date = editTextDate.getText().toString();

        if (date.isEmpty()) {
            editTextDate.setError("Date required");
            return;
        }

        String time = editTextTime.getText().toString();

        if (time.isEmpty()) {
            editTextTime.setError("Time required");
            return;
        }
        User user = new User("Amir", "Mohamed");
        Task task = new Task(title, date, time, "active", user);

        TasksDatabase.db.tasksDAO().insertTask(task);
    }


}
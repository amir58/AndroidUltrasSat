package com.amirmohammed.androidultrassat.firebase;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.amirmohammed.androidultrassat.R;
import com.amirmohammed.androidultrassat.database.TasksDatabase;
import com.amirmohammed.androidultrassat.database.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class FirebaseInsertTaskActivity extends AppCompatActivity {
    TextInputEditText editTextTitle;
    AutoCompleteTextView editTextDate, editTextTime;

    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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

                if (v.getId() == R.id.et_task_date && hasFocus) {
                    showDatePickerDialog();
                }
            }
        });

        editTextTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

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

        String uid = firebaseAuth.getUid();
        String taskId = String.valueOf(System.currentTimeMillis());

        Task task = new Task(taskId, title, date, time, "active");

        firestore.collection("roomOneUsers").document(uid)
                .collection("myTasks").document(taskId)
                .set(task);

    }


}
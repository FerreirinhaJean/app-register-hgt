package br.com.jean.registerhgt.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.jean.registerhgt.R;

public class OldRegisterActivity extends AppCompatActivity {

    TextView dataRegistro, horaRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataRegistro = findViewById(R.id.activity_old_register_data_registro);
        horaRegistro = findViewById(R.id.activity_old_register_hora_registro);

        Calendar calendar = Calendar.getInstance();
        int yearField = calendar.get(Calendar.YEAR);
        int monthField = calendar.get(Calendar.MONTH);
        int dayField = calendar.get(Calendar.DAY_OF_MONTH);
        int hourField = calendar.get(Calendar.HOUR_OF_DAY);
        int minuteField = calendar.get(Calendar.MINUTE);


        dataRegistro.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    dataRegistro.setText(sdf.format(selectedDate.getTime()));
                }, yearField, monthField, dayField);
                datePickerDialog.show();
            }
        });

        horaRegistro.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                Calendar selectedTime = Calendar.getInstance();
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                selectedTime.set(Calendar.MINUTE, minute);
                horaRegistro.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(selectedTime.getTime()));
            },
                    hourField, minuteField, true);
            timePickerDialog.show();
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
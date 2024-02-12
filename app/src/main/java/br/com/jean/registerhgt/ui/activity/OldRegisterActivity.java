package br.com.jean.registerhgt.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.jean.registerhgt.R;

public class OldRegisterActivity extends AppCompatActivity {

    TextView dataRegistro, horaRegistro;
    Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataRegistro = findViewById(R.id.activity_old_register_data_registro);
        horaRegistro = findViewById(R.id.activity_old_register_hora_registro);
        confirmar = findViewById(R.id.activity_old_register_confirmar);

        Calendar calendar = Calendar.getInstance();
        int yearField = calendar.get(Calendar.YEAR);
        int monthField = calendar.get(Calendar.MONTH);
        int dayField = calendar.get(Calendar.DAY_OF_MONTH);
        int hourField = calendar.get(Calendar.HOUR_OF_DAY);
        int minuteField = calendar.get(Calendar.MINUTE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dataRegistro.setHint(sdf.format(new Date()));

        horaRegistro.setHint(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.getTime()));

        dataRegistro.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);
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

        confirmar.setOnClickListener(v -> {
            if (checkFields()) {
                Intent intent = new Intent(this, CurrentRegisterActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    private boolean checkFields() {
        String dataSelect = dataRegistro.getText().toString();
        String hourSelect = horaRegistro.getText().toString();
        boolean isValid = true;

        if (hourSelect.isEmpty()) {
            horaRegistro.requestFocus();
            horaRegistro.setError("Informe o horário do exame");
            isValid = false;
        } else
            horaRegistro.setError(null);

        if (dataSelect.isEmpty()) {
            dataRegistro.requestFocus();
            dataRegistro.setError("Informe a data do exame");
            isValid = false;
        } else
            dataRegistro.setError(null);

        return isValid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
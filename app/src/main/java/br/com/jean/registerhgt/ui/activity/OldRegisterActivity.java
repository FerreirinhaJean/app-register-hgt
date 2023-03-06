package br.com.jean.registerhgt.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

        dataRegistro.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    Date dateSelect = calendar.getTime();
                    String dateFormatted = sdf.format(dateSelect);

                    dataRegistro.setText(dateFormatted);
                }, 2023, 2, 6);
                datePickerDialog.show();
            }
        });

        horaRegistro.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                horaRegistro.setText(hourOfDay + ":" + minute);
            },
                    11, 55, true);
            timePickerDialog.show();
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
package br.com.jean.registerhgt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import br.com.jean.registerhgt.R;
import br.com.jean.registerhgt.model.Register;
import br.com.jean.registerhgt.model.dao.RegisterDao;

public class CurrentRegisterActivity extends AppCompatActivity {

    private Spinner tipoGlicemia;
    private TextInputLayout valorGlicemia;
    private RegisterDao registerDao = new RegisterDao();

    private Date dateRegister;
    private Integer typeRegister;
    private EditText observacoes;
    private Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_register);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createFindViewById();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list_type_register,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        tipoGlicemia.setAdapter(adapter);

        Intent intent = getIntent();
        typeRegister = intent.getIntExtra("TYPE", 1);
        if (typeRegister == 0) {
            try {
                dateRegister = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(intent.getStringExtra("DATE_TIME_OLD_REGISTER"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        valorGlicemia.requestFocus();

        confirmar.setOnClickListener(v -> {
            saveRegister();
        });

    }

    private void createFindViewById() {
        tipoGlicemia = findViewById(R.id.activity_current_register_tipo_glicemia);
        valorGlicemia = findViewById(R.id.activity_current_register_valor_glicemia);
        observacoes = findViewById(R.id.activity_current_register_et_observacoes);
        confirmar = findViewById(R.id.activity_current_register_bt_confirmar);
    }

    private void saveRegister() {
        String glicemia = valorGlicemia.getEditText().getText().toString();
        if (glicemia.isEmpty()) {
            valorGlicemia.requestFocus();
            Toast.makeText(CurrentRegisterActivity.this, "Informe o valor da glicemia!", Toast.LENGTH_SHORT);
            return;
        }

        int value = Integer.parseInt(glicemia);

        if (typeRegister == 1)
            dateRegister = new Date();

        int type = tipoGlicemia.getSelectedItemPosition();
        String obs = observacoes.getText().toString();

        Register register = new Register(0, value, dateRegister, type, obs);
        registerDao.addRegister(register);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_current_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_current_activity_salvar)
            saveRegister();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
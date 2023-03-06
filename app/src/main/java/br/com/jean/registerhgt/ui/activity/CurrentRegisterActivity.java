package br.com.jean.registerhgt.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

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

    }

    private void createFindViewById() {
        tipoGlicemia = findViewById(R.id.activity_current_register_tipo_glicemia);
        valorGlicemia = findViewById(R.id.activity_current_register_valor_glicemia);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_current_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_current_activity_salvar) {
            int value = Integer.parseInt(valorGlicemia.getEditText().getText().toString());
            Date date = new Date();
            int type = tipoGlicemia.getSelectedItemPosition();

            Register register = new Register(0, value, date, type);
            registerDao.addRegister(register);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
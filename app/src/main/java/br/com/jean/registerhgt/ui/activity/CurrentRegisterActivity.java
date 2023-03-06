package br.com.jean.registerhgt.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.jean.registerhgt.R;

public class CurrentRegisterActivity extends AppCompatActivity {

    private Spinner tipoGlicemia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_register);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tipoGlicemia = findViewById(R.id.activity_current_register_tipo_glicemia);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list_type_register,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        tipoGlicemia.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_current_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_current_activity_salvar) {
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
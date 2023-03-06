package br.com.jean.registerhgt.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;

import br.com.jean.registerhgt.R;

public class CurrentRegisterActivity extends AppCompatActivity {

    private Spinner tipoGlicemia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_register);

        tipoGlicemia = findViewById(R.id.activity_current_register_tipo_glicemia);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list_type_register,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        tipoGlicemia.setAdapter(adapter);

    }
}
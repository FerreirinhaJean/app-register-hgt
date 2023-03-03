package br.com.jean.registerhgt.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.jean.registerhgt.R;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabNewRegister, fabOldRegister;
    private TextView tvNewRegister, tvOldRegister;
    private ExtendedFloatingActionButton fabAddRegister;
    private boolean hasClickeFab = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddRegister = findViewById(R.id.activity_main_adicionar_registro);
        fabNewRegister = findViewById(R.id.activity_main_adicionar_registro_atual);
        fabOldRegister = findViewById(R.id.activity_main_adicionar_registro_passado);
        tvNewRegister = findViewById(R.id.activity_main_adicionar_registro_atual_texto);
        tvOldRegister = findViewById(R.id.activity_main_adicionar_registro_passado_texto);

        fabAddRegister.shrink();
        fabAddRegister.setOnClickListener(v -> {
            if (!hasClickeFab) {
                fabAddRegister.extend();
                fabNewRegister.show();
                fabOldRegister.show();
                tvNewRegister.setVisibility(View.VISIBLE);
                tvOldRegister.setVisibility(View.VISIBLE);
                hasClickeFab = true;
            } else {
                fabAddRegister.shrink();
                fabNewRegister.hide();
                fabOldRegister.hide();
                tvNewRegister.setVisibility(View.GONE);
                tvOldRegister.setVisibility(View.GONE);
                hasClickeFab = false;
            }
        });

    }
}
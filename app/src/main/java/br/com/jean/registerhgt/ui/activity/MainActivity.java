package br.com.jean.registerhgt.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.jean.registerhgt.R;
import br.com.jean.registerhgt.model.Register;
import br.com.jean.registerhgt.ui.adapter.RegisterAdapter;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabNewRegister, fabOldRegister;
    private TextView tvNewRegister, tvOldRegister;
    private ExtendedFloatingActionButton fabAddRegister;
    private boolean hasClickeFab = false;
    private RecyclerView listItems;
    private RegisterAdapter registerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFindViewById();
        configListItemsAdapter();

        fabAddRegister.shrink();
        fabAddRegister.setOnClickListener(v -> {
            if (!hasClickeFab) {
                showExtendedFab();
            } else {
                hideExtendedFab();
            }
        });

        fabNewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, CurrentRegisterActivity.class);
            startActivity(intent);
            hideExtendedFab();
        });

        fabOldRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, OldRegisterActivity.class);
            startActivity(intent);
            hideExtendedFab();
        });
    }

    private void configListItemsAdapter() {
        registerAdapter = new RegisterAdapter(this);
        listItems.setLayoutManager(new LinearLayoutManager(this));
        listItems.setAdapter(registerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerAdapter.atualizar();
    }

    private void hideExtendedFab() {
        fabAddRegister.shrink();
        fabNewRegister.hide();
        fabOldRegister.hide();
        tvNewRegister.setVisibility(View.GONE);
        tvOldRegister.setVisibility(View.GONE);
        hasClickeFab = false;
    }

    private void showExtendedFab() {
        fabAddRegister.extend();
        fabNewRegister.show();
        fabOldRegister.show();
        tvNewRegister.setVisibility(View.VISIBLE);
        tvOldRegister.setVisibility(View.VISIBLE);
        hasClickeFab = true;
    }

    private void createFindViewById() {
        fabAddRegister = findViewById(R.id.activity_main_adicionar_registro);
        fabNewRegister = findViewById(R.id.activity_main_adicionar_registro_atual);
        fabOldRegister = findViewById(R.id.activity_main_adicionar_registro_passado);
        tvNewRegister = findViewById(R.id.activity_main_adicionar_registro_atual_texto);
        tvOldRegister = findViewById(R.id.activity_main_adicionar_registro_passado_texto);
        listItems = findViewById(R.id.activity_main_lista_registros);
    }
}
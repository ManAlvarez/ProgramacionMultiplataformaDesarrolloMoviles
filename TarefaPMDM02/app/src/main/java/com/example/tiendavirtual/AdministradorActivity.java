package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdministradorActivity extends AppCompatActivity {

    private static Intent login;
    private static TextView textViewNombre;
    private static String Nombre;
    private static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        bundle = getIntent().getExtras();
        Nombre = bundle.getString("Nombre");
        textViewNombre.setText(Nombre);
    }

    public void Salir(View view){
        super.onBackPressed();
        login = new Intent(AdministradorActivity.this, MainActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
    }
}

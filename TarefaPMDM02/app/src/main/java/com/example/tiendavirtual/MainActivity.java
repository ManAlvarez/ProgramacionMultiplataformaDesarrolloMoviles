package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static EditText editTextUsuario, editTextPass;
    private static TextView textError;
    private static CheckBox checkBoxAdministrador;
    private static Intent intentCliente, intentAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        editTextPass = (EditText) findViewById(R.id.editTextPassword);
        textError = (TextView) findViewById(R.id.textViewLogin);
        checkBoxAdministrador = (CheckBox) findViewById(R.id.checkBoxAdministrador);
    }

    public void realizaLogin(View view) {

        intentCliente = new Intent( MainActivity.this, ClienteActivity.class);
        intentAdmin = new Intent( MainActivity.this, AdministradorActivity.class);

        if(checkBoxAdministrador.isChecked()){
            if (editTextUsuario.getText().toString().equals("admin") && editTextPass.getText().toString().equals("abc123.")) {
                intentAdmin.putExtra("Nombre",editTextUsuario.getText().toString());
                textoLogin("");
                startActivity(intentAdmin);
            }else {
                    textoLogin("¡¡Credenciales incorrectoas!!!");
                }
        }else if (editTextUsuario.getText().toString().equals("cliente1") && editTextPass.getText().toString().equals("abc123.")){
            intentCliente.putExtra("Nombre",editTextUsuario.getText().toString());
            textoLogin("");
            startActivity(intentCliente);
        }else {
            textoLogin("¡¡Credenciales incorrectoas!!!");
        }

    }
    private void textoLogin(String error){

        editTextUsuario.setText("");
        editTextPass.setText("");
        textError.setText(error);
        checkBoxAdministrador.setChecked(false);
        editTextUsuario.requestFocus();

    }
}

package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(this, "tienda", null, 1);
    private EditText etUsuario, etContrasenha;

    /**
     * Método para crear la vista.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.et_usuario_login);
        etContrasenha = (EditText) findViewById(R.id.et_password_login);
    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void registrarse(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    /**
     * Método que abre una interacción con otra actividad, comprueba que tipo de usuario es y le pasa unos datos.
     *
     * @param view
     */

    public void acceder(View view) {

        String usuario = etUsuario.getText().toString();
        String contrasenha = etContrasenha.getText().toString();

        BD.abrir();

        Cursor cursor = BD.consultarUsuarioYContrasenha(usuario, contrasenha);
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            String idKey = cursor.getString(0);
            String nombre = cursor.getString(1);
            String apellidos = cursor.getString(2);
            String tipo = cursor.getString(6);
            Toast.makeText(this, "Cargando datos.....", Toast.LENGTH_SHORT).show();
            Intent intent;
            if (tipo.equals("administrador")) {
                intent = new Intent(this, AdminActivity.class);
                intent.putExtra("idKey", idKey);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellidos", apellidos);
                startActivity(intent);
            } else if (tipo.equals("cliente")) {
                intent = new Intent(this, ClienteActivity.class);
                intent.putExtra("idKey", idKey);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellidos", apellidos);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error al acceder a la aplicación contacta con el administrador", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_LONG).show();
            etUsuario.setText("");
            etContrasenha.setText("");
        }
        BD.close();
        etUsuario.setText("");
        etContrasenha.setText("");
    }
}

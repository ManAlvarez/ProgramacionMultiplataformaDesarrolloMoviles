package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(this, "tienda", null, 1);
    private EditText etNombre, etApellidos, etMail, etUsuario, etPass, etCompPass;
    private CheckBox ckbAdmin;
    private Intent intent;

    /**
     * Método para crear la vista.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.et_nombre_registro);
        etApellidos = (EditText) findViewById(R.id.et_apellidos_registro);
        etMail = (EditText) findViewById(R.id.et_email_registro);
        etUsuario = (EditText) findViewById(R.id.et_usuario_registro);
        etPass = (EditText) findViewById(R.id.et_password_registro);
        etCompPass = (EditText) findViewById(R.id.et_confirmar_password_registro);
        ckbAdmin = (CheckBox) findViewById(R.id.ckb_administrador_registro);
    }

    /**
     * Método para registrar a un cliente en la base de datos, interacciona con una actividad y le pasa datos.
     *
     * @param view
     */

    public void registrar(View view) {

        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String email = etMail.getText().toString();
        String usuario = etUsuario.getText().toString();
        String contrasenha = etPass.getText().toString();
        String confContrasenha = etCompPass.getText().toString();
        String tipo = "cliente";
        if (ckbAdmin.isChecked()) {
            tipo = "administrador";
        }

        BD.abrir();

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !usuario.isEmpty() && !contrasenha.isEmpty() && !confContrasenha.isEmpty()) {
            if (contrasenha.equals(confContrasenha)) {
                int cantidad = BD.consultaUsuario(usuario).getCount();
                if (cantidad == 0) {
                    BD.insertarRegistro(nombre, apellidos, email, usuario, contrasenha, tipo);
                    Toast.makeText(this, "Usuario registrado correctamente.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "El usuario ya existe.", Toast.LENGTH_LONG).show();
                    etUsuario.setText("");
                }
            } else {
                Toast.makeText(this, "Confirmación de contraseña incorrecta, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                etPass.setText("");
                etCompPass.setText("");
            }
        } else {
            Toast.makeText(this, "Deber rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        BD.cerrar();

        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void cancelar(View view) {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

package com.example.tiendavirtual;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegistroActivity extends AppCompatActivity {

    private static final int hacerFoto = 0x0001;
    private static final int seleccionarImagen = 0x0002;
    private static final int pedirPermisos = 0x0003;

    private AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(this, "tienda", null, 1);
    private EditText etNombre, etApellidos, etMail, etUsuario, etPass, etCompPass;
    private CheckBox ckbAdmin;
    private Intent intent;
    private ImageView imagenUsuario;

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
        imagenUsuario = (ImageView) findViewById(R.id.imageViewUsuario);

        imagenUsuario.setImageDrawable(getResources().getDrawableForDensity(R.drawable.previo, getResources().getDisplayMetrics().densityDpi));
    }

    /**
     * Método para registrar a un cliente en la base de datos, interacciona con una actividad y le pasa datos.
     *
     * @param view
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

                    // Cogemos la imagen de la view imagenUsuario y la guardamos con el nombre del usuario.
                    BitmapDrawable drawable = (BitmapDrawable) imagenUsuario.getDrawable();
                    Bitmap image = drawable.getBitmap();
                    Context context = getApplicationContext();
                    File avatar = new File(context.getFilesDir(), usuario + ".jpg");

                    try (FileOutputStream fos = context.openFileOutput(usuario + ".jpg", Context.MODE_PRIVATE)) {
                        image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (IOException e) {}

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
     * Método que pide permisos para la camara y nos permite hacer fotos.
     * @param view
     */

    public void hacerFoto(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, pedirPermisos);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, hacerFoto);
        }
    }

    /**
     * Método para poder seleccionar una imagen.
     * @param view
     */

    public void seleccionarImagen(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, seleccionarImagen);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==hacerFoto && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, hacerFoto);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == hacerFoto && resultCode == RESULT_OK && data != null){
            imagenUsuario.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }else if (requestCode == seleccionarImagen && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            imagenUsuario.setImageURI(uri);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return super.onSupportNavigateUp();
    }
}

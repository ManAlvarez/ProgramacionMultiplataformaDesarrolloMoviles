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
import android.graphics.BitmapFactory;
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

public class ModificarDatosActivity extends AppCompatActivity {

    private static final int hacerFoto = 0x0001;
    private static final int seleccionarImagen = 0x0002;
    private static final int pedirPermisos = 0x0003;

    private AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(this, "tienda", null, 1);
    private EditText etNombre, etApellidos, etMail,  etPassOld, etPassNew, etCompPass;
    private String idKey, nombre, apellidos, email, usuario, contrasenha, tipo;
    private CheckBox ckbAdmin;
    private Intent intent;
    private ImageView imagenUsuario;
    private int idKeyInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);

        etNombre = (EditText) findViewById(R.id.et_nombre_perfil);
        etApellidos = (EditText) findViewById(R.id.et_apellidos_perfil);
        etMail = (EditText) findViewById(R.id.et_email_perfil);
        etPassOld = (EditText) findViewById(R.id.et_old_pass_perfil);
        etPassNew = (EditText) findViewById(R.id.et_new_pass_perfil);
        etCompPass = (EditText) findViewById(R.id.et_conf_pass_perfil);
        ckbAdmin = (CheckBox) findViewById(R.id.ckb_admin_perfil);
        imagenUsuario = (ImageView) findViewById(R.id.imageViewUsuarioPerfil);

        idKey = getIntent().getStringExtra("idKey");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        email = getIntent().getStringExtra("email");
        usuario = getIntent().getStringExtra("usuario");
        contrasenha = getIntent().getStringExtra("contrasenha");
        tipo = getIntent().getStringExtra("tipo");

        idKeyInt = Integer.parseInt(idKey);

        etNombre.setText(nombre);
        etApellidos.setText(apellidos);
        etMail.setText(email);
        if(tipo.equals("administrador")){
            ckbAdmin.setChecked(true);
        }else{
            ckbAdmin.setChecked(false);
        }
        Context context = getApplicationContext();
        Bitmap bitmap = BitmapFactory.decodeFile(context.getFileStreamPath(usuario + ".jpg").getAbsolutePath());
        imagenUsuario.setImageBitmap(bitmap);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void guardar(View view){

        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String email = etMail.getText().toString();
        String contrasenhaOld = etPassOld.getText().toString();
        String contrasenhaNew = etPassNew.getText().toString();
        String confContrasenha = etCompPass.getText().toString();
        String tipo = "cliente";
        if (ckbAdmin.isChecked()) {
            tipo = "administrador";
        }

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !contrasenhaOld.isEmpty() && !contrasenhaNew.isEmpty() && !confContrasenha.isEmpty()) {
            if(contrasenhaOld.equals(contrasenha)){
                if(contrasenhaNew.equals(confContrasenha)){
                    BD.abrir();
                    BD.updatearPerfilUsuario(idKeyInt,nombre,apellidos,email,contrasenhaNew,tipo);
                    BD.close();

                    BitmapDrawable drawable = (BitmapDrawable) imagenUsuario.getDrawable();
                    Bitmap image = drawable.getBitmap();
                    Context context = getApplicationContext();
                    File avatar = new File(context.getFilesDir(), usuario + ".jpg");

                    try (FileOutputStream fos = context.openFileOutput(usuario + ".jpg", Context.MODE_PRIVATE)) {
                        image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (IOException e) {}

                    Toast.makeText(this, "Perfil modificado correctamente.", Toast.LENGTH_LONG).show();

                    if(tipo.equals("administrador")){
                        intent = new Intent(this, AdminActivity.class);
                    }else{
                        intent = new Intent(this, ClienteActivity.class);
                    }
                    intent.putExtra("idKey", idKey);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellidos", apellidos);
                    intent.putExtra("email",email);
                    intent.putExtra("usuario",usuario);
                    intent.putExtra("contrasenha", contrasenhaNew);
                    intent.putExtra("tipo",tipo);
                    startActivity(intent);

                }else{
                    Toast.makeText(this,"La contraseña nueva no coincide vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                    etPassNew.setText("");
                    etCompPass.setText("");
                }
            }else{
                Toast.makeText(this,"La contraseña antigua no es correcta",Toast.LENGTH_SHORT).show();
                etPassOld.setText("");
            }
        }
        else if(!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty()){
            BD.abrir();
            BD.updatearPerfilUsuario(idKeyInt,nombre,apellidos,email,contrasenha,tipo);
            BD.close();

            BitmapDrawable drawable = (BitmapDrawable) imagenUsuario.getDrawable();
            Bitmap image = drawable.getBitmap();
            Context context = getApplicationContext();
            File avatar = new File(context.getFilesDir(), usuario + ".jpg");

            try (FileOutputStream fos = context.openFileOutput(usuario + ".jpg", Context.MODE_PRIVATE)) {
                image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } catch (IOException e) {}

            Toast.makeText(this, "Perfil modificado correctamente.", Toast.LENGTH_LONG).show();

            if(tipo.equals("administrador")){
                intent = new Intent(this, AdminActivity.class);
            }else{
                intent = new Intent(this, ClienteActivity.class);
            }
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            intent.putExtra("email",email);
            intent.putExtra("usuario",usuario);
            intent.putExtra("contrasenha", contrasenha);
            intent.putExtra("tipo",tipo);
            startActivity(intent);

        }else{
            BD.abrir();
            BD.updatearPerfilUsuario(idKeyInt,this.nombre,this.apellidos,this.email,contrasenha,this.tipo);
            BD.close();

            BitmapDrawable drawable = (BitmapDrawable) imagenUsuario.getDrawable();
            Bitmap image = drawable.getBitmap();
            Context context = getApplicationContext();
            File avatar = new File(context.getFilesDir(), usuario + ".jpg");

            try (FileOutputStream fos = context.openFileOutput(usuario + ".jpg", Context.MODE_PRIVATE)) {
                image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } catch (IOException e) {}

            Toast.makeText(this, "Perfil modificado correctamente.", Toast.LENGTH_LONG).show();

            if(tipo.equals("administrador")){
                intent = new Intent(this, AdminActivity.class);
            }else{
                intent = new Intent(this, ClienteActivity.class);
            }
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", this.nombre);
            intent.putExtra("apellidos", this.apellidos);
            intent.putExtra("email",this.email);
            intent.putExtra("usuario",usuario);
            intent.putExtra("contrasenha", contrasenha);
            intent.putExtra("tipo",this.tipo);
            startActivity(intent);
        }
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

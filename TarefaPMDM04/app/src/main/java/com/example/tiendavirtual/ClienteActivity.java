package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ClienteActivity extends AppCompatActivity {

    private String idKey, nombre, apellidos, email, usuario, contrasenha, tipo;
    private Intent intent;

    /**
     * Método para crear la vista.
     *
     * @param savedInstanceState
     */

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        TextView tvNombre = (TextView) findViewById(R.id.tv_nombre_apellidos_usuario_cliente);
        ImageView ivImagenUser = (ImageView) findViewById(R.id.iv_usuario_cliente);

        idKey = getIntent().getStringExtra("idKey");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        email = getIntent().getStringExtra("email");
        usuario = getIntent().getStringExtra("usuario");
        contrasenha = getIntent().getStringExtra("contrasenha");
        tipo = getIntent().getStringExtra("tipo");

        tvNombre.setText(nombre + " " + apellidos);

        Context context = getApplicationContext();
        Bitmap bitmap = BitmapFactory.decodeFile(context.getFileStreamPath(usuario + ".jpg").getAbsolutePath());
        ivImagenUser.setImageBitmap(bitmap);

    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void perfil(View view) {
        intent = new Intent(this, ModificarDatosActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        intent.putExtra("email", email);
        intent.putExtra("usuario", usuario);
        intent.putExtra("contrasenha", contrasenha);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void hacerPedido(View view) {
        intent = new Intent(this, HacerPedidoActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        startActivity(intent);
    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void verPedidosTramite(View view) {
        intent = new Intent(this, PedidosTramiteCienteActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        startActivity(intent);
    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void verComprasRealizadas(View view) {
        intent = new Intent(this, ComprasRealizadasActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        startActivity(intent);
    }

    /**
     * Método que abre una interacción con otra actividad y le pasa unos datos.
     *
     * @param view
     */

    public void salir(View view) {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Método para crea un menú con acciones.
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barra_acciones_disponibles_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Método que comprueba que acción se ha seleccionado.
     *
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_hacer_pedido) {
            intent = new Intent(this, HacerPedidoActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        } else if (id == R.id.item_ver_pedidos_tramite_cliente) {
            intent = new Intent(this, PedidosTramiteCienteActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        } else if (id == R.id.item_ver_compras_realizadas) {
            intent = new Intent(this, ComprasRealizadasActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        } else if (id == R.id.item_salir_cliente) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

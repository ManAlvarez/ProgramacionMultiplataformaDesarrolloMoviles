package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private String idKey, nombre, apellidos;
    private Intent intent;

    /**
     * Método para crea la vista.
     *
     * @param savedInstanceState
     */

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        TextView tvNombre = (TextView) findViewById(R.id.tv_nombre_apellidos_usuario_admin);
        idKey = getIntent().getStringExtra("idKey");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        tvNombre.setText(nombre + " " + apellidos);
    }

    /**
     * Método que interacciona con una activiadad y le pasa unos datos.
     *
     * @param view
     */

    public void verPedidosTramite(View view) {
        intent = new Intent(this, PedidosTramiteAdministradorActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        startActivity(intent);
    }

    /**
     * Método que interacciona con una activiadad y le pasa unos datos.
     *
     * @param view
     */

    public void verPedidosAceptados(View view) {
        intent = new Intent(this, PedidosAceptadosActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        startActivity(intent);
    }

    /**
     * Método que interacciona con una activiadad y le pasa unos datos.
     *
     * @param view
     */

    public void verPedidosRechazados(View view) {
        intent = new Intent(this, PedidosRechazadosActivity.class);
        intent.putExtra("idKey", idKey);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        startActivity(intent);
    }

    /**
     * Método que interacciona con una activiadad.
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
        getMenuInflater().inflate(R.menu.barra_acciones_disponibles_admin, menu);
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
        if (id == R.id.item_ver_pedidos_tramite_admin) {
            intent = new Intent(this, PedidosTramiteAdministradorActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        } else if (id == R.id.item_ver_pedidos_aceptados) {
            intent = new Intent(this, PedidosAceptadosActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        } else if (id == R.id.item_ver_pedidos_rechazados) {
            intent = new Intent(this, PedidosRechazadosActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        } else if (id == R.id.item_salir_admin) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

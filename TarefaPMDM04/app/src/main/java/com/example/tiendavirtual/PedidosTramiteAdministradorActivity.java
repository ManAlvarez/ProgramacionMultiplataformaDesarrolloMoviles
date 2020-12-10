package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class PedidosTramiteAdministradorActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(this, "tienda", null, 1);
    private ArrayList<PedidosObject> listaPedidos = new ArrayList<>();
    private String idKey, nombre, apellidos;

    /**
     * Método para crear la vista.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_tramite_administrador);

        idKey = getIntent().getStringExtra("idKey");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        consultarListaPedidosAdmin();
        AdaptadorRecyclerViewAdmin adaptadorRecyclerViewAdmin = new AdaptadorRecyclerViewAdmin(listaPedidos);
        recyclerView.setAdapter(adaptadorRecyclerViewAdmin);
    }

    /**
     * Método para consultar los pedidos.
     */

    public void consultarListaPedidosAdmin() {
        PedidosObject pedido = null;
        BD.abrir();
        Cursor cursor = BD.consultaPedidosTramite();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            pedido = new PedidosObject();
            pedido.setIdPedido(cursor.getInt(0));
            pedido.setCategoria(cursor.getString(1));
            pedido.setProducto(cursor.getString(2));
            pedido.setCantidad(cursor.getInt(3));
            pedido.setDireccion(cursor.getString(4));
            pedido.setCiudad(cursor.getString(5));
            pedido.setCp(cursor.getInt(6));
            pedido.setEstado(cursor.getString(7));
            pedido.setIdUsuario(cursor.getInt(8));
            listaPedidos.add(pedido);
            cursor.moveToNext();
        }
        BD.close();
    }

    /**
     * Método para crea un menú con acciones.
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barra_acciones_atras, menu);
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
        if (id == R.id.item_atras) {
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}

package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HacerPedidoActivity extends AppCompatActivity {

    private String idKey, nombre, apellidos, categoria, producto, cantidad;
    private Spinner spCategoria, spProducto, spCantidad;
    private Intent intent;
    private ArrayAdapter<String> arrayAdapterProdInf;
    private ArrayAdapter<String> arrayAdapterProdEle;
    private ArrayAdapter<String> arrayAdapterProMov;

    /**
     * Método para crear la vista.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pedido);

        idKey = getIntent().getStringExtra("idKey");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");

        spCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        spProducto = (Spinner) findViewById(R.id.spinnerProducto);
        spCantidad = (Spinner) findViewById(R.id.spinnerCantidad);
        Button btn_siguiente = (Button) findViewById(R.id.btnSiguiente_hacerpedido);

        ArrayAdapter<String> arrayAdapterCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.categoria_spinner));
        arrayAdapterProdInf = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.productos_informaticos_spinner));
        arrayAdapterProdEle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.productos_electronicos_spinner));
        arrayAdapterProMov = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.productos_moviles_spinner));
        ArrayAdapter<String> arrayAdapterCant = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.cantidad_spinner));

        spCategoria.setAdapter(arrayAdapterCat);
        spCantidad.setAdapter(arrayAdapterCant);

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spProducto.setAdapter(arrayAdapterProdInf);
                        break;
                    case 1:
                        spProducto.setAdapter(arrayAdapterProdEle);
                        break;
                    case 2:
                        spProducto.setAdapter(arrayAdapterProMov);
                        break;
                    default:
                        spProducto.setAdapter(null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoria = spCategoria.getSelectedItem().toString();
                producto = spProducto.getSelectedItem().toString();
                cantidad = spCantidad.getSelectedItem().toString();
                intent = new Intent(getApplicationContext(), DatosPedidoActivity.class);
                intent.putExtra("idKey", idKey);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellidos", apellidos);
                intent.putExtra("categoria", categoria);
                intent.putExtra("producto", producto);
                intent.putExtra("cantidad", cantidad);
                startActivity(intent);
            }
        });
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
            intent = new Intent(getApplicationContext(), ClienteActivity.class);
            intent.putExtra("idKey", idKey);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

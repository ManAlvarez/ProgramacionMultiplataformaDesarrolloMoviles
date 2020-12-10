package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class HacerPedidoActivity extends AppCompatActivity {

    private static List<String> listInformatica, listElectronica, listMoviles;
    private static ArrayAdapter<String> informatica, electronica, moviles;
    private static Spinner categorias, articulos, cantidad;
    private static Intent direccionPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pedido);

        listInformatica = new ArrayList<String>();
        listInformatica.add("Pc Sobremesa");
        listInformatica.add("Portátil");
        listInformatica.add("Monitor");
        informatica = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listInformatica);
        informatica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listElectronica = new ArrayList<String>();
        listElectronica.add("Televisión");
        listElectronica.add("DVD");
        electronica = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listElectronica);
        electronica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listMoviles = new ArrayList<String>();
        listMoviles.add("Pixel");
        listMoviles.add("Galaxy");
        listMoviles.add("Iphone");
        listMoviles.add("Xiaomi");
        moviles = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMoviles);
        moviles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        this.categorias = (Spinner)findViewById(R.id.spinnerCategoria);
        this.cantidad = (Spinner)findViewById(R.id.spinnerCantidad);

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                articulos = (Spinner)findViewById(R.id.spinnerArticulo);

                switch (i){
                    case 0:
                        articulos.setAdapter(informatica);
                        break;
                    case 1:
                        articulos.setAdapter(electronica);
                        break;
                    case 2:
                        articulos.setAdapter(moviles);
                        break;
                    default:
                        articulos.setAdapter(null);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    public void realizarPedido(View view){
        direccionPedido = new Intent(HacerPedidoActivity.this, DireccionPedidoActivity.class);
        direccionPedido.putExtra("Categoria", categorias.getSelectedItem().toString());
        direccionPedido.putExtra("Articulo", articulos.getSelectedItem().toString());
        direccionPedido.putExtra("Cantidad", cantidad.getSelectedItem().toString());
        startActivity(direccionPedido);
    }

}

package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DireccionPedidoActivity extends AppCompatActivity {

    private static EditText editTextDireccion, editTextCiudad, editTextCp;
    private static String categoria, articulo, cantidad, direccion, ciudad, cp, pedido;
    private static Bundle bundle;
    private static Toast toast;
    private static Context context;
    private static Intent intentCliente;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion_pedido);
    }

    public void finalizarPedido(View view){

        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextCiudad = findViewById(R.id.editTextCiudad);
        editTextCp = findViewById(R.id.editTextCP);

        bundle = getIntent().getExtras();
        categoria = bundle.getString("Categoria");
        articulo = bundle.getString("Articulo");
        cantidad = bundle.getString("Cantidad");

        direccion = editTextDireccion.getText().toString();
        ciudad = editTextCiudad.getText().toString();
        cp = editTextCp.getText().toString();

        pedido = "Pedido: " + categoria + "\nArticulo: " + articulo + "\nCantidad: " + cantidad +
                 "\nDirección: " + direccion + "\nCP: " + cp + "\nCiudad: " + ciudad;

        Toast.makeText(DireccionPedidoActivity.this, pedido, Toast.LENGTH_LONG).show();
        intentCliente = new Intent(DireccionPedidoActivity.this, ClienteActivity.class);
        startActivity(intentCliente);
    }
}

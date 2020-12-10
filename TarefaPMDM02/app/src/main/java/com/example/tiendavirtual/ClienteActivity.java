package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClienteActivity extends AppCompatActivity {

    private static Intent HacerPedido, VerPedidos, VerCompras, login;
    private static TextView textViewNombre;
    private static String Nombre;
    private static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        bundle = getIntent().getExtras();
        Nombre = bundle.getString("Nombre");
        textViewNombre.setText(Nombre);
    }

    public void HacerPedido(View view) {
        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        HacerPedido = new Intent(ClienteActivity.this, HacerPedidoActivity.class);
        startActivity(HacerPedido);
    }
    public void VerPedidos(View view) {
        VerPedidos = new Intent(ClienteActivity.this, VerPedidoActivity.class);
        startActivity(VerPedidos);
    }
    public void VerCompras(View view) {
        VerCompras = new Intent(ClienteActivity.this, VerComprasActivity.class);
        startActivity(VerCompras);
    }
    public void Salir(View view){
        super.onBackPressed();
        login = new Intent(ClienteActivity.this, MainActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
    }
}

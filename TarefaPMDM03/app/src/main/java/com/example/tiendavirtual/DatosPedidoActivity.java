package com.example.tiendavirtual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatosPedidoActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(this, "tienda", null, 1);
    private String idKey, nombre, apellidos, categoria, producto, cantidad, direccion, ciudad, cp, estado;
    private EditText etDireccion, etCiudad, etCP;
    private Intent intent;
    private int cantidadInt, idKeyInt, cpInt;

    /**
     * Método para crear la vista.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_pedido);

        etDireccion = (EditText) findViewById(R.id.et_direccion);
        etCiudad = (EditText) findViewById(R.id.et_ciudad);
        etCP = (EditText) findViewById(R.id.et_cp);

        idKey = getIntent().getStringExtra("idKey");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        categoria = getIntent().getStringExtra("categoria");
        producto = getIntent().getStringExtra("producto");
        cantidad = getIntent().getStringExtra("cantidad");
    }

    /**
     * Método para llamar al metodo mostrarDialogosPersonalizados().
     *
     * @param view
     */

    public void finalizar(View view) {

        direccion = etDireccion.getText().toString();
        ciudad = etCiudad.getText().toString();
        cp = etCP.getText().toString();
        if (!direccion.isEmpty() && !ciudad.isEmpty() && !cp.isEmpty()) {
            mostrarDialogoPersonalizado();
        } else {
            Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para mostrar un diálogo personalizado.
     */

    @SuppressLint({"SetTextI18n", "InflateParams"})
    private void mostrarDialogoPersonalizado() {

        // Usa la clase Builder para la construcción del diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Obtengo el inflador del latout
        LayoutInflater inflater = getLayoutInflater();

        //Creo la vista del layout
        View view = inflater.inflate(R.layout.dialog_personalizado, null);

        //Se lo doy al constructor
        builder.setView(view);

        //Construyo el diálogo
        AlertDialog dialog = builder.create();

        //Muestro el diálogo
        dialog.show();

        TextView tvDialog = view.findViewById(R.id.tv_dialogo);
        Button btnAceptar = view.findViewById(R.id.btn_aceptar_dialog);
        Button btnRechazar = view.findViewById(R.id.btn_rechazar_dialog);

        tvDialog.setText("Pedido: \nCategoria: " + categoria + "\nProducto :" + producto + "\nCantidad: " + cantidad + "\nDirección: " + direccion + "\nCiudad: " + ciudad + "\nCódigo postal: " + cp);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cantidadInt = Integer.parseInt(cantidad);
                idKeyInt = Integer.parseInt(idKey);
                cpInt = Integer.parseInt(cp);
                estado = "en_tramite";
                BD.abrir();
                BD.insertarPedido(categoria, producto, cantidadInt, direccion, ciudad, cpInt, estado, idKeyInt);
                Toast.makeText(getApplicationContext(), "Pedido en tramite", Toast.LENGTH_SHORT).show();
                BD.close();
                intent = new Intent(getApplicationContext(), ClienteActivity.class);
                intent.putExtra("idKey", idKey);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellidos", apellidos);
                startActivity(intent);
            }
        });

        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ClienteActivity.class);
                intent.putExtra("idKey", idKey);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellidos", apellidos);
                startActivity(intent);
            }
        });

    }
}

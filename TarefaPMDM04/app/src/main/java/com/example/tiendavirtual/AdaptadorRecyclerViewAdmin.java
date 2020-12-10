package com.example.tiendavirtual;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Se encarga de realimentar el item list pedidos.
 */

public class AdaptadorRecyclerViewAdmin extends RecyclerView.Adapter<AdaptadorRecyclerViewAdmin.ViewHolderClienter> {

    private ArrayList<PedidosObject> listaPedidos;

    /**
     * Constructor de la clase.
     *
     * @param listaPedidos
     */

    public AdaptadorRecyclerViewAdmin(ArrayList<PedidosObject> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    /**
     * Proporcione una referencia a las vistas para cada elemento de datos.
     */

    public static class ViewHolderClienter extends RecyclerView.ViewHolder {

        TextView tvItemAdmin;
        Button btnAceptar;
        Button btnRechazar;

        public ViewHolderClienter(@NonNull View itemView) {
            super(itemView);
            tvItemAdmin = (TextView) itemView.findViewById(R.id.tv_item_pedido_admin);
            btnAceptar = (Button) itemView.findViewById(R.id.btn_pedido_aceptar);
            btnRechazar = (Button) itemView.findViewById(R.id.btn_pedido_rechazar);
        }
    }

    /**
     * Método para crear nuevas vistas (invocadas por el administrador de diseño(LayoutManager)).
     *
     * @param parent
     * @param viewType
     * @return
     */

    @NonNull
    @Override
    public ViewHolderClienter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Crea una nueva vista.
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_perdidos_tramite_admin, null);
        return new ViewHolderClienter(view);
    }

    /**
     * Método para reemplazar el contenido de una vista (invocada por el administrador de diseño(LayoutManager)).
     *
     * @param holder
     * @param position
     */

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolderClienter holder, final int position) {

        //Obtiene el elemento de su conjunto de datos en esta posición y reemplaza el contenido de la vista con ese elemento.
        holder.tvItemAdmin.setText(
                "ID PEDIDO: " + listaPedidos.get(position).getIdPedido() +
                        "\nCATEGORIA: " + listaPedidos.get(position).getCategoria() +
                        "\nPRODUCTO: " + listaPedidos.get(position).getProducto() +
                        "\nCANTIDAD: " + listaPedidos.get(position).getCantidad() +
                        "\nDIRECCION: " + listaPedidos.get(position).getDireccion() +
                        "\nCIUDAD: " + listaPedidos.get(position).getCiudad() +
                        "\nCP: " + listaPedidos.get(position).getCp() +
                        "\nUSUARIO: " + listaPedidos.get(position).getIdUsuario());

        holder.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(v.getContext(), "tienda", null, 1);
                BD.abrir();
                BD.updatearPedidoAceptado(listaPedidos.get(position).getIdPedido());
                BD.close();
                holder.btnAceptar.setVisibility(View.INVISIBLE);
                holder.btnRechazar.setVisibility(View.VISIBLE);
            }
        });

        holder.btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper BD = new AdminSQLiteOpenHelper(v.getContext(), "tienda", null, 1);
                BD.abrir();
                BD.updatearPedidoRechazado(listaPedidos.get(position).getIdPedido());
                BD.close();
                holder.btnRechazar.setVisibility(View.INVISIBLE);
                holder.btnAceptar.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Método que devuelve el tamaño de su conjunto de datos (invocado por el administrador de diseño(LayoutManager))
     *
     * @return
     */

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }
}

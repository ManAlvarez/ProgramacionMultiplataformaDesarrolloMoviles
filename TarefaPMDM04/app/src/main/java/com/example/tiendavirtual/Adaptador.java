package com.example.tiendavirtual;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Se encarga de realimentar el item list pedidos.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private ArrayList<PedidosObject> listaPedidos;

    /**
     * Costructor de la clase.
     *
     * @param listaPedidos
     */

    public Adaptador(ArrayList<PedidosObject> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    /**
     * Proporcione una referencia a las vistas para cada elemento de datos.
     */

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemPedidos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemPedidos = (TextView) itemView.findViewById(R.id.tv_item_pedido);
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Crea una nueva vista.
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pedidos_tramite, null);
        return new ViewHolder(view);
    }

    /**
     * Método para reemplazar el contenido de una vista (invocada por el administrador de diseño(LayoutManager)).
     *
     * @param holder
     * @param position
     */

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Obtiene el elemento de su conjunto de datos en esta posición y reemplaza el contenido de la vista con ese elemento.
        holder.tvItemPedidos.setText(
                "\nID USUARIO: " + listaPedidos.get(position).getIdUsuario() +
                        "\nID PEDIDO: " + listaPedidos.get(position).getIdPedido() +
                        "\nCATEGORIA: " + listaPedidos.get(position).getCategoria() +
                        "\nPRODUCTO: " + listaPedidos.get(position).getProducto() +
                        "\nCANTIDAD: " + listaPedidos.get(position).getCantidad() +
                        "\nDIRECCION: " + listaPedidos.get(position).getDireccion() +
                        "\nCIUDAD: " + listaPedidos.get(position).getCiudad() +
                        "\nCP: " + listaPedidos.get(position).getCp() +
                        "\nESTADO: " + listaPedidos.get(position).getEstado() +
                        "\n---------------------------------------------------------------");
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

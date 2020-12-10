package com.example.tiendavirtual;

import java.io.Serializable;

class PedidosObject implements Serializable {

    private int idPedido, idUsuario, cantidad , cp;
    private String categoria, producto, direccion, ciudad, estado;

    /**
     * Constructor de la clase;
     */

    PedidosObject() {
    }

    /**
     *
     * @return idPedido
     */

    int getIdPedido() {
        return idPedido;
    }

    /**
     *
     * @param idPedido
     */

    void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     *
     * @return idUsuario
     */

    int getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     */

    void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @return cantidad
     */

    int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param cantidad
     */

    void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     *
     * @return cp
     */

    int getCp() {
        return cp;
    }

    /**
     *
     * @param cp
     */

    void setCp(int cp) {
        this.cp = cp;
    }

    /**
     *
     * @return categoria
     */

    String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     */

    void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return producto
     */

    String getProducto() {
        return producto;
    }

    /**
     *
     * @param producto
     */

    void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     *
     * @return direccion
     */

    String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */

    void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return ciudad
     */

    String getCiudad() {
        return ciudad;
    }

    /**
     *
     * @param ciudad
     */

    void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @return estado
     */

    String getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */

    void setEstado(String estado) {
        this.estado = estado;
    }

}

package com.example.tiendavirtual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * Método para conectarnos con la base de datos.
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Método que crea la base de datos.
     *
     * @param BaseDeDatos
     */

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {

        BaseDeDatos.execSQL("create table usuarios(" +
                "id_usuario integer primary key autoincrement, " +
                "nombre text not null, " +
                "apellidos text not null, " +
                "email text not null, " +
                "usuario text not null, " +
                "contrasenha text not null, " +
                "tipo text not null)");

        BaseDeDatos.execSQL("create table pedidos(" +
                "id_pedido integer primary key autoincrement," +
                "categoria text not null, " +
                "producto text not null, " +
                "cantidad int not null, " +
                "direccion text not null, " +
                "ciudad text not null, " +
                "cp int not null, " +
                "estado text not null, " +
                "id_usuario integer," +
                "foreign key (id_usuario) references usuarios(id_usuario))");
    }

    /**
     * Método que elimina las tablas si hay una nueva versión de la base de datos.
     *
     * @param BaseDeDatos
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int oldVersion, int newVersion) {
        BaseDeDatos.execSQL("drop table if exists usuarios");
        BaseDeDatos.execSQL("drop table if exists pedidos");
    }

    /**
     * Método para permitir la escritura en la base de datos.
     */

    public void abrir() {
        this.getWritableDatabase();
    }

    /**
     * Método para cerrar la conexión con la base de datos.
     */

    public void cerrar() {
        this.close();
    }

    /**
     * Método para insertar un usuario en la base de datos.
     *
     * @param nombre
     * @param apellidos
     * @param email
     * @param usuario
     * @param contrasenha
     * @param tipo
     */

    public void insertarRegistro(String nombre, String apellidos, String email, String usuario, String contrasenha, String tipo) {
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("apellidos", apellidos);
        registro.put("email", email);
        registro.put("usuario", usuario);
        registro.put("contrasenha", contrasenha);
        registro.put("tipo", tipo);
        this.getWritableDatabase().insert("usuarios", null, registro);
    }

    /**
     * Método para consultar si el usuario está en la base de datos.
     *
     * @param user
     * @return
     */

    public Cursor consultaUsuario(String user) {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("usuarios",
                new String[]{"id_usuario", "nombre", "apellidos", "email", "usuario", "contrasenha", "tipo"},
                "usuario like '" + user + "'", null, null, null, null);
        return cursor;
    }

    /**
     * Método para consultar si el usuario y la contraseña coinciden.
     *
     * @param user
     * @param pass
     * @return
     */

    public Cursor consultarUsuarioYContrasenha(String user, String pass) {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("usuarios",
                new String[]{"id_usuario", "nombre", "apellidos", "email", "usuario", "contrasenha", "tipo"},
                "usuario like '" + user + "' and contrasenha like '" + pass + "'", null, null, null, null);
        return cursor;
    }

    /**
     * Método para insertar pedidos en la base de datos.
     *
     * @param categoria
     * @param producto
     * @param cantidad
     * @param direccion
     * @param ciudad
     * @param cp
     * @param estado
     * @param id_usuario
     */

    public void insertarPedido(String categoria, String producto, int cantidad, String direccion, String ciudad, int cp, String estado, int id_usuario) {
        ContentValues values = new ContentValues();
        values.put("categoria", categoria);
        values.put("producto", producto);
        values.put("cantidad", cantidad);
        values.put("direccion", direccion);
        values.put("ciudad", ciudad);
        values.put("cp", cp);
        values.put("estado", estado);
        values.put("id_usuario", id_usuario);
        this.getWritableDatabase().insert("pedidos", null, values);
    }

    /**
     * Método para consultar los pedidos en tramite de un determinado cliente.
     *
     * @param idUsuario
     * @return
     */

    public Cursor consultaPedidosClienteTramite(int idUsuario) {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("pedidos",
                new String[]{"id_pedido", "categoria", "producto", "cantidad", "direccion", "ciudad", "cp", "estado", "id_usuario"},
                "id_usuario = " + idUsuario + " and estado like 'en_tramite'", null, null, null, null);
        return cursor;
    }

    /**
     * Método para consultar todos los pedidos en tramite.
     *
     * @return
     */

    public Cursor consultaPedidosTramite() {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("pedidos",
                new String[]{"id_pedido", "categoria", "producto", "cantidad", "direccion", "ciudad", "cp", "estado", "id_usuario"},
                "estado like 'en_tramite'", null, null, null, null, null);
        return cursor;
    }

    /**
     * Método para actualizar un pedido determinada a aceptado.
     *
     * @param idPedido
     */

    public void updatearPedidoAceptado(int idPedido) {
        int pedido = idPedido;
        ContentValues values = new ContentValues();
        values.put("estado", "aceptado");
        this.getWritableDatabase().update("pedidos", values, "id_pedido = " + idPedido, null);
    }

    /**
     * Método para actualizar un pedido determinado a rechazado.
     *
     * @param idPedido
     */

    public void updatearPedidoRechazado(int idPedido) {
        int pedido = idPedido;
        ContentValues values = new ContentValues();
        values.put("estado", "rechazado");
        this.getWritableDatabase().update("pedidos", values, "id_pedido = " + idPedido, null);
    }

    /**
     * Método para consultar los pedidos aceptados de un determinado cliente.
     *
     * @param idUsuario
     * @return
     */

    public Cursor consultarComprasRealizadasCliente(int idUsuario) {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query(
                "pedidos",
                new String[]{"id_pedido", "categoria", "producto", "cantidad", "direccion", "ciudad", "cp", "estado", "id_usuario"},
                "id_usuario =" + idUsuario + " and estado like 'aceptado'", null, null, null, null);
        return cursor;
    }

    /**
     * Método para consuletar los pedidos rechazados de un determinado cliente.
     *
     * @return
     */

    public Cursor consultarPedidosAceptados() {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("pedidos",
                new String[]{"id_pedido", "categoria", "producto", "cantidad", "direccion", "ciudad", "cp", "estado", "id_usuario"},
                "estado like 'aceptado'", null, null, null, null, null);
        return cursor;
    }

    /**
     * Método para consultar los pedidos rechazados.
     *
     * @return
     */

    public Cursor consultarPedidosRechazados() {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("pedidos",
                new String[]{"id_pedido", "categoria", "producto", "cantidad", "direccion", "ciudad", "cp", "estado", "id_usuario"},
                "estado like 'rechazado'", null, null, null, null, null);
        return cursor;
    }
}
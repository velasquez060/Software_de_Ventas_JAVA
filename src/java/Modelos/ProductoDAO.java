/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AdminSena
 */
public class ProductoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Producto ConsultaPorCodigo(int codigoProducto) {
        Producto producto = new Producto();
        con = cn.Conexion();
        String consulta = "SELECT * FROM productos WHERE id = " + codigoProducto;

        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while(rs.next()){
                producto.setCodigo(rs.getInt("id"));
                producto.setNombreProducto(rs.getString("nombreproducto"));
                producto.setDescripcion(rs.getString("descripcionproducto"));
                producto.setUnidad(rs.getString("unidad"));
                producto.setPrecio(rs.getString("precio"));
                               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    public int Agregar(Producto producto) {
        int r = 0;
        con = cn.Conexion();
        String sentencia = "INSERT INTO productos (nombreproducto, descripcionproducto, unidad, precio) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sentencia);
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getUnidad());
            ps.setString(4, producto.getPrecio());
            ps.executeUpdate();

        } catch (Exception e) {

        }

        return r;

    }

    public List Listar() {
        String consulta = "SELECT * FROM productos";
        List<Producto> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt("id"));
                producto.setNombreProducto(rs.getString("nombreproducto"));
                producto.setDescripcion(rs.getString("descripcionproducto"));
                producto.setUnidad(rs.getString("unidad"));
                producto.setPrecio(rs.getString("precio"));
                lista.add(producto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }
    
    
    
    

}

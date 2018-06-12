package Services;

import Classes.Comment;
import Classes.Product;
import Classes.Tag;
import Classes.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class productServices {

    public List<Product> ProductList() {
        List<Product> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from products";
            con = DatabaseService.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setBody(rs.getString("body"));
                product.setAuthor(rs.getObject("author", User.class));
                product.setDateTime(rs.getDate("date"));
                product.setComments((ArrayList<Comment>) rs.getArray("comments"));
                product.setTags((ArrayList<Tag>) rs.getArray("tags"));

                list.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    /**
     *
     * @param id
     * @return
     */

    public Product getProduct(int id) {
        Product product = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from products where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setBody(rs.getString("body"));
                product.setAuthor(rs.getObject("author", User.class));
                product.setDateTime(rs.getDate("date"));
                product.setComments((ArrayList<Comment>) rs.getArray("comments"));
                product.setTags((ArrayList<Tag>) rs.getArray("tags"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return product;
    }

    public boolean CreateProduct(Product product){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into products(title, body, author, datep) values(?,?,?,?)";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            //prepareStatement.setLong(1, product.getId());
            prepareStatement.setString(1, product.getTitle());
            prepareStatement.setString(2, product.getBody());
            prepareStatement.setObject(3, product.getAuthor());
            prepareStatement.setDate(4, (Date) product.getDateTime());
            //prepareStatement.setDate(4, (Date) product.getDateTime());
            //prepareStatement.setArray(6, (Array) product.getComments());
            //prepareStatement.setArray(7, (Array) product.getTags());

            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public boolean UpdateProduct(Product product){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "update products set id=?, title=?, body=?, author=?, datep=?, comments=?, tags=? where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.


            prepareStatement.setString(1, product.getTitle());
            prepareStatement.setString(2, product.getBody());
            prepareStatement.setObject(3, product.getAuthor());
            prepareStatement.setDate(4, (Date) product.getDateTime());
            prepareStatement.setArray(5, (Array) product.getComments());
            prepareStatement.setArray(6, (Array) product.getTags());

            //Indica el where...
            prepareStatement.setLong(7, product.getId());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public boolean DeleteProduct(int id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from products where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setInt(1, id);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }
    
}

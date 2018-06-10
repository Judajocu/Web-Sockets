package Services;

import Classes.Comment;
import Classes.Product;
import Classes.Tag;
import Classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentServices {

    public List<Comment> CommentList() {
        List<Comment> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from comments";
            con = DatabaseService.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment(rs.getString("comment"));
                comment.setAuthor(rs.getObject("author", User.class));
                comment.setProduct(rs.getObject("product", Product.class));

                list.add(comment);
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

    public Comment getComment(int id) {
        Comment comment = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from commets where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment(rs.getString("comment"));
                comment.setAuthor(rs.getObject("author", User.class));
                comment.setProduct(rs.getObject("product", Product.class));

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

        return comment;
    }

    public boolean CreateComment(Comment comment){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into comments(id, comment, author, product) values(?,?,?,?)";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, comment.getId());
            prepareStatement.setString(2, comment.getComment());
            prepareStatement.setObject(3, comment.getAuthor());
            prepareStatement.setObject(4, comment.getProduct());

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

    public boolean UpdateComment(Comment comment){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "update comments set id=?, comment=?, author=?, product=?, where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.


            prepareStatement.setLong(1, comment.getId());
            prepareStatement.setString(2, comment.getComment());
            prepareStatement.setObject(3, comment.getAuthor());
            prepareStatement.setObject(4, comment.getProduct());

            //Indica el where...
            prepareStatement.setLong(5, comment.getId());
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

    public boolean DeleteComment(int id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from comments where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setInt(1, id);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

}

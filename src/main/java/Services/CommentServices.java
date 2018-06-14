package Services;

import Classes.Comment;
import Classes.Product;
import Classes.User;
import Services.CommentServices;
import Services.TagServices;
import Services.UserServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommentServices {

    public List<Comment> CommentList() {
        List<Comment> list = new ArrayList<>();
        productServices pro=new productServices();
        UserServices us=new UserServices();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from comments order by id desc";
            con = DatabaseService.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getLong("id"));
                comment.setComment(rs.getString("comment"));
                comment.setAuthor(us.getUser(rs.getString("username")));
                comment.setProduct(pro.getProduct(rs.getLong("product")));

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

    public Comment getComment(long id) {
        Comment comment = null;
        Connection con = null;
        productServices pro=new productServices();
        UserServices us=new UserServices();
        try {
            //utilizando los comodines (?)...
            String query = "select * from comments where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                comment = new Comment();
                comment.setId(rs.getLong("id"));
                comment.setComment(rs.getString("comment"));
                comment.setAuthor(us.getUser(rs.getString("username")));
                comment.setProduct(pro.getProduct(rs.getLong("product")));

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

            String query = "insert into comments(comment, username, product) values(?,?,?)";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, comment.getComment());
            prepareStatement.setString(2, comment.getAuthor().getUsername());
            prepareStatement.setLong(3, comment.getProduct().getId());

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

            String query = "update comments set comment=? where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.

            prepareStatement.setString(1, comment.getComment());
            //prepareStatement.setObject(2, comment.getAuthor());
            //prepareStatement.setObject(3, comment.getProduct());

            //Indica el where...
            prepareStatement.setLong(2, comment.getId());
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

    public boolean DeleteComment(long id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from comments where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setLong(1, id);
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

    public boolean DeleteComment2(long id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from comments where product = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setLong(1, id);
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

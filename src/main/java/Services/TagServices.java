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

public class TagServices {

    public List<Tag> TagList() {
        List<Tag> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from tags";
            con = DatabaseService.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Tag tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setTag(rs.getString("tag"));

                list.add(tag);
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

    public Tag getTag(int id) {
        Tag tag = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from tags where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setTag(rs.getString("tag"));

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

        return tag;
    }

    public boolean CreateTag(Tag tag){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into tags(tag) values(?)";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            //prepareStatement.setLong(1, tag.getId());
            prepareStatement.setString(1, tag.getTag());

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

    public boolean UpdateTag(Tag tag){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "update tags set id=?, tag=?, where id = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.


            prepareStatement.setLong(1, tag.getId());
            prepareStatement.setString(2, tag.getTag());

            //Indica el where...
            prepareStatement.setLong(3, tag.getId());
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

    public boolean DeleteTag(int id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from tags where id = ?";
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

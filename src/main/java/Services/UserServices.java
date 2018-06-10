package Services;

import Classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServices {

    public List<User> UserList() {
        List<User> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {

            String query = "select * from users";
            con = DatabaseService.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setNombre(rs.getString("nombre"));
                user.setPassword(rs.getString("password"));
                user.setAdministrator(rs.getBoolean("administrator"));
                user.setAuthor(rs.getBoolean("author"));

                list.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    /**
     *
     * @param username
     * @return
     */

    public User getUser(String username) {
        User user = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from users where username = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, username);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setNombre(rs.getString("nombre"));
                user.setPassword(rs.getString("password"));
                user.setAdministrator(rs.getBoolean("administrator"));
                user.setAuthor(rs.getBoolean("author"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }

    public boolean CreateUser(User user){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "insert into users(username, nombre, password, administrator, author) values(?,?,?,?,?)";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, user.getUsername());
            prepareStatement.setString(2, user.getNombre());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.setBoolean(4, user.isAdministrator());
            prepareStatement.setBoolean(5, user.isAuthor());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public boolean UpdateUser(User user){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "update users set username=?, nombre=?, password=?, Administrator=?, author=? where username = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.

            prepareStatement.setString(1, user.getNombre());
            prepareStatement.setString(2, user.getPassword());
            prepareStatement.setBoolean(3, user.isAdministrator());
            prepareStatement.setBoolean(4, user.isAuthor());
            //Indica el where...
            prepareStatement.setString(5, user.getUsername());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public boolean DeleteUser(String username){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from users where username = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setString(1, username);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

}

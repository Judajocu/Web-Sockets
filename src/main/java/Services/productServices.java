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

import Services.CommentServices;
import Services.TagServices;
import Services.UserServices;

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
                product.setId(rs.getLong("id"));
                product.setTitle(rs.getString("title"));
                product.setBody(rs.getString("body"));
                //
                product.setAuthor(devolverUser(rs.getString("author")));
                product.setDateTime(rs.getDate("datep"));
                //product.setAuthor(rs.getObject("author", User.class));

                //product.setComments((ArrayList<Comment>) rs.getArray("comments"));
                //product.setTags((ArrayList<Tag>) rs.getArray("tags"));


                product.setTags(pTags(product.getId()));
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
                product.setAuthor(devolverUser(rs.getString("author")));
                product.setDateTime(rs.getDate("datep"));
                product.setTags(pTags(product.getId()));
                //product.setComments((ArrayList<Comment>) rs.getArray("comments"));
                //product.setTags((ArrayList<Tag>) rs.getArray("tags"));

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

    public boolean CreateProduct(Product product, String[] tags){
        boolean ok =false;

        Connection con = null;
        try {

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate hoy = LocalDate.now();
            String query = "insert into products(title, body, author, datep) values(?,?,?,'" + formato.format(hoy).toString()  + "')";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            //prepareStatement.setLong(1, product.getId());
            prepareStatement.setString(1, product.getTitle());
            prepareStatement.setString(2, product.getBody());
            prepareStatement.setString(3, product.getAuthor().getUsername());
            //prepareStatement.setDate(4, (Date) product.getDateTime());
            //prepareStatement.setDate(4, (Date) product.getDateTime());
            //prepareStatement.setArray(6, (Array) product.getComments());
            //prepareStatement.setArray(7, (Array) product.getTags());
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

            con.close();

            //trabajo con las etiquetas
            String query2 = "select * from products order by id desc LIMIT 1";
            con = DatabaseService.getInstancia().getConexion();
            PreparedStatement prepareStatement2 = con.prepareStatement(query2);
            ResultSet rs = prepareStatement2.executeQuery();
            long pid=0;
            while(rs.next()){
                pid=rs.getLong("id");
            }
            con.close();

            TagServices t=new TagServices();
            List<Tag> lista = t.TagList();

            for (int i = 0; i < tags.length; i++)
            {
                boolean noesta=false;
                for (Tag t2: lista)
                {
                    if(t2.getTag().equals(tags[i]))
                    {
                        //insertar en tabla etiqueta y producto
                        String query3 = "insert into TAGPRODUCTS(tag, product) values(?,?)";
                        //
                        con = DatabaseService.getInstancia().getConexion();
                        PreparedStatement prepareStatement3 = con.prepareStatement(query3);
                        //Antes de ejecutar seteo los parametros.
                        prepareStatement3.setLong(1, t2.getId());
                        prepareStatement3.setLong(2, pid);
                        int fila2 = prepareStatement3.executeUpdate();
                        ok = fila2 > 0 ;
                        con.close();
                        noesta=true;
                    }
                }
                if(!noesta)
                {
                    TagServices tass=new TagServices();
                    Tag insert=new Tag();
                    insert.setTag(tags[i]);
                    tass.CreateTag(insert);

                    String querys = "select * from tags order by id desc LIMIT 1";
                    con = DatabaseService.getInstancia().getConexion();
                    PreparedStatement prepareStatements = con.prepareStatement(querys);
                    ResultSet rs2 = prepareStatements.executeQuery();
                    Tag ya=null;
                    while(rs2.next()){
                        ya = new Tag();
                        ya.setId(rs2.getLong("id"));
                        ya.setTag(rs2.getString("tag"));
                    }
                    con.close();

                    String query4 = "insert into TAGPRODUCTS(tag, product) values(?,?)";
                    //
                    con = DatabaseService.getInstancia().getConexion();
                    PreparedStatement prepareStatement4 = con.prepareStatement(query4);
                    //Antes de ejecutar seteo los parametros.
                    prepareStatement4.setLong(1, ya.getId());
                    prepareStatement4.setLong(2, pid);
                    int fila2 = prepareStatement4.executeUpdate();
                    ok = fila2 > 0 ;
                    con.close();
                    //insertar en tabla etiqueta y producto
                    //insertar en tabla etiqueta
                }
            }

            /*Product esto=null;
            while(rs.next()){
                esto = new Product();
                esto.setId(rs.getInt("id"));
                esto.setTitle(rs.getString("title"));
                esto.setBody(rs.getString("body"));

                User us=null;
                String user = rs.getString("author");
                UserServices users=new UserServices();
                List<User> lista = users.UserList();
                for(User u: lista){
                    if(u.getUsername().equals(user)) {
                        us=u;
                    }
                }
                esto.setAuthor(us);
                esto.setDateTime(rs.getDate("date"));

            }*/

            //

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
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

    public User devolverUser(String name)
    {
        User us=null;
        String user = name;
        UserServices users=new UserServices();
        List<User> lista = users.UserList();
        for(User u: lista){
            if(u.getUsername().equals(user)) {
                us=u;
            }
        }

        return us;
    }

    public List<Tag> pTags(long n){
        List<Tag> et=new ArrayList<>();

        TagServices t=new TagServices();
        List<Tag> lista = t.TagList();

        Connection con = null; //objeto conexion.
        try {

            String query = "select * from TAGPRODUCTS where product = ?";
            con = DatabaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, n);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Tag tag = new Tag();
                Long idt= rs.getLong("tag");
                for(Tag g: lista){
                    if(g.getId()==idt){
                        tag=g;
                        et.add(tag);
                    }
                }
            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return et;
    }

    public void prueba(){
        boolean ok =false;

        Connection con = null;
        try {

            String query2 = "select * from products order by id desc LIMIT 1";
            con = DatabaseService.getInstancia().getConexion();
            PreparedStatement prepareStatement2 = con.prepareStatement(query2);
            ResultSet rs = prepareStatement2.executeQuery();
            long pid=0;
            while(rs.next()){
                pid=rs.getLong("id");
                System.out.println(pid);
            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(productServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

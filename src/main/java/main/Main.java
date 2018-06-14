package main;

import Services.*;
import freemarker.template.Configuration;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.Session;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.*;

import Classes.User;
import Classes.Comment;
import Classes.Tag;
import Classes.Product;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args)throws SQLException {

        //Iniciando el servicio
        BootstrapService.startDb();

        //Prueba de Conexión.
        DatabaseService.getInstancia().testConexion();

        BootstrapService.CreateTable();

        UserServices servicios_user= new UserServices();

        //Insertando administrador por defecto
        User insertar = new User();
        insertar.setUsername("Admin");
        insertar.setNombre("Carla");
        insertar.setPassword("123");
        insertar.setAuthor(true);
        insertar.setAdministrator(false);
        if(servicios_user.getUser(insertar.getUsername())==null){
            servicios_user.CreateUser(insertar);
        }


        /*UserServices prueb=new UserServices();
        User u=prueb.getUser("User");
        u.setAdministrator(false);
        prueb.UpdateUser(u);
        List<User> list = prueb.UserList();
        System.out.println("La cantidad de users: "+list.size());
        for(User est : list){
            System.out.println("user: "+est.getUsername()+", pass: "+ est.getPassword()+", name: "+ est.getNombre()+", author: "+ est.isAuthor()+", admin: "+ est.isAdministrator());
        }*/

        productServices prueba=new productServices();
        /*UserServices lala=new UserServices();
        Comment cc=new Comment();
        cc.setProduct(prueba.getProduct(5));
        cc.setComment("wow, este articulo es tan fantastico!!");
        cc.setAuthor(lala.getUser("User"));
        CommentServices ccc=new CommentServices();
        ccc.CreateComment(cc);*/

        CommentServices ccc=new CommentServices();
        List<Comment> cc=ccc.CommentList();
        System.out.println("La cantidad de comentarios: "+cc.size());
        for(Comment c: cc){
            System.out.println("articulo: "+c.getId()+", autor: "+c.getAuthor().getUsername()+" art: "+c.getProduct().getId()+", body: "+c.getComment());
        }
        prueba.prueba();
        List<Product> lista = prueba.ProductList();
        System.out.println("La cantidad de articulo: "+lista.size());
        for(Product est : lista) {
            System.out.println("id: " + est.getId() + " title: " + est.getTitle() + ",Autor: " + est.getAuthor().getUsername() + ",fecha: " + est.getDateTime() + ",# de c: " + est.getComments().size() + ",# de t: " + est.getTags().size());
        }

        //boolean p2 =prueba.DeleteProduct(3);
        int n=1;
        if(prueba.getProduct(n)!=null) {
            Product p2 = prueba.getProduct(n);
            p2.setComments(prueba.pComment(p2.getId()));
            for (Tag est2 : p2.getTags()) {
                System.out.println("producto:" + p2.getId() + ", tag id: " + est2.getId() + ", tag name: " + est2.getTag());
            }
            for (Comment est2 : p2.getComments()) {
                System.out.println("producto:" + p2.getId() + ", coment id: " + est2.getId() + ", coment name: " + est2.getComment()+ ", coment user: " + est2.getAuthor().getUsername());
            }


        }
        //System.out.println(prueba.getProduct(1).getBody());
        /*

        Date today = Calendar.getInstance().getTime();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);*/
        new Main().manejadorFremarker();

        //BootstrapService.stopDb();


    }

    public void manejadorFremarker()throws SQLException{

        //

        //staticFileLocation("/public");
        staticFiles.location("/public");

        ArrayList<User> UserList = new ArrayList<User>();
        ArrayList<Comment> CommentList = new ArrayList<Comment>();
        ArrayList<Tag> TagList = new ArrayList<Tag>();

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(
                Main.class,"/templates/");
        FreeMarkerEngine motor= new FreeMarkerEngine(configuration);

        get("/", (request, response) -> {
            User user= request.session(true).attribute("user");
            productServices manejo_p = new productServices();
            List<Product> articulos= manejo_p.ProductList();
            for(Product p: articulos){
                String up = p.getBody().substring(0, Math.min(p.getBody().length(), 70));
                //System.out.println("caracteres "+up);
                //System.out.println("caracteres 2 "+p.getBody());
                p.setBody(up+"...");
            }



            Map<String, Object> mapa = new HashMap<>();
            mapa.put("name","Bienvenidos");
            mapa.put("userl",user);
            mapa.put("art",articulos);
            return new ModelAndView(mapa, "inicio.ftl");
        }, motor);

        Spark.get("/login", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "login.ftl");
        }, motor);

        Spark.post("/login", (request, response) -> {


            String username =request.queryParams("username") != null ? request.queryParams("username") : "unknown";
            String pass =request.queryParams("username") != null ? request.queryParams("pass") : "unknown";

            User user = encontrarUser(username);
            //System.out.println("usuario "+ user.getUsername());
            if(user!=null){
                if(user.getPassword().equals(pass)) {

                    request.session(true);
                    request.session().attribute("user", user);
                    response.redirect("/");
                } else{
                    response.redirect("/");
                }

            }else{
                response.redirect("/");
            }


            return "";

        });

        Spark.get("/logout", (request, response) -> {

            Session actual = request.session(true);
            actual.invalidate();
            response.redirect("/");
            return "";
        });

        get("/userlist", (request, response) -> {
            User user= request.session(true).attribute("user");
            UserServices servicios_user= new UserServices();
            List<User> usuarios = servicios_user.UserList();


            Map<String, Object> mapa = new HashMap<>();
            mapa.put("lista",usuarios);
            mapa.put("userl",user);
            return new ModelAndView(mapa, "esto.ftl");
        }, motor);

        get("/product", (request, response) -> {
            User user= request.session(true).attribute("user");


            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            return new ModelAndView(mapa, "crearproduct.ftl");
        }, motor);

        post("/add", (request, response) -> {
            User user= request.session(true).attribute("user");

            String title =request.queryParams("title");
            String body =request.queryParams("body");
            String[] tags =request.queryParams("tag").split(",");
            Date today = Calendar.getInstance().getTime();

            productServices ps=new productServices();
            Product insertar = new Product();
            insertar.setAuthor(user);
            insertar.setTitle(title);
            insertar.setBody(body);
            insertar.setDateTime(today);
            ps.CreateProduct(insertar, tags);



            response.redirect("/");
            return "";
        });

        get("product/:id",(request, response) -> {
            User user= request.session(true).attribute("user");
            productServices pro=new productServices();
            ArrayList<Product> ProductList = new ArrayList<Product>();

            long productid = Long.parseLong(request.params("id"));
            Product p=pro.getProduct(productid);
            p.setComments(pro.pComment(p.getId()));
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("art",p);
            return new ModelAndView(mapa,"producto.ftl");
        },motor);

        post("/addcomment/:id", (request, response) -> {
            User user= request.session(true).attribute("user");

            productServices pro=new productServices();
            long productid = Long.parseLong(request.params("id"));
            Product p=pro.getProduct(productid);

            String body =request.queryParams("body");

            CommentServices ps=new CommentServices();
            Comment insertar = new Comment();
            insertar.setAuthor(user);
            insertar.setComment(body);
            insertar.setProduct(p);
            ps.CreateComment(insertar);


            String re ="/product/"+p.getId();
            response.redirect(re);
            return "";
        });

        get("/deleteuser/:username", (request, response) -> {

            String username = request.params("username");
            UserServices servicios_user= new UserServices();
            List<User> usuarios = servicios_user.UserList();

            usuarios.removeIf(User -> User.getUsername().equalsIgnoreCase(username));
            servicios_user.DeleteUser(username);
            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa,"deleteUser.ftl");

        },motor);

        get("/editaruserForm/:username", (request,response) -> {

            String username = request.params("username");
            UserServices servicios_user= new UserServices();
            List<User> usuarios = servicios_user.UserList();
            User user = new User();
            int index = 0;
            for (User usr: usuarios) {
                if(usr.getUsername().equalsIgnoreCase(username))
                {
                    index = usuarios.indexOf(usr);
                    user = usr;
                }
            }

            Map<String,Object> mapa = new HashMap<>();
            mapa.put("index",index);
            mapa.put("user",user);
            return new ModelAndView(mapa, "editUser.ftl");

        }, motor);

        Spark.post("/editaruser/:index/:user", (request, response) -> {
            StringWriter writer = new StringWriter();
            UserServices servicios_user= new UserServices();
            List<User> usuarios = servicios_user.UserList();
            int index = Integer.parseInt(request.params("index"));
            try {
                String Username = request.queryParams("username");
                String Nombre = request.queryParams("nombre");
                String Password = request.queryParams("password");
                String Author = request.queryParams("author");
                String Administrator = request.queryParams("administrator");
                boolean author=false;
                boolean administrator=false;
                if(Author==null)
                {
                    author=false;
                } else if(Author.equalsIgnoreCase("on"))
                {
                    author=true;
                }
                if(Administrator==null)
                {
                    administrator=false;
                } else if(Administrator.equalsIgnoreCase("on"))
                {
                    administrator=true;
                }
                for (User s: usuarios) {
                    if (usuarios.indexOf(s)==index)
                    {
                        s.setUsername(Username);
                        s.setNombre(Nombre);
                        s.setPassword(Password);
                        s.setAuthor(author);
                        s.setAdministrator(administrator);
                        servicios_user.UpdateUser(s);
                    }
                }
                response.redirect("/userlist/");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/editaruser/");
            }
            return writer;
        });

        get("/addUserForm/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "addUser.ftl");
        }, motor);

        Spark.post("/AddUser/", (request, response) -> {
            StringWriter writer = new StringWriter();
            UserServices servicios_user= new UserServices();
            try {
                String Username = request.queryParams("username");
                String Nombre = request.queryParams("nombre");
                String Password = request.queryParams("password");
                String Author = request.queryParams("author");
                String Administrator = request.queryParams("administrator");
                boolean author=false;
                boolean administrator=false;
                if(Author==null)
                {
                    author=false;
                } else if(Author.equalsIgnoreCase("on"))
                {
                    author=true;
                }
                if(Administrator==null)
                {
                    administrator=false;
                } else if(Administrator.equalsIgnoreCase("on"))
                {
                    administrator=true;
                }
                servicios_user.CreateUser(new User(Username,Nombre,Password,author,administrator));
                response.redirect("/userlist/");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/addUserForm/");
            }
            return writer;
        });

    }

    public static User encontrarUser(String username){
        UserServices usuario =new UserServices();
        User user = usuario.getUser(username);
        return user;
    }

}

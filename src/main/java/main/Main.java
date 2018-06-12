package main;

import freemarker.template.Configuration;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.Session;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import Classes.User;
import Classes.Comment;
import Classes.Tag;
import Classes.Product;
import Services.TagServices;
import Services.BootstrapService;
import Services.DatabaseService;
import Services.productServices;
import Services.UserServices;

public class Main {
    public static void main(String[] args)throws SQLException {
        //System.out.println("La cantidad de estudiantes: "+listaEstudiantes.size());
        // for(Estudiante est : listaEstudiantes){
        //    System.out.println("La matricula: "+est.getMatricula());
        // }

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


        TagServices prueb=new TagServices();
        List<Tag> list = prueb.TagList();
        System.out.println("La cantidad de articulo: "+list.size());
        for(Tag est : list){
            System.out.println("La matricula: "+est.getId()+" title: "+ est.getTag());
        }

        productServices prueba=new productServices();
        prueba.prueba();
        /*List<Product> lista = prueba.ProductList();
        System.out.println("La cantidad de articulo: "+lista.size());
        for(Product est : lista){
            System.out.println("La matricula: "+est.getId()+" title: "+ est.getTitle()+",Autor"+est.getAuthor().getUsername()+",fecha"+est.getDateTime());
        }*/

        //boolean p2 =prueba.DeleteProduct(3);
        int n=5;
        if(prueba.getProduct(n)!=null) {
            Product p = prueba.getProduct(n);
            for (Tag est : p.getTags()) {
                System.out.println("producto:" + p.getId() + ", tag id: " + est.getId() + ", tag name: " + est.getTag());
            }
        }
        //System.out.println(prueba.getProduct(1).getBody());
        /*Date today = Calendar.getInstance().getTime();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);*/
        new Main().manejadorFremarker();

        //BootstrapService.stopDb();


    }

    public void manejadorFremarker()throws SQLException{

        //

        staticFiles.location("/templates");

        ArrayList<Product> ProductList = new ArrayList<Product>();
        ArrayList<User> UserList = new ArrayList<User>();
        ArrayList<Comment> CommentList = new ArrayList<Comment>();
        ArrayList<Tag> TagList = new ArrayList<Tag>();

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(
                Main.class,"/templates/");
        FreeMarkerEngine motor= new FreeMarkerEngine(configuration);
        System.out.println("usuario");

        get("/", (request, response) -> {
            User user= request.session(true).attribute("user");
            productServices manejo_p = new productServices();
            List<Product> articulos= manejo_p.ProductList();


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

            System.out.println("usuario log");

            String username =request.queryParams("username") != null ? request.queryParams("username") : "unknown";
            String pass =request.queryParams("username") != null ? request.queryParams("pass") : "unknown";

            User user = encontrarUser(username);
            //System.out.println("usuario "+ user.getUsername());
            if(user!=null){
                System.out.println("usuario yes");
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

        get("product/:productid",(request, response) -> {
            int productid = Integer.parseInt(request.params("productid"));
            int index = 0;
            for (Product product: ProductList) {
                if (product.getId() == productid)
                {
                    index = ProductList.indexOf(product);
                }
            }
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("index",index);
            return new ModelAndView(mapa,"producto.ftl");
        },motor);

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
            int index = 0;
            for (User usr: usuarios) {
                if(usr.getUsername().equalsIgnoreCase(username))
                {
                    index = usuarios.indexOf(usr);
                }
            }

            Map<String,Object> mapa = new HashMap<>();
            mapa.put("index",index);
            return new ModelAndView(mapa, "editUser.ftl");

        }, motor);

        Spark.post("/editaruser/:index", (request, response) -> {
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
                if(Author.equalsIgnoreCase(null))
                {
                    author=false;
                } else if(Author.equalsIgnoreCase("on"))
                {
                    author=true;
                }
                if(Administrator.equalsIgnoreCase(null))
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

    }

    public static User encontrarUser(String username){
        UserServices usuario =new UserServices();
        User user = usuario.getUser(username);
        return user;
    }

}

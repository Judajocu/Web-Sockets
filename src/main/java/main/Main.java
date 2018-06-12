package main;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import Classes.User;
import Classes.Comment;
import Classes.Tag;
import Classes.Product;
import Services.CommentServices;
import Services.TagServices;
import Services.BootstrapService;
import Services.DatabaseService;
import Services.productServices;
import Services.UserServices;

import javax.jws.soap.SOAPBinding;

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
        List<Product> lista = prueba.ProductList();
        System.out.println("La cantidad de articulo: "+lista.size());
        for(Product est : lista){
            System.out.println("La matricula: "+est.getId()+" title: "+ est.getTitle()+",Autor"+est.getAuthor().getUsername()+",fecha"+est.getDateTime());
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate).toString());

        Date today = Calendar.getInstance().getTime();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
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


            Map<String, Object> mapa = new HashMap<>();
            mapa.put("name","Bienvenidos");
            mapa.put("userl",user);
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

            Map<String, Object> mapa = new HashMap<>();
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

        /*Spark.post("product/:productid",(request, response) -> {
            StringWriter writer = new StringWriter();
            int index = Integer.parseInt(request.params("index"));
            try {
                String Nombre = request.queryParams("nombre");
                String Apellido = request.queryParams("apellido");
                String Matricula = request.queryParams("matricula");
                String Telefono = request.queryParams("telefono");
                for (Product s: ProductList) {
                    if (ProductList.indexOf(s)==index)
                    {
                        s.setMatricula(Integer.parseInt(Matricula));
                        s.setNombre(Nombre);
                        s.setApellido(Apellido);
                        s.setTelefono(Telefono);
                    }
                }
                response.redirect("/Students/");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/ModifyStudentForm/");
            }
            return writer;
        });*/

    }

    public static User encontrarUser(String username){
        UserServices usuario =new UserServices();
        User user = usuario.getUser(username);
        return user;
    }

}

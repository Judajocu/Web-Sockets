package main;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        insertar.setAdministrator(true);
        if(servicios_user.getUser(insertar.getUsername())==null){
            servicios_user.CreateUser(insertar);
        }

        List<User> listaUsuarios = servicios_user.UserList();
        //System.out.println("La cantidad de estudiantes: "+listaEstudiantes.size());
        // for(Estudiante est : listaEstudiantes){
        //    System.out.println("La matricula: "+est.getMatricula());
        // }

        new Main().manejadorFremarker(listaUsuarios);

        BootstrapService.stopDb();
    }

    public void manejadorFremarker(List usuarios){

        staticFiles.location("/templates");

        ArrayList<Product> ProductList = new ArrayList<Product>();
        ArrayList<User> UserList = new ArrayList<User>();
        ArrayList<Comment> CommentList = new ArrayList<Comment>();
        ArrayList<Tag> TagList = new ArrayList<Tag>();

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(
                Main.class,"/templates/");
        FreeMarkerEngine motor= new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("name","Bienvenidos");
            mapa.put("lista",usuarios);
            return new ModelAndView(mapa, "inicio.ftl");
        }, motor);

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

}

package main;

import Classes.Comment;
import Classes.Product;
import Classes.Tag;
import Classes.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args){
        new Main().manejadorFremarker();
    }

    public void manejadorFremarker(){

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

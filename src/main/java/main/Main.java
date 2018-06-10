package main;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args){
        new Main().manejadorFremarker();
    }

    public void manejadorFremarker(){

        staticFiles.location("/templates");

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(
                Main.class,"/templates/");
        FreeMarkerEngine motor= new FreeMarkerEngine(configuration);

        get("/", (request, response) -> {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("name","Bienvenidos");
            return new ModelAndView(mapa, "inicio.ftl");
        }, motor);
    }

}

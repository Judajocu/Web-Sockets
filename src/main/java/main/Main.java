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

import org.jasypt.util.text.BasicTextEncryptor;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args)throws SQLException {


        //Iniciando el servicio
        BootstrapService.getInstancia().init();
        /*BootstrapService.startDb();

        //Prueba de Conexión.
        DatabaseService.getInstancia().testConexion();

        BootstrapService.CreateTable();

        UserServices servicios_user= new UserServices();*/

        //Insertando administrador por defecto
        User insertar = new User();
        insertar.setUsername("Admin");
        insertar.setNombre("Carla");
        insertar.setPassword("123");
        insertar.setAuthor(true);
        insertar.setAdministrator(true);
        UserServices.getInstancia().crear(insertar);

        User a=null;
        a=UserServices.getInstancia().find("Admin");
        if(a!=null){
            System.out.println("Username "+a.getUsername());
        }
        else {System.out.println("no esta");}


        /*Product p=new Product();
        p.setTitle("Articulo 1");
        p.setBody("primer comentario en un sistema hecho cin hibernate, espacio de mas y fdfbgbddhdfhdfdfhdfhdhg fgfgfjfhfjnfjbnfjd");
        p.setAuthor(UserServices.getInstancia().find("Admin"));
        Date today = Calendar.getInstance().getTime();
        p.setDateTime(today);
        List<Tag> lala=TagServices.getInstancia().findAllBytag("tag1");
        p.setTags(lala);
        productServices.getInstancia().crear(p);*/

        //long i=129;
        //TagServices.getInstancia().eliminar(i);
        /*
        Tag t=new Tag();
        t.setTag("tag2");
        boolean exist=false;
        //TagServices.getInstancia().crear(t);

        List<Tag> ss=TagServices.getInstancia().findAll();
        for (Tag u: ss){
            if(u.getTag().equals(t.getTag())){
                exist=true;
                System.out.println("existe");
            }
        }
        if(exist=false){
            TagServices.getInstancia().crear(t);
        }

        //List<Tag> ss2=TagServices.getInstancia().findAllBytag("tag1");
        List<Tag> ss2=TagServices.getInstancia().findAll();
        for (Tag u: ss2){
            System.out.println("tag ss2: "+u.getTag()+" ID "+ u.getId());
        }

        List<Product> spp=productServices.getInstancia().findAll();
        for (Product u: spp){
            System.out.println("product: "+u.getId()+" titulo "+ u.getTitle()+" body: "+u.getBody()+" fecha: "+u.getDateTime());
            for (Tag t3:u.getTags()){
                System.out.println("Tag: "+t3.getTag());
            }
        }

        Comment c=new Comment();
        c.setComment("primer comentario");
        c.setAuthor(UserServices.getInstancia().find("Admin"));
        long esto=33;
        c.setProduct(productServices.getInstancia().find(esto));
        CommentServices.getInstancia().crear(c);
        */

        //productServices prueba=new productServices();

        List<Tag> ss2=TagServices.getInstancia().findAll();
        for (Tag u: ss2){
            System.out.println("tag ss2: "+u.getTag()+" ID "+ u.getId());
        }

        List<Product> spp=productServices.getInstancia().findAllById();
        for (Product u: spp){
            System.out.println("product: "+u.getId()+" titulo "+ u.getTitle()+" body: "+u.getBody()+" fecha: "+u.getDateTime());
        }

        String prueba = encrypt("prueba");
        System.out.println(prueba);
        prueba = decrypt(prueba);
        System.out.println(prueba);

        /*

        Date today = Calendar.getInstance().getTime();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);*/
        manejadorFremarker();

        //BootstrapService.stopDb();


    }

    public static void manejadorFremarker()throws SQLException{

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
            //UserServices u=new UserServices();
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else {
                user= request.session(true).attribute("user");
            }


            /*user=UserServices.getInstancia().find("Admin");
            if(UserServices.getInstancia().find(cook)!=null){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else {
                user= request.session(true).attribute("user");
            }*/

            //productServices manejo_p = new productServices();
            List<Product> articulos= productServices.getInstancia().findAllById();
            for(Product p: articulos){
                String up = p.getBody().substring(0, Math.min(p.getBody().length(), 70));
                //System.out.println("caracteres "+up);
                //System.out.println("caracteres 2 "+p.getBody());
                p.setBody(up+"...");
            }

            List<Tag> mtag=TagServices.getInstancia().findAll();

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("name","Bienvenidos");
            mapa.put("userl",user);
            mapa.put("art",articulos);
            mapa.put("tl",mtag);
            return new ModelAndView(mapa, "inicio.ftl");
        }, motor);

        get("/tag/:id", (request, response) -> {
            //UserServices u=new UserServices();
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else {
                user= request.session(true).attribute("user");
            }
            long productid = Long.parseLong(request.params("id"));
            Tag tt=TagServices.getInstancia().find(productid);

            //productServices manejo_p = new productServices();
            List<Product> aux= productServices.getInstancia().findAllById();
            List<Product> articulos= new ArrayList<>();
            for(Product pro:aux){
                boolean esta=false;
                for(Tag aux2:pro.getTags()){
                    if(aux2.getId()==tt.getId()){
                        esta=true;
                    }
                }
                if(esta){articulos.add(pro);}
            }
            for(Product p: articulos){
                String up = p.getBody().substring(0, Math.min(p.getBody().length(), 70));
                p.setBody(up+"...");
            }

            List<Tag> mtag=TagServices.getInstancia().findAll();

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("name","Bienvenidos");
            mapa.put("userl",user);
            mapa.put("art",articulos);
            mapa.put("tl",mtag);
            mapa.put("tt",tt);
            return new ModelAndView(mapa, "bytag.ftl");
        }, motor);

        Spark.get("/login", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "login.ftl");
        }, motor);

        Spark.post("/login", (request, response) -> {


            String username =request.queryParams("username") != null ? request.queryParams("username") : "unknown";
            String pass =request.queryParams("username") != null ? request.queryParams("pass") : "unknown";

            User user = UserServices.getInstancia().find(username);
            //System.out.println("usuario "+ user.getUsername());
            if(user!=null){
                if(user.getPassword().equals(pass)) {

                    request.session(true);
                    request.session().attribute("user", user);
                    response.cookie("/", "test", encrypt(user.getUsername()), 604800, false);
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
            response.removeCookie("test");
            response.redirect("/");
            return "";
        });

        get("/userlist", (request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else {
                user= request.session(true).attribute("user");
            }
            //UserServices servicios_user= new UserServices();
            List<User> usuarios = UserServices.getInstancia().findAll();


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

            //productServices ps=new productServices();
            Product insertar = new Product();
            insertar.setAuthor(user);
            insertar.setTitle(title);
            insertar.setBody(body);
            insertar.setDateTime(today);

            List<Tag> ss=TagServices.getInstancia().findAll();
            List<Tag> gt=new ArrayList<>();
            System.out.println("ENTRA AQUI");
            for (int i=0;i<tags.length;i++){
                boolean exist=false;
                for (Tag u: ss){
                    if(u.getTag().equals(tags[i])){
                        exist=true;
                        List<Tag> aux2=TagServices.getInstancia().findAllBytag(tags[i]);
                        for (Tag tt: aux2){
                            gt.add(tt);
                            System.out.println("TAG YA CREADO!!: "+tt.getTag()+" ID "+ tt.getId());
                        }
                    }
                }
                if(!exist){
                    System.out.println("CREAR NUEVO");
                    Tag t=new Tag();
                    t.setTag(tags[i]);
                    System.out.println("A CREAR: "+t.getTag());
                    TagServices.getInstancia().crear(t);
                    List<Tag> aux2=TagServices.getInstancia().findAllBytag(tags[i]);
                    for (Tag tt: aux2){
                        gt.add(tt);
                        System.out.println("TAG RECIEN CREADO!!: "+tt.getTag()+" ID "+ tt.getId());
                    }
                }
            }
            for (Tag u: gt){
                    System.out.println("tag gt: "+u.getTag()+" ID "+ u.getId());
            }
            insertar.setTags(gt);
            productServices.getInstancia().crear(insertar);
            //ps.CreateProduct(insertar, tags);

            response.redirect("/");
            return "";
        });

        get("product/:id",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else {
                user= request.session(true).attribute("user");
            }
            //productServices pro=new productServices();
            ArrayList<Product> ProductList = new ArrayList<Product>();

            long productid = Long.parseLong(request.params("id"));
            Product p=productServices.getInstancia().find(productid);//pro.getProduct(productid);
            //p.setComments(pro.pComment(p.getId()));
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("art",p);
            System.out.println("art");
            return new ModelAndView(mapa,"producto.ftl");
        },motor);

        post("product/addcomment/:id", (request, response) -> {
            User user= request.session(true).attribute("user");

            long productid = Long.parseLong(request.params("id"));
            Product p=productServices.getInstancia().find(productid);//pro.getProduct(productid);

            String body =request.queryParams("body");

            Comment insertar = new Comment();
            insertar.setAuthor(user);
            insertar.setComment(body);
            insertar.setProduct(p);
            CommentServices.getInstancia().crear(insertar);

            String re ="/product/"+p.getId();
            response.redirect(re);
            return "";
        });

        get("product/edit/:id", (request, response) -> {
            User user= request.session(true).attribute("user");
            long productid = Long.parseLong(request.params("id"));

            Product p=productServices.getInstancia().find(productid);//.getProduct(productid);
            String tag="";
            for (Tag t:p.getTags()){
                tag+=t.getTag()+",";
            }
            if(tag.length()>1){
            tag.substring(0, Math.min(tag.length(), tag.length()-1));}

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("art",p);
            mapa.put("tag",tag);
            return new ModelAndView(mapa, "editarP.ftl");
        }, motor);

        post("/edit/:id", (request, response) -> {
            User user= request.session(true).attribute("user");
            long productid = Long.parseLong(request.params("id"));

            String title =request.queryParams("title");
            String body =request.queryParams("body");
            String[] tags =request.queryParams("tag").split(",");
            Date today = Calendar.getInstance().getTime();

            Product insertar = productServices.getInstancia().find(productid);//ps.getProduct(productid);
            insertar.setTitle(title);
            insertar.setBody(body);

            List<Tag> ss=TagServices.getInstancia().findAll();
            List<Tag> gt=new ArrayList<>();
            for (int i=0;i<tags.length;i++){
                boolean exist=false;
                for (Tag u: ss){
                    if(u.getTag().equals(tags[i])){
                        exist=true;
                        gt.add(u);
                    }
                }
                if(!exist){
                    Tag t=new Tag();
                    t.setTag(tags[i]);
                    TagServices.getInstancia().crear(t);
                    List<Tag> aux2=TagServices.getInstancia().findAllBytag(tags[i]);
                    for (Tag tt: aux2){
                        gt.add(tt);
                    }
                }
            }
            insertar.setTags(gt);
            productServices.getInstancia().editar(insertar);

            String re ="/product/"+insertar.getId();
            response.redirect(re);
            return "";
        });

        get("product/del/:id", (request, response) -> {
            System.out.println("delete");
            User user= request.session(true).attribute("user");
            long productid = Long.parseLong(request.params("id"));

            productServices.getInstancia().eliminar(productid);//ps.DeleteProduct(productid);

            response.redirect("/");
            return "";
        });

        // comentarios
        get("product/editc/:id", (request, response) -> {
            User user= request.session(true).attribute("user");
            long productid = Long.parseLong(request.params("id"));

            Comment p=CommentServices.getInstancia().find(productid);//pro.getComment(productid);

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("c",p);
            return new ModelAndView(mapa, "editarC.ftl");
        }, motor);

        post("/editc/:id", (request, response) -> {
            User user= request.session(true).attribute("user");
            long productid = Long.parseLong(request.params("id"));
            String body =request.queryParams("body");

            Comment insertar = CommentServices.getInstancia().find(productid);//ps.getComment(productid);
            insertar.setComment(body);
            CommentServices.getInstancia().editar(insertar);//ps.UpdateComment(insertar);

            String re ="/product/"+ insertar.getProduct().getId();
            response.redirect(re);
            return "";
        });

        get("product/delc/:id", (request, response) -> {
            User user= request.session(true).attribute("user");
            long productid = Long.parseLong(request.params("id"));

            Comment cc= CommentServices.getInstancia().find(productid);//ps.getComment(productid);
            CommentServices.getInstancia().eliminar(productid);//ps.DeleteComment(productid);

            String re ="/product/"+cc.getProduct().getId();
            response.redirect(re);
            return "";
        });

        //likes y dislikes
        get("product/like/:id", (request, response) -> {
            User user= request.session(true).attribute("user");

            long productid = Long.parseLong(request.params("id"));
            Product insertar = productServices.getInstancia().find(productid);
            insertar.plus();

            productServices.getInstancia().editar(insertar);

            String re ="/product/"+insertar.getId();
            response.redirect(re);
            return "";
        });

        get("product/dislike/:id", (request, response) -> {
            User user= request.session(true).attribute("user");

            long productid = Long.parseLong(request.params("id"));
            Product insertar = productServices.getInstancia().find(productid);
            insertar.minus();

            productServices.getInstancia().editar(insertar);

            String re ="/product/"+insertar.getId();
            response.redirect(re);
            return "";
        });

        get("userlist/deleteuser/:username", (request, response) -> {

            String username = request.params("username");
            //UserServices servicios_user= new UserServices();
            List<User> usuarios = UserServices.getInstancia().findAll();

            usuarios.removeIf(User -> User.getUsername().equalsIgnoreCase(username));
            //servicios_user.DeleteUser(username);
            List<Comment> c=CommentServices.getInstancia().findAll();
            List<Comment> caux=new ArrayList<>();
            for(Comment cc:c){
                if(cc.getAuthor().equals(username)){
                    caux.add(cc);
                    System.out.println("id "+cc.getId()+" username: "+cc.getAuthor().getUsername());
                }
            }
            List<Product> p=productServices.getInstancia().findAll();
            List<Product> paux=new ArrayList<>();
            for(Product pp:p){
                if(pp.getAuthor().equals(username)){
                    paux.add(pp);
                }
            }
            for (Comment esc: caux){CommentServices.getInstancia().eliminar(esc);}
            for (Product pesc: paux){productServices.getInstancia().eliminar(pesc);}
            UserServices.getInstancia().eliminar(username);
            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa,"deleteUser.ftl");

        },motor);

        get("userlist/editaruserForm/:username", (request,response) -> {

            String username = request.params("username");
            //UserServices servicios_user= new UserServices();
            List<User> usuarios = UserServices.getInstancia().findAll();
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
            //UserServices servicios_user= new UserServices();
            List<User> usuarios = UserServices.getInstancia().findAll();
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
                        //servicios_user.UpdateUser(s);
                        UserServices.getInstancia().editar(s);
                    }
                }
                response.redirect("/userlist");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/editaruser/");
            }
            return writer;
        });

        get("userlist/addUserForm/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "addUser.ftl");
        }, motor);

        Spark.post("/AddUser/", (request, response) -> {
            StringWriter writer = new StringWriter();
            //UserServices servicios_user= new UserServices();
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
                UserServices.getInstancia().crear(new User(Username,Nombre,Password,author,administrator));
                //servicios_user.CreateUser(new User(Username,Nombre,Password,author,administrator));
                response.redirect("/userlist");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/addUserForm/");
            }
            return writer;
        });

        get("/invalid", (request, response) -> {
            Map<String,Object> attributes = new HashMap<>();
            return new ModelAndView(attributes,"invalid.ftl");
        },motor);

        before("/userlist/*",(request, response) -> {
            //UserServices u=new UserServices();
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                System.out.println(user.isAdministrator());
                request.session(true);
                request.session().attribute("user", user);
                if(user.isAdministrator()==false)
                {
                    response.redirect("/invalid");
                }
            }
            else{
                response.redirect("/invalid");
            }

        });

        before("/userlist",(request, response) -> {
            //UserServices u=new UserServices();
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                System.out.println(user.isAdministrator());
                request.session(true);
                request.session().attribute("user", user);
                if(user.isAdministrator()==false)
                {
                    response.redirect("/invalid");
                }
            }
            else{
                response.redirect("/invalid");
            }

        });

        before("/product/(:id)(*)",(request, response) -> {
            //UserServices u=new UserServices();
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                System.out.println(user.isAdministrator());
                request.session(true);
                request.session().attribute("user", user);
                if(user.isAdministrator()==false || user.isAuthor()==false)
                {
                    response.redirect("/invalid");
                }
            }
            else{
                response.redirect("/invalid");
            }

        });

        before("/product",(request, response) -> {
            //UserServices u=new UserServices();
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                System.out.println(user.isAdministrator());
                request.session(true);
                request.session().attribute("user", user);
                if(user.isAdministrator()==false && user.isAuthor()==false)
                {
                    response.redirect("/invalid");
                }
            }
            else{
                response.redirect("/invalid");
            }

        });
    }

    public static String encrypt(String secret){
        BasicTextEncryptor a = new BasicTextEncryptor();
        a.setPasswordCharArray("some-random-data".toCharArray());
        String topsecret = a.encrypt(secret);
        return topsecret;

    }

    public static String decrypt(String secret){
        BasicTextEncryptor a = new BasicTextEncryptor();
        a.setPasswordCharArray("some-random-data".toCharArray());
        String topsecret = a.decrypt(secret);

        return topsecret;
    }

    /*public static User encontrarUser(String username){
        UserServices usuario =new UserServices();
        User user = usuario.getUser(username);
        return user;
    }*/

}

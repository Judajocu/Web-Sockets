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

public class productServices extends DatabaseService<Product> {

    private static productServices instancia;

    private productServices() {
        super(Product.class);
    }

    public static productServices getInstancia(){
        if(instancia==null){
            instancia = new productServices();
        }
        return instancia;
    }

    /**
     *
     * @param nombre
     * @return
     */
    
}

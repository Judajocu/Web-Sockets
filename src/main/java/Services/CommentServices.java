package Services;

import Classes.Comment;
import Classes.Product;
import Classes.User;
import Services.CommentServices;
import Services.TagServices;
import Services.UserServices;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommentServices extends DatabaseService<Comment> {

    private static CommentServices instancia;

    private CommentServices() {
        super(Comment.class);
    }

    public static CommentServices getInstancia(){
        if(instancia==null){
            instancia = new CommentServices();
        }
        return instancia;
    }

    /**
     *
     * @param productid
     * @return
     */



}

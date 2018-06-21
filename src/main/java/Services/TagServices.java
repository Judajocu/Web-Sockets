package Services;

import Classes.Comment;
import Classes.Product;
import Classes.Tag;
import Classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TagServices extends DatabaseService<Tag> {

    private static TagServices instancia;

    private TagServices() {
        super(Tag.class);
    }

    public static TagServices getInstancia(){
        if(instancia==null){
            instancia = new TagServices();
        }
        return instancia;
    }

    /**
     *
     * @param nombre
     * @return
     */

}

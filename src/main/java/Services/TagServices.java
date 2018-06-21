package Services;

import Classes.Comment;
import Classes.Product;
import Classes.Tag;
import Classes.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
     * @param tag
     * @return
     */
    public List<Tag> findAllBytag(String tag){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Tag.findAllBytag");
        query.setParameter("tag", "%"+tag+"%");
        List<Tag> lista = query.getResultList();
        return lista;
    }

}

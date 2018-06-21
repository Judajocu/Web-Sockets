package Services;

import Classes.Product;
import Classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServices extends DatabaseService<User> {

    private static UserServices instancia;

    private UserServices() {
        super(User.class);
    }

    public static UserServices getInstancia(){
        if(instancia==null){
            instancia = new UserServices();
        }
        return instancia;
    }

    /**
     *
     * @param username
     * @return
     */

}

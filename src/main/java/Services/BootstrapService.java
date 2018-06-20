package Services;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BootstrapService {

    private static BootstrapService instancia;

    private BootstrapService(){

    }

    public static BootstrapService getInstancia(){
        if(instancia == null){
            instancia=new BootstrapService();
        }
        return instancia;
    }

    public void startDb() {
        try {
            Server.createTcpServer("-tcpPort",
                    "9092",
                    "-tcpAllowOthers",
                    "-tcpDaemon").start();
        }catch (SQLException ex){
            System.out.println("Problema con la base de datos: "+ex.getMessage());
        }
    }

    public void init(){
        startDb();
    }

}

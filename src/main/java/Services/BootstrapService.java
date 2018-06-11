package Services;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BootstrapService {

    /**
     *
     * @throws SQLException
     */
    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     *
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }


    /**
     * Metodo para recrear las tablas necesarios
     * @throws SQLException
     */
    public static void CreateTable() throws  SQLException{
        //String esto = "DROP TABLE PRODUCTS";
        String sqlUser = "CREATE TABLE IF NOT EXISTS USERS\n" +
                "(\n" +
                "  USERNAME VARCHAR(100) PRIMARY KEY NOT NULL,\n" +
                "  NOMBRE VARCHAR(100) NOT NULL,\n" +
                "  PASSWORD VARCHAR(100) NOT NULL,\n" +
                "  ADMINISTRATOR BOOLEAN NOT NULL,\n" +
                "  AUTHOR BOOLEAN NOT NULL\n" +
                ");";
        String sqlProduct = "CREATE TABLE IF NOT EXISTS PRODUCTS\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                "  TITLE VARCHAR(100) NOT NULL,\n" +
                "  BODY VARCHAR(1000) NOT NULL,\n" +
                "  AUTHOR VARCHAR(100) NOT NULL,\n" +
                "  DATEP DATE NOT NULL,\n" +
                "FOREIGN KEY (AUTHOR) REFERENCES USERS(USERNAME)\n" +
                ");";
        String sqlTag = "CREATE TABLE IF NOT EXISTS TAGS\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  TAG VARCHAR(100) NOT NULL\n" +
                ");";
        String sqlComment = "CREATE TABLE IF NOT EXISTS COMMENTS\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  COMMENT VARCHAR(10000) NOT NULL,\n" +
                "  USERNAME VARCHAR(100) NOT NULL,\n" +
                "  PRODUCT INTEGER NOT NULL,\n" +
                "FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME),\n" +
                "FOREIGN KEY (PRODUCT) REFERENCES PRODUCTS(ID)\n" +
                ");";
        String sqlTagProduct = "CREATE TABLE IF NOT EXISTS TAGPRODUCTS\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  TAG INTEGER NOT NULL,\n" +
                "  PRODUCT INTEGER NOT NULL,\n" +
                "FOREIGN KEY (TAG) REFERENCES TAGS(ID),\n" +
                "FOREIGN KEY (PRODUCT) REFERENCES PRODUCTS(ID)\n" +
                ");";
        Connection con = DatabaseService.getInstancia().getConexion();
        Statement statement = con.createStatement();
        //statement.execute(esto);
        statement.execute(sqlUser);
        statement.execute(sqlProduct);
        statement.execute(sqlTag);
        statement.execute(sqlComment);
        statement.execute(sqlTagProduct);
        statement.close();
        con.close();
    }

}

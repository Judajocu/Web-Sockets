package Classes;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import Classes.objeto;
import Classes.objetob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/websocket",encoders = {objeto.class},decoders = {objetob.class})
public class chat {

    private static final List<Session> conectados=new ArrayList<>();
    //estados
    @OnOpen
    public void inicio(Session sesion){
        conectados.add(sesion);
    }

    @OnClose
    public void finalizar(Session session) {
        conectados.remove(session);
    }

    //mensajes
    @OnMessage
    public void mensajes(mensaje mail) throws IOException, EncodeException {
        for (Session session: conectados){
            session.getBasicRemote().sendObject(mail);
        }
    }

}

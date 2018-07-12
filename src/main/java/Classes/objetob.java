package Classes;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Reader;

public class objetob implements Decoder.TextStream<mensaje> {
    @Override
    public mensaje decode(Reader reader) throws DecodeException, IOException {
        mensaje mail= new mensaje();

        try (JsonReader jsonReader=Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            mail.setNombre(json.getString("nombre"));
            mail.setMensaje(json.getString("mensaje"));
        }
        return null;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}

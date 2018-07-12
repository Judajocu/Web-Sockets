package Classes;

import javax.json.Json;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Writer;
import javax.json.JsonObject;
//import org.jasypt.contrib.org.apache.commons.codec_1_3.Encoder;
//import javax.websocket.Encoder;


public class objeto implements javax.websocket.Encoder.TextStream<mensaje> {
    @Override
    public void encode(mensaje object, Writer writer) throws EncodeException, IOException {
        javax.json.JsonObject json= Json.createObjectBuilder()
                .add("nombre",object.getNombre())
                .add("mensaje",object.getMensaje())
                .build();
        try(JsonWriter jsonWriter= Json.createWriter(writer)){
            jsonWriter.writeObject(json);
        }
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}

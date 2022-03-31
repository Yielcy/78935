package mx.uv.t4js.Saludos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BorrarSaludoRequest;
import https.t4is_uv_mx.saludos.BorrarSaludoResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class SaludosEndPoint {
    int id = 1;
    List<Saludo>saludos = new ArrayList<>();
    //List<BuscarSaludosResponse.Saludos>saludos = new ArrayList<>();

    @PayloadRoot(localPart = "SaludarRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola" + peticion.getNombre());
        Saludo saludo = new Saludo();
        saludo.setNombre(peticion.getNombre());
        saludo.setId(id);
        saludos.add(saludo);
        id += 1;
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarSaludosRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse BuscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        for (Saludo saludo : saludos) {
            BuscarSaludosResponse.Saludos saludosBuscar = new BuscarSaludosResponse.Saludos();
            saludosBuscar.setId(saludo.getId());
            saludosBuscar.setNombre(saludo.getNombre());
            respuesta.getSaludos().add(saludosBuscar);
        }
        return respuesta;
    }

    /*ArrayList<String> lista = new ArrayList<>();
    @PayloadRoot(localPart = "SaludarRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola"+peticion.getNombre());
        Saludos e = new Saludos();
        e.setNombre(peticion.getNombre());
        e.setId(id++);
        lista.add(e);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarSaludosRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse BuscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        for (Saludos o : lista) {
            respuesta.getSaludos().add(o);
        }
        return respuesta;
    }
    */
    /*
    @PayloadRoot(localPart = "BuscarSaludosRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse BuscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        for (BuscarSaludosResponse.Saludos saludo : saludos) {
            respuesta.getSaludos().add(saludo);
        }
        return respuesta;
    }
    */

    @PayloadRoot(localPart = "ModificarSaludoRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludo e = new Saludo();
        e.setId(peticion.getId());
        e.setNombre(peticion.getNombre());
        saludos.set(peticion.getId()-1, e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarSaludoRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BorrarSaludoResponse borrarSaludo(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        //saludos.remove(peticion.getId()-1);
        for (Saludo o : saludos){
            if (o.getId() == peticion.getId()){
                saludos.remove(o);
                break;
            }
        }
        respuesta.setRespuesta(true);
        return respuesta;
    }
}

package mx.uv.t4js.SaludosBd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    //int id = 1;
    //List<Saludos>saludos = new ArrayList<>();
    //List<BuscarSaludosResponse.Saludos>saludos = new ArrayList<>();

    @Autowired
    Isaludadores isaludadores;

    @PayloadRoot(localPart = "SaludarRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola" + peticion.getNombre());
        Saludadores saludo = new Saludadores();
        saludo.setNombre(peticion.getNombre());
        //saludo.setId(id);
        //saludos.add(saludo);
        //id += 1;
        isaludadores.save(saludo);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarSaludosRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse BuscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        Iterable<Saludadores> lista = isaludadores.findAll();
        for (Saludadores o : lista) {
            BuscarSaludosResponse.Saludos saludosBuscar = new BuscarSaludosResponse.Saludos();
            saludosBuscar.setId(o.getId());
            saludosBuscar.setNombre(o.getNombre());
            respuesta.getSaludos().add(saludosBuscar);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarSaludoRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludadores e = new Saludadores();
        e.setId(peticion.getId());
        e.setNombre(peticion.getNombre());
        //saludos.set(peticion.getId()-1, e);
        isaludadores.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarSaludoRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BorrarSaludoResponse borrarSaludo(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        //saludos.remove(peticion.getId()-1);
        /*for (Saludos o : saludos){
            if (o.getId() == peticion.getId()){
                saludos.remove(o);
                break;
            }
        }*/
        isaludadores.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }
}

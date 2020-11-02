/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryRedSocial;

import java.util.HashMap;

/**
 *
 * @author casierrav
 */
public class Fabrica {
   
    /**
     * Constructor de la clase
     */
    public Fabrica(){} 
    
    public ComponenteRedSocial crearComponenteRedSocial(String tipo, HashMap<String, String> requerimiento){
        ComponenteRedSocial respuesta = null;
        switch(tipo){
            case "Usuario":
                String nombre = requerimiento.get("nombre");
                String email = requerimiento.get("email");
                String alias = requerimiento.get("alias");
                respuesta = new Usuario(nombre, email, alias);
                break;
            case "Grupo":
                String nombreGrupo = requerimiento.get("nombre");
                respuesta = new Grupo(nombreGrupo);
                break;
            case "Estado":
                String fecha = requerimiento.get("fecha");
                String contenido = requerimiento.get("contenido");
                respuesta = new Estado(fecha, contenido);
                break;
        }
        
        return respuesta;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryRedSocial;

import java.util.ArrayList;

/**
 *
 * @author casierrav
 */
public class Estado implements ComponenteRedSocial{
    
    //Atributos del Estado
    private String fecha = "";
    private String contenido = "";
    private ArrayList<String> comentarios = null;
    
    
    /**
     * Constructor de la Clase
     * @param fecha
     * @param contenido
     */
    public Estado(String fecha, String contenido){
        this.fecha = fecha;
        this.contenido = contenido;
        this.comentarios = new ArrayList<>();
    }
    
    
    /**
     * Este método es utilizado para agregar comentarios al estado.
     * @param nuevoComentario
     * @return éxito de la operación
     */
    public String agregarComentario(String nuevoComentario){
       try{
            this.comentarios.add(nuevoComentario);
            return "Proceso Exitoso";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "Proceso No Exitoso";
        } 
    }
    
    
    /**
     * Este método es utilizado para retornar todos los comentarios del estado en un solo String
     * @return comentarios
     */
    public String cargarComentarios(){
        String comentariosTotal = "";
        for(String comentario: this.comentarios){
            comentariosTotal += comentario + "\n";
        }
        return comentariosTotal;
    }
}
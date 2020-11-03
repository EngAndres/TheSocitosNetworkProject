/**
 Copyright (C) 2020 Carlos Andrés Sierra (casierrav)
 This file is part of TheSocitosNetworkProject <https://github.com/EngAndres/TheSocitosNetworkProject>.

 TheSocitosNetworkProject is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 TheSocitosNetworkProject is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with TheSocitosNetworkProject.  If not, see <http://www.gnu.org/licenses/>.
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
    
    
    @Override
    public String toString(){
        String respuesta = "Fecha: " + this.fecha + ". Contenido: " + this.contenido + "\n";
        return respuesta;
    }
}
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
 * Esta clase modela los atributos y comportamientos básicos de un Grupo en la red de Socitos.
 * Esta clase es parte de la implementación del Patrón de Diseño Factoty, como un objeto a ser construido por la fábrica.
 * @author casierrav
 */
public class Grupo implements ComponenteRedSocial{
   
    //Atributos del Grupo
    public String nombre = ""; //este debe ser único e irrepetible
    private ArrayList<String> usuarios = null;
    private ArrayList<Estado> estadosEnGrupo = null;
    
    
    /**
     * Constructor de la Clase
     * @param nombre
     */
    public Grupo(String nombre){
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.estadosEnGrupo = new ArrayList<>();
    }
    
    
    /**
     * Este método es utilizado para agregar (afiliar) un usuario al grupo
     * @param nuevoUsuario
     * @return éxito de la operación
     */
    public String agregarUsuario(String nuevoUsuario){
        try{
            this.usuarios.add(nuevoUsuario);
            return "Proceso Exitoso";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "Proceso No Exitoso";
        }
    }
    
    
    /**
     * Este método es utilizado para buscar un usuario afiliado al grupo
     * @param usuario
     * @return encontrado(true) o no encontrado (false)
     */
    public boolean buscarUsuario(String usuario){
        try{
            return this.usuarios.contains(usuario);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * Este método es utilizado para eliminar un usuario (desafiliar) del grupo
     * @param usuario
     * @return éxito de la operación 
     */
    public String eliminarUsuario(String usuario){
        try{
            this.usuarios.remove(usuario);
            return "Proceso Exitoso";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "Proceso No Exitoso";
        }
    }
    
    
    /**
     * Método utilizado para agregar un nuevo estado "escrito" por el usuario.
     * @param nuevoEstado
     * @return éxito de la operación
     */
    public String agregarEstado(Estado nuevoEstado){
        try{
            this.estadosEnGrupo.add(nuevoEstado);
            
            
            
            return "Proceso Exitoso";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "Proceso No Exitoso.";
        }
    }
    
    
    /**
     * Método que permite obtener los últimos 10 estados que ha publicado el usuario si tiene más de cinco. 
     * Si tiene menos de cinco estados, retornará los que tenga.
     * @return arreglo con los últimos estados
     */
    public String ultimosEstados(){
        int cantidad = (this.estadosEnGrupo.size() < 10) ? this.estadosEnGrupo.size() : 10;
        String estados = "";
        
        for(int i = 0; i < cantidad; i++){
            estados += this.estadosEnGrupo.get(this.estadosEnGrupo.size() - 1 - i).toString();
        }
        
        return estados;
    }
    
    
    /**
     * Este método se utiliza para retornar la lista de usuarios inscritos al grupo
     * @return 
     */
    public String[] listaUsuarios(){
        int cantidad = this.usuarios.size();
        String[] listaActual = new String[cantidad];
       
        for(int i = 0; i < cantidad; i++){
            listaActual[i] = this.usuarios.get(i);
        }
        
        return listaActual;
    }
    
    
    /**
     * Este método es utilizado para dar formato a la infomración del Grupo y empaquetarlo en un solo String
     * @return datos del grupo
     */
    @Override
    public String toString(){
        String grupo = "El grupo tiene por nombre " + this.nombre + ". Se compone por " + this.usuarios.size() +
                    " usuarios y se han escrito " + this.estadosEnGrupo.size() + " estados.\n\nUsuarios:";
        for(String usuario: this.usuarios){
            grupo += usuario + "\t";
        }
        return grupo;
    }
}
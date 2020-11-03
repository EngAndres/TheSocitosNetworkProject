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
package FacadeRedSocial;

import FactoryRedSocial.Estado;
import FactoryRedSocial.Fabrica;
import java.util.ArrayList;

import FactoryRedSocial.Grupo;
import FactoryRedSocial.Usuario;
import java.util.HashMap;

/**
 *
 * @author casierrav
 */
public class Fachada extends Fabrica{
    
    private ArrayList<Usuario> usuarios = null;
    private ArrayList<Grupo> grupos = null;
    
    
    /**
     * Constructor de la Clase
     */
    public Fachada(){
        this.usuarios = new ArrayList<>();
        this.grupos = new ArrayList<>();
    }
                
    
    /**
     * Se utiliza este método para buscar el índide de un usuario en la lista, Si no está, retorna -1 como marca lógica.
     * @param alias
     * @return índice
     */
    private int indiceDeUsuario(String alias){
        int indiceDe = -1;
        for(int i = 0; i < this.usuarios.size(); i++){
            if(usuarios.get(i).alias.equals(alias)){
                indiceDe = i;
                break;
            }
        }
        return indiceDe;
    }
    
    
    /**
     * Se utiliza este método para buscar el índide de un usuario en la lista, Si no está, retorna -1 como marca lógica.
     * @param alias
     * @return índice
     */
    private int indiceDeGrupo(String nombre){
        int indiceDe = -1;
        for(int i = 0; i < this.grupos.size(); i++){
            if(grupos.get(i).nombre.equals(nombre)){
                indiceDe = i;
                break;
            }
        }
        return indiceDe;
    }
    
    
    /**
     * Se utiliza este metodo para agregar un nuevo usuario a la red social.
     * @param nombre
     * @param email
     * @param alias
     * @return mensajeDeProceso
     */
    public String crearUsuario(String nombre, String email, String alias){
        try {
            int indice = this.indiceDeUsuario(alias);
            
            if(indice == -1) {
                HashMap<String, String> nuevoUsuario = new HashMap<>();
                nuevoUsuario.put("nombre", nombre);
                nuevoUsuario.put("email", email);
                nuevoUsuario.put("alias", alias);
                this.usuarios.add((Usuario)this.crearComponenteRedSocial("Usuario", nuevoUsuario));
                return "Usuario Creado Exitosamente.";
            }else{
                return "El usuario ya existe en la lista. Por favor, seleccionar un nuevo alias.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se ha podido crear el usuario.";
        }
    }
     

    /**
     * Se utiliza este método para agregar un nuevo grupo a la red social.
     * @param nombre
     * @return mensajeDeProceso
     */
    public String crearGrupo(String nombre){
        try {
            int indice = this.indiceDeGrupo(nombre);
            
            if(indice == -1){
                HashMap<String, String> nuevoGrupo = new HashMap<>();
                nuevoGrupo.put("nombre", nombre);
                this.grupos.add((Grupo)this.crearComponenteRedSocial("Grupo", nuevoGrupo));
                return "Grupo Creado Exitosamente.";
            }else{
                return "El grupo ya existe en la lista. Por favor, seleccionar un nuevo nombre.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se ha podido crear el grupo.";
        }
    }
     
     
    /**
     * Este método se utiliza para agregar un estado a un usuario específico.
     * @param usuario
     * @param fecha
     * @param contenido
     * @return mensajeDeProceso
     */
    public String crearEstadoUsuario(String usuario, String fecha, String contenido){
        try {
            HashMap<String, String> nuevoEstado = new HashMap<>();
            nuevoEstado.put("fecha", fecha);
            nuevoEstado.put("contenido", contenido);
            
            int indice = this.indiceDeUsuario(usuario); 
            if(indice != -1){
                this.usuarios.get(indice).agregarEstado((Estado)this.crearComponenteRedSocial("Estado", nuevoEstado));
                return "Estado Agregado Existosamente.";
            }else{
                return "El usuario seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se ha podido crear el estado al usuario.";
        }
    }
     
    
    /**
     * Este método se utiliza para agregar un usuario de un grupo específico
     * @param grupo
     * @param usuario
     * @return mensajeDeProceso
     */
    public String agregarUsuarioAGrupo(String grupo, String usuario){
        try{
            int indiceGrupo = this.indiceDeGrupo(grupo);
            if(indiceGrupo != -1){
                if(this.grupos.get(indiceGrupo).buscarUsuario(usuario)){
                    return "El usuario ya se encuentra en el grupo.";
                }else{
                    this.grupos.get(indiceGrupo).agregarUsuario(usuario);
                    return "El usuario ha sido correctamente agregado al grupo.";
                }
            }else{
                return "El grupo seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
             ex.printStackTrace();
            return "No se ha podido agregar el usuario al grupo.";
        }
    }
    
    
    /**
     * Este método se utiliza para realizar las notificaciones a todos los 
     * @param grupo 
     */
    private void notificarUsuariosDeGrupo(String grupo){
        int indiceGrupo = this.indiceDeGrupo(grupo);
        int indiceUsuario;
        
        String[] usuariosGrupo = this.grupos.get(indiceGrupo).listaUsuarios();
        String notificacion = "Se ha agregado un nuevo estado al grupo " + grupo + ".";
        
        for(String usuario: usuariosGrupo){
            indiceUsuario = this.indiceDeUsuario(usuario);
            this.usuarios.get(indiceUsuario).agregarNotificacion(notificacion);
        }
    }
    
     
    /**
     * Este método se utiliza para eliminar un usuario de un grupo específico
     * @param grupo
     * @param usuario
     * @return mensajeDeProceso
     */
    public String eliminarUsuarioDeGrupo(String grupo, String usuario){
        try{
            int indiceGrupo = this.indiceDeGrupo(grupo);
            if(indiceGrupo != -1){
                if(this.grupos.get(indiceGrupo).buscarUsuario(usuario)){
                    this.grupos.get(indiceGrupo).eliminarUsuario(usuario);
                    return "El usuario se ha eliminado del grupo.";
                }else{
                    return "El usuario no existe en el grupo.";
                }
            }else{
                return "El grupo seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
             ex.printStackTrace();
            return "No se ha podido agregar el usuario al grupo.";
        }
    }
     
     
     /**
      * Este método se utiliza para agregar un estado a un grupo específico.
      * @param nombre
      * @param fecha
      * @param contenido
      * @return mensajeDeProceso
      */
     public String agregarEstadoAGrupo(String nombre, String fecha, String contenido){
        try {
            HashMap<String, String> nuevoEstado = new HashMap<>();
            nuevoEstado.put("fecha", fecha);
            nuevoEstado.put("contenido", contenido);
            
            int indice = this.indiceDeGrupo(nombre); 
            if(indice != -1){
                this.grupos.get(indice).agregarEstado((Estado)this.crearComponenteRedSocial("Estado", nuevoEstado));
                return "Estado Agregado Existosamente.";
            }else{
                return "El grupo seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se ha podido crear el estado al grupo.";
        }
    }
     
     
    /**
     * Este método se utiliza para visualizar la información general de un usuario.
     * @param usuario
     * @return mensajeDeProceso
     */
    public String verInformacionUsuario(String usuario){
        try{
            int indice = this.indiceDeUsuario(usuario);
            if(indice != -1){
                System.out.println(this.usuarios.get(indice).toString());
                return "Transacción exitosa.";
            }else{
                return "El usuario seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se han podido cargar la información del usuario.";
        } 
    }
     
     
    /**
     * Este métdo se utuliza para visualizar la información general de un grupo.
     * @param nombre
     * @return mensajeDeProceso
     */
    public String verInformacionGrupo(String nombre){
        try{
            int indice = this.indiceDeGrupo(nombre);
            if(indice != -1){
                System.out.println(this.grupos.get(indice).toString());
                return "Transacción exitosa.";
            }else{
                return "El grupo seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se han podido cargar la información del grupo.";
        } 
    }
     
     
    /**
     * Este método se utiliza este método para cargar los últimos 5 estados de un usuario. 
     * Si hay menos de 10, se cargan todos los que se encuentren.
     * @param alias
     * @return mensajeDeProceso
     */
    public String ultimosEstadosUsuario(String usuario){
        try{
            int indice = this.indiceDeUsuario(usuario);
            if(indice != -1){
                System.out.println(this.usuarios.get(indice).ultimosEstados());
                return "Transacción exitosa.";
            }else{
                return "El usuario seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se han podido cargar los últimos estados del usuario.";
        } 
    }
     
     
    /**
     * Este método se utiliza este método para cargar los últimos 10 estados de un grupo. 
     * Si hay menos de 10, se cargan todos los que se encuentren.
     * @param nombre
     * @return mensajeDeProceso
     */
    public String ultimosEstadosGrupo(String nombre){
        try{
            int indice = this.indiceDeGrupo(nombre);
            if(indice != -1){
                System.out.println(this.grupos.get(indice).ultimosEstados());
                return "Transacción exitosa.";
            }else{
                return "El grupo seleccionado no existe en la lista.";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "No se han podido cargar los últimos estados del grupo.";
        } 
    }
}
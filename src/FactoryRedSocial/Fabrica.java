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

import java.util.HashMap;

/**
 * Clase que implementa como tal el Patrón de Diseño Factory
 * @author casierrav
 */
public class Fabrica {
   
    /**
     * Constructor de la clase
     */
    public Fabrica(){} 
    
    protected ComponenteRedSocial crearComponenteRedSocial(String tipo, HashMap<String, String> requerimiento){
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

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
 * Clase que implementa la parte frontal el Patrón de Diseño Factory. Corresponde a la fábrica en donde se recibe la solicitud
 * de creación de componentes, se aplica la lógica correspondiente para la creación de acuerdo a las clases definidas como posible
 * componente en la fábrica, y oculta del usuario final la complejidad de generación de objetos y relaciones .
 * @author casierrav
 */
public class Fabrica {
   
    /**
     * Constructor de la clase
     */
    public Fabrica(){} 
    
    
    /**
     * Este método es utilizado para recibir la solicitud del cliente (en este caso desde la clase Fachada, implementación
     * del patrón de diseño Facade), y generar el objeto correspondiente para ser enviado al usuario.
     * @param tipo
     * @param requerimiento
     * @return componente de la red social
     */
    protected ComponenteRedSocial crearComponenteRedSocial(String tipo, HashMap<String, String> requerimiento){
        // en el HashMap llamado "requerimiento", se deberán enviar los datos necesarios para crear cada componente según corresponda
        
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
        
        // ATENCIÓN: Si se requuiere agregar un nuevo componente, simplemente se agrega la opción en el Switch y se notifica
        // al usuario el requerimiento de datos para la creación de dicho componente. De esta manera, será transparente el proceso
        
        return respuesta;
    }
}

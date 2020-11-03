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
package thesocitosnetwork;

import FacadeRedSocial.Fachada;
import java.util.Scanner;

/**
 *
 * @author casierrav
 */
public class Principal extends Fachada{

    /**
     * Menú Principal de la Aplicación
     * @return menú en formato String
     */
    public static String menu(){
        String menu = " \n\n1. Crear Usuario\n"
                    + " 2. Crear Grupo\n"
                    + " 3. Crear Estado de Usuario\n"
                    + " 4. Agregar Usuario a Grupo\n"
                    + " 5. Eliminar Usuario de Grupo\n"
                    + " 6. Agregar Estado a Grupo\n"
                    + " 7. Ver Información de Usuario\n"
                    + " 8. Ver Información de Grupo\n"
                    + " 9. Ver Últimos Estados de Usuario\n"
                    + "10. Ver Últimos Estados de Grupo\n"
                    + "11. Salir";   
                    
        return menu;
    }
    
    
    /**
     * Esta función es utilizada para leer una línea completa desde la consola verificando que no sea vacía
     * @param texto
     * @param entrada
     * @return valor desde consola
     */
    public static String leerLinea(String texto, Scanner entrada){
        System.out.print(texto); // Texto de guia para que el usuario sepa que va a diligenciar
        String response = entrada.nextLine();
        while(response.length() == 0){ 
            response = entrada.nextLine();
        }
        return response;
    }
    
    
    /**
     * Esta función es utilizada para leer un valor escrito desde la consola verificando que no sea vacío
     * @param texto
     * @param entrada
     * @return valor desde consola
     */
    public static String leer(String texto, Scanner entrada){
        System.out.print(texto); // Texto de guia para que el usuario sepa que va a diligenciar
        String response = entrada.next();
        while(response.length() == 0){
            response = entrada.next();
        }
        return response;
    }
            
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String usuario = "", grupo = "", fecha = "", contenido = "", nombre = "", email = "";
        int opcion = -1;
        Scanner entrada = new Scanner(System.in);
        Fachada fachada = new Fachada();
        
        // Se usa un DoWhile para primero mostrar el menú y luego validar la opción seleccionada por el usuario
        do {
            System.out.println(menu());
            opcion = Integer.parseInt(entrada.next());
            
            // Por el uso de Facade, para cada caso del menú solo se requiere que el usuario diligencie las entradas necesarias
            // Una vez se tengan las entradas, solo es necesario llamar a la función correspondiente en la Fachada.
            // Esto implica que en el main no se define ninguna regla del negocio, separando el acceso del usuario de la lógica de la aplicación
            switch(opcion) {
                case 1:
                    System.out.println("========== CREAR USUARIO ==========");
                    nombre = leerLinea("Ingrese nombre del usuario: ", entrada);
                    email = leer("Ingrese correo del usuario: ", entrada);
                    usuario = leer("Ingrese alias del usuario: ", entrada);
                    System.out.println(fachada.crearUsuario(nombre, email, usuario));
                    break; 
                case 2:
                    System.out.println("========== CREAR GRUPO ==========");
                    grupo = leerLinea("Ingrese nombre del grupo: ", entrada);
                    System.out.println(fachada.crearGrupo(grupo));
                    break;
                case 3:
                    System.out.println("========== CREAR ESTADO DE USUARIO ==========");
                    usuario = leer("Ingrese alias del usuario: ", entrada);
                    fecha = leerLinea("Ingrese la fecha del estado: ", entrada);
                    contenido = leerLinea("Ingrese el contenido del estado: ", entrada);
                    System.out.println(fachada.crearEstadoUsuario(usuario, fecha, contenido));
                    break;
                case 4:
                    System.out.println("========== AGREGAR USUARIO A GRUPO ==========");
                    grupo = leerLinea("Ingrese nombre del grupo: ", entrada);
                    usuario = leer("Ingrese alias del usuario: ", entrada);
                    System.out.println(fachada.agregarUsuarioAGrupo(grupo, usuario));
                    break;
                case 5:
                    System.out.println("========== ELIMINAR USUARIO DE GRUPO ==========");
                    grupo = leerLinea("Ingrese nombre del grupo: ", entrada);
                    usuario = leer("Ingrese alias del usuario: ", entrada);
                    System.out.println(fachada.eliminarUsuarioDeGrupo(grupo, usuario));
                    break;
                case 6:
                    System.out.println("========== AGREGAR ESTADO A GRUPO ==========");
                    grupo = leerLinea("Ingrese nombre del grupo: ", entrada);
                    fecha = leerLinea("Ingrese la fecha del estado: ", entrada);
                    contenido = leerLinea("Ingrese el contenido del estado: ", entrada);
                    System.out.println(fachada.agregarEstadoAGrupo(grupo, fecha, contenido));
                    break;
                case 7:
                    System.out.println("========== VER INFORMACIÓN DE USUARIO ==========");
                    usuario = leer("Ingrese alias del usuario: ", entrada);
                    System.out.println(fachada.verInformacionUsuario(usuario));
                    break;
                case 8:
                    System.out.println("========== VER INFORMACIÓN DE GRUPO ==========");
                    grupo = leerLinea("Ingrese nombre del grupo: ", entrada);
                    System.out.println(fachada.verInformacionGrupo(grupo));
                    break;
                case 9:
                    System.out.println("========== ÚLTIMOS ESTADOS DE USUARIO ==========");
                    usuario = leer("Ingrese alias del usuario: ", entrada);
                    System.out.println(fachada.ultimosEstadosUsuario(usuario));
                    break;
                case 10:
                    System.out.println("========== ÚLTIMOS ESTADOS DE GRUPO ==========");
                    grupo = leerLinea("Ingrese nombre del grupo: ", entrada);
                    System.out.println(fachada.ultimosEstadosGrupo(grupo));
                    break;
                case 11:
                    System.out.println("Gracias por usar nuestra Red Social, socito!");
                    break;
                default:
                    System.out.println("Por favor, seleccione una opción válida.\n");
                   
            }
            
        }
        while(opcion != 11);
        entrada.close();
    }
}
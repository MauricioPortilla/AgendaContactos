/**
 * Engine es la clase que lleva a cabo el control en la
 * base de datos.
 * 
 * @author Mauricio Cruz Portilla
 * @version 1.0
 * @since 2019/02/22
 */
package agendacontactos.engine;

public class Engine {

    /**
     * Crea la base de datos AgendaContactosDB si no existe.
     */
    public static void createDatabase() {
        SQL.executeUpdate(
            "CREATE DATABASE IF NOT EXISTS AgendaContactosDB;" +
            "CREATE TABLE AgendaContactosDB.contacto (" +
                "idcontacto int PRIMARY KEY NOT NULL AUTO_INCREMENT," + 
                "nombre varchar(20) NOT NULL," + 
                "paterno varchar(30) NOT NULL," + 
                "materno varchar(30)," + 
                "telefono varchar(15) NOT NULL," + 
                "correo varchar(50) NOT NULL," + 
                "fechaNacimiento date NOT NULL" +
            ");", false
        );
    }
}

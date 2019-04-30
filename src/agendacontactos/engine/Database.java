/**
 * Database es la clase que lleva a cabo la conexion a la
 * base de datos.
 * 
 * @author Mauricio Cruz Portilla
 * @version 1.0
 * @since 2019/02/22
 */

package agendacontactos.engine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Database {
    private Connection connection;

    /**
     * Crea la conexion a la base de datos
     */
    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Connecting...");
            String url = "jdbc:mysql://localhost:3306/AgendaContactosDB";
            connection = DriverManager.getConnection(url, "agendauser", "2506");
            System.out.println("Connected.");
        } catch(SQLException sqlException){
			new Alert(
				AlertType.ERROR, 
				"Ocurrió un error al conectarse con la base de datos."
			).show();
            System.out.println("Connection error -> " + sqlException.getMessage());
        } catch(ClassNotFoundException classException){
			new Alert(
				AlertType.ERROR, 
				"Ocurrió un error al conectarse con la base de datos."
			).show();
            classException.printStackTrace();
        } catch(Exception e){
			new Alert(
				AlertType.ERROR, 
				"Ocurrió un error al conectarse con la base de datos."
			).show();
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Retorna la conexion a la base de datos
     * @return la conexion
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Cierra la conexion a la base de datos
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
			new Alert(AlertType.ERROR, "Ocurrió un error al cerrar la conexión.").show();
            System.err.println("Error: " + sqlException.getMessage());
        }
    }
}

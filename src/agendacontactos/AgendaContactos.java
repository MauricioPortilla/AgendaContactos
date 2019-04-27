/**
 * AgendaContactos es la clase principal que inicializa la aplicación.
 * 
 * @author Mauricio Cruz Portilla
 * @since 2019/04/26
 * @version 1.0
 */

package agendacontactos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AgendaContactos extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("interfaz/FXMLAgendaContactos.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Agenda de Contactos - Mauricio Cruz Portilla");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}

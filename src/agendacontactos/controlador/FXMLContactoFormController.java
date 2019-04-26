/**
 * FXMLContactoFormController es el controlador de la ventana
 * FXMLContactoForm.
 * 
 * @author Mauricio Cruz Portilla
 * @since 2019/04/26
 * @version 1.0
 */

package agendacontactos.controlador;

import agendacontactos.Contacto;
import agendacontactos.ContactoDAO;
import agendacontactos.IContactoDAO;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class FXMLContactoFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField fechaNacimientoTextField;

    @FXML
    private Button guardarButton;

    @FXML
    private TextField maternoTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField paternoTextField;

    @FXML
	private TextField telefonoTextField;
	
	private Contacto contacto;

	private IContactoDAO contactoDAO = new ContactoDAO();

    @FXML
    void initialize() {
		guardarButton.setOnAction(guardarButtonHandler());
    }
	
	/**
	 * Inicia la ventana con base en un contacto
	 * @param contacto contacto
	 */
	public void initData(Contacto contacto) {
		this.contacto = contacto;
		nombreTextField.setText(contacto.getNombre());
		paternoTextField.setText(contacto.getPaterno());
		maternoTextField.setText(contacto.getMaterno());
		telefonoTextField.setText(contacto.getTelefono());
		emailTextField.setText(contacto.getCorreo());
		fechaNacimientoTextField.setText(
			contacto.getFechaNacimiento().format(DateTimeFormatter.ofPattern("d/M/uuuu"))
		);
	}

	private EventHandler<ActionEvent> guardarButtonHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (
					nombreTextField.getText().isEmpty() ||
					paternoTextField.getText().isEmpty() ||
					telefonoTextField.getText().isEmpty() ||
					emailTextField.getText().isEmpty() ||
					fechaNacimientoTextField.getText().isEmpty()
				) {
					new Alert(AlertType.WARNING, "Debes completar los campos").show();
					return;
				}
				// Nuevo contacto
				if (contacto == null) {
					contactoDAO.insertContacto(new Contacto(
						nombreTextField.getText(), 
						paternoTextField.getText(), 
						maternoTextField.getText(),
						telefonoTextField.getText(), 
						emailTextField.getText(), 
						LocalDate.parse(
                            fechaNacimientoTextField.getText(), 
                            DateTimeFormatter.ofPattern("d/M/uuuu")
                        )
					));
					new Alert(AlertType.CONFIRMATION, "Contacto agregado").show();
				} else {
					// Editar contacto

					new Alert(AlertType.CONFIRMATION, "Contacto editado").show();
				}
				((Stage) guardarButton.getScene().getWindow()).close();
			}
		};
	}

}

package agendacontactos.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import agendacontactos.Contacto;
import agendacontactos.ContactoDAO;
import agendacontactos.IContactoDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXMLAgendaContactosController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button agregarButton;

	@FXML
	private TableColumn<Contacto, LocalDate> birthdateTableColumn;

	@FXML
	private TableView<Contacto> contactosTableView;

	@FXML
	private TableColumn<Contacto, String> correoTableColumn;

	@FXML
	private Button eliminarButton;

	@FXML
	private TableColumn<Contacto, String> maternoTableColumn;

	@FXML
	private TableColumn<Contacto, String> nombreTableColumn;

	@FXML
	private TableColumn<Contacto, String> paternoTableColumn;

	@FXML
	private TextField searchContactTextField;

	@FXML
	private Button sortAlphabeticButton;

	@FXML
	private Button sortBirthdateButton;

	@FXML
	private TableColumn<Contacto, String> telefonoTableColumn;

	private IContactoDAO contactoDAO = new ContactoDAO();

	@FXML
	void onSearchContactTextFieldKeyPress(KeyEvent event) {
	}

	@FXML
	void initialize() {
		nombreTableColumn.setCellValueFactory(
            new PropertyValueFactory<>("nombre")
		);
		paternoTableColumn.setCellValueFactory(
            new PropertyValueFactory<>("paterno")
		);
		maternoTableColumn.setCellValueFactory(
            new PropertyValueFactory<>("materno")
		);
		telefonoTableColumn.setCellValueFactory(
            new PropertyValueFactory<>("telefono")
		);
		correoTableColumn.setCellValueFactory(
            new PropertyValueFactory<>("correo")
		);
		birthdateTableColumn.setCellValueFactory(
            new PropertyValueFactory<>("fechaNacimiento")
		);
        contactosTableView.setItems(contactoDAO.getContactos());

        agregarButton.setOnAction(agregarButtonHandler());
    }
    
    private EventHandler<ActionEvent> agregarButtonHandler() {
        return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/agendacontactos/interfaz/FXMLContactoForm.fxml"
                    ));
                    Stage stage = new Stage();
                    stage.setScene(new Scene((AnchorPane)loader.load()));
                    stage.setTitle("Datos del contacto");
                    FXMLContactoFormController controller = loader.
                        <FXMLContactoFormController>getController();
                    if (contactosTableView.getSelectionModel().getSelectedItem() != null) {
                        controller.initData(contactosTableView.getSelectionModel().getSelectedItem());
                    }
                    stage.setOnHidden(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            // Recargar contactos
                            contactosTableView.setItems(contactoDAO.getContactos());
                        }
                    });
                    stage.show();
                } catch (IOException e) {
                    new Alert(AlertType.ERROR, "Ocurrió un error al realizar esta acción.").show();
                    System.out.println(e.getMessage());
                }
			}
		};
    }

}

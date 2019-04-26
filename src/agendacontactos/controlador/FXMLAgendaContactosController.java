package agendacontactos.controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import agendacontactos.Contacto;
import agendacontactos.ContactoDAO;
import agendacontactos.IContactoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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
	}

}

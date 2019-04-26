package agendacontactos.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class FXMLAgendaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agregarButton;

    @FXML
    private TableColumn<?, ?> birthdateTableColumn;

    @FXML
    private TableView<?> contactosTableView;

    @FXML
    private TableColumn<?, ?> correoTableColumn;

    @FXML
    private Button eliminarButton;

    @FXML
    private TableColumn<?, ?> maternoTableColumn;

    @FXML
    private TableColumn<?, ?> nombreTableColumn;

    @FXML
    private TableColumn<?, ?> paternoTableColumn;

    @FXML
    private TextField searchContactTextField;

    @FXML
    private Button sortAlphabeticButton;

    @FXML
    private Button sortBirthdateButton;

    @FXML
    private TableColumn<?, ?> telefonoTableColumn;


    @FXML
    void onSearchContactTextFieldKeyPress(KeyEvent event) {
    }

    @FXML
    void initialize() {
		
    }

}

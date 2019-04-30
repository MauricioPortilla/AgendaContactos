/**
 * FXMLAgendaContactosController es la clase que lleva el control de la interfaz FXMLAgendaContactos
 * 
 * @author Mauricio Cruz Portilla
 * @since 2019/04/26
 * @version 1.0
 */

package agendacontactos.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import agendacontactos.Contacto;
import agendacontactos.ContactoDAO;
import agendacontactos.IContactoDAO;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
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
	private TableColumn<Contacto, String> birthdateTableColumn;

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

	@FXML
	private Button editarButton;

	@FXML
	private Label birthdayEventsLabel;

	private IContactoDAO contactoDAO = new ContactoDAO();

	/**
	 * Filtra la tabla de contactos de acuerdo al texto introducido en el campo de texto.
	 * 
	 * @param event evento de la tecla pulsada
	 */
	@FXML
	void onSearchContactTextFieldKeyUp(KeyEvent event) {
		if (event.getCode().isFunctionKey() && event.getCode() != KeyCode.BACK_SPACE) {
			return;
		}
		reloadContactosTable();
		if (searchContactTextField.getText().isEmpty()) {
			return;
		} else {
			String nameToFind = searchContactTextField.getText();
			ArrayList<Contacto> contactosToRemove = new ArrayList<>();
			for (Contacto contacto : contactosTableView.getItems()) {
				if (!contacto.getNombre().toLowerCase().startsWith(nameToFind.toLowerCase())) {
					contactosToRemove.add(contacto);
				}
			}
			for (Contacto contacto : contactosToRemove) {
				contactosTableView.getItems().remove(contacto);
			}
		}
	}

	/**
	 * Inicializa la ventana.
	 */
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
		editarButton.setOnAction(editarButtonHandler());
		eliminarButton.setOnAction(eliminarButtonHandler());
		sortAlphabeticButton.setOnAction(sortAlphabeticButtonHandler());
		sortAlphabeticButton.fire();
		sortBirthdateButton.setOnAction(sortBirthdateButtonHandler());

		loadBirthdayEvents();
    }
	
	/**
	 * Abre la ventana que permite agregar un nuevo contacto.
	 * 
	 * @return el evento
	 */
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
	
	/**
	 * Abre la ventana que permite editar los datos del contacto seleccionado de la tabla.
	 * 
	 * @return el evento
	 */
	private EventHandler<ActionEvent> editarButtonHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (contactosTableView.getSelectionModel().getSelectedItem() == null) {
					new Alert(AlertType.WARNING, "Debes elegir un contacto de la lista.").show();
					return;
				}
				try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/agendacontactos/interfaz/FXMLContactoForm.fxml"
                    ));
                    Stage stage = new Stage();
                    stage.setScene(new Scene((AnchorPane)loader.load()));
                    stage.setTitle("Datos del contacto");
                    FXMLContactoFormController controller = loader.
						<FXMLContactoFormController>getController();
					controller.initData(contactosTableView.getSelectionModel().getSelectedItem());
                    stage.setOnHidden(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            reloadContactosTable();
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

	/**
	 * Elimina un contacto seleccionado de la tabla.
	 * 
	 * @return el evento
	 */
	private EventHandler<ActionEvent> eliminarButtonHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (contactosTableView.getSelectionModel().getSelectedItem() == null) {
					new Alert(AlertType.WARNING, "Debes elegir un contacto de la lista.").show();
					return;
				}
				Alert deleteAlert = new Alert(
					AlertType.CONFIRMATION, "¿Deseas eliminar este contacto?"
				);
				if (deleteAlert.showAndWait().get() == ButtonType.OK) {
					contactoDAO.deleteContacto(
						contactosTableView.getSelectionModel().getSelectedItem()
					);
					new Alert(AlertType.INFORMATION, "Contacto eliminado.").show();
					reloadContactosTable();
				}
			}
		};
	}

	/**
	 * Ordena los contactos de la tabla por nombre de forma ascendente.
	 * 
	 * @return el evento
	 */
	private EventHandler<ActionEvent> sortAlphabeticButtonHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				contactosTableView.getSortOrder().clear();
				contactosTableView.getSortOrder().add(nombreTableColumn);
				contactosTableView.getSortOrder().add(paternoTableColumn);
				contactosTableView.getSortOrder().add(maternoTableColumn);
			}
		};
	}

	/**
	 * Ordena los contactos de la tabla por fecha de nacimiento de forma descendente.
	 * 
	 * @return el evento
	 */
	private EventHandler<ActionEvent> sortBirthdateButtonHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				contactosTableView.getSortOrder().clear();
				contactosTableView.getSortOrder().add(birthdateTableColumn);
			}
		};
	}

	/**
	 * Recarga los contactos de la tabla.
	 */
	private void reloadContactosTable() {
		contactosTableView.setItems(contactoDAO.getContactos());
		sortAlphabeticButton.fire();
		loadBirthdayEvents();
	}

	/**
	 * Carga y muestra los eventos proximos (cumpleanios) de los contactos.
	 */
	private void loadBirthdayEvents() {
		ArrayList<Contacto> contactosSorted = new ArrayList<>();
		for (Contacto contacto : contactosTableView.getItems()) {
			if (contactosSorted.size() == 3) {
				break;
			}
			LocalDate fecha = contacto.getNaturalFechaNacimiento()
				.withYear(LocalDate.now().getYear());
			if (fecha.isAfter(LocalDate.now()) || fecha.equals(LocalDate.now())) {
				contactosSorted.add(contacto);
			}
		}
		contactosSorted.sort(new Comparator<Contacto>() {
			@Override
			public int compare(Contacto contacto1, Contacto contacto2) {
				LocalDate fechaContacto1 = contacto1.getNaturalFechaNacimiento()
					.withYear(LocalDate.now().getYear());
				LocalDate fechaContacto2 = contacto2.getNaturalFechaNacimiento()
					.withYear(LocalDate.now().getYear());
				return fechaContacto1.isBefore(fechaContacto2) ? -1 : 
					fechaContacto1.equals(fechaContacto2) ? 0 : 1;
			}
		});
		if (contactosSorted.isEmpty()) {
			birthdayEventsLabel.setText("No hay eventos próximos.");
			return;
		}
		String birthdayEventsString = "";
		if (contactosSorted.get(0).getNaturalFechaNacimiento().equals(LocalDate.now())) {
			birthdayEventsString += "¡Hoy es el cumpleaños de " + 
				contactosSorted.get(0).getNombre() + " " + 
				contactosSorted.get(0).getPaterno() + "!\n\n";
		}
		birthdayEventsString += "Próximos cumpleaños:";
		for (int i = 0; i < contactosSorted.size(); i++) {
			birthdayEventsString += "\n\t" + contactosSorted.get(i).getNombre() + " " + 
				contactosSorted.get(i).getPaterno() + " - " + 
				contactosSorted.get(i).getNaturalFechaNacimiento().withYear(
					LocalDate.now().getYear()
				).format(
					DateTimeFormatter.ofPattern("dd 'de' MMMM", Locale.forLanguageTag("es-ES"))
				);
		}
		birthdayEventsLabel.setText(birthdayEventsString);
		try {
			birthdayEventsLabel.getScene().getWindow().sizeToScene();
		} catch (NullPointerException e) {
			System.out.println("Error al redimensionar la ventana.");
		}
	}

}

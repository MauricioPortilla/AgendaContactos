/**
 * ContactoDAO es la clase que implementa la interfaz IContactoDAO
 * 
 * @author Mauricio Cruz Portilla
 * @since 2019/04/25
 * @version 1.0
 */
package agendacontactos;

import java.sql.Date;

import agendacontactos.engine.SQL;
import agendacontactos.engine.SQLRow;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactoDAO implements IContactoDAO {
    private ObservableList<Contacto> contactos;

    public ContactoDAO() {
        contactos = FXCollections.observableArrayList();
    }

    /**
     * Carga los contactos de la base de datos.
     */
    private void loadContactos() {
        SQL.executeQuery("SELECT * FROM contacto;", null, (result) -> {
            for (SQLRow row : result) {
                contactos.add(
                    new Contacto(
                        row.getColumnData("nombre").toString(),
                        row.getColumnData("paterno").toString(),
                        row.getColumnData("materno").toString(),
                        row.getColumnData("telefono").toString(),
                        row.getColumnData("correo").toString(),
                        ((Date) row.getColumnData("fechaNacimiento")).toLocalDate()
                    )
                );
            }
			return true;
        });
    }

    @Override
    public Contacto insertContacto(Contacto contacto) {
        if (contacto == null) {
            return contacto;
        }
        SQL.executeUpdate(
            "INSERT INTO contacto VALUES (NULL, ?, ?, ?, ?, ?, ?)",
            new ArrayList<Object>() {
                {
                    add(contacto.getNombre());
                    add(contacto.getPaterno());
                    add(contacto.getMaterno());
                    add(contacto.getTelefono());
                    add(contacto.getCorreo());
                    add(contacto.getFechaNacimiento());
                }
            }
        );
        return contacto;
    }

    @Override
    public Contacto deleteContacto(Contacto contacto) {
        if (contacto == null) {
            return contacto;
        }
        SQL.executeUpdate("DELETE FROM contacto WHERE idcontacto = ?", 
            new ArrayList<Object>() {
                {
                    add(contacto.getId());
                }
            }
        );
        return contacto;
    }

    @Override
    public ObservableList<Contacto> getContactos() {
        loadContactos();
        return contactos;
    }
}

/**
 * IContactoDAO es la interfaz que lleva a cabo las acciones
 * del contacto en la base de datos.
 * 
 * @author Mauricio Cruz Portilla
 * @since 219/04/25
 * @version 1.0
 */
package agendacontactos;

import javafx.collections.ObservableList;

public interface IContactoDAO {
    /**
     * Inserta el contacto en la base de datos.
     * 
     * @param contacto contacto a agregar
     * @return contacto agregado
     */
    Contacto insertContacto(Contacto contacto);

    /**
     * Modifica el contacto en la base de datos.
     * 
     * @param contacto contacto a modificar
     * @return contacto modificado
     */
    Contacto updateContacto(Contacto contacto);

    /**
     * Elimina el contacto de la base de datos.
     * 
     * @param contacto contacto a eliminar
     * @return contacto eliminado
     */
    Contacto deleteContacto(Contacto contacto);

    /**
     * Retorna una lista observable con los contactos recuperados
     * de la base de datos.
     * 
     * @return lista observable de contactos
     */
    ObservableList<Contacto> getContactos();
}

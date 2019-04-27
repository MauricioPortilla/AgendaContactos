/**
 * Contacto es la clase que define la informacion de un contacto
 * de la agenda.
 * 
 * @author Mauricio Cruz Portilla
 * @since 25/04/2019
 */
package agendacontactos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contacto {
    private int id;
    private String nombre;
    private String paterno;
    private String materno;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;

    /**
     * Crea un contacto nuevo.
     * 
     * @param id identificador del contacto
     * @param nombre nombre del contacto
     * @param paterno paterno del contacto
     * @param materno materno del contacto
     * @param telefono telefono del contacto
     * @param correo correo del contacto
     * @param fechaNacimiento fecha de nacimiento del contacto
     */
    public Contacto(
        int id, String nombre, String paterno, String materno, String telefono,
        String correo, LocalDate fechaNacimiento
    ) {
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Asigna un valor al nombre del contacto.
     * 
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna un valor al apellido paterno del contacto.
     * 
     * @param paterno nuevo apellido paterno
     */
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    /**
     * Asigna un valor al apellido materno del contacto.
     * 
     * @param materno nuevo apellido materno
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * Asigna un valor al telefono del contacto.
     * 
     * @param telefono nuevo telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Asigna un valor al correo del contacto.
     * 
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Asigna un valor a la fecha de nacimiento del contacto.
     * 
     * @param fechaNacimiento nueva fecha de nacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el identificador del contacto.
     * 
     * @return identificador del contacto
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el nombre del contacto.
     * 
     * @return nombre del contacto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el apellido paterno del contacto.
     * 
     * @return apellido paterno del contacto
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * Retorna el apellido materno del contacto.
     * 
     * @return apellido materno del contacto
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * Retorna el telefono del contacto.
     * 
     * @return telefono del contacto
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * Retorna el correo del contacto.
     * 
     * @return correo del contacto
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Retorna la fecha de nacimiento del contacto.
     * 
     * @return fecha de nacimiento del contacto
     */
    public String getFechaNacimiento() {
        return fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }

    /**
     * Retorna la fecha de nacimiento del contacto en su tipo LocalDate.
     * 
     * @return fecha de nacimiento del contacto
     */
    public LocalDate getNaturalFechaNacimiento() {
        return fechaNacimiento;
    }
}

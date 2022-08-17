
package comercialeliasib.entidadesdenegocio;

import java.time.LocalDate;




public class Empleado {
    private int id;
    private String nombre;
    private String apellidos;
    private String contacto;
    private LocalDate fecha;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellidos, String contacto, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contacto = contacto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
}


package comercialeliasib.entidadesdenegocio;


public class Categoria {
    
    
    private int Id;
    private String Nombre;  

    public Categoria() {
    }

    public Categoria(int Id, String Nombre) {
        this.Id = Id;
        this.Nombre = Nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    
    
}


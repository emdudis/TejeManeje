package Modelo;


public class Usuario {
    
    private int codUsu;
    private String nomUsu;
    private String apellido1;
    private String apellido2;
    private String domicilio;
    private String poblacion;
    private String provincia;
    private String codigoPostal;
    private String perfil;
    private String correo;
    private String claveUsu;
    private String telefono;
    
    
    
    public Usuario(int codUsu, String nomUsu, String apellido1, String apellido2, String domicilio, String poblacion, 
            String provincia, String codigoPostal, String perfil, String correo, String claveUsu, String telefono) {
        this.codUsu = codUsu;
        this.nomUsu = nomUsu;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.domicilio = domicilio;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.perfil = perfil;
        this.correo = correo;
        this.claveUsu = claveUsu;
        this.telefono = telefono;
    }

    public Usuario(String nomUsu, String apellido1, String apellido2, String domicilio, String poblacion, 
            String provincia, String codigoPostal, String correo, String claveUsu, String telefono) {
        this.nomUsu = nomUsu;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.domicilio = domicilio;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.correo = correo;
        this.claveUsu = claveUsu;
        this.telefono = telefono;
    }

    
    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClaveUsu() {
        return claveUsu;
    }

    public void setClaveUsu(String claveUsu) {
        this.claveUsu = claveUsu;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
    
    
}
package com.mypoo2.ejercicio_amigos_act5;

/**
 * Modelo que representa un contacto de la agenda.
 *
 * @author HP-255-G10
 */
public class Contacto {

    private String nombre;
    private String telefono;
    private String correo;

    public Contacto() {
    }

    public Contacto(String nombre, String telefono, String correo) {
        setNombre(nombre);
        setTelefono(telefono);
        setCorreo(correo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return nombre + "!" + telefono + "!" + correo;
    }

}

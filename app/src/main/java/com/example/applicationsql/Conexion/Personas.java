package com.example.applicationsql.Conexion;

public class Personas {


    int id;
    String nombres;
    String apellidos;
    int edad;
    String correo;
    String direccion;
    public Personas(int id, String correo, String direccion, int edad, String apellidos, String nombres) {
        this.id = id;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.edad = edad;
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }







}

package com.example.proyectofinal;

public class ListElement {

    public String ticket;
    public String nombre;
    public String status;

    public ListElement(String ticket, String nombre, String status) {
        this.ticket = ticket;
        this.nombre = nombre;
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

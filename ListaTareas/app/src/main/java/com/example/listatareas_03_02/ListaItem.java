package com.example.listatareas_03_02;

public class ListaItem {
    private int rowid;
    private String nombre;
    private String lugar;
    private String descripcion;
    private int importancia;

    public ListaItem(int rowid, String nombre, String lugar, String descripcion, int importancia) {
        this.rowid = rowid;
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.importancia = importancia;
    }
    public ListaItem(){ }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }
}

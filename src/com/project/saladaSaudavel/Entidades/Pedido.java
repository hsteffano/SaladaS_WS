package com.project.saladaSaudavel.Entidades;

public class Pedido {
    int idPedido,idPrato,idCliente;
    String descr,data;

    public Pedido() {
    }

    public Pedido(int idPedido, int idPrato, int idCliente, String descr, String data) {
        this.idPedido = idPedido;
        this.idPrato = idPrato;
        this.idCliente = idCliente;
        this.descr = descr;
        this.data = data;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

package com.project.saladaSaudavel.DAOs;

import com.project.saladaSaudavel.ConectaMySql;
import com.project.saladaSaudavel.Entidades.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {

    public boolean inserirPedido(Pedido pedido){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "INSERT INTO pedido VALUES (0, ?, ?, ?, 0)";

            PreparedStatement ppStm = conn.prepareStatement(queryInserir);

            ppStm.setString(1, pedido.getDescr());
            ppStm.setInt(2, pedido.getIdPrato());
            ppStm.setInt(3, pedido.getIdCliente());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean inserirPedidoOpcionais(int idPedido, int[] idOpcionais){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "INSERT INTO pedidoOpcionais VALUES (?, ?)";

            for(int i = 0;i<idOpcionais.length;i++){
            PreparedStatement ppStm = conn.prepareStatement(queryInserir);
            ppStm.setInt(1, idPedido);
            ppStm.setInt(2, idOpcionais[i]);
            ppStm.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    	
    }
    public int recuperaMaiorId(int id){

        int idPedido = 0;

        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT idPedido FROM pedido WHERE idCliente = ? ORDER BY data DESC LIMIT 1";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setInt(1, id);

            ResultSet rSet = ppStm.executeQuery();

            idPedido = rSet.getInt(1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPedido;
    }

    public Pedido recuperaPorId(int id){

        Pedido pedido = null;

        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM pedido WHERE idPedido = ?";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setInt(1, id);

            ResultSet rSet = ppStm.executeQuery();

            if(rSet.next()){
                pedido = new Pedido();
                pedido.setIdPedido(rSet.getInt(1));
                pedido.setDescr(rSet.getString(2));
                pedido.setIdCliente(rSet.getInt(3));
                pedido.setIdPrato(rSet.getInt(4));
                pedido.setData(rSet.getString(5));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedido;
    }
    public static ArrayList<Pedido> recuperaPorCliente(int idCliente){

        ArrayList<Pedido> lista = new ArrayList<Pedido>();
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM pedido WHERE idCliente = ?";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setInt(1, idCliente);

            ResultSet rSet = ppStm.executeQuery();

            while(rSet.next()){
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rSet.getInt(1));
                pedido.setIdCliente(rSet.getInt(2));
                pedido.setIdPrato(rSet.getInt(3));
                pedido.setDescr(rSet.getString(4));
                pedido.setData(rSet.getString(5));

                lista.add(pedido);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

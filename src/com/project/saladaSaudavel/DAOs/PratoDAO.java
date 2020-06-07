package com.project.saladaSaudavel.DAOs;

import com.project.saladaSaudavel.ConectaMySql;
import com.project.saladaSaudavel.Entidades.Prato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PratoDAO {
	
	public static boolean inserirPrato(Prato prato){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "INSERT INTO prato VALUES (null, ?, ?, ?)";

            PreparedStatement ppStm = conn.prepareStatement(queryInserir);

            ppStm.setString(1,prato.getNome());
            ppStm.setString(2,prato.getValor());
            ppStm.setString(3, prato.getIngredientes());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }	public static boolean excluirPrato(Prato prato){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryExcluir = "DELETE * FROM prato WHERE idPrato=?";

            PreparedStatement ppStm = conn.prepareStatement(queryExcluir);

            ppStm.setInt(1,prato.getId());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ArrayList<Prato> recuperaPrato(){

        ArrayList<Prato> lista = new ArrayList<Prato>();
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM prato";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);

            ResultSet rSet = ppStm.executeQuery();

            while(rSet.next()){
                Prato prato = new Prato();
                prato.setId(rSet.getInt(1));
                prato.setNome(rSet.getString(2));
                prato.setValor(rSet.getString(3));
                prato.setIngredientes(rSet.getString(4));

                lista.add(prato);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

     return lista;
    }

    public Prato recuperaPorId(int id){

        Prato prato = null;

        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM prato WHERE idPrato = ?";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setInt(1, id);

            ResultSet rSet = ppStm.executeQuery();

            if(rSet.next()){
                prato = new Prato();
                prato.setId(rSet.getInt(1));
                prato.setNome(rSet.getString(2));
                prato.setValor(rSet.getString(3));
                prato.setIngredientes(rSet.getString(4));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prato;
    }
}

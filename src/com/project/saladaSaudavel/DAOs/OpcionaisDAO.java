package com.project.saladaSaudavel.DAOs;

import com.project.saladaSaudavel.ConectaMySql;
import com.project.saladaSaudavel.Entidades.Opcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OpcionaisDAO {
	
	public static boolean inserirOpcionais(Opcionais opcionais){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "INSERT INTO opcionais VALUES (null, ?, ?)";

            PreparedStatement ppStm = conn.prepareStatement(queryInserir);

            ppStm.setString(1,opcionais.getNome());
            ppStm.setString(2,opcionais.getValor());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }	
	public static boolean excluirOpcionais(Opcionais opcionais){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryExcluir = "DELETE * FROM opcionais WHERE idOpcionais=?";

            PreparedStatement ppStm = conn.prepareStatement(queryExcluir);

            ppStm.setInt(1,opcionais.getId());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ArrayList<Opcionais> recuperaOpcionais(){

        ArrayList<Opcionais> lista = new ArrayList<Opcionais>();
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM opcionais";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);

            ResultSet rSet = ppStm.executeQuery();

            while(rSet.next()){
                Opcionais opcionais = new Opcionais();
                opcionais.setId(rSet.getInt(1));
                opcionais.setNome(rSet.getString(2));
                opcionais.setValor(rSet.getString(3));

                lista.add(opcionais);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public Opcionais recuperaPorId(int id){

    	Opcionais opcional = null;

        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM opcionais WHERE idOpcionais= ?";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setInt(1, id);

            ResultSet rSet = ppStm.executeQuery();

            if(rSet.next()){
            	opcional = new Opcionais();
            	opcional.setId(rSet.getInt(1));
            	opcional.setNome(rSet.getString(2));
            	opcional.setValor(rSet.getString(3));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return opcional;
    }
}

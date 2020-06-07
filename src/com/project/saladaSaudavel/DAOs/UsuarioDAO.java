package com.project.saladaSaudavel.DAOs;

import com.project.saladaSaudavel.ConectaMySql;
import com.project.saladaSaudavel.Entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static boolean inserirUsuario(Usuario usuario){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryInserir = "INSERT INTO usuario VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ppStm = conn.prepareStatement(queryInserir);

            ppStm.setString(1,usuario.getNome());
            ppStm.setString(2,usuario.getSobrenome());
            ppStm.setString(3, usuario.getEndereco());
            ppStm.setString(4, usuario.getCidade());
            ppStm.setString(5, usuario.getCep());
            ppStm.setInt(6, usuario.getTelefone());
            ppStm.setString(7, usuario.getTipo());
            ppStm.setString(8,usuario.getLogin());
            ppStm.setString(9,usuario.getSenha());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean excluirUsuario(Usuario usuario){
        try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryExcluir = "DELETE * FROM usuario WHERE idCliente=?";

            PreparedStatement ppStm = conn.prepareStatement(queryExcluir);

            ppStm.setInt(1,usuario.getId());

            ppStm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Usuario login(String login){

        Usuario usuario = null;

        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setString(1, login);

            ResultSet rSet = ppStm.executeQuery();

            if(rSet.next()){
                usuario = new Usuario();
                usuario.setId(rSet.getInt(1));
                usuario.setNome(rSet.getString(2));
                usuario.setSobrenome(rSet.getString(3));
                usuario.setEndereco(rSet.getString(4));
                usuario.setCidade(rSet.getString(5));
                usuario.setCep(rSet.getString(6));
                usuario.setTelefone(rSet.getInt(7));
                usuario.setTipo(rSet.getString(8));
                usuario.setLogin(rSet.getString(9));
                usuario.setSenha(rSet.getString(10));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    public Usuario recuperaPorId(int id){

        Usuario usuario = null;

        try {
            Connection conn = ConectaMySql.obtemConexao();
            String querySelect = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ppStm = conn.prepareStatement(querySelect);
            ppStm.setInt(1, id);

            ResultSet rSet = ppStm.executeQuery();

            if(rSet.next()){
                usuario = new Usuario();
                usuario.setId(rSet.getInt(1));
                usuario.setNome(rSet.getString(2));
                usuario.setSobrenome(rSet.getString(3));
                usuario.setEndereco(rSet.getString(4));
                usuario.setCidade(rSet.getString(5));
                usuario.setCep(rSet.getString(6));
                usuario.setTelefone(rSet.getInt(7));
                usuario.setTipo(rSet.getString(8));
                usuario.setLogin(rSet.getString(9));
                usuario.setSenha(rSet.getString(10));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    public boolean atualizarSenha(Usuario usuario){
    	try {
            Connection conn = ConectaMySql.obtemConexao();
            String queryUpdate = "UPDATE usuario SET senha = ? WHERE login = ?";
            PreparedStatement ppStm = conn.prepareStatement(queryUpdate);
            
            ppStm.setString(1, usuario.getSenha());
            ppStm.setString(1, usuario.getLogin());

            ppStm.executeUpdate();


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoloja.repository;

import br.com.projetoloja.config.Conex;
import br.com.projetoloja.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristiano
 */
public class UsuarioDAO {
    Connection conn;

    public UsuarioDAO() {
        conn = Conex.getConection();
    }
    
    public List<Usuario> buscar(String ema, String senha){
       List<Usuario> listAll  = new ArrayList<Usuario>();
        try {
            
            String sql ="Select * from usuarios where email = ? and senha =?";
            
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,ema);
            st.setString(2, senha);
            ResultSet rs  = st.executeQuery();
            
            while(rs.next())
                {
                    Usuario obj = new Usuario();
                    obj.setNome(rs.getString("nome"));
                    obj.setEmail(rs.getString("email"));
                    obj.setSenha(rs.getString("senha"));
                    listAll.add(obj);
                }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
         return listAll;
    }
    
    public Usuario validarLgin(String ema, String senha)
        {
              Usuario obj  = null;
        try {
            
            String sql ="Select * from usuarios where email = ? and senha =?";
            
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,ema);
            st.setString(2, senha);
            ResultSet rs  = st.executeQuery();
            
            while(rs.next())
                {
                     obj = new Usuario();
                    obj.setNome(rs.getString("nome"));
                    obj.setEmail(rs.getString("email"));
                    obj.setSenha(rs.getString("senha"));
                   
                }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
         return obj;
        }
}

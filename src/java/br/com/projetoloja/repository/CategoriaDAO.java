/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoloja.repository;

import br.com.projetoloja.config.Conex;
import br.com.projetoloja.model.Categoria;
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
public class CategoriaDAO {
  private  Connection conn = null;
    
    public CategoriaDAO() {
        
        conn = Conex.getConection();
        
    }
    
    public List<Categoria> listar()
        {
         List<Categoria> lista = new ArrayList<Categoria>();
            try {
                String sql = "Select * from categorias";
                Statement st = this.conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next())
                    {
                        Categoria obj = new Categoria();
                        obj.setIdcategorias(rs.getInt("idcategorias"));
                        obj.setNome(rs.getString("nome"));
                        obj.setDescricao(rs.getString("descricao"));
                        obj.setAtivo(rs.getString("ativo"));
                        lista.add(obj);
                    }
                
                rs.close();
                st.close();
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
          return lista;
        }
    
    /*Metodo para ser usado com BEAM*/
    public List<Categoria> getCategoria()
        {
            return listar();
        }

    public int cadastrar(Categoria cat) {
       
        String sql = "insert into categorias(nome,descricao,ativo)values(?,?,?)";
        int res = 0;
      try {
          PreparedStatement st = this.conn.prepareStatement(sql);
          st.setString(1, cat.getNome());
          st.setString(2, cat.getDescricao());
          st.setString(3, cat.getAtivo());
          
          res = st.executeUpdate();
          st.close();
          this.conn.close();
          
      } catch (SQLException ex) {
          Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return res;
    }
    
    public int deleteCat(int id)
        {
                    int rs = 0;
              try {
                  String sql = "delete from categorias where idcategorias = ?";
                  PreparedStatement st = conn.prepareStatement(sql);
                  st.setInt(1, id);
                   rs =  st.executeUpdate();
                   st.close();
                  conn.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }

           return rs;   
        }
    public int updateCat(Categoria cat)
        {
            int res = 0;
            try {
                String sql = "UPDATE categorias set nome = ? , descricao  = ? , ativo = ? where idcategorias=?";

                PreparedStatement st = conn.prepareStatement(sql);

                st.setString(1, cat.getNome());
                st.setString(2, cat.getDescricao());
                st.setString(3, cat.getAtivo());
                st.setInt(4, cat.getIdcategorias());
              res=   st.executeUpdate();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return res;
        }
    
    public Categoria buscarCatId( int id)
        {
            Categoria obj = new Categoria();
            try {
                String sql = "Select * from categorias where idcategorias = ?";
                PreparedStatement st = this.conn.prepareStatement(sql);
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                
                while(rs.next())
                    {
                        
                        obj.setIdcategorias(rs.getInt("idcategorias"));
                        obj.setNome(rs.getString("nome"));
                        obj.setDescricao(rs.getString("descricao"));
                        obj.setAtivo(rs.getString("ativo"));
                        
                    }
                
                rs.close();
                st.close();
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
          return obj;
        }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoloja.repository;

import br.com.projetoloja.config.Conex;
import br.com.projetoloja.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristiano
 */
public class ProdutoDAO {
    Connection conn;

    public ProdutoDAO() {
        
        conn = Conex.getConection();
    }
    
    public Produto buscarId(String idP)
        {
            Produto obj = null;
            int id = Integer.parseInt(idP);
             try {

                    String sql= "select p.* , c.nome as nomeCat from produtos p "
                            + "inner join categorias c on c.idcategorias = p.categorias_idcategorias "
                            + " where p.idprodutos = ?";

                   PreparedStatement st = conn.prepareStatement(sql);
                   st.setInt(1, id);
                    ResultSet rs = st.executeQuery();

                    while(rs.next())
                        {
                             obj = new Produto();
                            obj.setIdprodutos(rs.getInt("idprodutos"));
                            obj.setNome(rs.getString("nome"));
                            obj.setDescricao(rs.getString("descricao"));
                            obj.setMaisinfo(rs.getString("maisinfo"));
                            obj.setDestaque(rs.getString("destaque"));
                            obj.setPeso(rs.getDouble("peso"));
                            obj.setValor(rs.getDouble("valor"));
                            obj.setAtivo(rs.getString("ativo"));
                            obj.setCategorias_idcategorias(rs.getInt("categorias_idcategorias"));
                            obj.setNomeCat(rs.getString("nomeCat"));
                      
                }
            
                rs.close();
                st.close();
                conn.close();

            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
             return obj;
        }
    public List<Produto> lista()
        {
            List<Produto> listAll = new ArrayList<Produto>();
        try {
            
            String sql= "select * from produtos";
            
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
                {
                    Produto obj = new Produto();
                    obj.setIdprodutos(rs.getInt("idprodutos"));
                    obj.setNome(rs.getString("nome"));
                    obj.setDescricao(rs.getString("descricao"));
                    obj.setMaisinfo(rs.getString("maisinfo"));
                    obj.setDestaque(rs.getString("destaque"));
                    obj.setPeso(rs.getDouble("peso"));
                    obj.setValor(rs.getDouble("valor"));
                    obj.setAtivo(rs.getString("ativo"));
                    obj.setCategorias_idcategorias(rs.getInt("categorias_idcategorias"));
                    
                    listAll.add(obj);
                }
            
                rs.close();
                st.close();
                conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
        
        }
    public List<Produto> buscarDestaques(String filtro)
        {
            List<Produto> listAll = new ArrayList<Produto>();
        try {
            
            String sql= "select p.* , c.nome as nomeCat from produtos p "
                    + "inner join categorias c on c.idcategorias = p.categorias_idcategorias "
                    + " where "+filtro;
            
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
                {
                    Produto obj = new Produto();
                    obj.setIdprodutos(rs.getInt("idprodutos"));
                    obj.setNome(rs.getString("nome"));
                    obj.setDescricao(rs.getString("descricao"));
                    obj.setMaisinfo(rs.getString("maisinfo"));
                    obj.setDestaque(rs.getString("destaque"));
                    obj.setPeso(rs.getDouble("peso"));
                    obj.setValor(rs.getDouble("valor"));
                    obj.setAtivo(rs.getString("ativo"));
                    obj.setCategorias_idcategorias(rs.getInt("categorias_idcategorias"));
                    obj.setNomeCat(rs.getString("nomeCat"));
                    
                    listAll.add(obj);
                }
            
                rs.close();
                st.close();
                conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAll;
        
        }
    public List<Produto> getProdutos()
        {
            return lista();
        }
    public List<Produto> getDestaques()
        {
            return buscarDestaques("destaque='s'");
        }
    
    public int insertProduto(Produto prod)
        {
            int res=0;
        try {
            String sql = "INSERT INTO PRODUTOS (nome,descricao,maisinfo,destaque,peso,valor,ativo,categorias_idcategorias)"
                    +"values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setString(1, prod.getNome());
            st.setString(2, prod.getDescricao());
            st.setString(3, prod.getMaisinfo());
            st.setString(4, prod.getDestaque());
            st.setDouble(5, prod.getPeso());
            st.setDouble(6, prod.getValor());
            st.setString(7, prod.getAtivo());
            st.setInt(8, prod.getCategorias_idcategorias());
            
           res= st.executeUpdate();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;    
    }
    
    public void uptadeProduto(Produto prod)
        {
             try {
                  String sql = "update produtos set nome=?, descricao=?, maisinfo=?,destaque=?, peso=?, valor=?, ativo=?, categorias_idcategorias=? where idprodutos=?";
                        PreparedStatement st = conn.prepareStatement(sql);

                        st.setString(1, prod.getNome());
                        st.setString(2, prod.getDescricao());
                        st.setString(3, prod.getMaisinfo());
                        st.setString(4, prod.getDestaque());
                        st.setDouble(5, prod.getPeso());
                        st.setDouble(6, prod.getValor());
                        st.setString(7, prod.getAtivo());
                        st.setInt(   8, prod.getCategorias_idcategorias());
                        st.setInt(   9, prod.getIdprodutos());

                        st.executeUpdate();
                        st.close();
                        conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
                 JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            
     }
    
     public void deleteProd(int id)
        {
                    int rs = 0;
              try {
                  String sql = "delete from produtos where idprodutos = ?";
                  PreparedStatement st = conn.prepareStatement(sql);
                  st.setInt(1, id);
                   rs =  st.executeUpdate();
                   st.close();
                  conn.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }

              
        }
     
     public List<Produto> listaPorCat(String  idCat)
        {
             List<Produto> lista = new ArrayList<Produto>();
             Produto obj = null;
             int id=  Integer.parseInt(idCat);
        try {
            String sql="select * from produtos where categorias_idcategorias=?";
            PreparedStatement st  = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next())
                {
                    obj = new Produto();
                    obj.setIdprodutos(rs.getInt("idprodutos"));
                    obj.setNome(rs.getString("nome"));
                    obj.setDescricao(rs.getString("descricao"));
                    obj.setMaisinfo(rs.getString("maisinfo"));
                    obj.setDestaque(rs.getString("destaque"));
                    obj.setPeso(rs.getDouble("peso"));
                    obj.setValor(rs.getDouble("valor"));
                    obj.setAtivo(rs.getString("ativo"));
                    obj.setCategorias_idcategorias(rs.getInt("categorias_idcategorias"));
                    
                    
                    lista.add(obj);
                }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
             return lista;
        }
}

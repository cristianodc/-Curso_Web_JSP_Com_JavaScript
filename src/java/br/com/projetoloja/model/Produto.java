/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoloja.model;

/**
 *
 * @author Cristiano
 */
public class Produto {
   
    private int idprodutos;
    private String nome;
    private String descricao;
    private int categorias_idcategorias;
    private String maisinfo;
    private double peso;
    private double valor;
    private String destaque;
    private String ativo;
    private String nomeCat;
    
    public Produto() {
    }

    /**
     * @return the idprodutos
     */
    public int getIdprodutos() {
        return idprodutos;
    }

    /**
     * @param idprodutos the idprodutos to set
     */
    public void setIdprodutos(int idprodutos) {
        this.idprodutos = idprodutos;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the categorias_idcategorias
     */
    public int getCategorias_idcategorias() {
        return categorias_idcategorias;
    }

    /**
     * @param categorias_idcategorias the categorias_idcategorias to set
     */
    public void setCategorias_idcategorias(int categorias_idcategorias) {
        this.categorias_idcategorias = categorias_idcategorias;
    }

    /**
     * @return the maisinfo
     */
    public String getMaisinfo() {
        return maisinfo;
    }

    /**
     * @param maisinfo the maisinfo to set
     */
    public void setMaisinfo(String maisinfo) {
        this.maisinfo = maisinfo;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the destaque
     */
    public String getDestaque() {
        return destaque;
    }

    /**
     * @param destaque the destaque to set
     */
    public void setDestaque(String destaque) {
        this.destaque = destaque;
    }

    /**
     * @return the ativo
     */
    public String getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the nomeCat
     */
    public String getNomeCat() {
        return nomeCat;
    }

    /**
     * @param nomeCat the nomeCat to set
     */
    public void setNomeCat(String nomeCat) {
        this.nomeCat = nomeCat;
    }
    
    
    
}

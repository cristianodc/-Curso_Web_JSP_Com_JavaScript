/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoloja.model;

/**
 *
 * @author Cristiano
 */
public class Categoria {
    
    private int idcategorias;
    private String nome;
    private String descricao;
    private String ativo;

    public Categoria() {
    }

    /**
     * @return the idcategorias
     */
    public int getIdcategorias() {
        return idcategorias;
    }

    /**
     * @param idcategorias the idcategorias to set
     */
    public void setIdcategorias(int idcategorias) {
        this.idcategorias = idcategorias;
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
    
    
}

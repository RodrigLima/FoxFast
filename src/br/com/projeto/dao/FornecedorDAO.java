/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class FornecedorDAO {
    private Connection conexao;

    public FornecedorDAO() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    public void cadastrarFornecedores(Fornecedor obj){
        try {
            String sql = "insert into tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCpe());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
    }
    
    public void alterarFornecedor(Fornecedor obj){
        try {
            String sql = "update tb_fornecedores set nome = ?,  cnpj = ?, email = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ? where id = ? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCpe());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor alterado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    
    public void excluirFornecedor(Fornecedor obj){
        try {
            String sql = "delete from tb_fornecedores where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    
    public List<Fornecedor>listarFornecedores(){
        try {
            
            List<Fornecedor> lista = new ArrayList<>();
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
        return null;
    }
    
    public Fornecedor buscarFornecedor(String nome){
        try {
            String sql = "select * from tb_fornecedores where nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Fornecedor obj = new Fornecedor();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));               
            }
            return obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
        return null;
    }
 }

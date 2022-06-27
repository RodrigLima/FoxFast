/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Cliente;
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
public class ClienteDAO {
    private Connection conexao;
    
    public ClienteDAO(){
        this.conexao = new ConexaoBanco().pegarConexao();
 }
    
    public void cadastrarClientes(Cliente obj){
        try {
            String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCpe());
            stmt.setString(8, obj.getEndereco());
            stmt.setString(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
    }
    
    public void alterarCliente(Cliente obj){
        try {
            String sql = "update tb_clientes set nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ? where id = ? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCpe());
            stmt.setString(8, obj.getEndereco());
            stmt.setString(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            stmt.setInt(14, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    
    public void excluirCLiente(Cliente obj){
        try {
            String sql = "delete from tb_clientes where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente excluído com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    
    public List<Cliente>listarClientes(){
        try {
            
            List<Cliente> lista = new ArrayList<>();
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    public Cliente buscarCliente(String nome){
        try {
            String sql = "select * from tb_clientes where nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    public Cliente buscarClienteCPF(String cpf){
        try {
            String sql = "select * from tb_clientes where cpf like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
  /*  public List<Cliente>pesquisarNome(String nome){
        try {
            List<Cliente> lista = new ArrayList<>();
            String sql = "select * from tb_clientes like nome";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null,"erro " +  e);
        }
        return null;
    }*/
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Funcionario;
import br.com.projeto.view.FormAdm;
import br.com.projeto.view.FormLogin;
import br.com.projeto.view.FormVendedor;
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
public class FuncionarioDAO {
    private Connection conexao;

    public FuncionarioDAO() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    public void cadastrarFuncionarios(Funcionario obj){
        try {
            String sql = "insert into tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCpe());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário Cadastrado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
    }
    public void alterarFuncionario(Funcionario obj){
        try {
            String sql = "update tb_funcionarios set nome = ?, rg = ?, cpf = ?, email = ?, senha = ?, cargo = ?, nivel_acesso = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ? where id = ? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCpe());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            stmt.setInt(17, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário alterado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    
    public void excluirFuncionario(Funcionario obj){
        try {
            String sql = "delete from tb_funcionarios where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionário excluído com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }   
    public List<Funcionario>listarFuncionarios(){
        try {
            
            List<Funcionario> lista = new ArrayList<>();
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    public Funcionario buscarFuncionario(String nome){
        try {
            String sql = "select * from tb_funcionarios where nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionario obj = new Funcionario();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    public void efetuarLogin(String email, String senha){
        try {
            String sql = "select * from tb_funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getString("nivel_acesso").equals("Administrador")){
                    JOptionPane.showMessageDialog(null,"Logando, Administrador!");
                    FormAdm telaAdm = new FormAdm();
                    telaAdm.setVisible(true);
                }else if(rs.getString("nivel_acesso").equals("Vendedor")){
                    JOptionPane.showMessageDialog(null, "Logando, Funcionário!");
                    FormVendedor telaVendedor = new FormVendedor();
                }
            }else{
                FormLogin login = new FormLogin();
                JOptionPane.showMessageDialog(null, "E-mail ou Senha incorretas");
                login.setVisible(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}

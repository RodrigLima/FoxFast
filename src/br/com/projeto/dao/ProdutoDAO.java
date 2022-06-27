/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
import br.com.projeto.model.Produto;
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
public class ProdutoDAO {
    private Connection conexao;

    public ProdutoDAO() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    public void cadastrarProduto(Produto obj){
        try {
            String sql = "insert into tb_produtos(descricao, preco, qtd_estoque, for_id) values (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    
    public void alterarProduto(Produto obj){
        try {
            String sql = "update tb_produtos set descricao = ?, preco = ?, qtd_estoque = ?, for_id = ? where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto Alterado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }
    public void excluirProduto(Produto obj){
        try {
            String sql = "delete from tb_produtos where id =?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto Excluído com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
    }
    public List<Produto>listarProdutos(){
        try {
            List<Produto>lista = new ArrayList<>();
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Fornecedor f = new Fornecedor();
                Produto obj = new Produto();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);
                lista.add(obj);
            }
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
        return null;
    }
    
    public List<Produto>pesquisarProduto(String nome){
        try {
            List<Produto> lista = new ArrayList<>();
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id = f.id) where p.descricao like ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);
                lista.add(obj);
                
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Produto buscarProdutoNome(String nome){
        try {
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id = f.id) where p.descricao = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();            
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);       
            }
                return obj;
           
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void baixaEstoque(int id, int qtd_nova){
        try {
            String sql = "update tb_produtos set qtd_estoque = ? where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "A quantidade foi adicionada no estoque");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
    }
    
    public void adicionarEstoque(int id, int qtd_nova){
        try {
            String sql = "update tb_produtos set qtd_estoque = ? where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "A quantidade foi adicionada no estoque");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro " + e);
        }
    }
    
    public int retornaQTDatual(int id){
        try {
            int qtd_estoque = 0;
            String sql = "select qtd_estoque from tb_produtos where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       
    }
}

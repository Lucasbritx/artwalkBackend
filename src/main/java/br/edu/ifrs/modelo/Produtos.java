package br.edu.ifrs.modelo;

import br.edu.ifrs.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Produtos {
    private int id;
    private String nome;
    private String descricao;
    private String unidade;
    private double precoUnitario;

    public Produtos() {
        this.id = 0;
        this.nome = "";
        this.descricao = "";
        this.unidade = "";
        this.precoUnitario = 0.0;
    }

    public void inserir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("INSERT INTO produtos (nome, descricao, unidade, preco_unitario) VALUES (?, ?, ?, ?)");

            p.setString(1, this.nome);
            p.setString(2, this.descricao);
            p.setString(3, this.unidade);
            p.setDouble(4, this.precoUnitario);

            p.execute();
        } catch (Exception e) {
            throw new Exception("[Produtos.inserir] - Falha ao executar a operação de inserção no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    public void atualizar() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("UPDATE produtos SET nome = ?, descricao = ?, unidade = ?, preco_unitario = ? WHERE id = ?");

            p.setString(1, this.nome);
            p.setString(2, this.descricao);
            p.setString(3, this.unidade);
            p.setDouble(4, this.precoUnitario);
            p.setInt(5, this.id);

            p.execute();
        } catch (Exception e) {
            throw new Exception("[Produtos.atualizar] - Falha ao executar a operação de atualização no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    public void excluir(String id) throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("DELETE FROM produtos WHERE id = ?");

            p.setInt(1, Integer.parseInt(id));

            p.execute();
        } catch (Exception e) {
            throw new Exception("[Produtos.excluir] - Falha ao executar a operação de exclusão no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    public Produtos[] selecionar() throws Exception {
        List<Produtos> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("SELECT * FROM produtos");

            rs = p.executeQuery();

            while (rs.next()) {
                Produtos produto = new Produtos();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setUnidade(rs.getString("unidade"));
                produto.setPrecoUnitario(rs.getDouble("preco_unitario"));

                lista.add(produto);
            }
        } catch (Exception e) {
            throw new Exception("[Produtos.selecionar] - Falha ao executar a operação de seleção no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();
        }

        return lista.toArray(Produtos[]::new);
    }

    public void selecionarId() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("SELECT * FROM produtos WHERE id = ?");

            p.setInt(1, this.id);

            rs = p.executeQuery();

            if (rs.next()) {
                this.setNome(rs.getString("nome"));
                this.setDescricao(rs.getString("descricao"));
                this.setUnidade(rs.getString("unidade"));
                this.setPrecoUnitario(rs.getDouble("preco_unitario"));
            }
        } catch (Exception e) {
            throw new Exception("[Produtos.selecionarId] - Falha ao executar a operação de seleção pela chave primária no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    // Getter and Setter methods for id, nome, descricao, unidade, and precoUnitario...

    // Getter and Setter methods for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter methods for nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter and Setter methods for descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter and Setter methods for unidade
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    // Getter and Setter methods for precoUnitario
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}

package br.edu.ifrs.modelo;

import br.edu.ifrs.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoPedido {
    private int produtosId;
    private int pedidosNumero;
    private int quantidade;
    private double precoUnitario;
    private String unidade;

    public ProdutoPedido() {
        // Default constructor
    }

    public ProdutoPedido(int produtosId, int pedidosNumero, int quantidade, double precoUnitario, String unidade) {
        this.produtosId = produtosId;
        this.pedidosNumero = pedidosNumero;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
    }

    // Getter and Setter methods for all fields

    // Insert a new produto_pedido
    public void inserir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("INSERT INTO produtos_has_pedidos (produtos_id, pedidos_numero, quantidade, preco_unitario, unidade) VALUES (?, ?, ?, ?, ?)");

            p.setInt(1, this.produtosId);
            p.setInt(2, this.pedidosNumero);
            p.setInt(3, this.quantidade);
            p.setDouble(4, this.precoUnitario);
            p.setString(5, this.unidade);

            p.execute();
        } catch (Exception e) {
            throw new Exception("[ProdutoPedido.inserir] - Falha ao executar a operação de inserção no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    // Update an existing produto_pedido
    public void atualizar() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("UPDATE produtos_has_pedidos SET quantidade = ?, preco_unitario = ?, unidade = ? WHERE produtos_id = ? AND pedidos_numero = ?");

            p.setInt(1, this.quantidade);
            p.setDouble(2, this.precoUnitario);
            p.setString(3, this.unidade);
            p.setInt(4, this.produtosId);
            p.setInt(5, this.pedidosNumero);

            p.execute();
        } catch (Exception e) {
            throw new Exception("[ProdutoPedido.atualizar] - Falha ao executar a operação de atualização no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    // Delete an existing produto_pedido
    public void excluir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("DELETE FROM produtos_has_pedidos WHERE produtos_id = ? AND pedidos_numero = ?");

            p.setInt(1, this.produtosId);
            p.setInt(2, this.pedidosNumero);

            p.execute();
        } catch (Exception e) {
            throw new Exception("[ProdutoPedido.excluir] - Falha ao executar a operação de exclusão no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    // Select all produto_pedidos
    public ProdutoPedido[]  selecionarTodos() throws Exception {
        List<ProdutoPedido> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("SELECT * FROM produtos_has_pedidos");

            rs = p.executeQuery();

            while (rs.next()) {
                ProdutoPedido produtoPedido = new ProdutoPedido();
                produtoPedido.setProdutosId(rs.getInt("produtos_id"));
                produtoPedido.setPedidosNumero(rs.getInt("pedidos_numero"));
                produtoPedido.setQuantidade(rs.getInt("quantidade"));
                produtoPedido.setPrecoUnitario(rs.getDouble("preco_unitario"));
                produtoPedido.setUnidade(rs.getString("unidade"));

                lista.add(produtoPedido);
            }
        } catch (Exception e) {
            throw new Exception("[ProdutoPedido.selecionarTodos] - Falha ao executar a operação de seleção no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();
        }

        return lista.toArray(new ProdutoPedido[0]);
    }

    // Select produto_pedido by produtos_id and pedidos_numero
    public void selecionarPorId() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("SELECT * FROM produtos_has_pedidos WHERE produtos_id = ? AND pedidos_numero = ?");

            p.setInt(1, this.produtosId);
            p.setInt(2, this.pedidosNumero);

            rs = p.executeQuery();

            if (rs.next()) {
                this.setQuantidade(rs.getInt("quantidade"));
                this.setPrecoUnitario(rs.getDouble("preco_unitario"));
                this.setUnidade(rs.getString("unidade"));
            }
        } catch (Exception e) {
            throw new Exception("[ProdutoPedido.selecionarPorId] - Falha ao executar a operação de seleção pela chave primária no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

        // Getter and Setter methods for all fields

        public int getProdutosId() {
            return produtosId;
        }
    
        public void setProdutosId(int produtosId) {
            this.produtosId = produtosId;
        }
    
        public int getPedidosNumero() {
            return pedidosNumero;
        }
    
        public void setPedidosNumero(int pedidosNumero) {
            this.pedidosNumero = pedidosNumero;
        }
    
        public int getQuantidade() {
            return quantidade;
        }
    
        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
    
        public double getPrecoUnitario() {
            return precoUnitario;
        }
    
        public void setPrecoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }
    
        public String getUnidade() {
            return unidade;
        }
    
        public void setUnidade(String unidade) {
            this.unidade = unidade;
        }}

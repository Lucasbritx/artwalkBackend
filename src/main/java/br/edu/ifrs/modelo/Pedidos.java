package br.edu.ifrs.modelo;

import br.edu.ifrs.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private int numero;
    private String dataEmissao;
    private double valorFrete;
    private String dataEntrega;
    private int clientesId;

    public Pedidos() {
        this.numero = 0;
        this.dataEmissao = "";
        this.valorFrete = 0.0;
        this.dataEntrega = "";
        this.clientesId = 0;
    }
    
    PreparedStatement p = null;
    public void inserir() throws Exception {
        Connection con = null;
    try {
        con = Conexao.pegarConexao();
        p = con.prepareStatement("INSERT INTO pedidos (data_emissao, valor_frete, data_entrega, clientes_id) VALUES (?, ?, ?, ?)");

        p.setString(1, this.dataEmissao);
        p.setDouble(2, this.valorFrete);
        p.setString(3, this.dataEntrega);
        p.setInt(4, this.clientesId);

        p.execute();
    } catch (Exception e) {
        throw new Exception("[Pedido.inserir] - Falha ao executar a operação de inserção no banco de dados: " + e.getMessage());
    } finally {
        if (p != null) p.close();
        if (con != null) con.close();
    }
}

// Update an existing pedido
public void atualizar() throws Exception {
    Connection con = null;
    PreparedStatement p = null;

    try {
        con = Conexao.pegarConexao();
        p = con.prepareStatement("UPDATE pedidos SET data_emissao = ?, valor_frete = ?, data_entrega = ?, clientes_id = ? WHERE numero = ?");

        p.setString(1, this.dataEmissao);
        p.setDouble(2, this.valorFrete);
        p.setString(3, this.dataEntrega);
        p.setInt(4, this.clientesId);
        p.setInt(5, this.numero);

        p.execute();
    } catch (Exception e) {
        throw new Exception("[Pedidos.atualizar] - Falha ao executar a operação de atualização no banco de dados: " + e.getMessage());
    } finally {
        if (p != null) p.close();
        if (con != null) con.close();
    }
}

public void excluir(String numero) throws Exception {
    Connection con = null;
    PreparedStatement p = null;

    try {
        con = Conexao.pegarConexao();
        p = con.prepareStatement("DELETE FROM produtos_has_pedidos WHERE pedidos_numero = ?");
        p.setInt(1, Integer.parseInt(numero));
        p.execute();
        p = con.prepareStatement("DELETE FROM pedidos WHERE numero = ?");
        p.setInt(1, Integer.parseInt(numero));
        p.execute();
    } catch (Exception e) {
        throw new Exception("[Pedidos.excluir] - Falha ao executar a operação de exclusão no banco de dados: " + e.getMessage());
    } finally {
        if (p != null) p.close();
        if (con != null) con.close();
    }
}

// Retrieve all pedidos
public Pedidos[] selecionar() throws Exception {
    List<Pedidos> lista = new ArrayList<>();
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    try {
        con = Conexao.pegarConexao();
        p = con.prepareStatement("SELECT * FROM pedidos");

        rs = p.executeQuery();

        while (rs.next()) {
            Pedidos pedido = new Pedidos();
            pedido.setNumero(rs.getInt("numero"));
            pedido.setDataEmissao(rs.getString("data_emissao"));
            pedido.setValorFrete(rs.getDouble("valor_frete"));
            pedido.setDataEntrega(rs.getString("data_entrega"));
            pedido.setClientesId(rs.getInt("clientes_id"));

            lista.add(pedido);
        }
    } catch (Exception e) {
        throw new Exception("[Pedidos.selecionar] - Falha ao executar a operação de seleção no banco de dados: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (p != null) p.close();
        if (con != null) con.close();
    }

    return lista.toArray(new Pedidos[0]);
}

// Retrieve a pedido by its number
public void selecionarNumero() throws Exception {
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    try {
        con = Conexao.pegarConexao();
        p = con.prepareStatement("SELECT * FROM pedidos WHERE numero = ?");

        p.setInt(1, this.numero);

        rs = p.executeQuery();

        if (rs.next()) {
            this.setDataEmissao(rs.getString("data_emissao"));
            this.setValorFrete(rs.getDouble("valor_frete"));
            this.setDataEntrega(rs.getString("data_entrega"));
            this.setClientesId(rs.getInt("clientes_id"));
        }
    } catch (Exception e) {
        throw new Exception("[Pedidos.selecionarNumero] - Falha ao executar a operação de seleção pela chave primária no banco de dados: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (p != null) p.close();
        if (con != null) con.close();
    }
}
    // Getter and Setter methods for all fields

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getClientesId() {
        return clientesId;
    }

    public void setClientesId(int clientesId) {
        this.clientesId = clientesId;
    }

    // Additional methods for database operations (insert, update, delete, select) can be added here
}

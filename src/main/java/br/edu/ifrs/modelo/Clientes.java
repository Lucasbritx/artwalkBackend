package br.edu.ifrs.modelo;

import br.edu.ifrs.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private int id;
    private String nome;
    private int dataNascimento;
    private String cpf;
    private String rg;
    private String orgaoEmissor;
    private Sexo sexo;
    private String email;
    private String telefone;
    private Integer whats;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Clientes() {
        this.id = 0;
        this.nome = "";
        this.dataNascimento = 0;
        this.cpf = "";
        this.rg = "";
        this.orgaoEmissor = "";
        this.sexo = null; // Assuming your Sexo enum has a null value or you can set it to a default value
        this.email = "";
        this.telefone = "";
        this.whats = null; // Assuming Integer for WhatsApp, use null if not applicable
        this.logradouro = "";
        this.numero = "";
        this.bairro = "";
        this.cidade = "";
        this.estado = "";
        this.cep = "";
    }
    
    public void inserir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
    
        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("INSERT INTO clientes (nome, data_nascimento, cpf, rg, orgao_emissor, sexo, email, telefone, whats, logradouro, numero, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    
            p.setString(1, this.nome);
            p.setInt(2, this.dataNascimento);
            p.setString(3, this.cpf);
            p.setString(4, this.rg);
            p.setString(5, this.orgaoEmissor);
            p.setString(6, this.sexo.toString()); // Assuming Sexo is an enum with a toString method
            p.setString(7, this.email);
            p.setString(8, this.telefone);
            p.setObject(9, this.whats, java.sql.Types.INTEGER); // Assuming Integer for WhatsApp, use setObject to handle null
            p.setString(10, this.logradouro);
            p.setString(11, this.numero);
            p.setString(12, this.bairro);
            p.setString(13, this.cidade);
            p.setString(14, this.estado);
            p.setString(15, this.cep);
    
            p.execute();
        } catch (Exception e) {
            throw new Exception("[Clientes.inserir] - Falha ao executar a operação de inserção no banco de dados: " + e.getMessage());
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
            p = con.prepareStatement("UPDATE clientes SET nome = ?, data_nascimento = ?, cpf = ?, rg = ?, orgao_emissor = ?, sexo = ?, email = ?, telefone = ?, whats = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?");
    
            p.setString(1, this.nome);
            p.setInt(2, this.dataNascimento);
            p.setString(3, this.cpf);
            p.setString(4, this.rg);
            p.setString(5, this.orgaoEmissor);
            p.setString(6, this.sexo.toString()); // Assuming Sexo is an enum with a toString method
            p.setString(7, this.email);
            p.setString(8, this.telefone);
            p.setObject(9, this.whats, java.sql.Types.INTEGER); // Assuming Integer for WhatsApp, use setObject to handle null
            p.setString(10, this.logradouro);
            p.setString(11, this.numero);
            p.setString(12, this.bairro);
            p.setString(13, this.cidade);
            p.setString(14, this.estado);
            p.setString(15, this.cep);
            p.setInt(16, this.id);
    
            p.execute();
        } catch (Exception e) {
            throw new Exception("[Clientes.atualizar] - Falha ao executar a operação de atualização no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }

    public void excluir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
    
        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("DELETE FROM clientes WHERE id = ?");
    
            p.setInt(1, this.id);
    
            p.execute();
        } catch (Exception e) {
            throw new Exception("[Clientes.excluir] - Falha ao executar a operação de exclusão no banco de dados: " + e.getMessage());
        } finally {
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }
    
    public Clientes[] selecionar() throws Exception {
        List<Clientes> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
    
        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("SELECT * FROM clientes");
    
            rs = p.executeQuery();
    
            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNascimento(rs.getInt("data_nascimento"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setOrgaoEmissor(rs.getString("orgao_emissor"));
                cliente.setSexo(Sexo.valueOf(rs.getString("sexo"))); // Assuming Sexo is an enum with a valueOf method
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setWhats(rs.getInt("whats")); // Assuming Integer for WhatsApp
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));
    
                lista.add(cliente);
            }
        } catch (Exception e) {
            throw new Exception("[Clientes.selecionar] - Falha ao executar a operação de seleção no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();
        }
    
        return lista.toArray(new Clientes[0]);
    }
    
    public void selecionarId() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
    
        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("SELECT * FROM clientes WHERE id = ?");
    
            p.setInt(1, this.id);
    
            rs = p.executeQuery();
    
            if (rs.next()) {
                this.setNome(rs.getString("nome"));
                this.setDataNascimento(rs.getInt("data_nascimento"));
                this.setCpf(rs.getString("cpf"));
                this.setRg(rs.getString("rg"));
                this.setOrgaoEmissor(rs.getString("orgao_emissor"));
                this.setSexo(Sexo.valueOf(rs.getString("sexo"))); // Assuming Sexo is an enum with a valueOf method
                this.setEmail(rs.getString("email"));
                this.setTelefone(rs.getString("telefone"));
                this.setWhats(rs.getInt("whats")); // Assuming Integer for WhatsApp
                this.setLogradouro(rs.getString("logradouro"));
                this.setNumero(rs.getString("numero"));
                this.setBairro(rs.getString("bairro"));
                this.setCidade(rs.getString("cidade"));
                this.setEstado(rs.getString("estado"));
                this.setCep(rs.getString("cep"));
            }
        } catch (Exception e) {
            throw new Exception("[Clientes.selecionarId] - Falha ao executar a operação de seleção pela chave primária no banco de dados: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();
        }
    }
    
    
    // Getter and Setter methods for id, nome, dataNascimento, cpf, rg, orgaoEmissor, sexo, email, telefone, whats, logradouro, numero, bairro, cidade, estado, cep...

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

    // Getter and Setter methods for dataNascimento
    public int getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Getter and Setter methods for cpf
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter and Setter methods for rg
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    // Getter and Setter methods for orgaoEmissor
    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    // Getter and Setter methods for sexo
    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    // Getter and Setter methods for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter methods for telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Getter and Setter methods for whats
    public Integer getWhats() {
        return whats;
    }

    public void setWhats(Integer whats) {
        this.whats = whats;
    }

    // Getter and Setter methods for logradouro
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    // Getter and Setter methods for numero
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Getter and Setter methods for bairro
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Getter and Setter methods for cidade
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // Getter and Setter methods for estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getter and Setter methods for cep
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

enum Sexo {
    FEMININO,
    MASCULINO
}

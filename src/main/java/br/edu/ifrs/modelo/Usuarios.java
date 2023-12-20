package br.edu.ifrs.modelo;

import br.edu.ifrs.util.Conexao;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    private String cpf;
    private String nome;
    private String data_nascimento; // change to date?
    private String email;
    private String telefone;
    private int whats;
    private String username;
    private String senha;
    
    public Usuarios() {
        this.cpf = "";
        this.nome = "";
        this.data_nascimento = "";
        this.email = "";
        this.telefone = "";
        this.whats = 0;
        this.username = "";
        this.senha = "";
    }

    public void inserir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
        
        try {
            con = Conexao.pegarConexao();
            p = con.prepareStatement("insert into usuarios (cpf, nome, data_nascimento, email) values (?, ?, ?, ?)");
            
            p.setString(1, this.cpf);
            p.setString(2, this.nome);
            p.setString(3, this.data_nascimento);
            p.setString(4, this.email);
            
            p.execute();
        } catch (MysqlDataTruncation m) {
            throw new Exception("[Usuarios.inserir] - Informação muito longa ");
        } catch (Exception e) {
            throw new Exception("[Usuarios.inserir] - Falha ao executar a operação de inserção no banco de dados: "+e.getMessage());
        } finally {
            // Fechar a conexão
            if (p != null) p.close();
            if (con != null) con.close();            
        }
    }
    
    public void atualizar() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            // Informar qual o driver JDBC a ser utilizado
            /*Class.forName("com.mysql.jdbc.Driver");*/
            
            // Abrir uma Conexão com o Banco de Dados
            /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojao", "root", "12345678");*/
            
            /* Abertura de Conexão com Classe Utilitária */
            con = Conexao.pegarConexao();
            
            // Definir a sentença SQL
            p = con.prepareStatement("update usuarios set nome = ?, data_nascimento = ?, email = ?, email = ? where cpf = ?");
            
            // Parametrizar a sentença SQL
            p.setString(1, this.nome);
            p.setString(2, this.data_nascimento);
            p.setString(3, this.email);
            p.setString(4, this.telefone);
            p.setString(5, this.cpf);
            
            // Executar a operação
            p.execute();            
        } catch (Exception e) {
            throw new Exception("[Usuarios.atualizar] - Falha ao executar a operação de atualização no banco de dados: "+e.getMessage());
        } finally {
            // Fechar a conexão
            if (p != null) p.close();
            if (con != null) con.close();            
        }
    }
    
    public void excluir() throws Exception {
        Connection con = null;
        PreparedStatement p = null;

        try {
            // Informar qual o driver JDBC a ser utilizado
            /*Class.forName("com.mysql.jdbc.Driver");*/
            
            // Abrir uma Conexão com o Banco de Dados
            /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojao", "root", "12345678");*/
            
            /* Abertura de Conexão com Classe Utilitária */
            con = Conexao.pegarConexao();
            
            // Definir a sentença SQL
            p = con.prepareStatement("delete from usuarios where cpf = ?");
            
            // Parametrizar a sentença SQL
            p.setString(1, this.cpf);
            
            // Executar a operação
            p.execute();            
        } catch (Exception e) {
            throw new Exception("[Usuarios.excluir] - Falha ao executar a operação de exclusão no banco de dados: "+e.getMessage());
        } finally {
            // Fechar a conexão
            if (p != null) p.close();
            if (con != null) con.close();            
        }
    }
    
    public Usuarios[] selecionar() throws Exception {
        List<Usuarios> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            // Informar qual o driver JDBC a ser utilizado
            /*Class.forName("com.mysql.jdbc.Driver");*/
            
            // Abrir uma Conexão com o Banco de Dados
            /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojao", "root", "12345678");*/
            
            /* Abertura de Conexão com Classe Utilitária */
            con = Conexao.pegarConexao();
            
            // Definir a sentença SQL
            p = con.prepareStatement("select * from usuarios");
                        
            // Executar a operação
            rs = p.executeQuery();
            
            while (rs.next()) {
                Usuarios c = new Usuarios();
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
                c.setData_nascimento(rs.getString("data_nascimento"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setWhats(rs.getInt("whats"));
                c.setUsername(rs.getString("username"));
                c.setSenha(rs.getString("senha"));
                
                lista.add(c);
            }            
        } catch (Exception e) {
            throw new Exception("[Usuarios.selecionar] - Falha ao executar a operação de seleção no banco de dados: "+e.getMessage());
        } finally {
            // Fechar a conexão
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();            
        }
        
        return lista.toArray(new Usuarios[0]);
    }
    
    public void selecionarId() throws Exception {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            // Informar qual o driver JDBC a ser utilizado
            /*Class.forName("com.mysql.jdbc.Driver");*/
            
            // Abrir uma Conexão com o Banco de Dados
            /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojao", "root", "12345678");*/
            
            /* Abertura de Conexão com Classe Utilitária */
            con = Conexao.pegarConexao();
            
            // Definir a sentença SQL
            p = con.prepareStatement("select * from usuarios where cpf = ?");
            
            // Parametrizar a sentença SQL
            p.setString(1, this.cpf);
                        
            // Executar a operação
            rs = p.executeQuery();
            
            if (rs.next()) {
                this.setNome(rs.getString("nome"));
                this.setData_nascimento(rs.getString("tamanho"));
                this.setEmail(rs.getString("email"));
                this.setTelefone(rs.getString("telefone"));
                this.setWhats(rs.getInt("whats"));
                this.setUsername(rs.getString("username"));
                this.setSenha(rs.getString("senha"));
            }
            
            // Fechar a conexão
            p.close();
            con.close();
        } catch (Exception e) {
            throw new Exception("[Usuarios.selecionarId] - Falha ao executar a operação de seleção pela chave primária no banco de dados: "+e.getMessage());
        } finally {
            // Fechar a conexão
            if (rs != null) rs.close();
            if (p != null) p.close();
            if (con != null) con.close();            
        }
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
     * @return the data_nascimento
     */
    public String getData_nascimento() {
        return data_nascimento;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the whats
     */
    public int getWhats() {
        return whats;
    }

    /**
     * @param whats the whats to set
     */
    public void setWhats(int whats) {
        this.whats = whats;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

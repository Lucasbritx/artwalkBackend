
package br.edu.ifrs.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/lojao";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    
    public static Connection pegarConexao() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL_JDBC, USER, PASSWORD);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.pi3.a.gabriel.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author gabriel.fandrade3
 */
public class Agenda {

    private Connection obterConexao() throws ClassNotFoundException, SQLException {

        // 1A) Registrar driver JDBC
        Class.forName("com.mysql.jdbc.Driver");

        // 1B) Abrir conexão com DB
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/agendabd", "root", "");
    }

    public void listar() throws ClassNotFoundException, SQLException {

        // 2) Escrever a lógica para executar algum comando no banco
        String query = "SELECT id, nome, dtnascimento FROM pessoa";

        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet resultados = stmt.executeQuery()) {

                while (resultados.next()) {

                    long id = resultados.getLong("id");
                    String name = resultados.getString("nome");
                    Date data = resultados.getDate("dtnascimento");
                    System.out.println(id + " " + name + " " + data);
                }
            }
        } catch (SQLException e) {

        }

        // 3) Fechar conexão (tratado pelo try acima - try-with-resources)
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Agenda agenda = new Agenda();
        agenda.listar();
        
        for(int i = 0; i<10;i++){
            System.out.println(i);
        }

    }
}

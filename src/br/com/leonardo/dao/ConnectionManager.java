package br.com.leonardo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// instancia(unica) a ser fornecida

	private static ConnectionManager instance;

	private ConnectionManager() throws ClassNotFoundException {
		//
		// Registra o driver JDBC (neste caso o driver de conexao para o Oracle)
		//
		Class.forName("com.mysql.jdbc.Driver");

	}

	/**
	 * 
	 * Pega a instancia desta classe.
	 * 
	 * @return A instancia.
	 * 
	 * @throws SQLException
	 * 
	 */

	public static ConnectionManager getInstance() throws SQLException {

		try {

			// verifica se jah existe uma instancia, se nao existe entao
			// instancia

			if (instance == null) {

				instance = new ConnectionManager();

			}

		}

		catch (ClassNotFoundException e) {

			throw new SQLException("O Driver JDBC nao foi encontrado!");

		}

		return instance;

	}

	/**
	 * 
	 * Abre uma conexao com o banco de dados.
	 * 
	 * @return Um objeto que representa a conexao com o banco de dados.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */

	public Connection getConnection() throws SQLException, IllegalArgumentException, IOException {
		//
		// local
		//
		String usuario = "root";
		String senha = "*******";

		// acesso local
		String jdbcUrl = "jdbc:mysql://localhost/sinapsis_licenca";

		try {

			//
			// Abre a conexao com o SGBDR
			//
			System.out.println("Estabelecendo Conexão...");
			return DriverManager.getConnection(jdbcUrl, usuario, senha);

		}

		catch (SQLException e) {

			e.printStackTrace();

			throw new SQLException("Erro ao abrir a conexão com banco de dados", e);

		}

	}

}

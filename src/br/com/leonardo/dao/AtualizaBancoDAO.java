package br.com.leonardo.dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leonardo.security.pbkdf2.PasswordEncryptor;
import br.com.leonardo.to.Password;

public class AtualizaBancoDAO {

	private PreparedStatement stmt;

	public void atualizarSenha(List<Password> lista, Connection conn) throws SQLException {

		try {
			int i = 0;
			int n = lista.size();
			for (Password linha : lista) {
				System.out.println("Inserindo " + i++ + " de " + n + " licenca_id = " + linha.getId());
				stmt = conn.prepareStatement("UPDATE Licenca set password = ? where licenca_id = ? ");

				stmt.setString(1, linha.getSenha());
				stmt.setInt(2, linha.getId());

				stmt.execute();
			}
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o banco de dados!", e);
		}
	}

	public ArrayList<Password> listar(Connection conn) throws SQLException, IllegalArgumentException, IOException {
		Password password;
		// Lista a ser retornada
		ArrayList<Password> lista = new ArrayList<Password>();

		try {

			System.out.println("Inciciando a lista");

			PreparedStatement stmtSelect = conn.prepareStatement("SELECT * from Gestor");

			// executa a SQL

			ResultSet rs = stmtSelect.executeQuery();

			while (rs.next()) {

				password = new Password();
				password.setId(rs.getInt("gestor_id"));
				password.setSenha(PasswordEncryptor.generateStorngPasswordHash(rs.getString("gestor_password")));
				lista.add(password);

			}

		} catch (SQLException e) {

			e.printStackTrace();

			throw new SQLException("Erro ao conectar ou manipular o banco de dados!", e);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		System.out.println("retornando a lista");
		return lista;

	}

}

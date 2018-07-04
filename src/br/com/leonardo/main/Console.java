package br.com.leonardo.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.leonardo.dao.AtualizaBancoDAO;
import br.com.leonardo.dao.ConnectionManager;
import br.com.leonardo.to.Password;

public class Console {

	public static void main(String[] args) throws IllegalArgumentException, SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		Connection conn = ConnectionManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		
	AtualizaBancoDAO dao = new AtualizaBancoDAO();

	
	 FileWriter arq = new FileWriter("c:/tmp/updatePwGestor.sql");
	    PrintWriter gravarArq = new PrintWriter(arq);
		
		System.out.println("Listando");
	List<Password> lista = dao.listar(conn);

	System.out.println("Escrevendo no arquivo");
	for(Password n : lista){
		 gravarArq.println("UPDATE Licenca set password = '" + n.getSenha() + "' where licenca_id = " + n.getId() +";");
		 
				
		}
	
	System.out.println("Fechando printWrite");
	gravarArq.close();
	System.out.println("Fechando arquivo");
	arq.close();
	System.out.println("Finalizado!!!");
	
	

		


	}

}

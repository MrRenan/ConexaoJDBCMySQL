package unidade2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AcessoDB {

	static String url = "jdbc:mysql://localhost:3306/system";
	static String usuario = "root";
	static String senha = "p@ssword";
	static Connection conexao;

	public static void conectar() throws SQLException {
		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
	}

	public static void consultarCliente() throws SQLException {
		String consulta = "SELECT * FROM Cliente";
		Statement statement = conexao.createStatement();
		ResultSet rs = statement.executeQuery(consulta);
		while (rs.next()) {
			JOptionPane.showMessageDialog(null,
					"cpf: " + rs.getString(1) + " nome: " + rs.getString(2) + " email: " + rs.getString(3));
		}
	}

	public static void mostrarMetaInfoDB() throws SQLException {
		DatabaseMetaData meta = conexao.getMetaData();
		String fabricanteDB = meta.getDatabaseProductName();
		String versaoDB = meta.getDatabaseProductVersion();
		JOptionPane.showMessageDialog(null, fabricanteDB + "<~~>" + versaoDB);
	}

	public static void main(String[] args) {
		try {
			conectar();
			mostrarMetaInfoDB();
			consultarCliente();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

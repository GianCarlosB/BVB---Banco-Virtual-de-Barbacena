package tsi.too.bvb.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsi.too.bvb.gui.JanelaPopUpErro;

public abstract class BancoDeDadosDAO {
	
	private final String URL;
	private Connection conn;
	private PreparedStatement stmt;
	
	public BancoDeDadosDAO(String URL) {
		super();
		this.URL = URL;
	}

	public Connection getConn() {
		return conn;
	}

	public PreparedStatement getStmt() {
		return stmt;
	}

	public void setStmt(PreparedStatement stmt) {
		this.stmt = stmt;
	}

	public String getURL() {
		return URL;
	}

	protected boolean abrirConexao() {
		/*
		 * N�o h� mais necessidade de carregar o driver para acessar o banco de dados
		 * apartir da vers�o Java 1.6 (JDBC 4). O driver � reconhecido automaticamente.
		 * 
		 * Class.forName("org.hsqldb.jdbcDriver");
		 */
		
		try {
			conn = DriverManager.getConnection(URL, "admin", "123");
			return true;
		} catch (SQLException e) {
			conn = null;
			new JanelaPopUpErro(null, "BVB - ERRO", " Falha na aquisi��o do bloqueio do banco de dados!", e);
			return false;
		}
	}
	
	public PreparedStatement obterPreparedStatement(String sql) {
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return stmt;
	}
	
	public ResultSet obterResultSet() {
		ResultSet rset = null;
		
		try {
			rset = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return rset;
	}
	
	public boolean fecharPreparedStatement() {
		if(stmt == null) return false;
		
		try {
			stmt.close();
			return true;
		} catch (SQLException e) { return false; }
	}
	
	public boolean fecharConexao() {
		if(conn == null) return false;
		
		try {
			conn.close();
			return true;
		} catch (SQLException e) { 
			new JanelaPopUpErro(null, "BVB - ERRO", " Falha na finaliza��o da conex�o com o banco de dados!", e);
			return false; 
		}
	}
	
	public static boolean fecharResultSet(ResultSet rSet) {
		if(rSet == null) return false;
		
		try {
			rSet.close();
			return true;
		} catch (SQLException e) { return false; }
	}
	
	public boolean fecharTudo() {
		fecharPreparedStatement();
		if(!fecharConexao())
			return false;
		
		return true;
	}

	@Override
	protected void finalize() throws Throwable {
		fecharTudo();
	}
	
} // class DaoHelper

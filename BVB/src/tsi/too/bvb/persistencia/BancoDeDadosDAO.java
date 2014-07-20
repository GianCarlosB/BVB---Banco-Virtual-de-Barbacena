package tsi.too.bvb.persistencia;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

/** Classe para manipular o banco de dados
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * 
 * @see Connection
 * @see Statement
 */
public abstract class BancoDeDadosDAO {
	
	private final String LOGIN_BD = "admin";
	
	private final String SENHA_BD = "123";
	
	private final String URL;
	
	private Connection conn;
	
	private PreparedStatement stmt;
	
	/**
	   * O construtor cria uma nova conex�o com o banco de dados contido
	   * na <code>String</code> passado como par�metro. A conex�o � possibilitada pelo driver
	   * JDBC do banco de dados.
	   * 
	   * @param URL <code>String</code> com endere�o do banco de dados.
	   */
	public BancoDeDadosDAO(String URL) {
		super();
		this.URL = URL;
	}

	/** Seta a conex�o com o bando de dados
	 * @param conn <code>Connection</code> com a conex�o com o banco de dados
	 * @see Connection
	 */
	protected void setConn(Connection conn) {
		this.conn = conn;
	}
	
	/** Retorna um <code>Connection</code> com a conex�o com o banco de dados
	 * @return <code>Connection</code> com a conex�o com o banco de dados
	 * @see Connection
	 */
	public Connection getConn() {
		return conn;
	}

	/** Seta o executador de comandos SQL
	 * @param stmt <code>PreparedStatement</code> que � o executador de comandos SQL
	 * @see PreparedStatement
	 */
	protected void setStmt(PreparedStatement stmt) {
		this.stmt = stmt;
	}
	
	/** Retorna o <code>PreparedStatement</code> que � o executador de comandos SQL
	 * @return <code>PreparedStatement</code> que � o executador de comandos SQL
	 * @see PreparedStatement
	 */
	public PreparedStatement getStmt() {
		return stmt;
	}

	/** Retorna a URL com a conex�o com o banco de dados
	 * @return <code>String</code> com URL da conex�o com o banco de dados
	 */
	public String getURL() {
		return URL;
	}
	
	/** Abre a conex�o com o banco de dados
	 * @throws SQLException poss�vel erro gerado por m� configura��o do banco de dados
	 */
	protected void abrirConexao() throws SQLException {
		/*
		 * N�o h� mais necessidade de carregar o driver para acessar o banco de dados
		 * apartir da vers�o Java 1.6 (JDBC 4). O driver � reconhecido automaticamente.
		 * 
		 * Class.forName("org.hsqldb.jdbcDriver");
		 */
		
		conn = DriverManager.getConnection(URL, LOGIN_BD, SENHA_BD);
	}
	
	/** Armazena o comando SQL no <code>PreparedStatement</code>
	 * @param sql <code>String</code> com o comando SQL
	 * @return um <code>PreparedStatement</code>  que cont�m o comando SQL
	 * @throws SQLException poss�vel erro gerado por m� configura��o do banco de dados
	 */
	public PreparedStatement obterPreparedStatement(String sql) throws SQLException {
		return stmt = conn.prepareStatement(sql);
	}
	
	/** Executa o comando SQL armazenado no <code>PreparedStatement</code>
	 * @return um <code>ResultSet</code> que cont�m os dados produzidos pelo comando SQL executado
	 * @throws SQLException poss�vel erro gerado por m� configura��o do banco de dados
	 * @see ResultSet
	 */
	public ResultSet obterResultSet() throws SQLException {
		return stmt.executeQuery();
	}
	
	/** Fecha o executador de comandos SQL
	 * @throws SQLException poss�vel erro gerado pela impossibilidade de fechar o <code>PreparedStatement</code>
	 */
	public void fecharPreparedStatement() throws SQLException {
		stmt.close();
	}
	
	/** Fecha a conex�o com o banco de dados
	 * @throws SQLException poss�vel erro gerado pela impossibilidade de fechar a <code>Connection</code>
	 */
	public void fecharConexao() throws SQLException {
		conn.close();
	}
	
	/** Fecha um <code>ResultSet</code> que cont�m os dados produzidos por um comando SQL executado
	 * @param rSet <code>ResultSet</code> que cont�m os dados produzidos por um comando SQL executado
	 * @throws SQLException poss�vel erro gerado pela impossibilidade de fechar o <code>ResultSet</code>
	 */
	public static void fecharResultSet(ResultSet rSet) throws SQLException {
		rSet.close();
	}
	
	/** Fecha a conex�o e o executador de comandos SQL
	 * @throws SQLException poss�vel erro gerado pela impossibilidade de fechar a <code>Connection</code> ou o <code>PreparedStatement</code>
	 */
	public void fecharTudo() throws SQLException {
		fecharPreparedStatement();
		fecharConexao();
	}
	
	// Executa arquivos .sql
	/** Abre e executa os comando de um arquivo SQL
	 * @param enderecoArqSQL <code>String</code> com o endereco do arquivo sql
	 * @return <code>boolean</code> com <code>true</code> caso o arquivo tenha sido aberto e executado e <code>false</code> caso contr�rio
	 * @throws SQLException poss�vel erro gerado por m� configura��o do banco de dados
	 * @throws IOException poss�vel erro ao abrir o arquivo
	 */
	protected boolean abrirArquivoSQL(String enderecoArqSQL) throws SQLException, IOException {
		File arquivo = new File(enderecoArqSQL);
		SqlFile arquivoSql = new SqlFile(arquivo);
		boolean abriu = true;
		
		arquivoSql.setConnection(conn);
		
		try{
			arquivoSql.execute();
			abriu = true;
		}
		catch(SqlToolError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			abriu = false;
		}
		
		arquivoSql.closeReader();
		
		return abriu;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	protected void finalize() throws Throwable {
		fecharTudo();
	}
	
} // class DaoHelper

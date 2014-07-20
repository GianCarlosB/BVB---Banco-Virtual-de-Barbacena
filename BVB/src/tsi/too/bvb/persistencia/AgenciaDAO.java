package tsi.too.bvb.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tsi.too.bvb.entidades.agencia.Agencia;
import tsi.too.bvb.gui.JanelaPopUpErro;

/** Classe para manipular os dados das ag�ncias no banco de dados
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 *
 */
public class AgenciaDAO {
	
	/** Cria uma <code>AgenciaDAO</code> para realizar o CRUD da ag�ncia
	 */
	public AgenciaDAO() {
		super();
	}

	/** Insere uma ag�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param agencia <code>Agencia</code> que ser� inserida no banco de dados
	 * @return <code>boolean</code> com <code>true</code> caso tenha inserido com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see Agencia
	 */
	public boolean criar(BancoDeDadosDAO bdDao, Agencia agencia) {
		final String SQL = "INSERT INTO agencia VALUES (?, ?)";

		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setInt(1, agencia.getCodAgencia());
			bdDao.getStmt().setString(2, agencia.getDescricao());
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Ag�ncia inserida");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}
	
	/** Obt�m o pr�ximo valor do c�digo da ag�ncia de sua respectiva sequ�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @return <code>int</code> com o pr�ximo valor do c�digo da ag�ncia
	 * 
	 * @see BancoDeDadosDAO
	 */
	public int proximoValorSequencia(BancoDeDadosDAO bdDao) {
		final String SQL = "CALL NEXT VALUE FOR seq_agencia";
		int proximoValor = 0;
		
		try {
			bdDao.obterPreparedStatement(SQL);
			ResultSet rSet = bdDao.obterResultSet();
			
			rSet.next();
			
			proximoValor = rSet.getInt(1);
			System.out.println("Pr�xima chave ag�ncia: " + proximoValor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return proximoValor;
	}
	
	/** Pesquisa uma ag�ncia pelo c�digo no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param codigo <code>String</code> refer�nte ao c�digo da ag�ncia que ser� procurado
	 * @return <code>Agencia</code> com os dados da ag�ncia encontrada ou <code>null</code> caso n�o encontre
	 * 
	 * @see BancoDeDadosDAO
	 * @see Agencia
	 */
	public Agencia pesquisarCodigo(BancoDeDadosDAO bdDao, String codigo) {
		Agencia agencia = new Agencia();
		final String SQL = "SELECT * FROM agencia WHERE codAgencia = ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, codigo);
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			agencia.setCodAgencia(rSet.getInt(1));
			agencia.setDescricao(rSet.getString(2));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return agencia;
	}
	
	/** Pesquisa as ag�ncia pela descri��o no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param descricao <code>String</code> refer�nte a descri��o que ser� procurada
	 * @return Uma lista de <code>Agencia</code> com os dados da(s) ag�ncia(s) encontrada(s) ou <code>null</code> caso n�o encontre nem uma
	 * 
	 * @see BancoDeDadosDAO
	 * @see Agencia
	 * @see List
	 */
	public List<Agencia> pesquisarDescricao(BancoDeDadosDAO bdDao, String descricao) {
		List<Agencia> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM agencia WHERE LCASE (descricao) LIKE ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, "%" + descricao.toLowerCase() + "%");
			ResultSet rSet = bdDao.obterResultSet();
			
			while(rSet.next()) {
				Agencia agencia = new Agencia(rSet.getInt(1), rSet.getString(2));
				
				lista.add(agencia);
			}
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Altera a descri��o de uma ag�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param agencia <code>Agencia</code> cujo c�digo � refer�nte ao c�digo da ag�ncia que ser� alterada
	 * @param descricao <code>String</code> refer�nte a descri��o que ser� alterada
	 * @return <code>boolean</code> com <code>true</code> caso tenha alterado com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see Agencia
	 */
	public boolean alterarDescricao(BancoDeDadosDAO bdDao, Agencia agencia, String descricao) {
		final String SQL = "UPDATE agencia SET descricao = ? WHERE codAgencia = ?";
		
		try{
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, descricao);
			bdDao.getStmt().setInt(2, agencia.getCodAgencia());
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Descri��o da ag�ncia atualizada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}
	
	/** Exclui uma ag�ncia do banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param codigo <code>int</code> refer�nte ao c�digo da ag�ncia que ser� exclu�da
	 * @return <code>boolean</code> com <code>true</code> caso tenha exclu�do com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 */
	public boolean excluir(BancoDeDadosDAO bdDao, String codigo) {
		final String SQL = "DELETE FROM agencia WHERE codAgencia = ?";

		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, codigo);
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Ag�ncia deletada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}

} // class AgenciaDAO

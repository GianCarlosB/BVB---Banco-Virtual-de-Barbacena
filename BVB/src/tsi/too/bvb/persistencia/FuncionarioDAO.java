package tsi.too.bvb.persistencia;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.entidades.tiposenumerados.TipoUsuario;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.validacoes.Criptografia;

/** Classe para manipular os dados dos funcion�rios no banco de dados
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 *
 */
public class FuncionarioDAO {
	
	/** Cria um <code>FuncionarioDAO</code> para realizar o CRUD do funcion�rio
	 */
	public FuncionarioDAO() {
		super();
	}

	/** Insere um funcion�rio no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param funcionario <code>Funcionario</code> que ser� inserido no banco de dados
	 * @return <code>boolean</code> com <code>true</code> caso tenha inserido com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see Funcionario
	 */
	public boolean criar(BancoDeDadosDAO bdDao, Funcionario funcionario) {
		final String SQL = "INSERT INTO funcionario VALUES (?, ?, ?)";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, funcionario.getNomeUsuario());
			bdDao.getStmt().setString(2, Criptografia.converterSenhaParaMD5(funcionario.getSenha()));
			bdDao.getStmt().setString(3, Character.toString(funcionario.getTipoUsuario().getCaractere()));
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Funcion�rio inserido");
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}
	
	/** Pesquisa um funcion�rio pelo login no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param login <code>String</code> refer�nte ao login do funcion�rio que ser� procurado
	 * @return <code>Funcionario</code> com os dados do funcion�rio encontrado ou <code>null</code> caso n�o encontre
	 * 
	 * @see BancoDeDadosDAO
	 * @see Funcionario
	 */
	public Funcionario pesquisarLoginUnico(BancoDeDadosDAO bdDao, String login) {
		Funcionario funcionario = new Funcionario();
		final String SQL = "SELECT * FROM funcionario WHERE LCASE (nomeUsuario) LIKE ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, login.toLowerCase());
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			funcionario.setNomeUsuario(rSet.getString(1));
			funcionario.setSenha(rSet.getString(2));
			funcionario.setTipoUsuario(TipoUsuario.obterTipoUsuario(rSet.getString(3).charAt(0)));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return funcionario;
	}
	
	/** Pesquisa os funcion�rios pelo login no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param login <code>String</code> refer�nte ao login do funcion�rio que ser� procurado
	 * @return Uma lista de <code>Funcionario</code> com os dados do(s) funcion�rio(s) encontrado(s) ou <code>null</code> caso n�o encontre nem um
	 * 
	 * @see BancoDeDadosDAO
	 * @see Funcionario
	 * @see List
	 */
	public List<Funcionario> pesquisarLogin(BancoDeDadosDAO bdDao, String login) {
		List<Funcionario> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM funcionario WHERE LCASE (nomeUsuario) LIKE ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, "%" + login.toLowerCase() + "%");
			ResultSet rSet = bdDao.obterResultSet();
			
			while(rSet.next()) {
				Funcionario funcionario = new Funcionario(rSet.getString(1), rSet.getString(2),
						                                  TipoUsuario.obterTipoUsuario(rSet.getString(3).charAt(0)));
				
				lista.add(funcionario);
			}
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Pesquisa os funcion�rios pelo tipo no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param tipoUsuario <code>TipoUsuario</code> refer�nte ao tipo do funcion�rio que ser� procurado
	 * @return Uma lista de <code>Funcionario</code> com os dados do(s) funcion�rio(s) encontrado(s) ou <code>null</code> caso n�o encontre nem um
	 * 
	 * @see BancoDeDadosDAO
	 * @see Funcionario
	 * @see TipoUsuario
	 * @see List
	 */
	public List<Funcionario> pesquisarTipo(BancoDeDadosDAO bdDao, TipoUsuario tipoUsuario) {
		List<Funcionario> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM funcionario WHERE UCASE (tipoUsuario) LIKE ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, "%" + tipoUsuario.getCaractere() + "%");
			ResultSet rSet = bdDao.obterResultSet();
			
			while(rSet.next()) {
				Funcionario funcionario = new Funcionario(rSet.getString(1), rSet.getString(2), TipoUsuario.obterTipoUsuario(rSet.getString(3).charAt(0)));
				
				lista.add(funcionario);
			}
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Altera a senha de um funcion�rio no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param funcionario <code>Funcionario</code> cujo login � refer�nte ao login do funcion�rio que ser� alterado, e o senha 
	 * refer�nte a nova senha do usu�rio
	 * @return <code>boolean</code> com <code>true</code> caso tenha alterado com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see Funcionario
	 */
	public boolean alterarSenha(BancoDeDadosDAO bdDao, Funcionario funcionario) {
		final String SQL = "UPDATE funcionario SET senha = ? WHERE LCASE (nomeUsuario) LIKE ?";

		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, funcionario.getSenha());
			bdDao.getStmt().setString(2, funcionario.getNomeUsuario().toLowerCase());
			bdDao.getStmt().executeUpdate();

			System.out.println("Senha do funcion�rio atualizada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}
	
	/** Altera o tipo de um funcion�rio no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param funcionario <code>Funcionario</code> cujo login � refer�nte ao login do funcion�rio que ser� alterado
	 * @param tipoUsuario <code>TipoUsuario</code> refer�nte ao novo tipo do funcion�rio
	 * @return <code>boolean</code> com <code>true</code> caso tenha alterado com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see Funcionario
	 * @see TipoUsuario
	 */
	public boolean alterarTipo(BancoDeDadosDAO bdDao, Funcionario funcionario, TipoUsuario tipoUsuario) {
		final String SQL = "UPDATE funcionario SET tipoUsuario = ? WHERE LCASE (nomeUsuario) = ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, Character.toString(tipoUsuario.getCaractere()));
			bdDao.getStmt().setString(2, funcionario.getNomeUsuario().toLowerCase());
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Tipo do funcion�rio atualizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}

	/** Exclui um cleinte do banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param login <code>String</code> refer�nte ao login do funcin�rio que ser� exclu�do
	 * @return <code>boolean</code> com <code>true</code> caso tenha exclu�do com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 */
	public boolean excluir(BancoDeDadosDAO bdDao, String login) {
		final String SQL = "DELETE FROM funcionario WHERE LCASE (nomeUsuario) LIKE ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, "%" + login.toLowerCase() + "%");
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Funcion�rio deletado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}
	
} // class FuncionarioDAO

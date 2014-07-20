package tsi.too.bvb.persistencia;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tsi.too.bvb.entidades.contabancaria.ContaBancaria;
import tsi.too.bvb.entidades.tiposenumerados.TipoConta;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.validacoes.Criptografia;

/** Classe para manipular os dados das contas banc�rias no banco de dados
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 *
 */
public class ContaBancariaDAO {

	/** Cria uma <code>ContaBancariaDAO</code> para realizar o CRUD da conta banc�ria
	 */
	public ContaBancariaDAO() {
		super();
	}
	
	/** Insere uma conta banc�ria no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param contaBancaria <code>ContaBancaria</code> que ser� inserida no banco de dados
	 * @return <code>boolean</code> com <code>true</code> caso tenha inserido com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 */
	public boolean criar(BancoDeDadosDAO bdDao, ContaBancaria contaBancaria) {
		final String SQL = "INSERT INTO conta_bancaria VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setInt(1, contaBancaria.getNumConta());
			bdDao.getStmt().setInt(2, contaBancaria.getCodAgencia());
			bdDao.getStmt().setInt(3, contaBancaria.getTipoConta().getNumero());
			bdDao.getStmt().setString(4, contaBancaria.getCpf());
			bdDao.getStmt().setBoolean(5, contaBancaria.isContaSalario());
			bdDao.getStmt().setDate(6, new java.sql.Date(contaBancaria.getDataAbertura().getTime()));
			bdDao.getStmt().setDouble(7, contaBancaria.getSaldo());
			bdDao.getStmt().setString(8, Criptografia.converterSenhaParaMD5(contaBancaria.getSenhaNumerica()));
			bdDao.getStmt().setString(9, Criptografia.converterSenhaParaMD5(contaBancaria.getSenhaAlfabetica()));
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Conta Banc�ria inserida");
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}
	
	/** Obt�m o pr�ximo valor do n�mero da conta banc�ria de sua respectiva sequ�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @return <code>int</code> com o pr�ximo valor do n�mero da conta banc�ria
	 * 
	 * @see BancoDeDadosDAO
	 */
	public int proximoValorSequencia(BancoDeDadosDAO bdDao) {
		final String SQL = "CALL NEXT VALUE FOR seq_conta_bancaria";
		int proximoValor = 0;
		
		try {
			bdDao.obterPreparedStatement(SQL);
			ResultSet rSet = bdDao.obterResultSet();
			
			rSet.next();
			
			proximoValor = rSet.getInt(1);
			System.out.println("Pr�xima chave conta banc�ria: " + proximoValor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return proximoValor;
	}
	
	/** Pesquisa uma conta banc�ria pelo n�mero no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param numero <code>String</code> refer�nte ao n�mero que ser� procurado
	 * @return <code>ContaBancaria</code> com os dados da conta banc�ria encontrada ou <code>null</code> caso n�o encontre
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 */
	public ContaBancaria pesquisarNumConta(BancoDeDadosDAO bdDao, String numero) {
		ContaBancaria contaBancaria = new ContaBancaria();
		final String SQL = "SELECT * FROM conta_bancaria WHERE numeroConta = ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, numero);
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			contaBancaria.setNumConta(rSet.getInt(1));
			contaBancaria.setCodAgencia(rSet.getInt(2));
			contaBancaria.setTipoConta(TipoConta.obterTipoConta(rSet.getInt(3)));
			contaBancaria.setCpf(rSet.getString(4));
			contaBancaria.setContaSalario(rSet.getBoolean(5));
			contaBancaria.setDataAbertura(rSet.getDate(6));
			contaBancaria.setSaldo(rSet.getDouble(7));
			contaBancaria.setSenhaNumerica(rSet.getString(8));
			contaBancaria.setSenhaAlfabetica(rSet.getString(9));
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return contaBancaria;
	}
	
	/** Pesquisa as contas correntes de um cliente no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param cpf <code>String</code> refer�nte ao cpf que ser� procurado
	 * @param tipoConta <code>TipoConta</code> refer�nte ao tipo que ser� procurado
	 * @return Uma lista de <code>ContaBancaria</code> com os dados da(s) conta(s) encontrada(s) ou <code>null</code> caso n�o encontre nem uma
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 * @see TipoConta
	 * @see List
	 */
	public List<ContaBancaria> pesquisarCorrentista(BancoDeDadosDAO bdDao, String cpf, TipoConta tipoConta) {
		List<ContaBancaria> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM conta_bancaria WHERE CPF = ?  AND tipoConta = ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, cpf);
			bdDao.getStmt().setInt(2, tipoConta.getNumero());
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			do {
				ContaBancaria contaBancaria = new ContaBancaria(rSet.getInt(1), rSet.getInt(2), TipoConta.obterTipoConta(rSet.getInt(3)),
						                                        rSet.getString(4), rSet.getDate(6), rSet.getString(8), rSet.getString(9),
						                                        rSet.getBoolean(5), rSet.getDouble(7));
				
				lista.add(contaBancaria);
			}while(rSet.next());
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Pesquisa as contas banc�rias que foram abertas em um determinado m�s no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param data <code>Date</code> refer�nte ao m�s que ser� procurado
	 * @return Uma lista de <code>ContaBancaria</code> com os dados da(s) conta(s) encontrada(s) ou <code>null</code> caso n�o encontre nem uma
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 * @see Date
	 * @see List
	 */
	public List<ContaBancaria> pesquisarContas(BancoDeDadosDAO bdDao, Date data) {
		List<ContaBancaria> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM conta_bancaria WHERE MONTH(dataAbertura) = " +
				           "MONTH(CAST(? AS DATE)) AND YEAR(dataAbertura) = YEAR(CAST(? AS DATE))";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setDate(1, new java.sql.Date(data.getTime()));
			bdDao.getStmt().setDate(2, new java.sql.Date(data.getTime()));
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			do {
				ContaBancaria contaBancaria = new ContaBancaria(rSet.getInt(1), rSet.getInt(2), TipoConta.obterTipoConta(rSet.getInt(3)),
						                                        rSet.getString(4), rSet.getDate(6), rSet.getString(8), rSet.getString(9),
						                                        rSet.getBoolean(5), rSet.getDouble(7));

				lista.add(contaBancaria);
			}while(rSet.next());
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Pesquisa as datas em que contas banc�rias foram abertas, em um determinado per�odo e ag�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param codigo <code>String</code> refer�nte ao c�digo da ag�ncia que ser� procurado
	 * @param dataIni <code>Date</code> refer�nte a data inicial que ser� procurada
	 * @param dataFin <code>Date</code> refer�nte a data final que ser� procurada
	 * @return Uma cole��o set de <code>Date</code> com a(s) data(s) encontrada(s) ou <code>null</code> caso n�o encontre nem uma
	 * 
	 * @see BancoDeDadosDAO
	 * @see Date
	 * @see Set
	 */
	public Set<Date> pesquisarDatasContasAgencia(BancoDeDadosDAO bdDao, String codigo, Date dataIni, Date dataFin) {
		Set<Date> set = new HashSet<>();
		final String SQL = "SELECT * FROM conta_bancaria WHERE codAgencia = ? AND MONTH(dataAbertura) BETWEEN MONTH(CAST(? AS DATE)) AND " +
				           "MONTH(CAST(? AS DATE)) AND YEAR(dataAbertura) BETWEEN YEAR(CAST(? AS DATE)) AND YEAR(CAST(? AS DATE))";
		Calendar cal = Calendar.getInstance();
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, codigo);
			bdDao.getStmt().setDate(2, new java.sql.Date(dataIni.getTime()));
			bdDao.getStmt().setDate(3, new java.sql.Date(dataFin.getTime()));
			bdDao.getStmt().setDate(4, new java.sql.Date(dataIni.getTime()));
			bdDao.getStmt().setDate(5, new java.sql.Date(dataFin.getTime()));
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			 do {
				cal.setTime(rSet.getDate(6));
				cal.set(Calendar.DAY_OF_MONTH, 1);
				set.add(cal.getTime());
			}while(rSet.next());
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return set;
	}
	
	/** Pesquisa as contas banc�rias que foram abertas em uma determinada ag�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param codigo <code>String</code> refer�nte ao c�digo da ag�ncia que ser� procurado
	 * @return Uma lista de <code>ContaBancaria</code> com os dados da(s) conta(s) encontrada(s) ou <code>null</code> caso n�o encontre nem uma
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 * @see List
	 */
	public List<ContaBancaria> pesquisarContasAgencia(BancoDeDadosDAO bdDao, String codigo) {
		List<ContaBancaria> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM conta_bancaria WHERE codAgencia = ?";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, codigo);
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			 do {
				ContaBancaria contaBancaria = new ContaBancaria(rSet.getInt(1), rSet.getInt(2), TipoConta.obterTipoConta(rSet.getInt(3)),
						                                        rSet.getString(4), rSet.getDate(6), rSet.getString(8), rSet.getString(9),
						                                        rSet.getBoolean(5), rSet.getDouble(7));
				
				lista.add(contaBancaria);
			}while(rSet.next());
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Pesquisa as contas banc�rias que foram abertas em um determinado m�s e ag�ncia no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param codigo <code>String</code> refer�nte ao c�digo da ag�ncia que ser� procurado
	 * @param data <code>Date</code> refer�nte ao m�s que ser� procurado
	 * @return Uma lista de <code>ContaBancaria</code> com os dados da(s) conta(s) encontrada(s) ou <code>null</code> caso n�o encontre nem uma
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 * @see List
	 */
	public List<ContaBancaria> pesquisarContasAgencia(BancoDeDadosDAO bdDao, String codigo, Date data) {
		List<ContaBancaria> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM conta_bancaria WHERE codAgencia = ? AND MONTH(dataAbertura) = " +
				           "MONTH(CAST(? AS DATE)) AND YEAR(dataAbertura) = YEAR(CAST(? AS DATE))";
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, codigo);
			bdDao.getStmt().setDate(2, new java.sql.Date(data.getTime()));
			bdDao.getStmt().setDate(3, new java.sql.Date(data.getTime()));
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			do {
				ContaBancaria contaBancaria = new ContaBancaria(rSet.getInt(1), rSet.getInt(2), TipoConta.obterTipoConta(rSet.getInt(3)),
						                                        rSet.getString(4), rSet.getDate(6), rSet.getString(8), rSet.getString(9),
						                                        rSet.getBoolean(5), rSet.getDouble(7));

				lista.add(contaBancaria);
			}while(rSet.next());
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
	/** Altera o saldo de uma conta banc�ria no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param contaBancaria <code>ContaBancaria</code> cujo n�mero � refer�nte ao n�mero da conta que ser� alterada, e o saldo 
	 * refer�nte ao novo saldo da conta
	 * @return <code>boolean</code> com <code>true</code> caso tenha alterado com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 */
	public boolean alterarSaldo(BancoDeDadosDAO bdDao, ContaBancaria contaBancaria) {
		final String SQL = "UPDATE conta_bancaria SET saldo = ? WHERE numeroConta = ?";

		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setDouble(1, contaBancaria.getSaldo());
			bdDao.getStmt().setInt(2, contaBancaria.getNumConta());
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Saldo da Conta Banc�ria Atualizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return true;
		}
		
		return false;
	}
	
	/** Altera o tipo de uma conta banc�ria no banco de dados
	 * 
	 * @param bdDao <code>BancoDeDadosDAO</code> refer�nte a inst�ncia do banco de dados onde os m�todos de acesso ao banco est�o localizados
	 * @param contaBancaria <code>ContaBancaria</code> cujo n�mero � refer�nte ao n�mero da conta que ser� alterada, e o tipo 
	 * refer�nte ao novo tipo da conta
	 * @return <code>boolean</code> com <code>true</code> caso tenha alterado com sucesso, e <code>false</code> caso contr�rio
	 * 
	 * @see BancoDeDadosDAO
	 * @see ContaBancaria
	 */
	public boolean alterarTipoConta(BancoDeDadosDAO bdDao, ContaBancaria contaBancaria) {
		final String SQL = "UPDATE conta_bancaria SET tipoConta = ? WHERE numeroConta = ?";

		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setInt(1, contaBancaria.getTipoConta().getNumero());
			bdDao.getStmt().setInt(2, contaBancaria.getNumConta());
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Tipo da Conta Banc�ria Atualizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			return false;
		}
		
		return true;
	}

} // class ContaBancariaDAO

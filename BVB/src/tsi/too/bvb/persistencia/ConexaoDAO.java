package tsi.too.bvb.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tsi.too.bvb.entidades.conexao.Conexao;
import tsi.too.bvb.gui.JanelaPopUpErro;

public class ConexaoDAO {
	
	public ConexaoDAO() {
		super();
	}

	public void criar(BancoDeDadosDAO bdDao, Conexao conexao) {
		final String SQL = "INSERT INTO conexao VALUES (?, ?, ?)";

		try {
			Calendar cal = Calendar.getInstance();
			
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, conexao.getNomeUsuario());
			cal.setTime(conexao.getDataHoraInicial());
			bdDao.getStmt().setDate(2, new java.sql.Date(cal.getTimeInMillis()));
			cal.setTime(conexao.getDataHoraFinal());
			bdDao.getStmt().setDate(3, new java.sql.Date(cal.getTimeInMillis()));
			bdDao.getStmt().executeUpdate();
			
			System.out.println("Conex�o inserida");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
	}

	public List<Conexao> pesquisarConexao(BancoDeDadosDAO bdDao, String login) {
		List<Conexao> lista = new ArrayList<>();
		final String SQL = "SELECT * FROM conexao WHERE LCASE (nomeUsuario) LIKE ?";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		
		try {
			bdDao.obterPreparedStatement(SQL);
			bdDao.getStmt().setString(1, login.toLowerCase());
			ResultSet rSet = bdDao.obterResultSet();
			
			if(!rSet.next()) return null;
			
			do {
				Conexao conexao = new Conexao(rSet.getString(1), new Date(format.parse(rSet.getString(2)).getTime()),
						          new Date(format.parse(rSet.getString(3)).getTime()));
				
				lista.add(conexao);
			}while(rSet.next());
			
			BancoDeDadosDAO.fecharResultSet(rSet);
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			new JanelaPopUpErro(null, "BVB - ERRO", e);
		}
		
		return lista;
	}
	
} // class ConexaoDAO

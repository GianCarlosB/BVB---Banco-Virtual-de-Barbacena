package tsi.too.bvb.entidades.conexao;

import java.text.SimpleDateFormat;
import java.util.Date;

/** A classe <code>Conexao</code> manipula dados refer�nte as conex�es dos funcion�rios do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Conexao {
	
	private String nomeUsuario;
	private Date dataHoraInicial;
	private Date dataHoraFinal;
	
	/** Cria uma conex�o sem nem um atributo
	 */
	public Conexao() {
		super();
	}

	/** Cria uma conex�o com o nome do usu�rio, data inicial e data final
	 * 
	 * @param nomeUsuario <code>String</code> com nome do usu�rio da conex�o
	 * @param dataHoraInicial <code>Date</code> com a data inicial da conex�o
	 * @param dataHoraFinal <code>Date</code> com a data final da conex�o
	 */
	public Conexao(String nomeUsuario, Date dataHoraInicial, Date dataHoraFinal) {
		this();
		this.nomeUsuario = nomeUsuario;
		this.dataHoraInicial = dataHoraInicial;
		this.dataHoraFinal = dataHoraFinal;
	}

	/** Retorna uma <code>String</code> com o nome do usu�rio da conex�o
	 * @return <code>String</code> com o nome do usu�rio da conex�o
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/** Muda o nome do usu�rio da conex�o
	 * @param nomeUsuario <code>String</code> com o novo nome do usu�rio da conex�o
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/** Retorna um <code>Date</code> com a data inicial da conex�o
	 * @return <code>Date</code> com a data inicial da conex�o
	 */
	public Date getDataHoraInicial() {
		return dataHoraInicial;
	}

	/** Muda a data inicial da conex�o
	 * @param dataHoraInicial <code>Date</code> com a nova data inicial da conex�o
	 */
	public void setDataHoraInicial(Date dataHoraInicial) {
		this.dataHoraInicial = dataHoraInicial;
	}

	/** Retorna um <code>Date</code> com a data final da conex�o
	 * @return <code>Date</code> com a data final da conex�o
	 */
	public Date getDataHoraFinal() {
		return dataHoraFinal;
	}

	/** Muda a data final da conex�o
	 * @param dataHoraFinal <code>Date</code> com a nova data final da conex�o
	 */
	public void setDataHoraFinal(Date dataHoraFinal) {
		this.dataHoraFinal = dataHoraFinal;
	}

	/** Retorna uma <code>String</code> com todos os dados da conex�o
	 * @return <code>String</code> com todos os dados da conex�o
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Nome do Usu�rio: ").append(nomeUsuario)
				.append("\n Data Inicial: ").append(new SimpleDateFormat("dd/MM/yyyy").format(dataHoraInicial))
			    .append("\n Hora Inicial: ").append(new SimpleDateFormat("HH:mm:ss").format(dataHoraInicial))
			    .append("\n Data Final: ").append(new SimpleDateFormat("dd/MM/yyyy").format(dataHoraFinal))
				.append("\n Hora Final: ").append(new SimpleDateFormat("HH:mm:ss").format(dataHoraFinal));
		return builder.toString();
	}
	
} // class Conexao

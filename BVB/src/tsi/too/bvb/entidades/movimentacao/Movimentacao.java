package tsi.too.bvb.entidades.movimentacao;

import java.text.SimpleDateFormat;
import java.util.Date;

import tsi.too.bvb.entidades.tiposenumerados.TipoConta;
import tsi.too.bvb.entidades.tiposenumerados.TipoOperacao;

/** A classe <code>Movimentacao</code> manipula dados refer�nte as movimenta��es das contas banc�rias do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Movimentacao {
	
	private int numConta;
	private int codAgencia;
	private TipoConta tipoConta;
	private TipoOperacao tipoOperacao;
	private Date dataHora;
	private double valor;
	
	/** Cria uma movimenta��o sem nem um atributo
	 */
	public Movimentacao() {
		super();
	}
	
	/** Cria uma movimenta��o com o n�mero da conta banc�ria, c�digo da ag�ncia, tipo da conta, tipo da opera��o, data, hora e valor
	 * 
	 * @param numConta <code>int</code> com o n�mero da conta banc�ria movimentada
	 * @param codAgencia <code>int</code> com o c�digo da ag�ncia da conta banc�ria movimentada
	 * @param tipoConta <code>TipoConta</code> com o tipo da conta banc�ria movimentada
	 * @param tipoOperacao <code>TipoOperacao</code> com o tipo de opera��o da movimenta��o
	 * @param dataHora <code>Date</code> com a data e a hora da movimenta��o
	 * @param valor <code>double</code> com o valor da movimenta��o
	 * 
	 * @see TipoConta
	 * @see TipoOperacao
	 */
	public Movimentacao(int numConta, int codAgencia, TipoConta tipoConta,
			TipoOperacao tipoOperacao, Date dataHora, double valor) {
		this();
		this.numConta = numConta;
		this.codAgencia = codAgencia;
		this.tipoConta = tipoConta;
		this.tipoOperacao = tipoOperacao;
		this.dataHora = dataHora;
		this.valor = valor;
	}

	/** Retorna um <code>int</code> com o n�mero da conta banc�ria movimentada
	 * @return <code>int</code> com o n�mero da conta banc�ria movimentada
	 */
	public int getCodAgencia() {
		return codAgencia;
	}

	/** Muda o n�mero da conta banc�ria movimentada
	 * @param codAgencia <code>int</code> com o novo n�mero da conta banc�ria movimentada
	 */
	public void setCodAgencia(int codAgencia) {
		this.codAgencia = codAgencia;
	}

	/** Retorna um <code>int</code> com o c�digo da ag�ncia da conta banc�ria movimentada
	 * @return <code>int</code> com o c�digo da ag�ncia da conta banc�ria movimentada
	 */
	public int getNumConta() {
		return numConta;
	}

	/** Muda o c�digo da ag�ncia da conta banc�ria movimentada
	 * @param numConta <code>int</code> com o novo c�digo da ag�ncia da conta banc�ria movimentada
	 */
	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	/** Retorna um <code>TipoConta</code> com o tipo da conta banc�ria movimentada
	 * @return <code>TipoConta</code> com o tipo da conta banc�ria movimentada
	 */
	public TipoConta getTipoConta() {
		return tipoConta;
	}

	/** Muda o tipo da conta banc�ria movimentada
	 * @param tipoConta <code>TipoConta</code> com o novo tipo da conta banc�ria movimentada
	 */
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	/** Retorna um <code>TipoOperacao</code> com o tipo de opera��o da movimenta��o
	 * @return <code>TipoOperacao</code> com o novo tipo de opera��o da movimenta��o
	 */
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	/** Muda o tipo de opera��o da movimenta��o
	 * @param tipoOperacao <code>TipoOperacao</code> com o novo tipo de opera��o da movimenta��o
	 */
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	/** Retorna um <code>Date</code> com a data e a hora da movimenta��o
	 * @return <code>Date</code> com a data e a hora da movimenta��o
	 */
	public Date getDataHora() {
		return dataHora;
	}

	/** Muda a data e a hora da movimenta��o
	 * @param dataHora <code>Date</code> com a nova data e a hora da movimenta��o
	 */
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	/** Retorna um <code>double</code> com o valor da movimenta��o
	 * @return <code>double</code> com o valor da movimenta��o
	 */
	public double getValor() {
		return valor;
	}

	/** Muda o valor da movimenta��o
	 * @param valor <code>double</code> com o novo valor da movimenta��o
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/** Retorna uma <code>String</code> com todos os dados da movimenta��o
	 * @return <code>String</code> com todos os dados da movimenta��o
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" C�digo da Ag�ncia: ").append(codAgencia)
				.append("\n N�mero da Conta: ").append(numConta).append("\n Tipo da Conta: ")
				.append(tipoConta).append("\n Tipo da Opera��o: ")
				.append(tipoOperacao.getDescricao()).append("\n Data: ").append(new SimpleDateFormat("dd/MM/yyyy").format(dataHora))
				.append("\n Hora: ").append(new SimpleDateFormat("HH:mm:ss").format(dataHora)).append("\n Valor: ")
				.append(valor);
		return builder.toString();
	}
	
} // class Movimentacao

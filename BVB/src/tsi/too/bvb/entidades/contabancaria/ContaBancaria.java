package tsi.too.bvb.entidades.contabancaria;

import java.text.SimpleDateFormat;
import java.util.Date;

import tsi.too.bvb.entidades.Mascara;
import tsi.too.bvb.entidades.tiposenumerados.TipoConta;
import tsi.too.bvb.validacoes.ValidarDados;

/** A classe <code>ContaBancaria</code> manipula dados refer�nte as contas banc�rias do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class ContaBancaria {
	
	private int numConta;
	private int codAgencia;
	private TipoConta tipoConta;
	private Date dataAbertura;
	private String cpf;
	private String senhaNumerica;
	private String senhaAlfabetica;
	private boolean contaSalario;
	private double saldo;
	
	/** Cria uma conta banc�ria sem nem um atributo
	 */
	public ContaBancaria() {
		super();
	}

	/** Cria uma conta banc�ria com o n�mero da conta, c�digo d ag�ncia, tipo da conta, cpf do cliente, data de abertura, 
	 * senha num�rica, senha alfab�tica, se � ou n�o conta sal�rio e o saldo
	 * 
	 * @param numConta <code>int</code> com o n�mero da conta banc�ria
	 * @param codAgencia <code>int</code> com o c�digo da ag�ncia da conta banc�ria
	 * @param tipoConta <code>TipoConta</code> com o tipo da conta banc�ria
	 * @param cpf <code>String</code> com o cpf do cliente propriet�rio da conta banc�ria
	 * @param dataAbertura <code>Date</code> com a data de abertura da conta banc�ria
	 * @param senhaNumerica <code>String</code> com a senha num�rica da conta banc�ria
	 * @param senhaAlfabetica <code>String</code> com a senha alfab�tica da conta banc�ria
	 * @param contaSalario <code>boolean</code> com <code>true</code> se a conta banc�ria for conta sal�rio e <code>false</code> caso contr�rio
	 * @param saldo <code>double</code> com o saldo da conta banc�ria
	 * 
	 * @see TipoConta
	 */
	public ContaBancaria(int numConta, int codAgencia, TipoConta tipoConta,
			String cpf, Date dataAbertura, String senhaNumerica,
			String senhaAlfabetica, boolean contaSalario, double saldo) {
		this();
		this.numConta = numConta;
		this.codAgencia = codAgencia;
		this.tipoConta = tipoConta;
		this.cpf = cpf;
		this.dataAbertura = dataAbertura;
		this.senhaNumerica = senhaNumerica;
		this.senhaAlfabetica = senhaAlfabetica;
		this.contaSalario = contaSalario;
		this.saldo = saldo;
	}

	/** Retorna um <code>int</code> com o c�digo da ag�ncia da conta banc�ria
	 * @return <code>int</code> com o c�digo da ag�ncia da conta banc�ria
	 */
	public int getCodAgencia() {
		return codAgencia;
	}
	
	/** Muda o c�digo da ag�ncia da conta banc�ria
	 * @param codAgencia <code>int</code> com o novo c�digo da ag�ncia da conta banc�ria
	 */
	public void setCodAgencia(int codAgencia) {
		this.codAgencia = codAgencia;
	}
	
	/** Retorna um <code>int</code> com o n�mero da conta banc�ria
	 * @return <code>int</code> com o n�mero da conta banc�ria
	 */
	public int getNumConta() {
		return numConta;
	}
	
	/** Muda o n�mero da conta banc�ria
	 * @param numConta <code>int</code> com o novo n�mero da conta banc�ria
	 */
	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}
	
	/** Retorna um <code>TipoConta</code> com o tipo da conta banc�ria
	 * @return <code>TipoConta</code> com o tipo da conta banc�ria
	 */
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	
	/** Muda o tipo da conta banc�ria
	 * @param tipoConta <code>TipoConta</code> com o novo tipo da conta banc�ria
	 */
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	/** Retorna um <code>String</code> com o cpf do cliente propriet�rio da conta banc�ria
	 * @return <code>String</code> com o cpf do cliente propriet�rio da conta banc�ria
	 */
	public String getCpf() {
		return cpf;
	}
	
	/** Muda o cpf do cliente propriet�rio da conta banc�ria
	 * @param cpf <code>String</code> com o novo cpf do cliente propriet�rio da conta banc�ria
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/** Retorna um <code>Date</code> com a data de abertura da conta banc�ria
	 * @return <code>Date</code> com a data de abertura da conta banc�ria
	 */
	public Date getDataAbertura() {
		return dataAbertura;
	}
	
	/** Muda a data de abertura da conta banc�ria
	 * @param dataAbertura <code>Date</code> com a nova data de abertura da conta banc�ria
	 */
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	/** Retorna um <code>String</code> com a senha num�rica da conta banc�ria
	 * @return <code>String</code> com a senha num�rica da conta banc�ria
	 */
	public String getSenhaNumerica() {
		return senhaNumerica;
	}
	
	/** Muda a senha num�rica da conta banc�ria
	 * @param senhaNumerica <code>String</code> com a nova senha num�rica da conta banc�ria
	 */
	public void setSenhaNumerica(String senhaNumerica) {
		this.senhaNumerica = senhaNumerica;
	}
	
	/** Retorna um <code>String</code> com a senha alfab�tica da conta banc�ria
	 * @return <code>String</code> com a senha alfab�tica da conta banc�ria
	 */
	public String getSenhaAlfabetica() {
		return senhaAlfabetica;
	}
	
	/** Muda a senha alfab�tica da conta banc�ria
	 * @param senhaAlfabetica <code>String</code> com a nova senha alfab�tica da conta banc�ria
	 */
	public void setSenhaAlfabetica(String senhaAlfabetica) {
		this.senhaAlfabetica = senhaAlfabetica;
	}
	
	/** Retorna um <code>boolean</code> com <code>true</code> se a conta banc�ria for conta sal�rio e <code>false</code> caso contr�rio
	 * @return <code>boolean</code> com <code>true</code> se a conta banc�ria for conta sal�rio e <code>false</code> caso contr�rio
	 */
	public boolean isContaSalario() {
		return contaSalario;
	}
	
	/** Muda a op��o de conta sal�rio para sim <code>true</code> ou n�o <code>false</code> da conta banc�ria
	 * @param contaSalario <code>boolean</code> com <code>true</code> se a conta banc�ria for conta sal�rio, e <code>false</code> caso contr�rio
	 */
	public void setContaSalario(boolean contaSalario) {
		this.contaSalario = contaSalario;
	}
	
	/** Retorna um <code>double</code> com o saldo da conta banc�ria
	 * @return <code>double</code> com o saldo da conta banc�ria
	 */
	public double getSaldo() {
		return saldo;
	}
	
	/** Muda o saldo da conta banc�ria
	 * @param saldo <code>double</code> com a novo saldo da conta banc�ria
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	/** Retorna uma <code>String</code> com todos os dados da conta banc�ria formatados, com o n�mero da conta oculto
	 * @return <code>String</code> com todos os dados da conta banc�ria formatados, com o n�mero da conta oculto
	 */
	public String exibeDadosFormatados() {
		String contaSalario = "N�o";
		String cpf = this.cpf;
		Date dataAbertura = this.dataAbertura;
		
		if(this.contaSalario)
			contaSalario = "Sim";
		
		if(ValidarDados.validarVazio(cpf.trim()))
			cpf = Mascara.formatarString(cpf, "###.###.###-##");
		
		StringBuilder builder = new StringBuilder();
		builder.append(" C�digo da Ag�ncia: ").append(codAgencia)
				.append("\n N�mero da Conta: ").append("XXXXXXXXX").append("\n Tipo da Conta: ")
				.append(tipoConta.getDescricao()).append("\n CPF: ").append(cpf)
				.append("\n Data de Abertura: ").append(new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(dataAbertura))
				.append("\n Senha N�merica: ").append(senhaNumerica)
				.append("\n Senha Alfab�tica: ").append(senhaAlfabetica)
				.append("\n Conta S�lario: ").append(contaSalario)
				.append("\n Saldo: R$").append(saldo);
		return builder.toString();
	}

	/** Retorna uma <code>String</code> com todos os dados da conta banc�ria
	 * @return <code>String</code> com todos os dados da conta banc�ria
	 */
	@Override
	public String toString() {
		String contaSalario = "N�o";
		String cpf = this.cpf;
		Date dataAbertura = this.dataAbertura;
		
		if(this.contaSalario)
			contaSalario = "Sim";
		
		if(ValidarDados.validarVazio(cpf.trim()))
			cpf = Mascara.formatarString(cpf, "###.###.###-##");
		
		StringBuilder builder = new StringBuilder();
		builder.append(" C�digo da Ag�ncia: ").append(codAgencia)
				.append("\n N�mero da Conta: ").append(numConta).append("\n Tipo da Conta: ")
				.append(tipoConta.getDescricao()).append("\n CPF: ").append(cpf)
				.append("\n Data de Abertura: ").append(new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(dataAbertura))
				.append("\n Senha N�merica: ").append(senhaNumerica)
				.append("\n Senha Alfab�tica: ").append(senhaAlfabetica)
				.append("\n Conta S�lario: ").append(contaSalario)
				.append("\n Saldo: R$").append(saldo);
		return builder.toString();
	}
	
} // class ContaBancaria

package tsi.too.bvb.entidades.contabancaria;

import java.text.SimpleDateFormat;
import java.util.Date;

import tsi.too.bvb.entidades.Mascara;
import tsi.too.bvb.entidades.tiposenumerados.TipoConta;

public class ContaBancaria {
	
	private int numConta;
	private int codAgencia;
	private TipoConta tipoConta;
	private Date dataAbertura;
	private String cpf, senhaNumerica, senhaAlfabetica;
	private boolean contaSalario;
	private double saldo;
	
	public ContaBancaria() {
		super();
	}
	
	public ContaBancaria(String senhaNumerica, String senhaAlfabetica) {
		this();
		this.senhaNumerica = senhaNumerica;
		this.senhaAlfabetica = senhaAlfabetica;
	}

	public ContaBancaria(int numConta, int codAgencia, TipoConta tipoConta) {
		this();
		this.numConta = numConta;
		this.codAgencia = codAgencia;
		this.tipoConta = tipoConta;
	}

	public ContaBancaria(String cpf, Date dataAbertura, String senhaNumerica,
			String senhaAlfabetica, boolean contaSalario, double saldo) {
		this();
		this.cpf = cpf;
		this.dataAbertura = dataAbertura;
		this.senhaNumerica = senhaNumerica;
		this.senhaAlfabetica = senhaAlfabetica;
		this.contaSalario = contaSalario;
		this.saldo = saldo;
	}

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

	public int getCodAgencia() {
		return codAgencia;
	}
	
	public void setCodAgencia(int codAgencia) {
		this.codAgencia = codAgencia;
	}
	
	public int getNumConta() {
		return numConta;
	}
	
	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}
	
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public String getSenhaNumerica() {
		return senhaNumerica;
	}
	
	public void setSenhaNumerica(String senhaNumerica) {
		this.senhaNumerica = senhaNumerica;
	}
	
	public String getSenhaAlfabetica() {
		return senhaAlfabetica;
	}
	
	public void setSenhaAlfabetica(String senhaAlfabetica) {
		this.senhaAlfabetica = senhaAlfabetica;
	}
	
	public boolean isContaSalario() {
		return contaSalario;
	}
	
	public void setContaSalario(boolean contaSalario) {
		this.contaSalario = contaSalario;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String exibeDadosFormatados() {
		String contaSalario = "N�o";
		String cpf = this.cpf;
		Date dataAbertura = this.dataAbertura;
		
		if(this.contaSalario)
			contaSalario = "Sim";
		
		StringBuilder builder = new StringBuilder();
		builder.append(" C�digo da Ag�ncia: ").append(codAgencia)
				.append("\n N�mero da Conta: ").append("XXXXXXXXX").append("\n Tipo da Conta: ")
				.append(tipoConta.getDescricao()).append("\n CPF: ").append(Mascara.formatarString(cpf, "###.###.###-##"))
				.append("\n Data de Abertura: ").append(new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(dataAbertura))
				.append("\n Senha N�merica: ").append(senhaNumerica)
				.append("\n Senha Alfab�tica: ").append(senhaAlfabetica)
				.append("\n Conta S�lario: ").append(contaSalario)
				.append("\n Saldo: R$").append(saldo);
		return builder.toString();
	}

	@Override
	public String toString() {
		String contaSalario = "N�o";
		String cpf = this.cpf;
		Date dataAbertura = this.dataAbertura;
		
		if(this.contaSalario)
			contaSalario = "Sim";
		
		StringBuilder builder = new StringBuilder();
		builder.append(" C�digo da Ag�ncia: ").append(codAgencia)
				.append("\n N�mero da Conta: ").append(numConta).append("\n Tipo da Conta: ")
				.append(tipoConta.getDescricao()).append("\n CPF: ").append(Mascara.formatarString(cpf, "###.###.###-##"))
				.append("\n Data de Abertura: ").append(new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(dataAbertura))
				.append("\n Senha N�merica: ").append(senhaNumerica)
				.append("\n Senha Alfab�tica: ").append(senhaAlfabetica)
				.append("\n Conta S�lario: ").append(contaSalario)
				.append("\n Saldo: R$").append(saldo);
		return builder.toString();
	}
	
} // class ContaBancaria

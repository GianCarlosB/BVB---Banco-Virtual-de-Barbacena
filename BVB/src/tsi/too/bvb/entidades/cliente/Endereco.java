package tsi.too.bvb.entidades.cliente;

import tsi.too.bvb.entidades.tiposenumerados.UF;

/** A classe <code>Endereco</code> manipula dados refer�nte aos dados do endere�o dos clientes do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Endereco {
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private UF uf;
	
	/** Cria um endere�o sem nem um atributo
	 */
	public Endereco() {
		super();
	}
	
	/** Cria um endere�o com o logradouro, n�mero, complemento, bairro, cidade, uf e cep
	 * 
	 * @param logradouro <code>String</code> com o logradouro do endere�o
	 * @param numero <code>String</code> com o n�mero do endere�o
	 * @param complemento <code>String</code> com o complemento do endere�o
	 * @param bairro <code>String</code> com bairro do endere�o
	 * @param cidade <code>String</code> com a cidade do endere�o
	 * @param uf <code>UF</code> com o uf do endere�o
	 * @param cep <code>String</code> com o cep do endere�o
	 * 
	 * @see UF
	 */
	public Endereco(String logradouro, String numero, String complemento,
			String bairro, String cidade, UF uf, String cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}

	/** Retorna uma <code>String</code> com o logradouro do endere�o
	 * @return <code>String</code> com o logradouro do endere�o
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/** Muda o logradouro do endere�o
	 * @param logradouro <code>String</code> com o novo logradouro do endere�o
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/** Retorna uma <code>String</code> com o n�mero do endere�o
	 * @return <code>String</code> com o n�mero do endere�o
	 */
	public String getNumero() {
		return numero;
	}

	/** Muda o n�mero do endere�o
	 * @param numero <code>String</code> com o novo n�mero do endere�o
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/** Retorna uma <code>String</code> com o complemento do endere�o
	 * @return <code>String</code> com o complemento do endere�o
	 */
	public String getComplemento() {
		return complemento;
	}

	/** Muda o complemento do endere�o
	 * @param complemento <code>String</code> com o novo complemento do endere�o
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/** Retorna uma <code>String</code> com o bairro do endere�o
	 * @return <code>String</code> com o bairro do endere�o
	 */
	public String getBairro() {
		return bairro;
	}

	/** Muda o bairro do endere�o
	 * @param bairro <code>String</code> com o novo bairro do endere�o
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/** Retorna uma <code>String</code> com a cidade do endere�o
	 * @return <code>String</code> com a cidade do endere�o
	 */
	public String getCidade() {
		return cidade;
	}

	/** Muda a cidade do endere�o
	 * @param cidade <code>String</code> com a nova cidade do endere�o
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Retorna um <code>UF</code> com o uf do endere�o
	 * @return <code>UF</code> com o uf do endere�o
	 */
	public UF getUf() {
		return uf;
	}

	/** Muda o uf do endere�o
	 * @param uf <code>UF</code> com o novo uf do endere�o
	 */
	public void setUf(UF uf) {
		this.uf = uf;
	}

	/** Retorna uma <code>String</code> com o cep do endere�o
	 * @return <code>String</code> com o cep do endere�o
	 */
	public String getCep() {
		return cep;
	}

	/** Muda o cep do endere�o
	 * @param cep <code>String</code> com o novo cep do endere�o
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/** Retorna uma <code>String</code> com todos os dados do endere�o
	 * @return <code>String</code> com todos os dados do endere�o
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n Logradouro: ").append(logradouro)
				.append("\n N�mero: ").append(numero).append("\n Complemento: ")
				.append(complemento).append("\n Bairro: ").append(bairro)
				.append("\n Cidade: ").append(cidade).append("\n UF: ").append(uf.getUf())
				.append("\n CEP: ").append(cep);
		return builder.toString();
	}
	
} // class Endereco

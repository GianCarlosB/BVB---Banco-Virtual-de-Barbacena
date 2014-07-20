package tsi.too.bvb.entidades.cliente;

/** A classe <code>Contato</code> manipula dados refer�nte aos contatos dos clientes do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Contato {
	
	private String telefoneFixo;
	private String telefoneMovel;
	
	/** Cria um contato sem nem um atributo
	 */
	public Contato() {
		super();
	}

	/** Cria um contato com o telefone fixo e o telefone m�vel
	 * 
	 * @param telefoneFixo <code>String</code> com o telefone fixo do contato
	 * @param telefoneMovel <code>String</code> com o telefone m�vel do contato
	 */
	public Contato(String telefoneFixo, String telefoneMovel) {
		this.telefoneFixo = telefoneFixo;
		this.telefoneMovel = telefoneMovel;
	}

	/** Retorna uma <code>String</code> com o telefone fixo do contato
	 * @return <code>String</code> com o telefone fixo do contato
	 */
	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	/** Muda o telefone fixo do contato
	 * @param telefoneFixo <code>String</code> com o novo telefone fixo do contato
	 */
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	/** Retorna uma <code>String</code> com o telefone m�vel do contato
	 * @return <code>String</code> com o telefone m�vel do contato
	 */
	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	/** Muda o telefone n�vel do contato
	 * @param telefoneMovel <code>String</code> com o novo telefone m�vel do contato
	 */
	public void setTelefoneMovel(String telefoneMovel) {
		this.telefoneMovel = telefoneMovel;
	}

	/** Retorna uma <code>String</code> com todos os dados do contato
	 * @return <code>String</code> com todos os dados do contato
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n Telefone Fixo: ").append(telefoneFixo)
				.append("\n Telefone M�vel: ").append(telefoneMovel);
		return builder.toString();
	}
	
} // class Contato

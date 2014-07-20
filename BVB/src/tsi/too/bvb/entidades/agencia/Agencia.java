package tsi.too.bvb.entidades.agencia;

/** A classe <code>Agencia</code> manipula dados refer�nte as ag�ncias do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Agencia {
	
	private int codAgencia;
	private String descricao;
	
	/** Cria uma ag�ncia sem nem um atributo
	 */
	public Agencia() {
		super();
	}
	
	/** Cria uma ag�ncia com o c�digo e a descri��o
	 * 
	 * @param codAgencia <code>int</code> com o c�digo da ag�ncia
	 * @param descricao <code>String</code> com a descri��o da ag�ncia
	 */
	public Agencia(int codAgencia, String descricao) {
		this();
		this.codAgencia = codAgencia;
		this.descricao = descricao;
	}

	/** Retorna um <code>int</code> com o c�digo da ag�ncia
	 * @return <code>int</code> com o c�digo da ag�ncia
	 */
	public int getCodAgencia() {
		return codAgencia;
	}

	/** Muda o c�digo da ag�ncia
	 * @param codAgencia <code>int</code> com o novo c�digo da ag�ncia
	 */
	public void setCodAgencia(int codAgencia) {
		this.codAgencia = codAgencia;
	}

	/** Retorna uma <code>String</code> com a descri��o da ag�ncia
	 * @return <code>String</code> com a descri��o da ag�ncia
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Muda a descri��o da ag�ncia
	 * @param descricao <code>String</code> com a nova descri��o da ag�ncia
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Retorna uma <code>String</code> com todos os dados da ag�ncia
	 * @return <code>String</code> com todos os dados da ag�ncia
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" C�digo da Ag�ncia: ").append(codAgencia)
				.append("\n Descri��o: ").append(descricao);
		return builder.toString();
	}
	
} // class Agencia

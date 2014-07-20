package tsi.too.bvb.entidades.tiposenumerados;

/** Enumera��o com os tipos de opera��es de contas banc�rias permitidos no sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */ 
public enum TipoOperacao {
	
	/** Saque */
	SAQUE(1, "Saque"),
	/** Dep�sito */
	DEPOSITO(2, "Dep�sito"),
	/** Transfer�ncia */
	TRANSFERENCIA(3, "Transfer�ncia"),
	/** Resgate */
	RESGATE(4, "Resgate");
	
	private int numero;
	private String descricao;
	private final static int NUM_TIPOS = 4;

	private TipoOperacao(int numero, String descricao) {
		this.numero = numero;
		this.descricao = descricao;
	}

	/** Retorna um <code>int</code> com o n�mero do tipo de opera��o de conta banc�ria
	 * @return <code>int</code> com o n�mero do tipo de opera��o de conta banc�ria
	 */
	public int getNumero() {
		return numero;
	}

	/** Retorna uma <code>String</code> com a descri��o do tipo de opera��o de conta banc�ria
	 * @return <code>String</code> com a descri��o do tipo de opera��o conta banc�ria
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Retorna um <code>int</code> o n�mero de tipos
	 * @return <code>int</code> o n�mero de tipos
	 */
	public static int getNumTipos() {
		return NUM_TIPOS;
	}

	/** Retorna um <code>String[]</code> com todos os tipos da enumera��o
	 * @return <code>String[]</code> com todos os tipos da enumera��o
	 */
	public static String[] obterArrayTipos() {
		String arrayTipos[] = new String[NUM_TIPOS];
		int i = 0;
		
		arrayTipos[i++] = SAQUE.getDescricao();
		arrayTipos[i++] = DEPOSITO.getDescricao();
		arrayTipos[i++] = TRANSFERENCIA.getDescricao();
		arrayTipos[i++] = RESGATE.getDescricao();
		
		return arrayTipos;
	}
	
	/** Obt�m o tipo da enumera��o corresponte ao par�metro passado
	 * @param descricao <code>String</code> refer�rente ao tipo desejado
	 * @return <code>TipoOperacao</code> com o tipo desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static TipoOperacao obterTipoOperacao(String descricao) {
		if(descricao.equalsIgnoreCase(SAQUE.getDescricao()))
			return SAQUE;
		else if(descricao.equalsIgnoreCase(DEPOSITO.getDescricao()))
			return DEPOSITO;
		else if(descricao.equalsIgnoreCase(TRANSFERENCIA.getDescricao()))
			return TRANSFERENCIA;
		else if(descricao.equalsIgnoreCase(RESGATE.getDescricao()))
			return RESGATE;
		
		return null;
	}
	
	/** Obt�m o tipo da enumera��o corresponte ao par�metro passado
	 * @param numero <code>int</code> refer�rente ao tipo desejado
	 * @return <code>TipoOperacao</code> com o tipo desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static TipoOperacao obterTipoOperacao(int numero) {
		if(numero == SAQUE.getNumero())
			return SAQUE;
		else if(numero == DEPOSITO.getNumero())
			return DEPOSITO;
		else if(numero == TRANSFERENCIA.getNumero())
			return TRANSFERENCIA;
		else if(numero == RESGATE.getNumero())
			return RESGATE;
		
		return null;
	}
	
} // enum TipoOperacao

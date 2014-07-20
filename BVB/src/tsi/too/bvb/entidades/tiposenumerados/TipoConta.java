package tsi.too.bvb.entidades.tiposenumerados;

/** Enumera��o com os tipos de conta banc�rias permitidos no sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */ 
public enum TipoConta {
	
	/** Conta Corrente */
	CONTA_CORRENTE(1, "Conta Corrente"),
	/** Conta Poupan�a */
	CONTA_POUPANCA(2, "Conta Poupan�a"),
	/** BVB FIF Pr�tico */
	FIF_PRATICO(3, "BVB FIF Pr�tico"),
	/** BVB FIF Executivo */
	FIF_EXECUTIVO(4, "BVB FIF Executivo");
	
	private int numero;
	private String descricao;
	private final static int NUM_TIPOS = 4;
	
	private TipoConta(int tipo, String descricao) {
		this.numero = tipo;
		this.descricao = descricao;
	}

	/** Retorna um <code>int</code> com o n�mero do tipo de conta banc�ria
	 * @return <code>int</code> com o n�mero do tipo de conta banc�ria
	 */
	public int getNumero() {
		return numero;
	}

	/** Retorna uma <code>String</code> com a descri��o do tipo de conta banc�ria
	 * @return <code>String</code> com a descri��o do tipo de conta banc�ria
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

	/** Retorna um <code>String[]</code> com os tipos comuns da enumera��o (conta corrente e poupan�a)
	 * @return <code>String[]</code> com os tipos comuns da enumera��o (conta corrente e poupan�a)
	 */
	public static String[] obterArrayTiposComuns() {
		String arrayTipos[] = new String[NUM_TIPOS];
		int i = 0;
		
		arrayTipos[i++] = CONTA_CORRENTE.getDescricao();
		arrayTipos[i++] = CONTA_POUPANCA.getDescricao();
		
		return arrayTipos;
	}
	
	/** Retorna um <code>String[]</code> com os tipos fif da enumera��o (fif pr�tico e executivo)
	 * @return <code>String[]</code> com os tipos fif da enumera��o (fif pr�tico e executivo)
	 */
	public static String[] obterArrayTiposFIF() {
		String arrayTipos[] = new String[NUM_TIPOS];
		int i = 0;
		
		arrayTipos[i++] = FIF_PRATICO.getDescricao();
		arrayTipos[i++] = FIF_EXECUTIVO.getDescricao();
		
		return arrayTipos;
	}
	
	/** Obt�m o tipo da enumera��o corresponte ao par�metro passado
	 * @param descricao <code>String</code> refer�rente ao tipo desejado
	 * @return <code>TipoConta</code> com o tipo desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static TipoConta obterTipoConta(String descricao) {
		if(descricao.equalsIgnoreCase(CONTA_CORRENTE.getDescricao()))
			return CONTA_CORRENTE;
		else if(descricao.equalsIgnoreCase(CONTA_POUPANCA.getDescricao()))
			return CONTA_POUPANCA;
		else if(descricao.equalsIgnoreCase(FIF_PRATICO.getDescricao()))
			return FIF_PRATICO;
		else if(descricao.equalsIgnoreCase(FIF_EXECUTIVO.getDescricao()))
			return FIF_EXECUTIVO;
		
		return null;
	}
	
	/** Obt�m o tipo da enumera��o corresponte ao par�metro passado
	 * @param numero <code>int</code> refer�rente ao tipo desejado
	 * @return <code>TipoConta</code> com o tipo desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static TipoConta obterTipoConta(int numero) {
		if(numero == CONTA_CORRENTE.getNumero())
			return CONTA_CORRENTE;
		else if(numero == CONTA_POUPANCA.getNumero())
			return CONTA_POUPANCA;
		else if(numero == FIF_PRATICO.getNumero())
			return FIF_PRATICO;
		else if(numero == FIF_EXECUTIVO.getNumero())
			return FIF_EXECUTIVO;
		
		return null;
	}

} // enum TipoConta

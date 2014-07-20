package tsi.too.bvb.entidades.tiposenumerados;

/** Enumera��o com os meses do ano
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */ 
public enum Mes {
	
    /** M�s de Janeiro */
	JANEIRO(0, "Janeiro"),
	/** M�s de Fevereiro */
	FEVEREIRO(1, "Fevereiro"),
	/** M�s de Mar�o */
	MARCO(2, "Mar�o"),
	/** M�s de Abriu */
	ABRIU(3, "Abriu"),
	/** M�s de Maio */
	MAIO(4, "Maio"),
	/** M�s de Junho */
	JUNHO(5, "Junho"),
	/** M�s de Julho */
	JULHO(6, "Julho"),
	/** M�s de Agosto */
	AGOSTO(7, "Agosto"),
	/** M�s de Setembro */
	SETEMBRO(8, "Setembro"),
	/** M�s de Outrubro */
	OUTUBRO(9, "Outrubro"),
	/** M�s de Novembro */
	NOVEMBRO(10, "Novembro"),
	/** M�s de Dezembro */
	DEZEMBRO(11, "Dezembro");
	
	private int numero;
	private String descricao;
	private final static int NUM_TIPOS = 12;
	
	private Mes(int numero, String descricao) {
		this.numero = numero;
		this.descricao = descricao;
	}
	
	/** Retorna um <code>int</code> com o n�mero do m�s
	 * @return <code>int</code> com o n�mero do m�s
	 */
	public int getNumero() {
		return numero;
	}

	/** Retorna uma <code>String</code> o nome do m�s
	 * @return <code>String</code> o nome do m�s
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Retorna um <code>int</code> o n�mero de meses
	 * @return <code>int</code> o n�mero de meses
	 */
	public static int getNumTipos() {
		return NUM_TIPOS;
	}

	/** Retorna um <code>String[]</code> com todos os meses da enumera��o
	 * @return <code>String[]</code> com todos os meses da enumera��o
	 */
	public static String[] obterArrayMeses() {
		String arrayTipos[] = new String[NUM_TIPOS];
		int i = 0;
		
		arrayTipos[i++] = JANEIRO.getDescricao();
		arrayTipos[i++] = FEVEREIRO.getDescricao();
		arrayTipos[i++] = MARCO.getDescricao();
		arrayTipos[i++] = ABRIU.getDescricao();
		arrayTipos[i++] = MAIO.getDescricao();
		arrayTipos[i++] = JUNHO.getDescricao();
		arrayTipos[i++] = JULHO.getDescricao();
		arrayTipos[i++] = AGOSTO.getDescricao();
		arrayTipos[i++] = SETEMBRO.getDescricao();
		arrayTipos[i++] = OUTUBRO.getDescricao();
		arrayTipos[i++] = NOVEMBRO.getDescricao();
		arrayTipos[i++] = DEZEMBRO.getDescricao();
		
		return arrayTipos;
	}
	
	/** Retorna um <code>String[]</code> com o n�mero de meses desejados da enumera��o
	 * @param numMeses <code>int</code> com o n�mero de meses desejados no array
	 * @return <code>String[]</code> com o n�mero de meses desejados da enumera��o
	 */
	public static String[] obterArrayMeses(int numMeses) {
		String arrayTipos[] = new String[numMeses];
		int i = 0;
		
		arrayTipos[i++] = JANEIRO.getDescricao(); if(numMeses == 1) return arrayTipos;
		arrayTipos[i++] = FEVEREIRO.getDescricao(); if(numMeses == 2) return arrayTipos;
		arrayTipos[i++] = MARCO.getDescricao(); if(numMeses == 3) return arrayTipos;
		arrayTipos[i++] = ABRIU.getDescricao(); if(numMeses == 4) return arrayTipos;
		arrayTipos[i++] = MAIO.getDescricao(); if(numMeses == 5) return arrayTipos;
		arrayTipos[i++] = JUNHO.getDescricao(); if(numMeses == 6) return arrayTipos;
		arrayTipos[i++] = JULHO.getDescricao(); if(numMeses == 7) return arrayTipos;
		arrayTipos[i++] = AGOSTO.getDescricao(); if(numMeses == 8) return arrayTipos;
		arrayTipos[i++] = SETEMBRO.getDescricao(); if(numMeses == 9) return arrayTipos;
		arrayTipos[i++] = OUTUBRO.getDescricao(); if(numMeses == 10) return arrayTipos;
		arrayTipos[i++] = NOVEMBRO.getDescricao(); if(numMeses == 11) return arrayTipos;
		arrayTipos[i++] = DEZEMBRO.getDescricao(); if(numMeses == 12) return arrayTipos;
		
		return arrayTipos;
	}
	
	/** Obt�m o m�s da enumera��o corresponte ao par�metro passado
	 * @param mes <code>String</code> refer�rente ao m�s desejado
	 * @return <code>Mes</code> com o m�s desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static Mes obterMes(String mes) {
		if(mes.equalsIgnoreCase(JANEIRO.getDescricao()))
			return JANEIRO;
		else if(mes.equalsIgnoreCase(FEVEREIRO.getDescricao()))
			return FEVEREIRO;
		else if(mes.equalsIgnoreCase(MARCO.getDescricao()))
			return MARCO;
		else if(mes.equalsIgnoreCase(ABRIU.getDescricao()))
			return ABRIU;
		else if(mes.equalsIgnoreCase(MAIO.getDescricao()))
			return MAIO;
		else if(mes.equalsIgnoreCase(JUNHO.getDescricao()))
			return JUNHO;
		else if(mes.equalsIgnoreCase(JULHO.getDescricao()))
			return JULHO;
		else if(mes.equalsIgnoreCase(AGOSTO.getDescricao()))
			return AGOSTO;
		else if(mes.equalsIgnoreCase(SETEMBRO.getDescricao()))
			return SETEMBRO;
		else if(mes.equalsIgnoreCase(OUTUBRO.getDescricao()))
			return OUTUBRO;
		else if(mes.equalsIgnoreCase(NOVEMBRO.getDescricao()))
			return NOVEMBRO;
		else if(mes.equalsIgnoreCase(DEZEMBRO.getDescricao()))
			return DEZEMBRO;
		
		return null;
	}
	
	/** Obt�m o m�s da enumera��o corresponte ao par�metro passado
	 * @param numero <code>int</code> refer�rente ao m�s desejado
	 * @return <code>Mes</code> com o m�s desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static Mes obterMes(int numero) {
		if(numero == JANEIRO.getNumero())
			return JANEIRO;
		else if(numero == FEVEREIRO.getNumero())
			return FEVEREIRO;
		else if(numero == MARCO.getNumero())
			return MARCO;
		else if(numero == ABRIU.getNumero())
			return ABRIU;
		else if(numero == MAIO.getNumero())
			return MAIO;
		else if(numero == JUNHO.getNumero())
			return JUNHO;
		else if(numero == JULHO.getNumero())
			return JULHO;
		else if(numero == AGOSTO.getNumero())
			return AGOSTO;
		else if(numero == SETEMBRO.getNumero())
			return SETEMBRO;
		else if(numero == OUTUBRO.getNumero())
			return OUTUBRO;
		else if(numero == NOVEMBRO.getNumero())
			return NOVEMBRO;
		else if(numero == DEZEMBRO.getNumero())
			return DEZEMBRO;
		
		return null;
	}
	
} // enum Mes

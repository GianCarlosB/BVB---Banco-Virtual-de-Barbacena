package tsi.too.bvb.entidades.tiposenumerados;

/** Enumera��o com os tipos de funcion�rios permitidos no sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */ 
public enum TipoUsuario {
	
	/** Administrador */
	ADMINISTRADOR('A', "Administrador",
			  " � o respons�vel pela seguran�a do sistema. As suas fun��es se\n"
			+ " resumem em administrar o m�dulo Controle de Usu�rios. � o �nico\n"
			+ " usu�rio autorizado a cadastrar, alterar, consultar e excluir os\n"
			+ " dados de um funcion�rio e a visualizar e imprimir o arquivo de log\n"
			+ " de cada usu�rio."),
	/** Caixa */
	CAIXA('C', "Caixa",
			  " Este funcion�rio possui as seguintes fun��es:\n"
			+ " 1. cadastrar, consultar, alterar e imprimir os dados do cliente;\n"
			+ " 2. consultar os dados das ag�ncias;\n"
			+ " 3. abrir conta;\n"
			+ " 4. depositar sal�rio."),
	/** Gerente */
	GERENTE('G', "Gerente",
			  " � o respons�vel pela administra��o e coordena��o dos servi�os da\n"
			+ " ag�ncia banc�ria. Possui acesso irrestrito a todos os servi�os do\n"
			+ " m�dulo Contas Banc�rias. � o �nico funcion�rio que tem poderes\n"
			+ " para:\n"
			+ " 1. excluir um cliente encerrando as suas contas banc�rias;\n"
			+ " 2. cadastrar, consultar, alterar, excluir e imprimir os dados das\n"
			+ "    ag�ncias;"
			+ " 3. criar aplica��es financeiras;\n"
			+ " 4. alterar o tipo de conta banc�ria de um cliente;\n"
			+ " 5. ativar e desativar o m�dulo Caixa Eletr�nico.");
	
	private char caractere;
	private String perfil, descricao;
	private final static int NUM_TIPOS = 3;

	private TipoUsuario(char caractere, String perfil, String descricao) {
		this.caractere = caractere;
		this.perfil = perfil;
		this.descricao = descricao;
	}

	/** Retorna um <code>char</code> com o caractere do tipo de funcion�rio
	 * @return <code>char</code> com o caractere do tipo de funcion�rio
	 */
	public char getCaractere() {
		return caractere;
	}

	/** Retorna uma <code>String</code> com a perfil do tipo de funcion�rio
	 * @return <code>String</code> com a perfil do tipo de funcion�rio
	 */
	public String getPerfil() {
		return perfil;
	}

	/** Retorna uma <code>String</code> com a descri��o do tipo de funcion�rio
	 * @return <code>String</code> com a descri��o do tipo de funcion�rio
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
		
		arrayTipos[i++] = ADMINISTRADOR.getPerfil();
		arrayTipos[i++] = CAIXA.getPerfil();
		arrayTipos[i++] = GERENTE.getPerfil();
		
		return arrayTipos;
	}
	
	/** Obt�m o tipo da enumera��o corresponte ao par�metro passado
	 * @param perfil <code>String</code> refer�rente ao tipo desejado
	 * @return <code>TipoUsuario</code> com o tipo desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static TipoUsuario obterTipoUsuario(String perfil) {
		if(perfil.equalsIgnoreCase(ADMINISTRADOR.getPerfil()))
			return ADMINISTRADOR;
		else if(perfil.equalsIgnoreCase(CAIXA.getPerfil()))
			return CAIXA;
		else if(perfil.equalsIgnoreCase(GERENTE.getPerfil()))
			return GERENTE;
		
		return null;
	}
	
	/** Obt�m o tipo da enumera��o corresponte ao par�metro passado
	 * @param caractere <code>char</code> refer�rente ao tipo desejado
	 * @return <code>TipoUsuario</code> com o tipo desajado ou <code>null</code> caso n�o seja encontrado
	 */
	public static TipoUsuario obterTipoUsuario(char caractere) {
		if(caractere == ADMINISTRADOR.getCaractere())
			return ADMINISTRADOR;
		else if(caractere == CAIXA.getCaractere())
			return CAIXA;
		else if(caractere == GERENTE.getCaractere())
			return GERENTE;
		
		return null;
	}
	
} // enum TipoUsuario

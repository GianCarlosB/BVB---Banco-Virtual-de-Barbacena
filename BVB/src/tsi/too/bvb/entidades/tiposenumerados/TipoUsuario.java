package tsi.too.bvb.entidades.tiposenumerados;

public enum TipoUsuario {
	
	ADMINISTRADOR('A', "Administrador", "� o respons�vel pela seguran�a do sistema.\n"
			+ "As suas fun��es se resumem em administrar o m�dulo Controle de Usu�rios.\n"
			+ "� o �nico usu�rio autorizado a cadastrar, alterar, consultar e excluir os dados\n"
			+ "de um funcion�rio e a visualizar e imprimir o arquivo de log de cada usu�rio.\n"),
	CAIXA('C', "Caixa", "Este funcion�rio possui as seguintes fun��es:\n"
			+ "1. cadastrar, consultar, alterar e imprimir os dados do cliente;\n"
			+ "2. consultar os dados das ag�ncias;\n"
			+ "3. abrir conta;\n"
			+ "4. depositar sal�rio."),
	GERENTE('G', "Gerente", "� o respons�vel pela administra��o e coordena��o dos servi�os da ag�ncia banc�ria.\n"
			+ "Possui acesso irrestrito a todos os servi�os do m�dulo Contas Banc�rias. � o �nico funcion�rio que tem poderes para:\n"
			+ "1. excluir um cliente encerrando as suas contas banc�rias;\n"
			+ "2. cadastrar, consultar, alterar, excluir e imprimir os dados das ag�ncias;\n"
			+ "3. criar aplica��es financeiras;\n"
			+ "4. alterar o tipo de conta banc�ria de um cliente;\n"
			+ "5. ativar e desativar o m�dulo Caixa Eletr�nico.");
	
	private char caractere;
	private String perfil, descricao;
	private final static int NUM_TIPOS = 3;

	private TipoUsuario(char caractere, String perfil, String descricao) {
		this.caractere = caractere;
		this.perfil = perfil;
		this.descricao = descricao;
	}

	public char getCaractere() {
		return caractere;
	}

	public void setCaractere(char caractere) {
		this.caractere = caractere;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static int getNumTipos() {
		return NUM_TIPOS;
	}

	public static String[] obterArrayTipos() {
		String arrayTipos[] = new String[NUM_TIPOS];
		int i = 0;
		
		arrayTipos[i++] = ADMINISTRADOR.getPerfil();
		arrayTipos[i++] = CAIXA.getPerfil();
		arrayTipos[i++] = GERENTE.getPerfil();
		
		return arrayTipos;
	}
	
	public static TipoUsuario obterTipoUsuario(String caractere) {
		if(caractere.equalsIgnoreCase(ADMINISTRADOR.getPerfil()))
			return ADMINISTRADOR;
		else if(caractere.equalsIgnoreCase(CAIXA.getPerfil()))
			return CAIXA;
		else if(caractere.equalsIgnoreCase(GERENTE.getPerfil()))
			return GERENTE;
		
		return null;
	}
	
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

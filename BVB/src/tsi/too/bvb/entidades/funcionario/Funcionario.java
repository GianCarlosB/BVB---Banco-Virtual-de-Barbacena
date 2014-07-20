package tsi.too.bvb.entidades.funcionario;

import tsi.too.bvb.entidades.tiposenumerados.TipoUsuario;

/** A classe <code>Funcionario</code> manipula dados refer�nte aos funcion�rios do BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Funcionario {
	
	private String nomeUsuario;
	private String senha;
	private TipoUsuario tipoUsuario;

	/** Cria um funcion�rio sem nem um atributo
	 */
	public Funcionario() {
		super();
	}
	
	/** Cria um funcion�rio com o nome de usu�rio, senha e tipo
	 * 
	 * @param nomeUsuario <code>String</code> com o nome do funcion�rio
	 * @param senha <code>String</code> com a senha do funcion�rio
	 * @param tipoUsuario <code>TipoUsuario</code> com o tipo do funcion�rio
	 * 
	 * @see TipoUsuario
	 */
	public Funcionario(String nomeUsuario, String senha, TipoUsuario tipoUsuario) {
		this();
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	
	/** Retorna uma <code>String</code> com o nome do funcion�rio
	 * @return <code>String</code> com o nome do funcion�rio
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	/** Muda o nome do funcion�rio
	 * @param nomeUsuario <code>String</code> com o novo nome do funcion�rio
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	/** Retorna uma <code>String</code> com a senha do funcion�rio
	 * @return <code>String</code> com a senha do funcion�rio
	 */
	public String getSenha() {
		return senha;
	}
	
	/** Muda a senha do funcion�rio
	 * @param senha <code>String</code> com a nova senha do funcion�rio
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/** Retorna um <code>TipoUsuario</code> com o tipo do funcion�rio
	 * @return <code>TipoUsuario</code> com o tipo do funcion�rio
	 */
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	/** Muda o tipo do funcion�rio
	 * @param tipoUsuario <code>TipoUsuario</code> com o novo tipo do funcion�rio
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/** Retorna uma <code>String</code> com o nome e o tipo do funcion�rio
	 * @return <code>String</code> com o nome e o tipo do funcion�rio
	 */
	public String toStringSemSenha() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Nome do Usu�rio: ").append(nomeUsuario)
			   .append("\n Tipo do Usu�rio: ").append(tipoUsuario.getPerfil());
		return builder.toString();
	}
	
	/** Retorna uma <code>String</code> com todos os dados do funcion�rio
	 * @return <code>String</code> com todos os dados do funcion�rio
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Nome do Usu�rio: ").append(nomeUsuario)
				.append("\n Senha: ").append(senha).append("\n Tipo do Usu�rio: ")
				.append(tipoUsuario.getPerfil());
		return builder.toString();
	}
	
} // class Funcionario

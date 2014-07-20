package tsi.too.bvb.gui;

/** Interface com m�todos b�sicos que algumas janelas e pain�is devem implementar
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public interface TratadorDeCampos {

	/** Limpa os campos do componente GUI
	 */
	public void limparCampos();
	
	/** Salva os dados inseridos no componente GUI em um objeto
	 * @param object <code>Object</code> refer�nte ao objeto que ser� salvo
	 */
	public void salvarCampos(Object object);
	
	/** Verifica se os campos do componente GUI foram preenchidos corretamente
	 *  @return <code>boolean</code> com <code>true</code> caso os campos tenham sido preenchidos corretamente, 
	 *  e <code>false</code> caso contr�rio
	 */
	public boolean validarCampos();
	
	/** Insere a borda padr�o nos campos do componente GUI
	 */
	public void inserirBordasPadrao();
	
} // interface PainelCliente

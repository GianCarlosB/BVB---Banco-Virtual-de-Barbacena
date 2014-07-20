package tsi.too.bvb.eventos.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.login.IgLogin;
import tsi.too.bvb.gui.menuprincipal.IgMenuPrincipal;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;

/** Classe para tratar os eventos de a��o da janela <code>IgLogin</code>
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see ActionListener
 */
public class TEActionLogin implements ActionListener {
	
	private IgLogin igLogin;

	/** Cria uma inst�ncia do Tratador de eventos de a��o da janela <code>IgAltAgencia</code>
	 * @param igLogin <code>IgLogin</code> que ser� manipulada
	 */
	public TEActionLogin(IgLogin igLogin) {
		super();
		this.igLogin = igLogin;
	}
	
	/** Trata os eventos de a��o dos elementos da janela <code>IgLogin</code>
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igLogin.getBtnFazerLogin()) {
			if(igLogin.isContemTxtLogin() && igLogin.isContemTxtSenha()) {
				if(BancoDeDadosBVB.getInstance().getConn() != null) {
					try {
						if(igLogin.validarCampos()) {
							igLogin.inserirBordasPadrao();
							igLogin.setLblCamposErrados(false);
							
							// Cria a janela que cont�m o menu principal.
							new IgMenuPrincipal(igLogin.getFuncionario());
							
							igLogin.dispose();
						}
					} catch (NoSuchAlgorithmException eNSA) {
						// TODO Auto-generated catch block
						new JanelaPopUpErro(null, "BVB - ERRO", eNSA);
					}
				}
				else
					new JanelaPopUpErro(igLogin, "BVB - ERRO", " A conex�o com o banco de dados n�o foi estabelecida!\n" +
				                        " Para realizar login reinicie a aplica��o!");
			}
			else {
				igLogin.inserirBordasPadrao();
				igLogin.setLblCamposErrados(false);
			}
		}
	}

} // class TEActionCadastrarFuncionario

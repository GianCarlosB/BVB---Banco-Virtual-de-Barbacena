package tsi.too.bvb.eventos.funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.gui.JanelaPopUpInfo;
import tsi.too.bvb.gui.funcionario.IgAltSenhaFuncionario;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.FuncionarioDAO;

/** Classe para tratar os eventos de a��o da janela <code>IgAltSenhaFuncionario</code>
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see ActionListener
 */
public class TEActionAltSenhaFuncionario implements ActionListener {
	
	private IgAltSenhaFuncionario igAltSenhaFuncionario;
	private Funcionario funcionario;

	/** Cria uma inst�ncia do Tratador de eventos de a��o da janela <code>IgAltSenhaFuncionario</code>
	 * @param igAltSenhaFuncionario <code>IgAltSenhaFuncionario</code> que ser� manipulada
	 * @param funcionario <code>Funcionario</code> com os dados do funcion�rio que ter� a senha alterada
	 * 
	 * @see Funcionario
	 */
	public TEActionAltSenhaFuncionario(IgAltSenhaFuncionario igAltSenhaFuncionario, Funcionario funcionario) {
		super();
		this.igAltSenhaFuncionario = igAltSenhaFuncionario;
		this.funcionario = funcionario;
	}
	
	/** Trata os eventos de a��o dos elementos da janela <code>IgAltSenhaFuncionario</code>
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igAltSenhaFuncionario.getBtnLimpar()) {
			igAltSenhaFuncionario.limparCampos();
			igAltSenhaFuncionario.setLblCamposErrados(false);
		}
		
		else if(e.getSource() == igAltSenhaFuncionario.getBtnAlterar()) {
			if(igAltSenhaFuncionario.validarCampos()) {
				igAltSenhaFuncionario.setLblCamposErrados(false);
				igAltSenhaFuncionario.salvarCampos(funcionario);
				igAltSenhaFuncionario.getaPasswordField().setBorder(UIManager.getBorder("PasswordField.border"));
				igAltSenhaFuncionario.getnPasswordField().setBorder(UIManager.getBorder("PasswordField.border"));
				igAltSenhaFuncionario.getrPasswordField().setBorder(UIManager.getBorder("PasswordField.border"));
				
				new FuncionarioDAO().alterarSenha(BancoDeDadosBVB.getInstance(), funcionario);

				new JanelaPopUpInfo(igAltSenhaFuncionario, "BVB - Altera��o de Senha", " Altera��o de Senha Realizada com Sucesso!",
						            " Nova Senha: " + new String(igAltSenhaFuncionario.getnPasswordField().getPassword()));
				igAltSenhaFuncionario.dispose();
			}
			else
				igAltSenhaFuncionario.setLblCamposErrados(true);
		}
	}

} // class TEActionAltSenhaFuncionario

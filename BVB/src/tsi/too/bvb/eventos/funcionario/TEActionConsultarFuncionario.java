package tsi.too.bvb.eventos.funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.entidades.tiposenumerados.TipoUsuario;
import tsi.too.bvb.gui.JanelaPopUpAviso;
import tsi.too.bvb.gui.funcionario.IgConsultarFuncionario;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.FuncionarioDAO;

/** Classe para tratar os eventos de a��o da janela <code>IgConsultarFuncionario</code>
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see ActionListener
 */
public class TEActionConsultarFuncionario implements ActionListener {
	
	private IgConsultarFuncionario igConsultarFuncionario;

	/** Cria uma inst�ncia do Tratador de eventos de a��o da janela <code>IgConsultarFuncionario</code>
	 * @param igConsultarFuncionario <code>IgConsultarFuncionario</code> que ser� manipulada
	 */
	public TEActionConsultarFuncionario(IgConsultarFuncionario igConsultarFuncionario) {
		super();
		this.igConsultarFuncionario = igConsultarFuncionario;
	}
	
	/** Trata os eventos de a��o dos elementos da janela <code>IgConsultarFuncionario</code>
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
		if(e.getSource() == igConsultarFuncionario.getBtnLimpar()) {
			igConsultarFuncionario.limpaTabela();
			igConsultarFuncionario.limpaCampos();
		}
		else if(e.getSource() == igConsultarFuncionario.getBtnBuscar()) {
			List<Funcionario> funcionarios = new FuncionarioDAO().pesquisarTipo(BancoDeDadosBVB.getInstance(), TipoUsuario.obterTipoUsuario
																			   (igConsultarFuncionario.getTipoUsuarioComboBox()));
			
			if(!funcionarios.isEmpty())
				igConsultarFuncionario.addLinhasTabela(funcionarios);
			else {
				igConsultarFuncionario.limpaTabela();
				new JanelaPopUpAviso(igConsultarFuncionario, "BVB - Consulta de Funcion�rio", " Nenhum funcion�rio do tipo '" +
						             igConsultarFuncionario.getTipoUsuarioComboBox() + "' foi encontrado.");
			}
		}
	}
	
} // class TEActionConsultarFuncionario

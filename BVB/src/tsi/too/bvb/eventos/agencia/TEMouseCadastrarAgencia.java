package tsi.too.bvb.eventos.agencia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import tsi.too.bvb.entidades.agencia.Agencia;
import tsi.too.bvb.gui.JanelaPopUpInfo;
import tsi.too.bvb.gui.agencia.IgCadAgencia;
import tsi.too.bvb.persistencia.AgenciaDAO;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.validacoes.ValidarDados;

public class TEMouseCadastrarAgencia implements ActionListener {
	
	private IgCadAgencia igCadAgencia;
	private Agencia agencia;

	public TEMouseCadastrarAgencia(IgCadAgencia igCadAgencia, Agencia agencia) {
		super();
		this.igCadAgencia = igCadAgencia;
		this.agencia = agencia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igCadAgencia.getBtnLimpar())
			igCadAgencia.setDescricaoEditorPane("");
		
		else if(e.getSource() == igCadAgencia.getBtnFinalizar()) {
			if(ValidarDados.validarVazio(igCadAgencia.getDescricaoEditorPane().getText()) == false) {
				igCadAgencia.setLblCamposErrados(true);
				igCadAgencia.getDescricaoEditorPane().setBorder(new LineBorder(Color.RED));
				igCadAgencia.getScrollPane().setBorder(new LineBorder(Color.RED));
			}
			else {
				igCadAgencia.setLblCamposErrados(false);
				igCadAgencia.salvarCampos(agencia);
				igCadAgencia.getDescricaoEditorPane().setBorder(UIManager.getBorder("EditorPane.border"));
				igCadAgencia.getScrollPane().setBorder(UIManager.getBorder("ScrollPane.border"));
				
				AgenciaDAO agenciaDAO = new AgenciaDAO();
				agenciaDAO.criar(BancoDeDadosBVB.getInstance(), agencia);
				
				new JanelaPopUpInfo(igCadAgencia, "Novo Cadastro de Ag�ncia", " Cadastro da Ag�ncia Realizado com Sucesso!",
						            " Descri��o: " + agencia.getDescricao());
				igCadAgencia.dispose();
			}
		}
	}

} // class TEMouseCadastrarAgencia

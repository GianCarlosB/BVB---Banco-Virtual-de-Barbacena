package tsi.too.bvb.eventos.cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import tsi.too.bvb.entidades.cliente.Cliente;
import tsi.too.bvb.gui.JanelaPopUpInfo;
import tsi.too.bvb.gui.cliente.IgCadCliente;
import tsi.too.bvb.gui.cliente.PainelCadCliente;
import tsi.too.bvb.gui.cliente.PainelCadContato;
import tsi.too.bvb.gui.cliente.PainelCadEndereco;
import tsi.too.bvb.gui.cliente.PainelCliente;
import tsi.too.bvb.gui.cliente.PainelConfCadCliente;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.ClienteDAO;

public class TEMouseCadastrarCliente extends MouseAdapter {
	
	private IgCadCliente igCadCliente;
	private PainelCadCliente painelCadCliente;
	private Cliente cliente;

	public TEMouseCadastrarCliente(PainelCadCliente painelCadCliente) {
		super();
		this.painelCadCliente = painelCadCliente;
	}

	public TEMouseCadastrarCliente(IgCadCliente igCadCliente, Cliente cliente) {
		super();
		this.painelCadCliente = null;
		this.igCadCliente = igCadCliente;
		this.cliente = cliente;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseClicked(e);
		
		if(e.getButton() == MouseEvent.BUTTON1) { // bot�o esquerdo do mouse
			if(painelCadCliente != null && e.getSource() == painelCadCliente.getBtnValidar())
				painelCadCliente.validarCampoCpf();
			else {
				PainelCliente painelVisivel = igCadCliente.obterPainelVisivel();
				
				if(e.getSource() == igCadCliente.getBtnLimpar())
					painelVisivel.limpaCampos();
				if(e.getSource() == igCadCliente.getBtnProximo()) {
					if(painelVisivel.validarCampos() == true) {
						igCadCliente.setLblCamposErrados(false);
						painelVisivel.salvarCampos(cliente);
						
						if(painelVisivel instanceof PainelCadCliente) {
							igCadCliente.getCardLayout().show(igCadCliente.getCardPanel(), "enderecoPanel");
							igCadCliente.setTxtpnSubTitulo("Insera o endere\u00E7o do novo cliente.");
							igCadCliente.setProgressBar(50);
							igCadCliente.getpCadEndereco().setVisible(true);
							igCadCliente.getBtnAnterior().setVisible(true);
						}
						else if(painelVisivel instanceof PainelCadEndereco) {
							igCadCliente.getCardLayout().show(igCadCliente.getCardPanel(), "contatoPanel");
							igCadCliente.setTxtpnSubTitulo("Insera o contato do novo cliente.");
							igCadCliente.setProgressBar(75);
							igCadCliente.getpCadContato().setVisible(true);
						}
						else if(painelVisivel instanceof PainelCadContato) {
							igCadCliente.getCardLayout().show(igCadCliente.getCardPanel(), "confClientePanel");
							igCadCliente.setTxtpnSubTitulo("Confirme os dados do novo cliente.");
							igCadCliente.setProgressBar(100);
							igCadCliente.getpConfCadCliente().setDadosEditorPane(cliente.toString());
							igCadCliente.getpConfCadCliente().setVisible(true);
							igCadCliente.getBtnLimpar().setVisible(false);
							igCadCliente.getBtnProximo().setVisible(false);
							igCadCliente.getBtnFinalizar().setVisible(true);
						}
					}
					else
						igCadCliente.setLblCamposErrados(true);
				} // fim if(e.getSource() == igCadCliente.getBtnProximo())
				
				if(e.getSource() == igCadCliente.getBtnAnterior()) {
					if(painelVisivel instanceof PainelCadEndereco) {
						igCadCliente.getCardLayout().show(igCadCliente.getCardPanel(), "clientePanel");
						igCadCliente.setTxtpnSubTitulo("Insira o nome e o CPF do novo cliente.");
						igCadCliente.setProgressBar(25);
						igCadCliente.setLblCamposErrados(false);
						igCadCliente.getpCadCliente().setVisible(true);
						igCadCliente.getBtnAnterior().setVisible(false);
					}
					else if(painelVisivel instanceof PainelCadContato) {
						igCadCliente.getCardLayout().show(igCadCliente.getCardPanel(), "enderecoPanel");
						igCadCliente.setTxtpnSubTitulo("Insera o endere\u00E7o do novo cliente.");
						igCadCliente.setProgressBar(50);
						igCadCliente.setLblCamposErrados(false);
						igCadCliente.getpCadEndereco().setVisible(true);
					}
					else if(painelVisivel instanceof PainelConfCadCliente) {
						igCadCliente.getCardLayout().show(igCadCliente.getCardPanel(), "contatoPanel");
						igCadCliente.setTxtpnSubTitulo("Insera o contato do novo cliente.");
						igCadCliente.setProgressBar(75);
						igCadCliente.setLblCamposErrados(false);
						igCadCliente.getpCadContato().setVisible(true);
						igCadCliente.getBtnLimpar().setVisible(true);
						igCadCliente.getBtnProximo().setVisible(true);
						igCadCliente.getBtnFinalizar().setVisible(false);
					}
				} // fim if(e.getSource() == igCadCliente.getBtnAnterior())
				
				if(e.getSource() == igCadCliente.getBtnFinalizar()) {
					ClienteDAO clienteDAO = new ClienteDAO();
					clienteDAO.criar(BancoDeDadosBVB.getInstance(), cliente);
					
					new JanelaPopUpInfo(igCadCliente, "Novo Cadastro de Cliente", " Cadastro do Cliente Realizado com Sucesso!", cliente.toString());
					igCadCliente.dispose();
				} // fim if(e.getSource() == igCadCliente.getBtnFinalizar())
			} // fim else
		} // fim if (e.getButton() == MouseEvent.BUTTON1)
	} // fim mouseClicked()

} // class TEMouseCadastrarCliente

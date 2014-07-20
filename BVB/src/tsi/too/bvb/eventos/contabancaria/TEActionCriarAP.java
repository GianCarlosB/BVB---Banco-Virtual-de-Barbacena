package tsi.too.bvb.eventos.contabancaria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.too.bvb.entidades.contabancaria.ContaBancaria;
import tsi.too.bvb.entidades.tiposenumerados.TipoConta;
import tsi.too.bvb.gui.JanelaPopUpInfo;
import tsi.too.bvb.gui.PainelConfCad;
import tsi.too.bvb.gui.TratadorDeCampos;
import tsi.too.bvb.gui.contabancaria.IgCriarAplicacao;
import tsi.too.bvb.gui.contabancaria.PainelAbContaDadosChave;
import tsi.too.bvb.gui.contabancaria.PainelAbContaDadosSec;
import tsi.too.bvb.gui.contabancaria.PainelAbContaSenhas;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.ContaBancariaDAO;

/** Classe para tratar os eventos de a��o da janela <code>IgCriarAplicacao</code> e de seu painel <code>PainelAbContaDadosChave</code>
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see ActionListener
 */
public class TEActionCriarAP implements ActionListener {
	
	private IgCriarAplicacao igCriarAplicacao;
	private PainelAbContaDadosChave painelAbContaDadosChave;
	private ContaBancaria contaBancaria;
	
	/** Cria uma inst�ncia do Tratador de eventos de a��o do painel <code>painelCadCliente</code>
	 * @param painelAbContaDadosChave <code>PainelAbContaDadosChave</code> que ser� manipulado
	 */
	public TEActionCriarAP(PainelAbContaDadosChave painelAbContaDadosChave) {
		super();
		this.painelAbContaDadosChave = painelAbContaDadosChave;
	}

	/** Cria uma inst�ncia do Tratador de eventos de a��o da janela <code>IgCriarAplicacao</code>
	 * @param igCriarAplicacao <code>IgCriarAplicacao</code> que ser� manipulada
	 * @param contaBancaria <code>ContaBancaria</code> refer�nte ao objeto que ser� manipulado
	 * 
	 * @see ContaBancaria
	 */
	public TEActionCriarAP(IgCriarAplicacao igCriarAplicacao, ContaBancaria contaBancaria) {
		super();
		this.painelAbContaDadosChave = null;
		this.igCriarAplicacao = igCriarAplicacao;
		this.contaBancaria = contaBancaria;
	}
	
	/** Trata os eventos de a��o dos elementos da janela <code>IgCriarAplicacao</code> e de seu painel <code>PainelAbContaDadosChave</code>
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(painelAbContaDadosChave != null && e.getSource() == painelAbContaDadosChave.getBtnValidarCodAgencia())
			painelAbContaDadosChave.validarCampoCodAgencia();
		else if(painelAbContaDadosChave != null && e.getSource() == painelAbContaDadosChave.getBtnValidarCpf())
			painelAbContaDadosChave.validarCampoCpf();
		else {
			TratadorDeCampos painelVisivel = igCriarAplicacao.obterPainelVisivel();
			
			if(e.getSource() == igCriarAplicacao.getBtnLimpar()) {
				painelVisivel.limparCampos();
				igCriarAplicacao.setLblCamposErrados(false);
			}
			else if(e.getSource() == igCriarAplicacao.getBtnProximo()) {
				if(painelVisivel.validarCampos()) {
					igCriarAplicacao.setLblCamposErrados(false);
					painelVisivel.salvarCampos(contaBancaria);
					
					if(painelVisivel instanceof PainelAbContaDadosChave) {
						igCriarAplicacao.getpAbContaDadosSec().setTipoConta(TipoConta.obterTipoConta(igCriarAplicacao
								                                            .getpAbContaDadosChave().obterRadioBtnSelecionado()));		
                		// Exibe ou esconde as op��es da conta sal�rio de acordo com o tipo da conta escolhido.
						igCriarAplicacao.getpAbContaDadosSec().visualizacaoOpcContaSal();
						// Atualiza o 'Tool Tip Text' do campo Saldo do painel de dados secund�rios.
						igCriarAplicacao.getpAbContaDadosSec().atualizaToolTipTextSaldo();

						igCriarAplicacao.getCardLayout().show(igCriarAplicacao.getCardPanel(), "dadosSecundarioPanel");
						igCriarAplicacao.setTxtpnSubTitulo("Insira os dados secund�rios da nova aplica��o financeira.");
						igCriarAplicacao.getpAbContaDadosSec().inserirBordasPadrao();
						igCriarAplicacao.setProgressBar(25);
						igCriarAplicacao.getpAbContaDadosSec().setVisible(true);
						igCriarAplicacao.getBtnAnterior().setVisible(true);
					}
					else if(painelVisivel instanceof PainelAbContaDadosSec) {
						igCriarAplicacao.getCardLayout().show(igCriarAplicacao.getCardPanel(), "senhasPanel");
						igCriarAplicacao.setTxtpnSubTitulo("Insera as senhas da nova aplica��o financeira.");
						igCriarAplicacao.getpAbContaSenhas().inserirBordasPadrao();
						igCriarAplicacao.setProgressBar(50);
						igCriarAplicacao.getpAbContaSenhas().setVisible(true);
					}
					else if(painelVisivel instanceof PainelAbContaSenhas) {
						igCriarAplicacao.getCardLayout().show(igCriarAplicacao.getCardPanel(), "confCadPanel");
						igCriarAplicacao.setTxtpnSubTitulo("Confirme os dados da nova aplica��o financeira.");
						igCriarAplicacao.setProgressBar(75);
						igCriarAplicacao.getpConfCad().setDadosEditorPane(contaBancaria.exibeDadosFormatados());
						igCriarAplicacao.getpConfCad().setVisible(true);
						igCriarAplicacao.getBtnLimpar().setVisible(false);
						igCriarAplicacao.getBtnProximo().setVisible(false);
						igCriarAplicacao.getBtnFinalizar().setVisible(true);
					}
				}
				else
					igCriarAplicacao.setLblCamposErrados(true);
			} // fim if(e.getSource() == igCriarAplicacao.getBtnProximo())
			
			else if(e.getSource() == igCriarAplicacao.getBtnAnterior()) {
				if(painelVisivel instanceof PainelAbContaDadosSec) {
					igCriarAplicacao.getCardLayout().show(igCriarAplicacao.getCardPanel(), "dadosChavePanel");
					igCriarAplicacao.setTxtpnSubTitulo("Insira os dados chave da nova aplica��o financeira.");
					igCriarAplicacao.setProgressBar(0);
					igCriarAplicacao.setLblCamposErrados(false);
					igCriarAplicacao.getpAbContaDadosChave().setVisible(true);
					igCriarAplicacao.getBtnAnterior().setVisible(false);
				}
				else if(painelVisivel instanceof PainelAbContaSenhas) {
					igCriarAplicacao.getCardLayout().show(igCriarAplicacao.getCardPanel(), "dadosSecundarioPanel");
					igCriarAplicacao.setTxtpnSubTitulo("Insira os dados secund�rios da nova aplica��o financeira.");
					igCriarAplicacao.setProgressBar(25);
					igCriarAplicacao.setLblCamposErrados(false);
					igCriarAplicacao.getpAbContaDadosSec().setVisible(true);
				}
				else if(painelVisivel instanceof PainelConfCad) {
					igCriarAplicacao.getCardLayout().show(igCriarAplicacao.getCardPanel(), "senhasPanel");
					igCriarAplicacao.setTxtpnSubTitulo("Insera as senhas da nova aplica��o financeira.");
					igCriarAplicacao.setProgressBar(50);
					igCriarAplicacao.setLblCamposErrados(false);
					igCriarAplicacao.getpAbContaSenhas().setVisible(true);
					igCriarAplicacao.getBtnLimpar().setVisible(true);
					igCriarAplicacao.getBtnProximo().setVisible(true);
					igCriarAplicacao.getBtnFinalizar().setVisible(false);
				}
			} // fim if(e.getSource() == igCriarAplicacao.getBtnAnterior())
			
			else if(e.getSource() == igCriarAplicacao.getBtnFinalizar()) {
				ContaBancariaDAO contaBancariaDAO = new ContaBancariaDAO();
				contaBancaria.setNumConta(contaBancariaDAO.proximoValorSequencia(BancoDeDadosBVB.getInstance()));
				contaBancariaDAO.criar(BancoDeDadosBVB.getInstance(), contaBancaria);
				igCriarAplicacao.setProgressBar(100);
				
				igCriarAplicacao.getpConfCad().setDadosEditorPane(contaBancaria.toString());
				igCriarAplicacao.getpAbContaDadosChave().setNumContaTextField(Integer.toString(contaBancaria.getNumConta()));
				new JanelaPopUpInfo(igCriarAplicacao, "BVB - Cria��o de Aplica��o", " Cria��o de Aplica��o Financeira Realizada com Sucesso!",
				                    contaBancaria.toString());
				igCriarAplicacao.dispose();
			} // fim if(e.getSource() == igCriarAplicacao.getBtnFinalizar())
		} // fim else
	}

} // class TEActionCriarAP

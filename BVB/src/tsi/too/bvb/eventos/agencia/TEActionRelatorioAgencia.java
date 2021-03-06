package tsi.too.bvb.eventos.agencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import tsi.too.bvb.entidades.contabancaria.ContaBancaria;
import tsi.too.bvb.entidades.tiposenumerados.Mes;
import tsi.too.bvb.entidades.tiposenumerados.TipoConta;
import tsi.too.bvb.gui.JanelaPopUpAviso;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.agencia.IgRelatorioAgencia;
import tsi.too.bvb.persistencia.AgenciaDAO;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.ContaBancariaDAO;
import tsi.too.bvb.validacoes.ValidarDados;

/** Classe para tratar os eventos de a��o da janela <code>IgRelatorioAgencia</code>
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see ActionListener
 */
public class TEActionRelatorioAgencia implements ActionListener {
	
	private IgRelatorioAgencia igRelatorioAgencia;

	/** Cria uma inst�ncia do Tratador de eventos de a��o da janela <code>IgRelatorioAgencia</code>
	 * @param igRelatorioAgencia <code>IgRelatorioAgencia</code> que ser� manipulada
	 */
	public TEActionRelatorioAgencia(IgRelatorioAgencia igRelatorioAgencia) {
		super();
		this.igRelatorioAgencia = igRelatorioAgencia;
	}

	/** Trata os eventos de a��o dos elementos da janela <code>IgRelatorioAgencia</code>
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igRelatorioAgencia.getBtnBuscar()) {
			if(!igRelatorioAgencia.getCodigoTextField().getText().isEmpty()) {
				if(ValidarDados.validarIntPositivo(igRelatorioAgencia.getCodigoTextField().getText()) &&
				   igRelatorioAgencia.getCodigoTextField().getText().length() <= 4) {
					if(new ContaBancariaDAO().pesquisarContasAgencia(BancoDeDadosBVB.getInstance(),
					   igRelatorioAgencia.getCodigoTextField().getText()) == null)
						new JanelaPopUpAviso(igRelatorioAgencia, "BVB - Relat�rio de Ag�ncia", " Nenhuma conta banc�ria foi encontrada na ag�ncia" +
								             " de\n c�digo '" + igRelatorioAgencia.getCodigoTextField().getText() + "'.");
					else {
						igRelatorioAgencia.getCodigoTextField().setEnabled(false);
						igRelatorioAgencia.exibeOpcoesGerarRelatorio();
					}
				}
				else
					new JanelaPopUpErro(igRelatorioAgencia, "BVB - Relat�rio de Ag�ncia", " O c�digo de ag�ncia '" +
							            igRelatorioAgencia.getCodigoTextField().getText() + "' � inv�lido!" +
							            "\n O campo de busca deve receber um valor inteiro e positivo" +
							            "\n com no m�ximo 4 d�gitos.");
			}
			else
				new JanelaPopUpErro(igRelatorioAgencia, "BVB - Relat�rio de Ag�ncia", " Entrada inv�lida!\n" +
                                    " O campo de busca n�o pode ser vazio.");
		}
		
		else if(e.getSource() == igRelatorioAgencia.getBtnGerarRelatorio()) {
			// Vari�veis que cont�m as datas em formato String.
			String 	mesIni = (String) igRelatorioAgencia.getMesIniComboBox().getSelectedItem(),
					anoIni = (String) igRelatorioAgencia.getAnoIniComboBox().getSelectedItem(),
					mesFin = (String) igRelatorioAgencia.getMesFinComboBox().getSelectedItem(),
					anoFin = (String) igRelatorioAgencia.getAnoFinComboBox().getSelectedItem();
			
			// Transforma os itens selecionados na caixa de combina��o em uma vari�vel do tipo Data.
			Calendar calIni = Calendar.getInstance(),
					 calFin = Calendar.getInstance();
			calIni.set(Calendar.DAY_OF_MONTH, 1);
			calIni.set(Calendar.MONTH, Mes.obterMes(mesIni).getNumero());
			calIni.set(Calendar.YEAR, Integer.parseInt(anoIni));
			
			calFin.set(Calendar.DAY_OF_MONTH, 1);
			calFin.set(Calendar.MONTH, Mes.obterMes(mesFin).getNumero());
			calFin.set(Calendar.YEAR, Integer.parseInt(anoFin));
			
			Date dataIni = (Date) calIni.getTime(), // Data incial.
					 dataFin = (Date) calFin.getTime(); // Data final.
			
			if(!ValidarDados.validarPeriodo(dataIni, dataFin))
				new JanelaPopUpErro(igRelatorioAgencia, "BVB - Relat�rio de Ag�ncia", " A data inicial n�o pode ser maior que a data final!");
			else {
				// Vari�veis que cont�m os elementos do relat�rio.
				String codAgencia = igRelatorioAgencia.getCodigoTextField().getText(),
					   descricaoAgencia = new AgenciaDAO().pesquisarCodigo(BancoDeDadosBVB.getInstance(), igRelatorioAgencia
						                                                   .getCodigoTextField().getText()).getDescricao(),
					   dataFormatada,
				 	   cabecalho = String.format("Ag�ncia: %s %-50s Per�odo: %s/%s a %s/%s", descricaoAgencia, " ", mesIni, anoIni, mesFin, anoFin),
					   titulosColunas = String.format("%-20s\t%-30s\t%s", "Tipo de Conta", "Quantidade de Clientes", "Saldo Total (R$)"),
					   separador = "\n---------------------------------------------------------------"
								 + "---------------------------------------------------------------\n",
					   relatorio = cabecalho;
				
				int qtdeClientes[]; // Array de inteiros para armazenar a quantidade de clientes dos 4 tipos de contas.
				double saldoTotal[]; // Array de n�meros reais para armazenar o valor total em reais dos 4 tipos de contas.
	
				ContaBancariaDAO contaBancariaDAO = new ContaBancariaDAO();
				Set<Date> setDatas = contaBancariaDAO.pesquisarDatasContasAgencia(BancoDeDadosBVB.getInstance(), codAgencia, dataIni, dataFin);
				
				if(setDatas == null)
					new JanelaPopUpAviso(igRelatorioAgencia, "BVB - Relat�rio de Ag�ncia", " Nenhuma conta banc�ria foi encontrada na ag�ncia de" +
							             "\n c�digo '" + codAgencia + "', no per�odo de " + mesIni + " de " + anoIni +
							             " a " + mesFin + " de " + anoFin + ".");
				else {
					List<Date> listDatas = new ArrayList<Date>(setDatas); // Converte a cole��o 'Set' para 'Array List'.
					Collections.sort(listDatas); // Ordena a lista de datas.
					
					// Loop para percorrer a cole��o 'listDatas' e procurar as contas banc�rias.
					for(Date data : listDatas) {
						dataFormatada = new SimpleDateFormat("MM/yyyy").format(data.getTime());
								
						List<ContaBancaria> contasBancarias = contaBancariaDAO.pesquisarContasAgencia(BancoDeDadosBVB.getInstance(),
																									  codAgencia, data);
						
						qtdeClientes = obterQtdeClientesAgencia(contasBancarias);
						saldoTotal = obterSaldoTotalAgencia(contasBancarias);
						
						relatorio += separador + String.format("M�s: %s", dataFormatada) + separador + titulosColunas + separador;
					
						if(qtdeClientes[0] > 0)
							relatorio += String.format("%-30s\t%-50s\t%s\n", "Conta Corrente", qtdeClientes[0], "R$ " + saldoTotal[0]);
						if(qtdeClientes[1] > 0)
							relatorio += String.format("%-30s\t%-50s\t%s\n", "Poupan�a", qtdeClientes[1], "R$ " + saldoTotal[1]);
						if(qtdeClientes[2] > 0)
							relatorio += String.format("%-30s\t%-50s\t%s\n", "BVB FIF Pr�tico", qtdeClientes[2], "R$ " + saldoTotal[2]);
						if(qtdeClientes[3] > 0)
							relatorio += String.format("%-30s\t%-50s\t%s\n", "BVB FIF Executivo", qtdeClientes[3], "R$ " + saldoTotal[3]);
						
						relatorio += "\n\n";
					} // fim for(Date data : listDatas)
					
					igRelatorioAgencia.setRelatorioEditorPane(relatorio);
				} // fim else
			} // fim else
		} // fim if(e.getSource() == igRelatorioAgencia.getBtnGerarRelatorio())
		
		else if(e.getSource() == igRelatorioAgencia.getBtnAlterar()) {
			igRelatorioAgencia.getCodigoTextField().setEnabled(true);
			igRelatorioAgencia.escondeOpcoesGerarRelatorio();
		}
	}
	
	/** Obt�m o n�mero de clientes de cada tipo de conta banc�ria
	 * @param contasBancarias <code>List</code> do tipo <code>ContaBancaria</code> com as contas banc�rias
	 * @return um <code>int[]</code> de 4 posi��es com o n�mero de clientes de cada tipo de conta banc�ria contido na lista <code>List</code>
	 * 
	 * @see List
	 * @see ContaBancaria
	 * @see TipoConta
	 */
	private int[] obterQtdeClientesAgencia(List<ContaBancaria> contasBancarias) {
		int qtdeClientes[] = new int[TipoConta.getNumTipos()];
		
		for(ContaBancaria cb : contasBancarias) {
			switch(cb.getTipoConta().getNumero()) {
			case 1: qtdeClientes[0]++; break;
			case 2: qtdeClientes[1]++; break; 
			case 3: qtdeClientes[2]++; break; 
			case 4: qtdeClientes[3]++; break; 
			}
		}
		
		return qtdeClientes;
	}
	
	/** Obt�m o valor total de cada tipo de conta banc�ria
	 * @param contasBancarias <code>List</code> do tipo <code>ContaBancaria</code> com as contas banc�rias
	 * @return um <code>double[]</code> de 4 posi��es com o valor total de cada tipo de conta banc�ria contido na lista <code>List</code>
	 * 
	 * @see List
	 * @see ContaBancaria
	 * @see TipoConta
	 */
	private double[] obterSaldoTotalAgencia(List<ContaBancaria> contasBancarias) {
		double saldoTotal[] = new double[TipoConta.getNumTipos()];
		
		for(ContaBancaria cb : contasBancarias) {
			switch(cb.getTipoConta().getNumero()) {
			case 1: saldoTotal[0] += cb.getSaldo(); break;
			case 2: saldoTotal[1] += cb.getSaldo(); break; 
			case 3: saldoTotal[2] += cb.getSaldo(); break; 
			case 4: saldoTotal[3] += cb.getSaldo(); break; 
			}
		}
		
		return saldoTotal;
	}

} // class TEActionRelatorioAgencia

package tsi.too.bvb.eventos.funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import tsi.too.bvb.entidades.conexao.Conexao;
import tsi.too.bvb.gui.JanelaPopUpAviso;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.funcionario.IgRelatorioFuncionario;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.ConexaoDAO;
import tsi.too.bvb.persistencia.FuncionarioDAO;
import tsi.too.bvb.validacoes.ValidarDados;

public class TEActionRelatorioFuncionario implements ActionListener {
	
	private IgRelatorioFuncionario igRelatorioFuncionario;

	public TEActionRelatorioFuncionario(IgRelatorioFuncionario igRelatorioFuncionario) {
		super();
		this.igRelatorioFuncionario = igRelatorioFuncionario;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igRelatorioFuncionario.getBtnBuscar()) {
			if(!igRelatorioFuncionario.getLoginTextField().getText().isEmpty()) {
				if(ValidarDados.validarLoginFunc(igRelatorioFuncionario.getLoginTextField().getText())) {
					if(new FuncionarioDAO().pesquisarLoginUnico(BancoDeDadosBVB.getInstance(),
					   igRelatorioFuncionario.getLoginTextField().getText()) == null)
							new JanelaPopUpAviso(igRelatorioFuncionario, "BVB - Relat�rio de Funcion�rio", " Nenhum funcion�rio com o login '" +
									             igRelatorioFuncionario.getLoginTextField().getText() + "' foi encontrado.");
					else {
						igRelatorioFuncionario.getLoginTextField().setEnabled(false);
						igRelatorioFuncionario.exibeOpcoesGerarRelatorio();
					}
				}
				else
					new JanelaPopUpErro(igRelatorioFuncionario, "BVB - Relat�rio de Funcion�rio", " O login de funcion�rio '" +
									    igRelatorioFuncionario.getLoginTextField().getText() + "' � inv�lido!" +
							            "\n O campo de busca deve receber no m�nimo 6 e no" +
							            "\n m�ximo 20 caracteres letras, d�gitos e os s�mbolos" +
							            "\n underscore (_) ou ponto (.).");
			}
			else
				new JanelaPopUpErro(igRelatorioFuncionario, "BVB - Relat�rio de Funcion�rio", " Entrada inv�lida!\n" +
						            " O campo de busca n�o pode ser vazio.");
		}
		
		if(e.getSource() == igRelatorioFuncionario.getBtnGerarRelatorio()) {
			// Vari�veis que cont�m os elementos do relat�rio.
			String login = igRelatorioFuncionario.getLoginTextField().getText(),
			 	   cabecalho,
				   titulosColunas = String.format("%-15s\t%-15s\t%-15s\t%-15s\t%s", "Data Inicial", "Hora Final", "Data Final",
						   					      "Hora Final", "Tempo de Conex�o"),
				   separador = "---------------------------------------------------------------"
							 + "---------------------------------------------------------------",
				   relatorio = "",
				   dataFormatada,
				   dataIni,
				   HoraIni,
				   dataFin,
				   HoraFin;
			
			// vari�veis para armazenar o tempo que o usu�rios ficou logado no sistema.
			long tempoConexao,
				 tempoTotalConexao = 0;
			
			List<Conexao> listConexoes = new ConexaoDAO().pesquisarConexao(BancoDeDadosBVB.getInstance(), login);
			
			if(listConexoes == null)
				new JanelaPopUpAviso(igRelatorioFuncionario, "BVB - Relat�rio de Funcion�rio", " Nenhum registro de conex�o foi encontrado com o" +
									 "\n login'" + login + "'.");
			else {
				Calendar calIni = Calendar.getInstance(),
						 calFin = Calendar.getInstance(),
						 calCorrente = Calendar.getInstance();
				Date dataCorrente = listConexoes.get(0).getDataHoraInicial();
				calCorrente.setTime(dataCorrente);
				
				dataFormatada = new SimpleDateFormat("MM/yyyy").format(calCorrente.getTime());
				cabecalho = separador + "\n" + "Arquivo de Conex�o do Usu�rio: " + login + " - " + "M�s:" +
						    dataFormatada +"\n" + separador +"\n" + titulosColunas + "\n" + separador;
				relatorio += cabecalho;
						
				// Loop para percorrer a cole��o 'listConexoes'.
				for(Conexao con : listConexoes) {
					calIni.setTime(con.getDataHoraInicial());
					calFin.setTime(con.getDataHoraFinal());
					
					dataIni = new SimpleDateFormat("MM/yyyy").format(calIni.getTime());
					HoraIni = new SimpleDateFormat("HH:mm:ss").format(calIni.getTime());
					dataFin = new SimpleDateFormat("MM/yyyy").format(calFin.getTime());
					HoraFin = new SimpleDateFormat("HH:mm:ss").format(calFin.getTime());
					
					// Calcula o tempo que o usu�rio ficou logado no sistema.
					tempoConexao = TimeUnit.MILLISECONDS.toMinutes(con.getDataHoraFinal().getTime() - con.getDataHoraInicial().getTime());
					tempoTotalConexao += (con.getDataHoraFinal().getTime() - con.getDataHoraInicial().getTime());
					
					if(calIni.get(Calendar.MONTH) == calCorrente.get(Calendar.MONTH) && calIni.get(Calendar.YEAR) == calCorrente.get(Calendar.YEAR))
						relatorio += String.format("\n%-15s\t%-15s\t%-15s\t%-15s\t%s minutos", dataIni, HoraIni, dataFin, HoraFin, tempoConexao);
					else {
						tempoTotalConexao = TimeUnit.MILLISECONDS.toHours(tempoTotalConexao);
						relatorio += "\n" + separador +  "\nTempo Total de Conex�o: " + tempoTotalConexao + " horas" + "\n" + separador + "\n\n";
						tempoTotalConexao = 0;
						
						calCorrente.setTime(con.getDataHoraInicial());
						
						dataFormatada = new SimpleDateFormat("MM/yyyy").format(calCorrente.getTime());
						
						cabecalho = "\n" + separador +"\n" + "Arquivo de Conex�o do Usu�rio: " + login + " - " + "M�s:" +
								    dataFormatada + separador + titulosColunas + "\n" + separador + "\n";
						relatorio += cabecalho + String.format("\n%-15s\t%-15s\t%-15s\t%-15s\t%s",
								     dataIni, HoraIni, dataFin, HoraFin, tempoConexao);
					} // fim else
				} // fim for(Conexao con : listConexoes)
				
				tempoTotalConexao = TimeUnit.MILLISECONDS.toHours(tempoTotalConexao);
				relatorio += "\n" + separador +  "\nTempo Total de Conex�o: " + tempoTotalConexao + " horas" + "\n" + separador + "\n\n";
				
				igRelatorioFuncionario.setRelatorioEditorPane(relatorio);
			} // fim else
		} // fim if(e.getSource() == IgRelatorioCliente.getBtnGerarRelatorio())
		
		else if(e.getSource() == igRelatorioFuncionario.getBtnAlterar()) {
			igRelatorioFuncionario.getLoginTextField().setEnabled(true);
			igRelatorioFuncionario.escondeOpcoesGerarRelatorio();
		}
	}

} // class TEActionRelatorioFuncionario

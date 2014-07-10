package tsi.too.bvb.eventos.contabancaria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.too.bvb.gui.JanelaPopUpAviso;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.JanelaPopUpInfo;
import tsi.too.bvb.gui.contabancaria.IgDepositarSal;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.ContaBancariaDAO;
import tsi.too.bvb.validacoes.ValidarDados;

public class TEActionDepositarSal implements ActionListener {
	
	private IgDepositarSal igDepositarSal;

	public TEActionDepositarSal(IgDepositarSal igDepositarSal) {
		super();
		this.igDepositarSal = igDepositarSal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igDepositarSal.getBtnBuscar()) {
			if(!igDepositarSal.getNumContaTextField().getText().isEmpty()) {
				if(ValidarDados.validarIntPositivo(igDepositarSal.getNumContaTextField().getText()) &&
				   igDepositarSal.getNumContaTextField().getText().length() <=9) {
					igDepositarSal.setContaBancaria(new ContaBancariaDAO().pesquisarNumConta(BancoDeDadosBVB.getInstance(), igDepositarSal
                                                    .getNumContaTextField().getText()));
					
					if(igDepositarSal.getContaBancaria() == null)
						new JanelaPopUpAviso(igDepositarSal, "BVB - Dep�sito de Sal�rio", " Nenhuma conta banc�ria com o n�mero '" +
								             igDepositarSal.getNumContaTextField().getText() + "' foi encontrada.");
					else {
						if(igDepositarSal.getContaBancaria().isContaSalario()) {
							igDepositarSal.getNumContaTextField().setEnabled(false);
							igDepositarSal.exibeOpcoesDepositar();
						} // fim if(igDepositarSal.getContaBancaria().isContaSalario())
						else
							new JanelaPopUpAviso(igDepositarSal, "BVB - Dep�sito de Sal�rio", " A conta banc�ria de n�mero '" +
						                         igDepositarSal.getNumContaTextField().getText() + "' n�o � conta sal�rio." +
						                         "\n Insera o n�mero de uma conta banc�ria que esteja" +
						                         "\n habilitada a receber sal�rios.");
					} // fim else
				} // fim if(ValidarDados.validarIntPositivo(igDepositarSal.getNumContaTextField().getText()))
				else
					new JanelaPopUpErro(igDepositarSal, "BVB - Dep�sito de Sal�rio", " O n� de conta banc�ria '" +
							            igDepositarSal.getNumContaTextField().getText() + "' � inv�lido!" +
							             "\n O campo de busca deve receber um valor inteiro e positivo" +
							             "\n com no m�ximo 9 d�gitos.");
			} // fim if(!igDepositarSal.getNumContaTextField().getText().isEmpty())
			else
				new JanelaPopUpErro(igDepositarSal, "BVB - Dep�sito de Sal�rio", " Entrada inv�lida!\n" +
                                    " O campo de busca n�o pode ser vazio.");
		} // fim if(e.getSource() == igDepositarSal.getBtnBuscar())
		
		else if(e.getSource() == igDepositarSal.getBtnDepositar()) {
			double deposito = Double.parseDouble(igDepositarSal.getDepositoTextField().getText().replace(",", ".").replace("R", "").replace("$", "")),
			       saldo = igDepositarSal.getContaBancaria().getSaldo();
			
			if(deposito != 0) {
				igDepositarSal.getContaBancaria().setSaldo(saldo + deposito);
				new ContaBancariaDAO().alterarSaldo(BancoDeDadosBVB.getInstance(), igDepositarSal.getContaBancaria());
				new JanelaPopUpInfo(igDepositarSal, "BVB - Dep�sito de Sal�rio", " Dep�sito realizado com sucesso!");
				
				igDepositarSal.escondeOpcoesDepositar();
				igDepositarSal.getNumContaTextField().setEnabled(true);
			}
			else
				new JanelaPopUpErro(igDepositarSal, "BVB - Dep�sito de Sal�rio", " O valor do dep�sito n�o pode ser igual a '0'!");
		}
		
		else if(e.getSource() == igDepositarSal.getBtnAlterar()) {
			igDepositarSal.getNumContaTextField().setEnabled(true);
			igDepositarSal.escondeOpcoesDepositar();
		}
	}

} // class TEActionDepositarSal

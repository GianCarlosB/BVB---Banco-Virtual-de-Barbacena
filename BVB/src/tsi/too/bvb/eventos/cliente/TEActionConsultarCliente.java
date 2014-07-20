package tsi.too.bvb.eventos.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.too.bvb.entidades.cliente.Cliente;
import tsi.too.bvb.gui.JanelaPopUpAviso;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.cliente.IgConsultarCliente;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.ClienteDAO;
import tsi.too.bvb.validacoes.ValidarDados;

/** Classe para tratar os eventos de a��o da janela <code>IgConsultarCliente</code>
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see ActionListener
 */
public class TEActionConsultarCliente implements ActionListener {
	
	private IgConsultarCliente igConsultarCliente;

	/** Cria uma inst�ncia do Tratador de eventos de a��o da janela <code>IgConsultarCliente</code>
	 * @param igConsultarCliente <code>IgConsultarCliente</code> que ser� manipulada
	 */
	public TEActionConsultarCliente(IgConsultarCliente igConsultarCliente) {
		super();
		this.igConsultarCliente = igConsultarCliente;
	}

	/** Trata os eventos de a��o dos elementos da janela <code>IgConsultarCliente</code>
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igConsultarCliente.getBtnLimpar()) {
			igConsultarCliente.limpaTabela();
			igConsultarCliente.limpaCampos();
		}
		else if(e.getSource() == igConsultarCliente.getBtnBuscar()) {
			String cpfFormatado = igConsultarCliente.getCpfFormattedTextField().getText();
			String cpf = igConsultarCliente.getCpfFormattedTextField().getText().replace(".", "").replace("-", "");

			if(!igConsultarCliente.getCpfFormattedTextField().getText().replace(".", "").replace("-", "").replace(" ", "").isEmpty()) {
				if(ValidarDados.validarCPF(cpf)) {
					Cliente cliente = new ClienteDAO().pesquisarCpf(BancoDeDadosBVB.getInstance(), cpf);
					
					if(cliente != null) {
						if(!igConsultarCliente.pesquisaTabela(cliente))
							igConsultarCliente.addLinhasTabela(cliente);
						else
							new JanelaPopUpAviso(igConsultarCliente, "BVB - Consulta de Cliente", " O cliente de cpf '" +
									             cpfFormatado + "' j� foi consultado.");
					}
					else
						new JanelaPopUpAviso(igConsultarCliente, "BVB - Consulta de Cliente", " Nenhum cliente com o cpf '" +
								             cpfFormatado + "' foi encontrado.");
				}
				else
					new JanelaPopUpErro(igConsultarCliente, "BVB - Consulta de Cliente", " O n� de CPF '" +
							            cpfFormatado + "' � inv�lido!" +
				                        "\n O campo de busca deve receber 11 d�gitos decimais" +
				                        "\n de um CPF v�lido.");
			}
			else
				new JanelaPopUpErro(igConsultarCliente, "BVB - Consulta de Cliente", " Entrada inv�lida!\n" +
			                        " O campo de busca n�o pode ser vazio.");
		} // fim if(e.getSource() == igConsultarCliente.getBtnBuscar())
	}

} // class TEActionConsultarCliente

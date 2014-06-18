package tsi.too.bvb.eventos.menuprincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.too.bvb.entidades.agencia.Agencia;
import tsi.too.bvb.entidades.cliente.Cliente;
import tsi.too.bvb.entidades.cliente.Contato;
import tsi.too.bvb.entidades.cliente.Endereco;
import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.JanelaPopUpInfo;
import tsi.too.bvb.gui.agencia.IgCadAgencia;
import tsi.too.bvb.gui.agencia.IgConsultarAgencia;
import tsi.too.bvb.gui.cliente.IgCadCliente;
import tsi.too.bvb.gui.cliente.IgConsultarCliente;
import tsi.too.bvb.gui.excluircadastro.IgExcluirCadastro;
import tsi.too.bvb.gui.funcionario.IgCadFuncionario;
import tsi.too.bvb.gui.funcionario.IgConsultarFuncionario;
import tsi.too.bvb.gui.menuprincipal.IgMenuPrincipal;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;

public class TEActionMenuPrincipal implements ActionListener {
	
	private IgMenuPrincipal igMenuPrincipal;

	public TEActionMenuPrincipal(IgMenuPrincipal igMenuPrincipal) {
		super();
		this.igMenuPrincipal = igMenuPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == igMenuPrincipal.getAjudaImgBtn()){}
		
		else if(e.getSource() == igMenuPrincipal.getMntmLogout()){}
		
		else if(e.getSource() == igMenuPrincipal.getMntmAutor())
			new JanelaPopUpInfo(igMenuPrincipal, "BVB - Sobre", " Criado por:\n\n Diego Oliveira   &   Gian Carlos Barros Hon�rio");
		
		else if(e.getSource() == igMenuPrincipal.getMntmSair())
			igMenuPrincipal.terminaPrograma();
		
		else if(BancoDeDadosBVB.getInstance().getConn() != null) {
			if((e.getSource() == igMenuPrincipal.getCadClienteBtn()) || (e.getSource() == igMenuPrincipal.getCadClienteImgBtn()))
				new IgCadCliente(igMenuPrincipal, new Cliente(new Contato(), new Endereco()));
			
			else if((e.getSource() == igMenuPrincipal.getConsClienteBtn()) || (e.getSource() == igMenuPrincipal.getConsClienteImgBtn()))
				new IgConsultarCliente(igMenuPrincipal);
				
			else if((e.getSource() == igMenuPrincipal.getAltClienteBtn()) || (e.getSource() == igMenuPrincipal.getAltClienteImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getExClienteBtn()) || (e.getSource() == igMenuPrincipal.getExClienteImgBtn()))
				new IgExcluirCadastro(igMenuPrincipal, "BVB - Exclus�o de Cliente", "Exclus�o de Cliente",
						              "Insira o CPF do cliente que desja excluir.", 1);
			
			else if((e.getSource() == igMenuPrincipal.getRelClienteBtn()) || (e.getSource() == igMenuPrincipal.getRelClienteImgBtn())){}
		
			// Fim dos bot�es da aba "cliente".
			
			
			else if((e.getSource() == igMenuPrincipal.getCadFuncBtn()) || (e.getSource() == igMenuPrincipal.getCadFuncImgBtn()))
				new IgCadFuncionario(igMenuPrincipal, new Funcionario());
			
			else if((e.getSource() == igMenuPrincipal.getConsFuncBtn()) || (e.getSource() == igMenuPrincipal.getConsFuncImgBtn()))
				new IgConsultarFuncionario(igMenuPrincipal);
			
			else if((e.getSource() == igMenuPrincipal.getAltFuncBtn()) || (e.getSource() == igMenuPrincipal.getAltFuncImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getExFuncBtn()) || (e.getSource() == igMenuPrincipal.getExFuncImgBtn()))
				new IgExcluirCadastro(igMenuPrincipal, "BVB - Exclus�o de Funcion�rio", "Exclus�o de Funcion�rio",
			                          "Insira o Login do funcion�rio que desja excluir.", 2);
			
			else if((e.getSource() == igMenuPrincipal.getRelFuncBtn()) || (e.getSource() == igMenuPrincipal.getRelFuncImgBtn())){}
	
			// Fim dos bot�es da aba "funcion�rio".
			
			
			else if((e.getSource() == igMenuPrincipal.getCadAgBtn()) || (e.getSource() == igMenuPrincipal.getCadAgImgBtn()))
				new IgCadAgencia(igMenuPrincipal, new Agencia());
			
			else if((e.getSource() == igMenuPrincipal.getConsAgBtn()) || (e.getSource() == igMenuPrincipal.getConsAgImgBtn()))
				new IgConsultarAgencia(igMenuPrincipal);
			
			else if((e.getSource() == igMenuPrincipal.getAltAgBtn()) || (e.getSource() == igMenuPrincipal.getAltAgImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getExAgBtn()) || (e.getSource() == igMenuPrincipal.getExAgImgBtn()))
				new IgExcluirCadastro(igMenuPrincipal, "BVB - Exclus�o de Ag�ncia", "Exclus�o de Ag�ncia",
                                      "Insira o c�digo da ag�ncia que desja excluir.", 3);
			
			else if((e.getSource() == igMenuPrincipal.getRelAgBtn()) || (e.getSource() == igMenuPrincipal.getRelAgImgBtn())){}
			
			// Fim dos bot�es da aba "ag�ncia".
			
			
			else if((e.getSource() == igMenuPrincipal.getAbrirContaBtn()) || (e.getSource() == igMenuPrincipal.getAbrirContaImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getDepositarSalContaBtn()) || (e.getSource() == igMenuPrincipal.getDepositarSalContaImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getCriarAplicContaBtn()) || (e.getSource() == igMenuPrincipal.getCriarAplicContaImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getAlterarAplicContaBtn()) || (e.getSource() == igMenuPrincipal.getAlterarAplicContaImgBtn())){}
			
			// Fim dos bot�es da aba "conta".
			
			
			else if((e.getSource() == igMenuPrincipal.getAtmBtn()) || (e.getSource() == igMenuPrincipal.getAtmImgBtn())){}
			
			else if((e.getSource() == igMenuPrincipal.getAlterarSenhaBtn()) || (e.getSource() == igMenuPrincipal.getAlterarSenhaImgBtn())){}
			
			// Fim dos bot�es da aba "outros".
		} // fim if(BancoDeDadosBVB.getInstance().getConn() != null)
		else
			new JanelaPopUpErro(igMenuPrincipal, "BVB - ERRO", " A conex�o com o banco de dados n�o foi estabelecida!\n" +
					            " Para realizar esta opera��o reinicie a aplica��o!");
	}
	
} // class TEActionMenuPrincipal
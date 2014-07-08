package tsi.too.bvb.app;

import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.gui.IgApresentacao;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.login.IgLogin;
import tsi.too.bvb.gui.menuprincipal.IgMenuPrincipal;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;

public class bvbApp {
	
	public static void main(String[] args) {
		// Inicia a janela de apresenta��o (carregamento).
		IgApresentacao igApresentacao = new IgApresentacao();
		
		// Inicia a thread que faz a conex�o com o banco de dados.
		Thread threadBancoDeDados = new Thread(BancoDeDadosBVB.getInstance());
		threadBancoDeDados.start();
		
		try { 
			threadBancoDeDados.join(); // Faz o programa s� iniciar ap�s o banco de dados iniciar
		}
		catch (InterruptedException e) {
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			System.exit(0);
		}
		
		// Finaliza a Thread da janela de apresenta��o.
		igApresentacao.finalizarThread();
		
		// Finaliza a janela de apresenta��o.
		igApresentacao.dispose();
		
		IgMenuPrincipal igMenuPrincipal;
		
		// Enquanto o usu�rio clicar em Logout, a tela de login reaparece. 
		do{
			// O objeto 'funcion�rio' recebe os dados do funcion�rio caso ele consiga logar com sucesso.
			Funcionario funcionario = new IgLogin().getFuncionario();
			
			// Cria a janela que cont�m o menu principal
			igMenuPrincipal = new IgMenuPrincipal(funcionario);
		}while(igMenuPrincipal.isLogout());
	} // main()
	
} // class bvbApp

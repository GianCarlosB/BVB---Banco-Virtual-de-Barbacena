package tsi.too.bvb.app;

import tsi.too.bvb.gui.IgApresentacao;
import tsi.too.bvb.gui.JanelaPopUpErro;
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
			new JanelaPopUpErro(null, "BVB - ERRO", " Ocorreu uma interrup��o durante a conex�o com o banco de dados!", e);
			System.exit(0);
		}

		igApresentacao.finalizarThread();
		igApresentacao.dispose();
		new IgMenuPrincipal(); // Cria a janela que cont�m o menu principal
	} // main()
	
} // class bvbApp

package tsi.too.bvb.app;

import tsi.too.bvb.gui.IgApresentacao;
import tsi.too.bvb.gui.JanelaPopUpErro;
import tsi.too.bvb.gui.login.IgLogin;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;

/** 
 * Inicia o sistema do Banco Virtual de Barbacena. Cria uma conex�o com o banco de dados e a tela de login do sistema
 *
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see BancoDeDadosBVB
 * @see IgApresentacao
 * @see IgLogin
*/
public abstract class bvbApp {
	
	/** Cria uma inst�ncia do programa BVB
	 * @param args <code>String[]</code> com os argumentos de linha de comando, esses argumentos n�o s�o utilizados na aplica��o
	 */
	public static void main(String[] args) {
		// Inicia a janela de apresenta��o (carregamento).
		IgApresentacao igApresentacao = new IgApresentacao();

		// Inicia a thread que faz a conex�o com o banco de dados.
		Thread threadBancoDeDados = new Thread(BancoDeDadosBVB.getInstance());
		threadBancoDeDados.start();
		
		try { 
			threadBancoDeDados.join(); // Faz o programa s� iniciar ap�s o banco de dados iniciar.
		}
		catch (InterruptedException e) {
			new JanelaPopUpErro(null, "BVB - ERRO", e);
			System.exit(0);
		}
		
		// Finaliza a Thread da janela de apresenta��o.
		igApresentacao.finalizarThread();
		
		// Finaliza a janela de apresenta��o.
		igApresentacao.dispose();

		// Cria a janela que cont�m o login.
		new IgLogin();
	} // main()
	
} // class bvbApp

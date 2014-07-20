package tsi.too.bvb.gui;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/** Classe que define a GUI de uma janela popup de informa��o
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see JDialog
 */
public class JanelaPopUpInfo extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -5819037031569739045L;
	
	private final JPanel contentPanel = new JPanel();

	/** Cria uma inst�ncia da janela popup de informa��o
	 * @param janelaPai <code>Window</code> com a janela pai da caixa de di�logo <code>JanelaPopUpInfo</code>
	 * @param titulo <code>String</code> com o t�tulo da janela
	 * @param msgInfo <code>String</code> com a mensagem que ser� mostrada na janela
	 * @param info <code>String</code> com as informa��es que ser�o mostradas na janela
	 * 
	 * @see Window
	 */
	public JanelaPopUpInfo(Window janelaPai, String titulo, String msgInfo, String info) {
		setType(Type.POPUP);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		setTitle(titulo);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(52, 152, 219));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(52, 152, 219));
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(236, 240, 241), 1, true), "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(236, 240, 241)));
		scrollPane.setBounds(10, 82, 424, 133);
		contentPanel.add(scrollPane);
		
		JTextPane infoTextPane = new JTextPane();
		infoTextPane.setForeground(new Color(236, 240, 241));
		infoTextPane.setFont(new Font("Dialog", Font.PLAIN, 12));
		infoTextPane.setEditable(false);
		infoTextPane.setBackground(new Color(52, 152, 219));
		infoTextPane.setText(info);
		scrollPane.setViewportView(infoTextPane);
		
		JSeparator separatorBtn = new JSeparator();
		separatorBtn.setBounds(0, 226, 444, 2);
		contentPanel.add(separatorBtn);
		
		JLabel lblImg = new JLabel("img");
		lblImg.setIcon(new ImageIcon(JanelaPopUpInfo.class.getResource("/tsi/too/bvb/recursos/imagens/info.png")));
		lblImg.setBounds(402, 26, 32, 32);
		contentPanel.add(lblImg);
		
		JEditorPane msgEditorPane = new JEditorPane();
		msgEditorPane.setForeground(new Color(236, 240, 241));
		msgEditorPane.setFont(new Font("Dialog", Font.BOLD, 12));
		msgEditorPane.setText(msgInfo);
		msgEditorPane.setEditable(false);
		msgEditorPane.setBackground(new Color(52, 152, 219));
		msgEditorPane.setBorder(new TitledBorder(new LineBorder(new Color(236, 240, 241), 1, true), "INFO", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(236, 240, 241)));
		msgEditorPane.setBounds(10, 11, 382, 60);
		contentPanel.add(msgEditorPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(52, 152, 219));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JanelaPopUpInfo.this.dispose();
					}
				});
				okButton.setBackground(new Color(52, 152, 219));
				okButton.setMnemonic(KeyEvent.VK_O);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JanelaPopUpInfo.this.setLocationRelativeTo(janelaPai);
		executarSomInfo();
		setVisible(true);
	}
	
	/** Cria uma inst�ncia da janela popup de informa��o
	 * @param janelaPai <code>Window</code> com a janela pai da caixa de di�logo <code>JanelaPopUpInfo</code>
	 * @param titulo <code>String</code> com o t�tulo da janela
	 * @param msgInfo <code>String</code> com a mensagem que ser� mostrada na janela
	 * 
	 * @see Window
	 */
	public JanelaPopUpInfo(Window janelaPai, String titulo, String msgInfo) {
		setType(Type.POPUP);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		setTitle(titulo);
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(52, 152, 219));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JSeparator separatorBtn = new JSeparator();
		separatorBtn.setBounds(0, 126, 444, 2);
		contentPanel.add(separatorBtn);
		
		JLabel lblImg = new JLabel("img");
		lblImg.setIcon(new ImageIcon(JanelaPopUpInfo.class.getResource("/tsi/too/bvb/recursos/imagens/info.png")));
		lblImg.setBounds(402, 26, 32, 32);
		contentPanel.add(lblImg);
		
		JEditorPane msgEditorPane = new JEditorPane();
		msgEditorPane.setForeground(new Color(236, 240, 241));
		msgEditorPane.setFont(new Font("Dialog", Font.BOLD, 12));
		msgEditorPane.setText(msgInfo);
		msgEditorPane.setEditable(false);
		msgEditorPane.setBackground(new Color(52, 152, 219));
		msgEditorPane.setBorder(new TitledBorder(new LineBorder(new Color(236, 240, 241), 1, true), "INFO", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(236, 240, 241)));
		msgEditorPane.setBounds(10, 11, 382, 104);
		contentPanel.add(msgEditorPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(52, 152, 219));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JanelaPopUpInfo.this.dispose();
					}
				});
				okButton.setBackground(new Color(52, 152, 219));
				okButton.setMnemonic(KeyEvent.VK_O);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JanelaPopUpInfo.this.setLocationRelativeTo(janelaPai);
		executarSomInfo();
		setVisible(true);
	}
	
	/** Executa o som de informa��o
	 */	
	private void executarSomInfo() {
		Applet.newAudioClip(JanelaPopUpErro.class.getResource("/tsi/too/bvb/recursos/sons/chimes.wav")).play();
	}
	
} // class JanelaPopUpInfo

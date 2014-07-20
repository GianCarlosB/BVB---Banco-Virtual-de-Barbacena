package tsi.too.bvb.gui.funcionario;

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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import tsi.too.bvb.eventos.funcionario.TEActionRelatorioFuncionario;

/** Classe que define a GUI de relat�rio de funcion�rio do sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see JDialog
 */
public class IgRelatorioFuncionario extends JDialog {
	
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 6407104861209370753L;
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnGerarRelatorio;
	private JEditorPane relatorioEditorPane;
	private JTextField loginTextField;
	private JButton btnBuscar;
	private JButton btnAlterar;

	/** Cria uma inst�ncia da janela de relat�rio de funcion�rio do sistema BVB
	 * @param janelaPai <code>Window</code> com a janela pai da caixa de di�logo <code>IgRelatorioFuncionario</code>
	 * 
	 * @see Window
	 */	
	public IgRelatorioFuncionario(Window janelaPai) {
		setModal(true);
		Color nephritis = new Color(39, 174, 96);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("BVB - Relat\u00F3rio de Funcion\u00E1rio");
		setBounds(100, 100, 690, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JSeparator separatorTitulo = new JSeparator();
		separatorTitulo.setBounds(0, 69, 685, 2);
		contentPanel.add(separatorTitulo);
		
		JTextPane txtpnSubTitulo = new JTextPane();
		txtpnSubTitulo.setEditable(false);
		txtpnSubTitulo.setForeground(Color.WHITE);
		txtpnSubTitulo.setBackground(nephritis);
		txtpnSubTitulo.setText("Insira o login do funcion\u00E1rio do qual deseja gerar o relat\u00F3rio de conex\u00F5es.");
		txtpnSubTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnSubTitulo.setBounds(20, 36, 420, 22);
		contentPanel.add(txtpnSubTitulo);
		
		JTextPane txtpnTitulo = new JTextPane();
		txtpnTitulo.setEditable(false);
		txtpnTitulo.setForeground(Color.WHITE);
		txtpnTitulo.setBackground(nephritis);
		txtpnTitulo.setText("Relat\u00F3rio de Funcion\u00E1rio");
		txtpnTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnTitulo.setBounds(10, 11, 170, 22);
		contentPanel.add(txtpnTitulo);
		
		JLabel lblImg = new JLabel("Label Img");
		lblImg.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblImg.setIcon(new ImageIcon(IgRelatorioFuncionario.class.getResource("/tsi/too/bvb/recursos/imagens/User-48.png")));
		lblImg.setBounds(624, 11, 48, 48);
		contentPanel.add(lblImg);
		
		JEditorPane dtrpnCampoTitulo = new JEditorPane();
		dtrpnCampoTitulo.setBackground(nephritis);
		dtrpnCampoTitulo.setEditable(false);
		dtrpnCampoTitulo.setBounds(0, 0, 685, 70);
		contentPanel.add(dtrpnCampoTitulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 435, 685, 43);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IgRelatorioFuncionario.this.dispose();
			}
		});
		
		btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.setEnabled(false);
		btnGerarRelatorio.addActionListener(new TEActionRelatorioFuncionario(this));
		btnGerarRelatorio.setMnemonic(KeyEvent.VK_G);
		panel.add(btnGerarRelatorio);
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		btnCancelar.setActionCommand("OK");
		panel.add(btnCancelar);
		
		JSeparator separatorBtn = new JSeparator();
		separatorBtn.setBounds(0, 424, 685, 2);
		contentPanel.add(separatorBtn);
		
		JPanel panelRelatorio = new JPanel();
		panelRelatorio.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 255)));
		panelRelatorio.setBounds(10, 127, 662, 285);
		contentPanel.add(panelRelatorio);
		panelRelatorio.setLayout(new BorderLayout(0, 0));
		
		JScrollPane relatorioScrollPane = new JScrollPane();
		relatorioScrollPane.setBorder(null);
		relatorioScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelRelatorio.add(relatorioScrollPane, BorderLayout.CENTER);
		
		relatorioEditorPane = new JEditorPane();
		relatorioEditorPane.setFont(new Font("Arial", Font.BOLD, 14));
		relatorioEditorPane.setEditable(false);
		relatorioEditorPane.setBackground(new Color(238, 238, 238));
		relatorioScrollPane.setViewportView(relatorioEditorPane);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setDisplayedMnemonic(KeyEvent.VK_L);
		lblLogin.setBounds(10, 95, 45, 14);
		contentPanel.add(lblLogin);
		
		loginTextField = new JTextField();
		lblLogin.setLabelFor(loginTextField);
		loginTextField.setToolTipText("insira o login do funcion\u00E1rio que deseja gerar o relat\u00F3rio, e clique em buscar");
		loginTextField.setColumns(10);
		loginTextField.setBounds(100, 92, 308, 20);
		contentPanel.add(loginTextField);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new TEActionRelatorioFuncionario(this));
		btnAlterar.setVisible(false);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new TEActionRelatorioFuncionario(this));
		btnBuscar.setMnemonic(KeyEvent.VK_B);
		btnBuscar.setBounds(418, 91, 89, 23);
		contentPanel.add(btnBuscar);
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setBounds(418, 91, 89, 23);
		contentPanel.add(btnAlterar);
		
		setLocationRelativeTo(janelaPai);
		setVisible(true);
	}
	
	/** Exibe as op��es de gerar relat�rio dados da janela <code>IgRelatorioFuncionario</code>
	 */
	public void exibeOpcoesGerarRelatorio() {
		btnGerarRelatorio.setEnabled(true);
		btnBuscar.setVisible(false);
		btnAlterar.setVisible(true);
	}
	
	/** Esconde as op��es de gerar relat�rio dados da janela <code>IgRelatorioFuncionario</code>
	 */
	public void escondeOpcoesGerarRelatorio() {
		btnGerarRelatorio.setEnabled(false);
		btnBuscar.setVisible(true);
		btnAlterar.setVisible(false);
		loginTextField.setText("");
		relatorioEditorPane.setText("");
	}

	/** Retorna um <code>JButton</code> com o bot�o gerar relat�rio
	 * @return <code>JButton</code> com o bot�o gerar relat�rio
	 */
	public JButton getBtnGerarRelatorio() {
		return btnGerarRelatorio;
	}

	/** Retorna um <code>JTextField</code> com o campo de texto login
	 * @return <code>JTextField</code> com o campo de texto login
	 */
	public JTextField getLoginTextField() {
		return loginTextField;
	}

	/** Retorna um <code>JButton</code> com o bot�o buscar
	 * @return <code>JButton</code> com o bot�o buscar
	 */
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	/** Retorna um <code>JButton</code> com o bot�o alterar
	 * @return <code>JButton</code> com o bot�o alterar
	 */
	public JButton getBtnAlterar() {
		return btnAlterar;
	}

	/** Muda o campo de texto relat�rio
	 * @param relatorio <code>String</code> com o novo relat�rio do campo
	 */
	public void setRelatorioEditorPane(String relatorio) {
		this.relatorioEditorPane.setText(relatorio);;
	}
	
} // class IgRelatorioFuncionario

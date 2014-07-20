package tsi.too.bvb.gui.agencia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import tsi.too.bvb.entidades.tiposenumerados.Mes;
import tsi.too.bvb.eventos.agencia.TEActionRelatorioAgencia;
import tsi.too.bvb.validacoes.ValidarDados;

/** Classe que define a GUI de relat�rio de ag�ncia do sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see JDialog
 */
public class IgRelatorioAgencia extends JDialog {
	
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 8082188793725103086L;
	
	private int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
	private int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
	
	private JPanel contentPane;
	private JTextField codigoTextField;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private UtilDateModel model;
	private JDatePanelImpl dateIniPanel;
	private JDatePanelImpl dateFinPanel;
	private JButton btnGerarRelatorio;
	private JButton btnAlterar;
	private JEditorPane relatorioEditorPane;
	private JComboBox<String> mesIniComboBox;
	private JComboBox<String> anoIniComboBox;
	private JComboBox<String> mesFinComboBox;
	private JComboBox<String> anoFinComboBox;
	private DefaultComboBoxModel<String> modeloIniAnoCorrente = new DefaultComboBoxModel<String>(Mes.obterArrayMeses(mesAtual + 1));
	private DefaultComboBoxModel<String> modeloIniAnosPassados = new DefaultComboBoxModel<String>(Mes.obterArrayMeses());
	private DefaultComboBoxModel<String> modeloFinAnoCorrente = new DefaultComboBoxModel<String>(Mes.obterArrayMeses(mesAtual + 1));
	private DefaultComboBoxModel<String> modeloFinAnosPassados = new DefaultComboBoxModel<String>(Mes.obterArrayMeses());

	/** Cria uma inst�ncia da janela de relat�rio de ag�ncia do sistema BVB
	 * @param janelaPai <code>Window</code> com a janela pai da caixa de di�logo <code>IgRelatorioAgencia</code>
	 * 
	 * @see Window
	 */	
	public IgRelatorioAgencia(Window janelaPai) {
		setModal(true);
		Color pumpkin = new Color(211, 84, 0);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("BVB - Relat\u00F3rio de Ag\u00EAncia");
		setBounds(100, 100, 690, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separatorTitulo = new JSeparator();
		separatorTitulo.setBounds(0, 69, 685, 2);
		contentPane.add(separatorTitulo);
		
		JLabel lblImg = new JLabel("Label Img");
		lblImg.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblImg.setIcon(new ImageIcon(IgRelatorioAgencia.class.getResource("/tsi/too/bvb/recursos/imagens/Building-48.png")));
		lblImg.setBounds(624, 11, 48, 48);
		contentPane.add(lblImg);
		
		JTextPane txtpnSubTitulo = new JTextPane();
		txtpnSubTitulo.setEditable(false);
		txtpnSubTitulo.setForeground(Color.WHITE);
		txtpnSubTitulo.setBackground(pumpkin);
		txtpnSubTitulo.setText("Insira o c\u00F3digo da ag\u00EAncia da qual deseja gerar o relat\u00F3rio de um determinado per\u00EDodo.");
		txtpnSubTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnSubTitulo.setBounds(20, 36, 490, 22);
		contentPane.add(txtpnSubTitulo);
		
		JTextPane txtpnConsultaDeAgncia = new JTextPane();
		txtpnConsultaDeAgncia.setEditable(false);
		txtpnConsultaDeAgncia.setForeground(Color.WHITE);
		txtpnConsultaDeAgncia.setBackground(pumpkin);
		txtpnConsultaDeAgncia.setText("Relat\u00F3rio de Ag\u00EAncia");
		txtpnConsultaDeAgncia.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnConsultaDeAgncia.setBounds(10, 11, 150, 22);
		contentPane.add(txtpnConsultaDeAgncia);
		
		JEditorPane dtrpnCampoTitulo = new JEditorPane();
		dtrpnCampoTitulo.setBackground(pumpkin);
		dtrpnCampoTitulo.setEditable(false);
		dtrpnCampoTitulo.setBounds(0, 0, 685, 70);
		contentPane.add(dtrpnCampoTitulo);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setDisplayedMnemonic(KeyEvent.VK_O);
		lblCodigo.setBounds(10, 95, 60, 14);
		contentPane.add(lblCodigo);
		
		codigoTextField = new JTextField();
		lblCodigo.setLabelFor(codigoTextField);
		codigoTextField.setToolTipText("insira o c\u00F3digo da ag\u00EAncia que deseja gerar o relat\u00F3rio, e clique em buscar");
		codigoTextField.setColumns(10);
		codigoTextField.setBounds(100, 92, 308, 20);
		contentPane.add(codigoTextField);

		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new TEActionRelatorioAgencia(this));
		btnBuscar.setMnemonic(KeyEvent.VK_B);
		btnBuscar.setBounds(418, 91, 89, 23);
		contentPane.add(btnBuscar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new TEActionRelatorioAgencia(this));
		btnAlterar.setVisible(false);
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setBounds(418, 91, 89, 23);
		contentPane.add(btnAlterar);
		
		JPanel Btnpanel = new JPanel();
		Btnpanel.setBounds(0, 435, 685, 43);
		contentPane.add(Btnpanel);
		Btnpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.addActionListener(new TEActionRelatorioAgencia(this));
		btnGerarRelatorio.setMnemonic(KeyEvent.VK_G);
		btnGerarRelatorio.setEnabled(false);
		Btnpanel.add(btnGerarRelatorio);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IgRelatorioAgencia.this.dispose();
			}
		});
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		btnCancelar.setActionCommand("OK");
		Btnpanel.add(btnCancelar);
		
		JSeparator separatorBtn = new JSeparator();
		separatorBtn.setBounds(0, 424, 685, 2);
		contentPane.add(separatorBtn);
		
		model = new UtilDateModel();
		dateIniPanel = new JDatePanelImpl(model);
		dateIniPanel.setBounds(100, 15, 112, 24);
		
		model = new UtilDateModel();
		dateFinPanel = new JDatePanelImpl(model);
		dateFinPanel.setBounds(100, 15, 112, 24);
		
		JPanel panelRelatorio = new JPanel();
		panelRelatorio.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 255)));
		panelRelatorio.setBounds(10, 195, 662, 217);
		contentPane.add(panelRelatorio);
		panelRelatorio.setLayout(new BorderLayout(0, 0));
		
		JScrollPane relatorioScrollPane = new JScrollPane();
		relatorioScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		relatorioScrollPane.setBorder(null);
		panelRelatorio.add(relatorioScrollPane);
		
		relatorioEditorPane = new JEditorPane();
		relatorioEditorPane.setEditable(false);
		relatorioEditorPane.setFont(new Font("Arial", Font.BOLD, 14));
		relatorioEditorPane.setBackground(new Color(238, 238, 238));
		relatorioScrollPane.setViewportView(relatorioEditorPane);
		
		JPanel dataInicialPanel = new JPanel();
		dataInicialPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Inicial", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 153, 255)));
		dataInicialPanel.setBounds(10, 125, 326, 58);
		contentPane.add(dataInicialPanel);
		dataInicialPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMesIni = new JLabel("M\u00EAs:");
		dataInicialPanel.add(lblMesIni);
		lblMesIni.setDisplayedMnemonic(KeyEvent.VK_M);
		
		mesIniComboBox = new JComboBox<String>();
		mesIniComboBox.setToolTipText("selecione o m\u00EAs inicial");
		mesIniComboBox.setModel(modeloIniAnosPassados);
		lblMesIni.setLabelFor(mesIniComboBox);
		dataInicialPanel.add(mesIniComboBox);
		
		JLabel lblAnoIni = new JLabel("Ano:");
		dataInicialPanel.add(lblAnoIni);
		lblAnoIni.setDisplayedMnemonic(KeyEvent.VK_A);
		
		anoIniComboBox = new JComboBox<String>();
		anoIniComboBox.setToolTipText("selecione o ano inicial");
		anoIniComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				verifcarAnoIniCorrente();
			}
		});
		anoIniComboBox.setModel(new DefaultComboBoxModel<String>(arrayAnos()));
		lblAnoIni.setLabelFor(anoIniComboBox);
		dataInicialPanel.add(anoIniComboBox);
		
		JPanel dataFianlPanel = new JPanel();
		dataFianlPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Final", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 153, 255)));
		dataFianlPanel.setBounds(348, 124, 326, 58);
		contentPane.add(dataFianlPanel);
		
		JLabel lblMesFin = new JLabel("M\u00EAs:");
		dataFianlPanel.add(lblMesFin);
		lblMesFin.setDisplayedMnemonic(KeyEvent.VK_S);
		
		mesFinComboBox = new JComboBox<String>();
		mesFinComboBox.setToolTipText("selecione o m\u00EAs final");
		mesFinComboBox.setModel(modeloFinAnosPassados);
		lblMesFin.setLabelFor(mesFinComboBox);
		dataFianlPanel.add(mesFinComboBox);
		
		JLabel lblAnoFin = new JLabel("Ano:");
		dataFianlPanel.add(lblAnoFin);
		lblAnoFin.setDisplayedMnemonic(KeyEvent.VK_N);
		
		anoFinComboBox = new JComboBox<String>();
		anoFinComboBox.setToolTipText("selecione o ano final");
		anoFinComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				verifcarAnoFinCorrente();
			}
		});
		anoFinComboBox.setModel(new DefaultComboBoxModel<String>(arrayAnos()));
		lblAnoFin.setLabelFor(anoFinComboBox);
		dataFianlPanel.add(anoFinComboBox);
		
		setLocationRelativeTo(janelaPai);
		setVisible(true);
	}
	
	/** Exibe as op��es de gerar relat�rio da janela <code>IgAltAgencia</code>
	 */
	public void exibeOpcoesGerarRelatorio() {
		btnGerarRelatorio.setEnabled(true);
		btnBuscar.setVisible(false);
		btnAlterar.setVisible(true);
	}
	
	/** Esconde as op��es de gerar relat�rio da janela <code>IgAltAgencia</code>
	 */
	public void escondeOpcoesGerarRelatorio() {
		btnGerarRelatorio.setEnabled(false);
		btnBuscar.setVisible(true);
		btnAlterar.setVisible(false);
		codigoTextField.setText("");
		relatorioEditorPane.setText("");
	}
	
	/** Cria um <code>String[]</code> com os anos permitidos dentro dos par�metros aceitos no sistema BVB
	 *  @return <code>String[]</code> com os anos permitidos dentro dos par�metros aceitos no sistema BVB
	 */
	private String[] arrayAnos() {
		ArrayList<String> anosList = new ArrayList<String>();
		String  arrayAnos[];
		
		for(int ano = ValidarDados.ANO_MIN; ano <= anoAtual && ano < ValidarDados.ANO_MAX; ano++)
			anosList.add(Integer.toString(ano));
		
		arrayAnos = new String[anosList.size()];
		arrayAnos = anosList.toArray(arrayAnos);
		
		return arrayAnos;
	}
	
	/** Verifica se o ano inicial selecionado na caixa de combina��o � igual ao ano atual
	 */
	private void verifcarAnoIniCorrente() {
		if(Integer.parseInt((String) anoIniComboBox.getSelectedItem()) == anoAtual)
			mesIniComboBox.setModel(modeloIniAnoCorrente);
		else
			mesIniComboBox.setModel(modeloIniAnosPassados);
	}
	
	/** Verifica se o ano final selecionado na caixa de combina��o � igual ao ano atual
	 */
	private void verifcarAnoFinCorrente() {
		if(Integer.parseInt((String) anoFinComboBox.getSelectedItem()) == anoAtual)
			mesFinComboBox.setModel(modeloFinAnoCorrente);
		else
			mesFinComboBox.setModel(modeloFinAnosPassados);
	}

	/** Retorna um <code>JTextField</code> com o campo de texto c�digo
	 * @return <code>JTextField</code> com o campo de texto c�digo
	 */
	public JTextField getCodigoTextField() {
		return codigoTextField;
	}

	/** Retorna um <code>JButton</code> com o bot�o buscar
	 * @return <code>JButton</code> com o bot�o buscar
	 */
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	/** Retorna um <code>JButton</code> com o bot�o gerar relat�rio
	 * @return <code>JButton</code> com o bot�o gerar relat�rio
	 */
	public JButton getBtnGerarRelatorio() {
		return btnGerarRelatorio;
	}

	/** Retorna um <code>JButton</code> com o bot�o alterar
	 * @return <code>JButton</code> com o bot�o alterar
	 */
	public JButton getBtnAlterar() {
		return btnAlterar;
	}

	/** Retorna um <code>JComboBox</code> com a caixa de combina��o do m�s inicial
	 * @return <code>JComboBox</code> com a caixa de combina��o do m�s inicial
	 */
	public JComboBox<String> getMesIniComboBox() {
		return mesIniComboBox;
	}

	/** Retorna um <code>JComboBox</code> com a caixa de combina��o do ano inicial
	 * @return <code>JComboBox</code> com a caixa de combina��o do ano inicial
	 */
	public JComboBox<String> getAnoIniComboBox() {
		return anoIniComboBox;
	}

	/** Retorna um <code>JComboBox</code> com a caixa de combina��o do m�s final
	 * @return <code>JComboBox</code> com a caixa de combina��o do m�s final
	 */
	public JComboBox<String> getMesFinComboBox() {
		return mesFinComboBox;
	}

	/** Retorna um <code>JComboBox</code> com a caixa de combina��o do ano final
	 * @return <code>JComboBox</code> com a caixa de combina��o do ano final
	 */
	public JComboBox<String> getAnoFinComboBox() {
		return anoFinComboBox;
	}

	/** Muda o campo de texto relat�rio
	 * @param relatorio <code>String</code> com o novo relat�rio do campo
	 */
	public void setRelatorioEditorPane(String relatorio) {
		this.relatorioEditorPane.setText(relatorio);
	}
	
} // class IgRelatorioAgencia

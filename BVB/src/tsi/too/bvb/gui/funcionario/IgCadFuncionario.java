package tsi.too.bvb.gui.funcionario;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.entidades.tiposenumerados.TipoUsuario;
import tsi.too.bvb.eventos.funcionario.TEActionCadastrarFuncionario;
import tsi.too.bvb.gui.TratadorDeCampos;
import tsi.too.bvb.persistencia.BancoDeDadosBVB;
import tsi.too.bvb.persistencia.FuncionarioDAO;
import tsi.too.bvb.validacoes.ValidarDados;

/** Classe que define a GUI de cadastro de funcion�rio do sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see JDialog
 */
public class IgCadFuncionario extends JDialog implements TratadorDeCampos {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 2966236349805360639L;
	
	private JPanel contentPane;
	private JTextField loginTextField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnFinalizar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JButton btnVerificar;
	private JRadioButton rdbtnAdministrador;
	private JRadioButton rdbtnCaixa;
	private JRadioButton rdbtnGerente;
	private JPanel verificacaoPanel;
	private JLabel lblCamposErrados;
	private JPasswordField rPasswordField;
	
	/** Cria uma inst�ncia da janela de cadastro de funcion�rio do sistema BVB
	 * @param janelaPai <code>Window</code> com a janela pai da caixa de di�logo <code>IgCadFuncionario</code>
	 * @param funcionario <code>Funcionario</code> refer�nte ao objeto onde os dados ser�o salvos
	 * 
	 * @see Window
	 * @see Funcionario
	 */	
	public IgCadFuncionario(Window janelaPai, Funcionario funcionario) {
		setModal(true);
		Color nephritis = new Color(39, 174, 96);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("BVB - Cadastro de Funcion\u00E1rio");
		setBounds(100, 100, 523, (int)(506 * 0.85));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separatorBtn = new JSeparator();
		separatorBtn.setBounds(0, 348, 517, 2);
		contentPane.add(separatorBtn);
		
		JSeparator separatorTitulo = new JSeparator();
		separatorTitulo.setBounds(0, 69, 517, 2);
		contentPane.add(separatorTitulo);
		
		JTextPane txtpnSubTitulo = new JTextPane();
		txtpnSubTitulo.setEditable(false);
		txtpnSubTitulo.setForeground(Color.WHITE);
		txtpnSubTitulo.setBackground(nephritis);
		txtpnSubTitulo.setText("Insira o login e a senha do novo funcion\u00E1rio.");
		txtpnSubTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnSubTitulo.setBounds(20, 36, 290, 22);
		contentPane.add(txtpnSubTitulo);
		
		JTextPane txtpnTitulo = new JTextPane();
		txtpnTitulo.setEditable(false);
		txtpnTitulo.setForeground(Color.WHITE);
		txtpnTitulo.setBackground(nephritis);
		txtpnTitulo.setText("Cadastro de Funcion\u00E1rio");
		txtpnTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnTitulo.setBounds(10, 11, 210, 22);
		contentPane.add(txtpnTitulo);
		
		JLabel lblImg = new JLabel("Label Img");
		lblImg.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblImg.setIcon(new ImageIcon(IgCadFuncionario.class.getResource("/tsi/too/bvb/recursos/imagens/User-48.png")));
		lblImg.setBounds(459, 11, 48, 48);
		contentPane.add(lblImg);
		
		JEditorPane dtrpnCampoTitulo = new JEditorPane();
		dtrpnCampoTitulo.setBackground(nephritis);
		dtrpnCampoTitulo.setEditable(false);
		dtrpnCampoTitulo.setBounds(0, 0, 517, 70);
		contentPane.add(dtrpnCampoTitulo);
		
		JPanel Btnpanel = new JPanel();
		Btnpanel.setBounds(0, 359, 517, 43);
		contentPane.add(Btnpanel);
		Btnpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new TEActionCadastrarFuncionario(this, funcionario));
		btnFinalizar.setMnemonic(KeyEvent.VK_F);
		Btnpanel.add(btnFinalizar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new TEActionCadastrarFuncionario(this, funcionario));
		btnLimpar.setMnemonic(KeyEvent.VK_L);
		Btnpanel.add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IgCadFuncionario.this.dispose();
			}
		});
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		Btnpanel.add(btnCancelar);
		
		verificacaoPanel = new JPanel();
		verificacaoPanel.setLayout(null);
		verificacaoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "N\u00E3o Verificado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		verificacaoPanel.setBounds(100, 95, 407, 58);
		contentPane.add(verificacaoPanel);
		
		loginTextField = new JTextField();
		loginTextField.setToolTipText("este campo \u00E9 de preenchimento obrigat\u00F3rio, deve conter no m\u00EDnimo 6 e no m\u00E1ximo 20 caracteres (letras, d\u00EDgitos e os s\u00EDmbolos underscore (_) ou ponto (.)) e deve ser \u00FAnico");
		loginTextField.setColumns(10);
		loginTextField.setBounds(10, 20, 288, 20);
		verificacaoPanel.add(loginTextField);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("este campo \u00E9 de preenchimento obrigat\u00F3rio e deve conter no m\u00EDnimo 6 e no m\u00E1ximo 10 caracteres");
		passwordField.setBounds(100, 172, 308, 20);
		contentPane.add(passwordField);
		
		JPanel tipoUsuarioPanel = new JPanel();
		tipoUsuarioPanel.setLayout(null);
		tipoUsuarioPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo do Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 255)));
		tipoUsuarioPanel.setBounds(10, 235, 497, 58);
		contentPane.add(tipoUsuarioPanel);
		
		rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setToolTipText("selecione se o funcion\u00E1rio for um administrador");
		buttonGroup.add(rdbtnAdministrador);
		rdbtnAdministrador.setSelected(true);
		rdbtnAdministrador.setMnemonic(KeyEvent.VK_A);
		rdbtnAdministrador.setBounds(10, 20, 108, 23);
		tipoUsuarioPanel.add(rdbtnAdministrador);
		
		rdbtnCaixa = new JRadioButton("Caixa");
		rdbtnCaixa.setToolTipText("selecione se o funcion\u00E1rio for um caixa");
		buttonGroup.add(rdbtnCaixa);
		rdbtnCaixa.setMnemonic(KeyEvent.VK_I);
		rdbtnCaixa.setBounds(120, 20, 58, 23);
		tipoUsuarioPanel.add(rdbtnCaixa);
		
		rdbtnGerente = new JRadioButton("Gerente");
		rdbtnGerente.setToolTipText("selecione se o funcion\u00E1rio for um gerente");
		buttonGroup.add(rdbtnGerente);
		rdbtnGerente.setMnemonic(KeyEvent.VK_G);
		rdbtnGerente.setBounds(220, 20, 72, 23);
		tipoUsuarioPanel.add(rdbtnGerente);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setLabelFor(loginTextField);
		
		btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new TEActionCadastrarFuncionario(this, funcionario));
		btnVerificar.setMnemonic(KeyEvent.VK_V);
		btnVerificar.setBounds(308, 19, 89, 23);
		verificacaoPanel.add(btnVerificar);
		lblLogin.setDisplayedMnemonic(KeyEvent.VK_O);
		lblLogin.setBounds(10, 115, 60, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setLabelFor(passwordField);
		lblSenha.setDisplayedMnemonic(KeyEvent.VK_S);
		lblSenha.setBounds(10, 175, 60, 14);
		contentPane.add(lblSenha);
		
		lblCamposErrados = new JLabel("* Os campos destacados de vermelho n\u00E3o foram preenchidos corretamente!");
		lblCamposErrados.setVisible(false);
		lblCamposErrados.setForeground(Color.RED);
		lblCamposErrados.setBounds(10, 323, 497, 14);
		contentPane.add(lblCamposErrados);
		
		JLabel lblRepetirSenha = new JLabel("Repita a Senha:");
		lblRepetirSenha.setDisplayedMnemonic(KeyEvent.VK_R);
		lblRepetirSenha.setBounds(10, 205, 90, 14);
		contentPane.add(lblRepetirSenha);
		
		rPasswordField = new JPasswordField();
		lblRepetirSenha.setLabelFor(rPasswordField);
		rPasswordField.setToolTipText("este campo \u00E9 de preenchimento obrigat\u00F3rio e as senhas devem conferir");
		rPasswordField.setBounds(100, 204, 308, 20);
		contentPane.add(rPasswordField);
		
		setLocationRelativeTo(janelaPai);
		setVisible(true);
	}
	
	/** Limpa os campos da janela
	 */
	@Override
	public void limparCampos() {
		inserirBordasPadrao();
		
		loginTextField.setText("");
		passwordField.setText("");
		rPasswordField.setText("");
		
		rdbtnAdministrador.setSelected(true);
	}
	
	/** Salva os dados inseridos na janela em um objeto do tipo <code>Funcionario</code>
	 * @param funcionario <code>Object</code> refer�nte ao objeto que ser� salvo
	 */
	@Override
	public void salvarCampos(Object funcionario) {
		((Funcionario) funcionario).setNomeUsuario(loginTextField.getText());
		((Funcionario) funcionario).setSenha(new String(passwordField.getPassword()));
		((Funcionario) funcionario).setTipoUsuario(TipoUsuario.obterTipoUsuario(obterRadioBtnSelecionado()));
	}

	/** Verifica se os campos da janela foram preenchidos corretamente. Os campos errados 
	 * recebem uma borda vermelha
	 *  @return <code>boolean</code> com <code>true</code> caso todos os campos tenham sido preenchidos corretamente, 
	 *  e <code>false</code> caso contr�rio
	 */
	@Override
	public boolean validarCampos() {
		boolean valido = true;
		
		if(!validarLogin()) valido = false;

		if(ValidarDados.validarSenhaFunc(passwordField.getPassword())) {
			passwordField.setBorder(UIManager.getBorder("PasswordField.border"));
			
			if(!ValidarDados.validarSenhasIguais(passwordField.getPassword(), rPasswordField.getPassword())) {
				rPasswordField.setBorder(new LineBorder(Color.RED));
				valido = false;
			}
			else rPasswordField.setBorder(UIManager.getBorder("PasswordField.border"));
		}
		else {
			passwordField.setBorder(new LineBorder(Color.RED));
			rPasswordField.setBorder(UIManager.getBorder("PasswordField.border"));
			valido = false;
		}
		
		return valido;
	}
	
	/** Insere a borda padr�o nos campos da janela
	 */
	@Override
	public void inserirBordasPadrao() {
		verificacaoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "N\u00E3o Verificado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		loginTextField.setBorder(UIManager.getBorder("TextField.border"));
		passwordField.setBorder(UIManager.getBorder("PasswordField.border"));
		rPasswordField.setBorder(UIManager.getBorder("PasswordField.border"));
	}

	/** Retorna o texto do bot�o de r�dio que est� selecionado
	 *  @return <code>String</code> com o texto do bot�o de r�dio que est� selecionado
	 */
	private String obterRadioBtnSelecionado() {
		String radioBtnTxt;
		
		if(rdbtnAdministrador.isSelected())
			radioBtnTxt = rdbtnAdministrador.getText();
		else if(rdbtnCaixa.isSelected())
			radioBtnTxt = rdbtnCaixa.getText();
		else
			radioBtnTxt = rdbtnGerente.getText();
		
		return radioBtnTxt;
	}
	
	/** Verifica se o campo login da janela for preenchido corretamente. Se o campo 
	 *  estiver errado, receber� uma borda vermelha
	 *  @return <code>boolean</code> com <code>true</code> caso o campo  tenha sido preenchido corretamente, 
	 *  e <code>false</code> caso contr�rio
	 */
	public boolean validarLogin() {
		if(ValidarDados.validarLoginFunc(loginTextField.getText())) {
			if(new FuncionarioDAO().pesquisarLoginUnico(BancoDeDadosBVB.getInstance(), loginTextField.getText()) == null) {
				verificacaoPanel.setBorder(new TitledBorder(null, "Dispon�vel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
				loginTextField.setBorder(UIManager.getBorder("TextField.border"));
				
				return true;
			}
			else {
				verificacaoPanel.setBorder(new TitledBorder(null, "Indispon�vel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
				loginTextField.setBorder(new LineBorder(Color.RED));
			}
		}
		else {
			verificacaoPanel.setBorder(new TitledBorder(null, "Inv�lido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
			loginTextField.setBorder(new LineBorder(Color.RED));
		}
		
		return false;
	}

	/** Retorna um <code>JTextField</code> com o campo de texto login
	 * @return <code>JTextField</code> com o campo de texto login
	 */
	public JTextField getLoginTextField() {
		return loginTextField;
	}

	/** Retorna um <code>JPasswordField</code> com o campo de texto senha
	 * @return <code>JPasswordField</code> com o campo de texto senha
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/** Retorna um <code>JPasswordField</code> com o campo de texto senha repetida
	 * @return <code>JPasswordField</code> com o campo de texto senha repetida
	 */
	public JPasswordField getrPasswordField() {
		return rPasswordField;
	}

	/** Retorna um <code>JButton</code> com o bot�o finalizar
	 * @return <code>JButton</code> com o bot�o finalizar
	 */
	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}

	/** Retorna um <code>JButton</code> com o bot�o limpar
	 * @return <code>JButton</code> com o bot�o limpar
	 */
	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	/** Retorna um <code>JButton</code> com o bot�o verificar
	 * @return <code>JButton</code> com o bot�o verificar
	 */
	public JButton getBtnVerificar() {
		return btnVerificar;
	}

	/** Retorna um <code>JRadioButton</code> com o bot�o de r�dio administrador
	 * @return <code>JRadioButton</code> com o bot�o de r�dio administrador
	 */
	public JRadioButton getRdbtnAdministrador() {
		return rdbtnAdministrador;
	}

	/** Retorna um <code>JRadioButton</code> com o bot�o de r�dio caixa
	 * @return <code>JRadioButton</code> com o bot�o de r�dio caixa
	 */
	public JRadioButton getRdbtnCaixa() {
		return rdbtnCaixa;
	}

	/** Retorna um <code>JRadioButton</code> com o bot�o de r�dio gerente
	 * @return <code>JRadioButton</code> com o bot�o de r�dio gerente
	 */
	public JRadioButton getRdbtnGerente() {
		return rdbtnGerente;
	}
	
	/** Muda a visibilidade do r�tulo que indica que algum campo n�o foi corretamente preenchido
	 * @param visivel <code>boolean</code> com <code>true</code> se for vis�vel e <code>false</code> caso contr�rio
	 */
	public void setLblCamposErrados(boolean visivel) {
		this.lblCamposErrados.setVisible(visivel);
	}
	
} // class IgCadFuncionario

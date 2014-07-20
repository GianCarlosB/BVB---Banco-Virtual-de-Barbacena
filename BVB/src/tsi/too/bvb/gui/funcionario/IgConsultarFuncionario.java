package tsi.too.bvb.gui.funcionario;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tsi.too.bvb.entidades.funcionario.Funcionario;
import tsi.too.bvb.entidades.tiposenumerados.TipoUsuario;
import tsi.too.bvb.eventos.funcionario.TEActionConsultarFuncionario;
import tsi.too.bvb.eventos.funcionario.TETecladoConsultarFuncionario;

/** Classe que define a GUI de consulta de funcion�rio do sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 * 
 * @see JDialog
 */
public class IgConsultarFuncionario extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 2318656837430102737L;
	
	// N�mero de colunas da tabela com os dados dos clientes pesquisados
	private final int NUMERO_COLUNAS_TABELA = 2;
	private int num_linhas;
	
	private final String COLUNA_LOGIN = "Login";
	private final String COLUNA_TIPO_USUARIO = "Tipo de Usu�rio";
	private final String[] COLUNAS_FUNCIONARIO = {COLUNA_LOGIN, COLUNA_TIPO_USUARIO};
	private String[][] linhasTabela = new String[0][NUMERO_COLUNAS_TABELA];
	
	private JPanel contentPane;
	private JTextField LoginTextField;
	private JComboBox<Object> tipoUsuarioComboBox;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JTable tableConsulta;
	private JButton btnBuscar;

	/** Cria uma inst�ncia da janela de consulta de funcion�rio do sistema BVB
	 * @param janelaPai <code>Window</code> com a janela pai da caixa de di�logo <code>IgConsultarFuncionario</code>
	 * 
	 * @see Window
	 */	
	public IgConsultarFuncionario(Window janelaPai) {
		setModal(true);
		final Color nephritis = new Color(39, 174, 96);
		final Color emerald = new Color(46, 204, 113);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("BVB - Consulta de Funcion\u00E1rio");
		setBounds(100, 100, 523, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separatorTitulo = new JSeparator();
		separatorTitulo.setBounds(0, 69, 517, 2);
		contentPane.add(separatorTitulo);
		
		JTextPane txtpnSubTitulo = new JTextPane();
		txtpnSubTitulo.setEditable(false);
		txtpnSubTitulo.setForeground(Color.WHITE);
		txtpnSubTitulo.setBackground(nephritis);
		txtpnSubTitulo.setText("Pesquise um funcion\u00E1rio pelo login ou pelo tipo.");
		txtpnSubTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnSubTitulo.setBounds(20, 36, 320, 22);
		contentPane.add(txtpnSubTitulo);
		
		JTextPane txtpnTitulo = new JTextPane();
		txtpnTitulo.setEditable(false);
		txtpnTitulo.setForeground(Color.WHITE);
		txtpnTitulo.setBackground(nephritis);
		txtpnTitulo.setText("Consulta de Funcion\u00E1rio");
		txtpnTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnTitulo.setBounds(10, 11, 210, 22);
		contentPane.add(txtpnTitulo);
		
		JLabel lblImg = new JLabel("Label Img");
		lblImg.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblImg.setIcon(new ImageIcon(IgConsultarFuncionario.class.getResource("/tsi/too/bvb/recursos/imagens/User-48.png")));
		lblImg.setBounds(459, 11, 48, 48);
		contentPane.add(lblImg);
		
		JEditorPane dtrpnCampoTitulo = new JEditorPane();
		dtrpnCampoTitulo.setBackground(nephritis);
		dtrpnCampoTitulo.setEditable(false);
		dtrpnCampoTitulo.setBounds(0, 0, 517, 70);
		contentPane.add(dtrpnCampoTitulo);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setDisplayedMnemonic(KeyEvent.VK_O);
		lblLogin.setBounds(10, 95, 60, 14);
		contentPane.add(lblLogin);
		
		JPanel Btnpanel = new JPanel();
		Btnpanel.setBounds(0, 435, 517, 43);
		contentPane.add(Btnpanel);
		Btnpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new TEActionConsultarFuncionario(this));
		btnLimpar.setMnemonic(KeyEvent.VK_L);
		Btnpanel.add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IgConsultarFuncionario.this.dispose();
			}
		});
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		btnCancelar.setActionCommand("OK");
		Btnpanel.add(btnCancelar);
		
		JSeparator separatorBtn = new JSeparator();
		separatorBtn.setBounds(0, 424, 517, 2);
		contentPane.add(separatorBtn);
		
		LoginTextField = new JTextField();
		LoginTextField.addKeyListener(new TETecladoConsultarFuncionario(this));
		LoginTextField.setToolTipText("a pesquisa \u00E9 realizada automaticamente quando cada caracter \u00E9 digitado");
		LoginTextField.setColumns(10);
		LoginTextField.setBounds(100, 92, 308, 20);
		contentPane.add(LoginTextField);
		
		tipoUsuarioComboBox = new JComboBox<Object>(TipoUsuario.obterArrayTipos());
		tipoUsuarioComboBox.setToolTipText("para pesquisar pelo tipo selecione um item da caixa e clique em buscar");
		tipoUsuarioComboBox.setBounds(10, 125, 109, 20);
		contentPane.add(tipoUsuarioComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 497, 257);
		contentPane.add(scrollPane);
		
		tableConsulta = new JTable(new DefaultTableModel(linhasTabela, COLUNAS_FUNCIONARIO)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableConsulta.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
		    /**
			 * @serial
			 */
			private static final long serialVersionUID = -2860037545840868142L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
		        if(row % 2 == 0)
		            setBackground(nephritis);  
		        else 
		            setBackground(emerald);  
		        
		        return this;  
		    }  
		});
		tableConsulta.setForeground(Color.WHITE);
		tableConsulta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableConsulta.setColumnSelectionAllowed(true);
		tableConsulta.setCellSelectionEnabled(true);
		tableConsulta.setBackground(nephritis);
		tableConsulta.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(tableConsulta);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new TEActionConsultarFuncionario(this));
		btnBuscar.setMnemonic(KeyEvent.VK_B);
		btnBuscar.setBounds(129, 124, 89, 23);
		contentPane.add(btnBuscar);
		
		setLocationRelativeTo(janelaPai);
		setVisible(true);
	}
	
	/** Adiciona linhas � tabela. � inserido o dado do funcion�rio que for passado por par�metro
	 * @param funcionario <code>Funcionario</code> com o dado do funcion�rio que ser� inserido na tabela
	 */
	public void addLinhasTabela(Funcionario funcionario) {
		DefaultTableModel model = ((DefaultTableModel)(tableConsulta.getModel()));
		model.setNumRows(num_linhas++);
		
		Object[] linha = new Object[NUMERO_COLUNAS_TABELA];

		linha[0] = funcionario.getNomeUsuario();
		linha[1] = funcionario.getTipoUsuario();
		model.addRow(linha);
	}
	
	/** Adiciona linhas � tabela. S�o inseridos os dados dos funcion�rios que foram passados por par�metro na lista <code>List</code>
	 * @param funcionarios <code>List</code> com os dados dos funcion�rios que ser�o inseridos na tabela
	 * 
	 * @see List
	 */
	public void addLinhasTabela(List<Funcionario> funcionarios) {
		DefaultTableModel model = limpaTabela();
		
		if (funcionarios.size() > 0) {
			Object[] linha = new Object[NUMERO_COLUNAS_TABELA];
			
			for(int i = 0; i < funcionarios.size(); i++, num_linhas++) {
				linha[0] = funcionarios.get(i).getNomeUsuario();
				linha[1] = funcionarios.get(i).getTipoUsuario().getPerfil();
				model.addRow(linha);
			}
		}
	}
	
	/** Limpa a tabela de pesquisa apagando os dados contidos nela
	 * @return <code>DefaultTableModel</code> com o modelo da tabela
	 */
	public DefaultTableModel limpaTabela() {
		DefaultTableModel model = ((DefaultTableModel)(tableConsulta.getModel()));

		num_linhas = 0;
		model.setNumRows(num_linhas);
		
		return model;
	}
	
	/** Limpa os campos da janela <code>IgConsultarFuncionario</code>
	 */
	public void limpaCampos() {
		LoginTextField.setText("");
		tipoUsuarioComboBox.setSelectedIndex(0);
	}
	
	/** Pesquisa se determinado funcion�rio foi inserida na tabela
	 * @param funcionario <code>Funcionario</code> com os dados do funcion�rio que ser� pesquisado
	 * @return <code>boolean</code> com <code>true</code> caso o funcion�rio tenha sido encontrado, e <code>false</code> caso contr�rio
	 */
	public boolean pesquisaTabela(Funcionario funcionario) {
		for(int i = 0; i < num_linhas; i++) {
			String login = tableConsulta.getModel().getValueAt(i, 0).toString();
			if(login.equals(funcionario.getNomeUsuario()))
				return true;
		}
		
		return false;
	}

	/** Retorna um <code>JTextField</code> com o campo de texto login
	 * @return <code>JTextField</code> com o campo de texto login
	 */
	public JTextField getLoginTextField() {
		return LoginTextField;
	}

	/** Retorna uma <code>String</code> com o texto o item selecionado na caixa de combina��o
	 * @return <code>String</code> com o texto o item selecionado na caixa de combina��o
	 */
	public String getTipoUsuarioComboBox() {
		return TipoUsuario.obterTipoUsuario((String)tipoUsuarioComboBox.getSelectedItem()).toString();
	}

	/** Retorna um <code>JButton</code> com o bot�o limpar
	 * @return <code>JButton</code> com o bot�o limpar
	 */
	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	/** Retorna um <code>JButton</code> com o bot�o buscar
	 * @return <code>JButton</code> com o bot�o buscar
	 */
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
} // class IgConsultarFuncionario

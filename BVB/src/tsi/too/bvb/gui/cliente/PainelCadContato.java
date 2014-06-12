package tsi.too.bvb.gui.cliente;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import tsi.too.bvb.entidades.Mascara;
import tsi.too.bvb.entidades.cliente.Cliente;
import tsi.too.bvb.validacoes.ValidarDados;

public class PainelCadContato extends JPanel implements PainelCliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2413065008402544562L;
	private JFormattedTextField telFixoFormTextField;
	private JFormattedTextField telMovelFormTextField;
	

	/**
	 * Create the panel.
	 */
	public PainelCadContato() {
		setLayout(null);
		
		JLabel lblTelefoneFixo = new JLabel("Tel Fixo:");
		lblTelefoneFixo.setDisplayedMnemonic(KeyEvent.VK_F);
		lblTelefoneFixo.setBounds(10, 25, 80, 14);
		add(lblTelefoneFixo);
		
		JLabel lblTelefoneMvel = new JLabel("Tel M\u00F3vel:");
		lblTelefoneMvel.setDisplayedMnemonic(KeyEvent.VK_M);
		lblTelefoneMvel.setBounds(10, 55, 90, 14);
		add(lblTelefoneMvel);
		
		telFixoFormTextField = new JFormattedTextField(new Mascara("(##)####-####"));
		lblTelefoneFixo.setLabelFor(telFixoFormTextField);
		telFixoFormTextField.setToolTipText("este campo \u00E9 de preenchimento obrigat\u00F3rio");
		telFixoFormTextField.setBounds(100, 22, 308, 20);
		add(telFixoFormTextField);
		
		telMovelFormTextField = new JFormattedTextField(new Mascara("(##)####-####"));
		lblTelefoneMvel.setLabelFor(telMovelFormTextField);
		telMovelFormTextField.setToolTipText("este campo \u00E9 de preenchimento opcional");
		telMovelFormTextField.setBounds(100, 52, 308, 20);
		add(telMovelFormTextField);
		
		JLabel lblTelFixoImg = new JLabel("New label");
		lblTelFixoImg.setIcon(new ImageIcon("D:\\Meus Documentos\\TSI\\Git\\tsi.too.bvb\\BVB\\src\\tsi\\too\\bvb\\recursos\\imagens\\Telephone-32.png"));
		lblTelFixoImg.setDisplayedMnemonic(KeyEvent.VK_F);
		lblTelFixoImg.setBounds(418, 16, 32, 32);
		add(lblTelFixoImg);
		
		JLabel lblTelMovelImg = new JLabel("New label");
		lblTelMovelImg.setIcon(new ImageIcon("D:\\Meus Documentos\\TSI\\Git\\tsi.too.bvb\\BVB\\src\\tsi\\too\\bvb\\recursos\\imagens\\Mobile-Phone-32.png"));
		lblTelMovelImg.setDisplayedMnemonic(KeyEvent.VK_M);
		lblTelMovelImg.setBounds(418, 46, 32, 32);
		add(lblTelMovelImg);
	}

	@Override
	public void limpaCampos() {
		telFixoFormTextField.setText("");
		telMovelFormTextField.setText("");
	}

	@Override
	public void salvarCampos(Cliente cliente) {
		cliente.getContato().setTelefoneFixo(telFixoFormTextField.getText().replace("(", "").replace(")", "").replace("-", ""));
		cliente.getContato().setTelefoneMovel(telMovelFormTextField.getText().replace("(", "").replace(")", "").replace("-", ""));
	}

	@Override
	public boolean validarCampos() {
		boolean valido = true;
		
		if(ValidarDados.validarIntPositivo(telFixoFormTextField.getText().replace("(", "")
				                           .replace(")", "").replace("-", "")) == false) {
			telFixoFormTextField.setBorder(new LineBorder(Color.RED));
			valido = false;
		}
		else telFixoFormTextField.setBorder(UIManager.getBorder("FormattedTextField.border"));
		
		return valido;
	}
	
} // class PainelCadContato

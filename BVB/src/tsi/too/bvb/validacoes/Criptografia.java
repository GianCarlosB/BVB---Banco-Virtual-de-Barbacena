package tsi.too.bvb.validacoes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** A classe <code>Criptografia</code> possui um �nico m�todo cuja fun��o � realizar a criptografia de senhas
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class Criptografia {
	 
	/** Criptografa uma <code>String</code> para MD5
	 *  @param password <code>String</code> com os caracteres que ser�o criptografados
	 *  @throws NoSuchAlgorithmException poss�vel erro disparado quando um algoritmo criptogr�fico particular � requerido,
	 *  mas n�o est� dispon�vel no ambiente
	 *  
	 *  @return <code>String</code> com a senha criptografada em MD5
	 */
	public static String converterSenhaParaMD5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
 
		return String.format("%32x", hash);
    }
	
} // class ConvertPasswordToMD5
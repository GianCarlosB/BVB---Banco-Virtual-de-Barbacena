package tsi.too.bvb.validacoes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

/** A classe <code>ValidarDados</code> possui diversos m�todos e constantes necess�rios na valida��o de opera��es do sistema BVB
 * 
 * @author Gian Carlos Barros Hon�rio
 * @author Diego Oliveira
 */
public class ValidarDados {
	
	/** <code>int</code> que determina o n�mero de d�gitos da senha n�merica de uma conta banc�ria */
	public static final int SENHA_NUM = 4;
	
	/** <code>int</code> que determina o n�mero de d�gitos da senha alfab�tica de uma conta banc�ria */
	public static final int SENHA_ALF = 6;
	
	/** <code>int</code> que determina o n�mero m�nimo de d�gitos de um login ou senha de funcion�rio */
	public static final int FUNC_MIN = 6;
	
	/** <code>int</code> que determina o n�mero m�ximo de d�gitos de um login de funcion�rio */
	public static final int FUNC_LOGIN_MAX = 20;
	
	/** <code>int</code> que determina o n�mero m�ximo de d�gitos de uma senha de funcion�rio */
	public static final int FUNC_SENHA_MAX = 10;
	
	/** <code>int</code> que determina o ano m�nimo de uma data de abertura de conta banc�ria */
	public static final int ANO_MIN = 2001;
	
	/** <code>int</code> que determina o ano m�ximo de uma data de abertura de conta banc�ria */
	public static final int ANO_MAX = 2099;
	
	/** Verifica se uma cadeia de caracteres <code>String</code> est� no formato de uma express�o regular
	 *  @param string <code>String</code> com a cadeia de caracteres que ser�o verificados
	 *  @param expressaoRegular <code>String</code> com a express�o regular
	 *  @return <code>boolean</code> com <code>true</code> caso o valor do objeto String seja igual ao padr�o 
	 *  definido pela express�o regular, e <code>false</code> caso contr�rio
	 */
	public static boolean verificarCadeiaDeCaracteres(String string, String expressaoRegular) {
		// Verifica se o valor do objeto String � igual ao padr�o definido pela express�o regular.
		if(string.matches(expressaoRegular))
			return true;
		
		return false;
	}
	
	/** Verifica se uma <code>String</code> de d�gitos decimais � um cpf v�lido
	 *  @param CPF <code>String</code> com os d�gitos do CPF
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como par�metro forme um cpf v�lido, e <code>false</code> caso contr�rio
	 */
	public static boolean validarCPF(String CPF) {
		// Considera-se erro CPF's formados por uma sequ�ncia de n�meros iguais
	    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
	        CPF.equals("22222222222") || CPF.equals("33333333333") ||
	        CPF.equals("44444444444") || CPF.equals("55555555555") ||
	        CPF.equals("66666666666") || CPF.equals("77777777777") ||
	        CPF.equals("88888888888") || CPF.equals("99999999999") ||
	       (CPF.length() != 11))
			return false;

	    try {
			char dig10, dig11;
			int sm = 0, i, r, num, peso = 10;

			// C�lculo do 1� d�gito verificador
			for(i = 0; i < 9; i++) {              
			/*
			 *  converte o i-esimo caractere do CPF em um numero:
			 *  por exemplo, transforma o caractere '0' no inteiro 0
			 *  (48 eh a posicao de '0' na tabela ASCII)
			 */    
				num = (int)(CPF.charAt(i) - 48); 
				sm = sm + (num * peso);
				peso = peso - 1;
			}
		
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
			   dig10 = '0';
			else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
			
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
			  num = (int)(CPF.charAt(i) - 48);
			  sm = sm + (num * peso);
			  peso = peso - 1;
			}
		
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
			   dig11 = '0';
			else dig11 = (char)(r + 48);
			
			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
			   return true;
		  
			return false;
			
		} catch (InputMismatchException erro) {
			return false;
		}
	}
	
	/** Verifica se uma <code>String</code> est� vazia
	 *  @param str <code>String</code> com a cadeia de caracteres que ser� verificada 
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como par�metro seja vazia, e <code>false</code> caso contr�rio
	 */
	public static boolean validarVazio(String str) {
		if(str.equals(""))
			return false;
		
		return true;
	}
	
	/** Verifica se uma <code>String</code> � um valor inteiro
	 *  @param str <code>String</code> com os caracteres que ser�o verificados 
	 *  @return <code>boolean</code> com <code>true</code> caso os caracteres da <code>String</code> 
	 *  passada como par�metro seja um valor inteiro, e <code>false</code> caso contr�rio
	 */
	public static boolean validarIntPositivo(String str) {
		try {
			if(Long.parseLong(str) >= 0)
				return true;
			
			return false;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	/** Verifica se uma <code>String</code> � um valor real
	 *  @param str <code>String</code> com os caracteres que ser�o verificados 
	 *  @return <code>boolean</code> com <code>true</code> caso os caracteres da <code>String</code> 
	 *  passada como par�metro seja um valor real, e <code>false</code> caso contr�rio
	 */
	public static boolean validarDoublePositivo(String str) {
		try {
			if(Double.parseDouble(str) >= 0)
				return true;
			
			return false;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	/** Verifica se uma <code>Date</code> � uma data poss�vel dentro dos par�metros aceitos no sistema BVB
	 *  @param data <code>Date</code> com a data que ser� verificada
	 *  @return <code>boolean</code> com <code>true</code> caso a data <code>Date</code> 
	 *  passada como par�metro seja uma data poss�vel, dentro dos par�metros aceitos no sistema BVB, e 
	 *  <code>false</code> caso contr�rio
	 *  
	 *  @see Date
	 *  @see ANO_MIN
	 *  @see ANO_MAX
	 */
	public static boolean validarData(Date data) {
		int ano = -1;
		
		if(data != null) {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(data);
	        ano = cal.get(Calendar.YEAR);
		}
		
		if(ano < ANO_MIN || ano > ANO_MAX) // Poss�vel valida��o || data.compareTo(new Date()) > 0
			return false;
		
		return true;
	}
	
	/** Verifica se duas <code>Date</code> formam um per�odo v�lido
	 *  @param dataIni <code>Date</code> com a data inicial do per�odo
	 *  @param dataFin <code>Date</code> com a data final do per�odo
	 *  @return <code>boolean</code> com <code>true</code> caso a data inicial <code>Date</code> 
	 *  passada como par�metro seja maior que a data final <code>Date</code> passada como par�metro, e 
	 *  <code>false</code> caso contr�rio
	 *  
	 *  @see Date
	 */
	public static boolean validarPeriodo(Date dataIni, Date dataFin) {
		Calendar calIni = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		
		if(dataIni != null && dataFin != null) {
			calIni.setTime(dataIni);
			calFin.setTime(dataFin);
		}
		else
			return false;
		
		if(calFin.compareTo(calIni) < 0) // Data final � antes da data inicial.
			return false;
		
		return true;
	}
	
	/** Verifica se uma <code>String</code> � uma senha num�rica de conta banc�ria v�lida: 4 d�gitos decimais
	 *  @param senha <code>String</code> com os caracteres que ser�o verificados
	 *  @return <code>boolean</code> com <code>true</code> caso os caracteres da <code>String</code> 
	 *  passada como par�metro seja uma senha num�rica de conta banc�ria v�lida, e <code>false</code> caso contr�rio
	 *  
	 *  @see SENHA_NUM
	 */
	public static boolean validarSenhaNumCB(String senha) {
		if(senha.length() != SENHA_NUM)
			return false;
		if(!verificarCadeiaDeCaracteres(senha, "[0-9]{4}"))
			return false;
		
		return true;
	}
	
	/** Verifica se uma <code>String</code> � uma senha alfab�tica de conta banc�ria v�lida: 6 letras mai�sculas ou min�sculas
	 *  @param senha <code>String</code> com os caracteres que ser�o verificados
	 *  @return <code>boolean</code> com <code>true</code> caso os caracteres da <code>String</code> 
	 *  passada como par�metro seja uma senha alfab�tica de conta banc�ria v�lida, e <code>false</code> caso contr�rio
	 *  
	 *  @see SENHA_ALF
	 */
	public static boolean validarSenhaAlfCB(String senha) {
		if(senha.length() != SENHA_ALF)
			return false;
		
		if(!verificarCadeiaDeCaracteres(senha, "[a-zA-Z]{6}"))
			return false;
		
		return true;
	}

	/** Verifica se uma <code>String</code> � um login de funcion�rio v�lido: 6 a 20 letras, d�gitos e os s�mbolos underscore (_) ou ponto (.)
	 *  @param login <code>String</code> com os caracteres que ser�o verificados
	 *  @return <code>boolean</code> com <code>true</code> caso os caracteres da <code>String</code> 
	 *  passada como par�metro seja um login de funcion�rio v�lido, e <code>false</code> caso contr�rio
	 *  
	 *  @see FUNC_MIN
	 *  @see FUNC_LOGIN_MAX
	 */
	public static boolean validarLoginFunc(String login) {
		if(login.length() < FUNC_MIN || login.length() > FUNC_LOGIN_MAX)
			return false;
		
		if(!verificarCadeiaDeCaracteres(login, "[a-zA-Z0-9\\.\\_]*"))
			return false;
		
		return true;
	}
	
	/** Verifica se um <code>char[]</code> � uma senha de funcion�rio v�lida
	 *  @param senha <code>char[]</code> com os caracteres que ser�o verificados
	 *  @return <code>boolean</code> com <code>true</code> caso os caracteres do <code>char[]</code> 
	 *  passado como par�metro seja uma senha de funcion�rio v�lida, e <code>false</code> caso contr�rio
	 *  
	 *  @see FUNC_MIN
	 *  @see FUNC_SENHA_MAX
	 */
	public static boolean validarSenhaFunc(char[] senha) {
		if(senha.length < FUNC_MIN || senha.length > FUNC_SENHA_MAX)
			return false;
		
		return true;
	}
	
	/** Verifica se dois <code>char[]</code> s�o iguais
	 *  @param senha1 <code>char[]</code> refer�nte a senha 1
	 *  @param senha2 <code>char[]</code> refer�nte a senha 2
	 *  @return <code>boolean</code> com <code>true</code> caso os dois <code>char[]</code> passados como 
	 *  par�metro sejam iguais, e <code>false</code> caso contr�rio
	 */
	public static boolean validarSenhasIguais(char[] senha1, char[] senha2) {
		if(!Arrays.equals(senha1, senha2))
			return false;
		
		return true;
	}
	
	/** Verifica se duas <code>String</code> s�o iguais
	 *  @param senha1 <code>String</code> refer�nte a senha 1
	 *  @param senha2 <code>String</code> refer�nte a senha 2
	 *  @return <code>boolean</code> com <code>true</code> caso as duas <code>String</code> passados como 
	 *  par�metro sejam iguais, e <code>false</code> caso contr�rio
	 */
	public static boolean validarSenhasIguais(String senha1, String senha2) {
		if(!senha1.equals(senha2))
			return false;
		
		return true;
	}

} // class ValidarDados

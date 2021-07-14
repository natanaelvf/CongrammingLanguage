package instrucoes;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

import run.LiConToJava;


public class Memoria {

	HashMap<String,Object> map = new HashMap<>();
	Stack<Object> stack = new Stack<>();

	private static final Pattern DOUBLE_PATTERN = Pattern.compile(
			"[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
					"([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
					"(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
			"[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

	private static final String SYSOUT = "System.out.println(";
	private static final String EOL = ");\r\n";

	public static final String[] MEMORY_INSTRUCTIONS = {"guarda", "empurra", "puxa", "espreita"};

	public static String escolhe(String str) {

		String instrucao = str.split(" ")[0];
		StringBuilder sb = new StringBuilder();

		switch(instrucao) {


		case "guarda":
			String[] chaveValor = depoisDaInstrucao(str.strip()).replace(" ","").split(",");
			checkAllTypes(chaveValor[0], chaveValor[1], sb, 0);

			break;

		case "empurra":
			sb.append("pilha.push(\""+depoisDaInstrucao(str.strip())+"\""+EOL);
			break;

		case "puxa":
			sb.append(SYSOUT+"pilha.pop().toString()"+EOL);
			break;

		case "espreita":
			sb.append(SYSOUT+"pilha.peek().toString()"+EOL);
			break;

		default:
			break;

		}
		return sb.toString();
	}


	public static void checkAllTypes(String valor, String chave, StringBuilder sb, int index)  {
		if (isFloat(valor)) 
			sb.append("double" + chave + " = " + valor + EOL);

		if (valor.charAt(index) == '\'') 
			sb.append("char" + chave + " = " + valor + EOL);

		if (valor.charAt(index) == '\"') 
			sb.append("String" + chave + " = " + valor + EOL);
	}

	public static String depoisDaInstrucao(String str) {
		return str.split(" ", 2)[1];
	}

	public static boolean isFloat(String s){
		return DOUBLE_PATTERN.matcher(s).matches();
	}
}

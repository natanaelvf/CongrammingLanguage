package instrucoes;

public class Maths {

	public static final String[] MATHS_INSTRUCTIONS = {"mais", "menos", "inc", "dec", "eleva", 
			"raiz", "vezes", "div", "mod"};

	public static final String SYSOUT = "System.out.println(";
	public static final String EOL = ");\r\n";
	public static final String POW = "Math.pow(";
	public static final String GET = "(double)mapa.get(\"";

	public static String escolhe(String str) {

		StringBuilder sb  = new StringBuilder();
		String instrucao  = str.split(" ")[0];
		String variavelUm = str.split(" ")[1];
		char c1			  = variavelUm.charAt(0);
		String variavelDois = str.split(" ")[3];
		char c2			  = variavelDois.charAt(0);

		switch(instrucao) {
		case "mais":
			appendInstruction("+",sb,variavelUm,variavelDois,c1,c2);
			break;

		case "menos":
			appendInstruction("-",sb,variavelUm,variavelDois,c1,c2);
			break;

		case "inc":
			if (Character.isDigit(c1)) 
				sb.append(SYSOUT + Double.parseDouble(variavelUm) + "+1" + EOL);
			else 
				sb.append(SYSOUT + GET + variavelUm + "\")+1" + EOL);
			break;

		case "dec":
			if (Character.isDigit(c1)) 
				sb.append(SYSOUT + Double.parseDouble(variavelUm) + "-1" + EOL);
			else 
				sb.append(SYSOUT + GET + variavelUm + "\")-1" + EOL);
			break;

		case "eleva":
			if (bothAreDigits(c1,c2)) 
				sb.append(SYSOUT + POW + Double.parseDouble(variavelUm) +
						"," + Double.parseDouble(variavelDois) + ")" + EOL);
			else 
				if (Character.isDigit(c1)) {
					sb.append(SYSOUT  + POW + Double.parseDouble(variavelUm) + 
							"," + GET + variavelDois + "\"))" + EOL);
				} else {
					sb.append(SYSOUT + POW + GET + variavelUm + "\")," 
							+ Double.parseDouble(variavelDois) + ")"+ EOL);
				}
			break;

		case "raiz":
			if (bothAreDigits(c1,c2)) 
				sb.append(SYSOUT + POW + Double.parseDouble(variavelUm) +
						",1/" + Double.parseDouble(variavelDois) + ")" + EOL);
			else 
				if (Character.isDigit(c1)) {
					sb.append(SYSOUT  + POW + Double.parseDouble(variavelUm) + 
							",1/" + GET + variavelDois + "\"))" + EOL);
				} else {
					sb.append(SYSOUT + POW + GET + variavelUm + "\"),1/" 
							+ Double.parseDouble(variavelDois) + ")"+ EOL);
				}
			break;

		case "vezes":
			appendInstruction("*",sb,variavelUm,variavelDois,c1,c2);
			break;

		case "div":
			appendInstruction("/",sb,variavelUm,variavelDois,c1,c2);
			break;

		case "mod":
			appendInstruction("%",sb,variavelUm,variavelDois,c1,c2);
			break;
		default:
			break;
		}
		return sb.toString();
	}

	public static void appendInstruction(String math,StringBuilder sb, 
										 String x1, String x2, 
										 char c1, char c2) {
		if (bothAreDigits(c1,c2)) 
			sb.append(SYSOUT + Double.parseDouble(x1) + math 
				+ Double.parseDouble(x2) + EOL);
		else 
			if (Character.isDigit(c1)) {
				sb.append(SYSOUT + Double.parseDouble(x1) + math 
						+ GET + x2 + "\")" + EOL);
			} else {
				sb.append(SYSOUT + Double.parseDouble(x2) + math 
						+ GET + x1 + "\")" + EOL);
			}
	}

	public static boolean bothAreDigits(char c1, char c2) {
		return Character.isDigit(c1)&&Character.isDigit(c2);
	}
}

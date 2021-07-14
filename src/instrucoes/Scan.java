package instrucoes;

import run.LiConToJava;

public class Scan {

	public static final String PUT = "mapa.put(";
	public static final String EOL = ");\r\n";
	public static final String NEW_SCANNER = "new Scanner(System.in)";

	public static String escolhe(String str) {

		StringBuilder sb = new StringBuilder();
		String instrucao = str.split(" ")[1];
		String varNome   = str.split(" ")[2];

		switch(instrucao) {
		case "numero":
			sb.append(PUT+"\""+varNome+"\","+NEW_SCANNER+".nextDouble()"+EOL);
			break;

		case "palavra":
			sb.append(PUT+"\""+varNome+"\","+NEW_SCANNER+".next()"+EOL);
			break;

		case "caracter":
			sb.append(PUT+"\""+varNome+"\","+NEW_SCANNER+".next()"+EOL);
			break;

		case "array":
			switch(varNome) {
				case "numero":
					sb.append(scaneiaArray("double", str.split(" ")[3]));
					break;
					
				case "palavra":
					sb.append(scaneiaArray("String", str.split(" ")[3]));
					break;
					
				case "caracter":
					sb.append(scaneiaArray("char", str.split(" ")[3]));
					break;
				default:
					break;
			}
			break;

		default:
			break;
		}

		return sb.toString();
	}
	
	public static String scaneiaArray(String classe, String varNome) {
		if (classe == "palavra") {
			return "\r\n"+ LiConToJava.tabs()+"String" + varNome + 
					" = (new Scanner(System.in)).nextLine().split(\"\\s+\");\\r\\n";
		} else {
			if (classe == "caracter") {
				return "\r\n"+ LiConToJava.tabs()+"String" + varNome + 
						" = (new Scanner(System.in)).nextLine();\\r\\n";
			}
		} // else is Double Array
		return "\r\n"+LiConToJava.tabs()+"String str = (new Scanner(System.in)).nextLine();\r\n" +
				LiConToJava.tabs() + "String[] split = str.split(\"\\s+\");\r\n" +
				LiConToJava.tabs() + classe + "[] " + varNome + " = new " + classe + "[split.length];\r\n"+
				LiConToJava.tabs() + "for (int i = 0; i < " + varNome + ".length; i++)\r\n" +
				LiConToJava.tabs() + "\t" + varNome + "[i] = " + 
				(classe.equals("double")?"Double.parseDouble(split[i]);\r\n\r\n":"split[i];\r\n\r\n");
	}
}

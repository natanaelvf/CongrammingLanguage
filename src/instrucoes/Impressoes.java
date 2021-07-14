package instrucoes;

public class Impressoes {
	
	public static final String EOL = ");\r\n";
	
	public static final String[] PRINT_INSTRUCTIONS = {"diz", "GRITA"};

	public static String escolhe(String str) {

		StringBuilder sb = new StringBuilder("System.out.println(");
		String semInstrucao = Memoria.depoisDaInstrucao(str);
		char fst = semInstrucao.charAt(0);
	

		switch(str.split(" ")[0]) {
		case "GRITA":
			if (fst == '\'')
				sb.append("Character.toUpperCase("
						 + semInstrucao
						 + ")" + EOL);
			
			if (fst == '\"')
				sb.append( semInstrucao
						 + ".toUpperCase()" + EOL);
			
			if (Character.isLetter(fst))
					sb.append("mapa.get(\""+semInstrucao+"\")" + EOL);
			
			break;
			
		case "diz":
			if (fst == '\'')
				sb.append("Character.toUpperCase("
						 + semInstrucao
						 + ")" + EOL);
			
			if (fst == '\"')
				sb.append( semInstrucao
						 + ".toUpperCase()" + EOL);
			
			//  IS ARRAY
			else {
				sb.append("Arrays.toString("+semInstrucao+")"+EOL);
			}
			break;
			
		default:
			break;
		}

		return sb.toString();
	}
}

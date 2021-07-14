package instrucoes;

public class Ciclos {
	
	public static final String[] CYCLE_INSTRUCTIONS = {"se", "enquanto"};

	public static String escolhe(String str) {

		StringBuilder sb = new StringBuilder();
		String instrucao = str.split(" ")[0];
		

		switch(instrucao) {
		case "se":
			sb.append( "if ("
					  + Memoria.depoisDaInstrucao(str)
					  + ") {\r\n");
			break;
			
		case "enquanto":
			sb.append( "while ("
					  + Memoria.depoisDaInstrucao(str)
					  + ") {\r\n");
			break;
			
		default:
			break;
		}
		return sb.toString();
	}
}

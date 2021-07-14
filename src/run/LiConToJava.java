package run;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import instrucoes.Ciclos;
import instrucoes.Impressoes;
import instrucoes.Memoria;
import instrucoes.Scan;
import instrucoes.Maths;

public class LiConToJava {

	public static final String FILENAME = "./src/run/LiConJavaRun.java";

	public static final String HEADER = """
			package run;

			import java.util.Scanner;\r
			import java.util.HashMap;\r
			import java.util.Arrays;\r
			import java.util.Stack;\r
			import java.util.Map;\r

			public class LiConJavaRun {\r\n

			public static Map<String,Object> mapa = new HashMap<>();\r\n
			public static Stack<Object> pilha = new Stack<>();\r\n

			\tpublic static void main(String[] args) {\r
			""";

	public static int nrTabs = 1;
	
	public static HashMap<String,Object> mapa = new HashMap<>();

	public static void main(String[] args) {

		String file = parseFileToString("licon.txt");

		writeToFile(FILENAME, HEADER + translate(new Scanner(file)) + "\t}\r\n\r\n}");
	}


	public static String translate(Scanner sc) {
		StringBuilder sb = new StringBuilder();

		while(sc.hasNextLine()) {

			String nextLine = sc.nextLine();
			String instruction = nextLine.split(" ")[0];

			for (String print: Impressoes.PRINT_INSTRUCTIONS)
				if (instruction.equals(print)) 
					sb.append(tabs() + Impressoes.escolhe(nextLine));

			for (String ciclo: Ciclos.CYCLE_INSTRUCTIONS)
				if (instruction.equals(ciclo))
					sb.append(tabs() + Ciclos.escolhe(nextLine));

			for (String math: Maths.MATHS_INSTRUCTIONS) 
				if (instruction.equals(math)) 
					sb.append(tabs() + Maths.escolhe(nextLine));

			for (String memoria: Memoria.MEMORY_INSTRUCTIONS) 
				if (instruction.equals(memoria)) 
					sb.append(tabs() + Memoria.escolhe(nextLine));
			
			if (instruction.equals("pede"))
				sb.append(tabs() + Scan.escolhe(nextLine));
		}
		return sb.toString();
	}

	public static String tabs() {
		return "\t".repeat(nrTabs);
	}

	public static String parseFileToString(String fileName) {
		String text = "";
		StringBuilder result = new StringBuilder();
		try {
			text = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(text);
		while(sc.hasNextLine()) {
			String next = sc.nextLine();
			if (!next.isBlank() && next.strip().charAt(0) != '$')
				result.append(next + "\r\n");
		}
		sc.close();
		return result.toString();
	}

	public static void writeToFile(String fileName, String str) {
		try (FileWriter output = new FileWriter(fileName);) {
			output.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

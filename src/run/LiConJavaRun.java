package run;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Stack;
import java.util.Map;

public class LiConJavaRun {


public static Map<String,Object> mapa = new HashMap<>();

public static Stack<Object> pilha = new Stack<>();


	public static void main(String[] args) {
	
	String str = (new Scanner(System.in)).nextLine();
	String[] split = str.split("\s+");
	System.out.println(Arrays.toString(split));
	String[] x = new String[split.length];
	for (int i = 0; i < x.length; i++)
		x[i] = split[i];

	System.out.println(Arrays.toString(x));
	}

}
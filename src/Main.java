import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Creating Lexer.......");
		System.out.println("java JLex.Main input/lexer");
		System.out.println(executeCommand("java JLex.Main input/lexer"));
		System.out.println("Compiling Lexer......");
		System.out.println("javac input/lexer.java");
		System.out.println(executeCommand("javac input/lexer.java"));
		System.out.println("Loading Files......");
		String seed = "Code";
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0;i<6;i++){
			seed+="0";
			String filename = seed+"1.java";
			names.add(seed+"1.lex");
			System.out.println("Creating Lex file for "+filename+".....");
			System.out.println("java -cp input lexer "+filename+ " "+ ""+seed+"1.txt");
			System.out.println(executeCommand("java -cp input lexer input/"+filename+ " input/"+seed+"1.lex"));
		}
		
		System.out.println("Generating SyntaxAnalyzer Grammar.gram");
		CodeGenerator.main(new String []{"input/Grammar.gram","SyntaxAnalyzer/"});
		System.out.println();
		//removing files
		System.out.println("Removing Old Files......");
		System.out.println(executeCommand("rm ./SyntaxAnalyzer/SyntaxAnalyzer.class"));
		System.out.println(executeCommand("rm ./SyntaxAnalyzer/Token.class"));
		System.out.println(executeCommand("rm ./SyntaxAnalyzer/Tokenizer.class"));
		//compiling
		System.out.println("Compiling SyntaxAnalyzer Files......");
		System.out.println("");
		System.out.println("javac SyntaxAnalyzer/SyntaxAnalyzer.java SyntaxAnalyzer/Token.java SyntaxAnalyzer/Tokenizer.java");
		System.out.println(executeCommand("javac SyntaxAnalyzer/SyntaxAnalyzer.java SyntaxAnalyzer/Token.java SyntaxAnalyzer/Tokenizer.java"));		
		System.out.println();
		System.out.println("Parsing Files using SyntaxAnalyzer......");
		
		for(String fileName : names){
			System.out.println("Parsing "+ fileName +".......");
			System.out.println(executeCommand("java -cp SyntaxAnalyzer SyntaxAnalyzer ./input/"+fileName));
			System.out.println();
		}
		
	}

	private static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}

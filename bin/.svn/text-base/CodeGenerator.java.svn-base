import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class CodeGenerator {

	public static void main(String[] args) throws IOException {
		InputStream grammar = new FileInputStream("src/Grammar.txt");
		BufferedReader grammarReader = new BufferedReader(
				new InputStreamReader(grammar));

		String line = "";
		while ((line = grammarReader.readLine()) != null) {
			String fileName = line.split("::=")[0];
			// System.out.println(fileName);
			fileName = getClassName(fileName);
			PrintWriter writer = new PrintWriter("src/syntaxAnalyzer/"
					+ fileName + ".java", "UTF-8");
			writer.println("package syntaxAnalyzer;");
			writer.println();
			writer.println("public class " + fileName + "{");
			writer.println("public static int input_index = 0;");
			writer.println();
			switch (fileName) {
			case "Keyword":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "BooleanLiteral":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "NullLiteral":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "NonZeroDigit":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "HexDigit":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "OctalDigit":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ExponentIndicator":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "IntegerTypeSuffix":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "AssignmentOperator": // TODO
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ClassModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "InterfaceModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "FieldModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ConstantModifiers":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ConstructorModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "MethodModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "IntegralType":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "FloatingPointType":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "EmptyStatement":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "Sign":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "SingleCharacter":
				// terminalCase(writer, line.split("::=")[1]);
				// PrintWriter writer = new
				// PrintWriter("src/syntaxAnalyzer/Identifier.java", "UTF-8");
				// writer.println("package syntaxAnalyzer;");
				// writer.println();
				// writer.println("public class SingleCharacter {");
				writer.println();
				writer.println("public static Object eval(Object o){return null;}");
				// writer.println("}");
				// writer.close();
				break;
			case "StringCharacter":
				// terminalCase(writer, line.split("::=")[1]);
				// PrintWriter writer = new
				// PrintWriter("src/syntaxAnalyzer/Identifier.java", "UTF-8");
				// writer.println("package syntaxAnalyzer;");
				// writer.println();
				// writer.println("public class StringCharacter {");
				// writer.println();
				writer.println("public static Object eval(Object o){return null;}");
				// writer.println("}");
				// writer.close();
				break;
			default:
				System.out.println(fileName);
				variableCase(writer, line.split("::=")[1]);
				break;
			}

			writer.println("}");
			writer.close();
		}
		PrintWriter writer = new PrintWriter(
				"src/syntaxAnalyzer/Identifier.java", "UTF-8");
		writer.println("package syntaxAnalyzer;");
		writer.println();
		writer.println("public class Identifier {");
		writer.println();
		writer.println("public static Object eval(Object o){return true;}");
		writer.println("}");
		writer.close();

		writer = new PrintWriter("src/syntaxAnalyzer/EscapeSequence.java",
				"UTF-8");
		writer.println("package syntaxAnalyzer;");
		writer.println();
		writer.println("public class EscapeSequence {");
		writer.println();
		writer.println("public static Object eval(Object o){return null;}");
		writer.println("}");
		writer.close();

		writer = new PrintWriter("src/syntaxAnalyzer/Lexer.java", "UTF-8");
		writer.println("package syntaxAnalyzer;");
		writer.println();
		writer.println("import java.util.ArrayList;");
		writer.println();
		writer.println("public class Lexer {");
		writer.println();
		writer.println("public static ArrayList<String> lexems = new ArrayList<String>();\n"
				+ "public static int index = 0;\n"
				+ "public static int EOF = 0;");

		writer.println("public static String getNextToken() {\n"
				+ "String s = lexems.get(index);\n" + "index++;\n"
				+ "return s;\n" + "}");
		writer.println("}");

		writer.close();

		grammarReader.close();
		grammar.close();
	}

	public static void terminalCase(PrintWriter writer, String line) {
		String normalized = line.replace(' ', '\0');
		writer.println("public static Object eval(Object o ){");
		writer.println("\tString keyword = (String) o;");
		writer.println("\tswitch(keyword){");
		String[] Keywords = normalized.split("\\|");
		for (int i = 0; i < Keywords.length; i++) {
			writer.println("\t\tcase \"" + Keywords[i] + "\" : return true;");
		}
		writer.println("\t\tdefault : return false;");
		writer.println("\t}");
		writer.println("}");
	}

	public static void variableCase(PrintWriter writer, String line) {
		if (line.contains("|")) {
			// String normalized = line.replace(' ', '\0');
			writer.println("public static Object eval(Object o ){");
			writer.println("input_index = Lexer.index;");
			// writer.println("\tString keyword = (String) o;");
			// writer.println("\tswitch(keyword){");
			String[] Keywords = line.split("\\|");

			for (int i = 0; i < Keywords.length; i++) {
				concatinationCase(writer, Keywords[i]);
			}

			// writer.println("\t\tdefault : return false;");
			writer.println("\treturn false;");
			writer.println("}");
		} else {
			writer.println("public static Object eval(Object o ){");
			writer.println("input_index = Lexer.index;");
			concatinationCase(writer, line);
			writer.println("\treturn false;");
			writer.println();
			writer.println("}");
		}
	}

	public static ArrayList<String> tokenize(String s) {
		boolean seenAngluarBracket = false;
		ArrayList<String> tokens = new ArrayList<String>();
		String currentToken = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ' && !seenAngluarBracket) {
				if (currentToken.length() > 0) {
					// s = s.replace("@", "<");
					// s = s.replace("#", ">");

					currentToken = currentToken.replace("$", "\\\"");

					tokens.add(currentToken);
					currentToken = "";
					continue;
				} else {
					continue;
				}
			}
			if (s.charAt(i) == '<') {
				seenAngluarBracket = true;
				currentToken += "<";
				continue;
			}
			if (s.charAt(i) == '>') {
				seenAngluarBracket = false;
				currentToken += ">";
				if (i + 1 < s.length() && s.charAt(i + 1) == '?') {
					currentToken += "?";
					i++;
				}
				tokens.add(currentToken);
				currentToken = "";
				continue;
			}

			currentToken += s.charAt(i);
		}
		if (currentToken.length() > 0) {
			tokens.add(currentToken);
		}
		return tokens;
	}

	public static void concatinationCase(PrintWriter writer, String line) {
		ArrayList<String> tokens = tokenize(line);
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<State> classesWithOneOrZero = new ArrayList<State>();
		ArrayList<State> classesWithOnlyOne = new ArrayList<State>();
		for (String s : tokens) {
			if (s.startsWith("<") && s.length() > 1) {
				if (s.endsWith("?")) {
					String normalized = s.substring(0, s.length() - 1);
					ClassHolder cls = new ClassHolder();
					cls.name = getClassName(normalized);
					cls.oneOrzero = true;
					states.add(cls);
					classesWithOneOrZero.add(cls);
				} else {
					ClassHolder cls = new ClassHolder();
					cls.name = getClassName(s);
					cls.oneOrzero = false;
					states.add(cls);
					classesWithOnlyOne.add(cls);
				}
			} else {
				if (s.endsWith("?")) {
					String normalized = s.substring(0, s.length() - 1);
					Token t = new Token();
					t.token = normalized;
					t.oneOrzero = true;
					states.add(t);
					classesWithOneOrZero.add(t);
				} else {
					Token t = new Token();
					t.token = s;
					t.oneOrzero = false;
					states.add(t);
					classesWithOnlyOne.add(t);
				}

			}
		}
		ArrayList<HashSet<State>> filtered = new ArrayList<HashSet<State>>();
		for (int i = classesWithOnlyOne.size(); i <= states.size(); i++) {
			ArrayList<HashSet<State>> set = getSubsets(states, i);
			// System.out.println(set);
			for (HashSet<State> s : set) {

				boolean allthere = true;
				for (State cls : classesWithOnlyOne) {
					if (s.contains(cls)) {
						allthere = true;
					} else {
						allthere = false;
						break;
					}
				}
				if (allthere) {
					if (s.size() > 0)
						filtered.add(s);
				}
			}

			// System.out.println(filtered);

		}

		for (HashSet<State> set : filtered) {
			String condition = "";
			for (State sorted : states) {
				if (set.contains(sorted)) {
					if (sorted instanceof Token) {
						((Token) sorted).token = ((Token) sorted).token
								.replace("@", "<");
						((Token) sorted).token = ((Token) sorted).token
								.replace("#", ">");

						condition += " \t\""
								+ ((Token) sorted).token
								+ "\""
								+ ".equals(Lexer.getNextToken())" + " &&\n";
					} else {
						condition += " \t((Boolean)"
								+ ((ClassHolder) sorted).name + ".eval(o))"
								+ " &&\n";
					}
				}
			}
			condition = condition.substring(0, condition.length() - 3);
			// System.out.println(condition);
			writer.println();
			writer.println("\tif(" + condition + "){");
			//writer.println("\t\tif(Lexer.index == Lexer.EOF)");
			writer.println("\t\treturn true;");
			writer.println("\t}");
			writer.println();
			writer.println("Lexer.index =input_index;");
			writer.println();
		}

	}

	private static void getSubsets(ArrayList<State> superSet, int k, int idx,
			HashSet<State> current, ArrayList<HashSet<State>> solution) {
		// successful stop clause
		if (current.size() == k) {
			solution.add(new HashSet<>(current));
			return;
		}
		// unseccessful stop clause
		if (idx == superSet.size())
			return;
		State x = superSet.get(idx);
		current.add(x);
		// "guess" x is in the subset
		getSubsets(superSet, k, idx + 1, current, solution);
		current.remove(x);
		// "guess" x is not in the subset
		getSubsets(superSet, k, idx + 1, current, solution);
	}

	public static ArrayList<HashSet<State>> getSubsets(
			ArrayList<State> superSet, int k) {
		ArrayList<HashSet<State>> res = new ArrayList<HashSet<State>>();
		getSubsets(superSet, k, 0, new HashSet<State>(), res);
		return res;
	}

	public static void zeroOrOneCase(PrintWriter writer, String line) {

	}

	public static String getClassName(String s) {
		String out = "";
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != '>')
				out += s.charAt(i);
			else
				break;
		}
		s = out;
		if (s.contains(" ")) {
			String[] temp = s.split(" ");
			s = "";
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("-")) {
					String[] temp2 = temp[i].split("-");
					for (int j = 0; j < temp2.length; j++) {
						s += Character.toUpperCase(temp2[j].charAt(0))
								+ temp2[j].substring(1);
					}
				} else {
					s += Character.toUpperCase(temp[i].charAt(0))
							+ temp[i].substring(1);
				}
			}
		} else {
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
		}
		return s;
	}

	static class State {

	}

	static class Token extends State {
		public String token;
		public boolean oneOrzero;

		public String toString() {
			return token;
		}
	}

	static class ClassHolder extends State {
		public String name;
		public boolean oneOrzero;

		public String toString() {
			return name + " " + oneOrzero;
		}
	}

}

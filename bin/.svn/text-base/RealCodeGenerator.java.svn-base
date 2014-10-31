import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RealCodeGenerator {

	public static void main(String[] args) throws IOException {
		InputStream grammar = new FileInputStream("src/Grammar.txt");
		BufferedReader grammarReader = new BufferedReader(
				new InputStreamReader(grammar));

		String line = "";
		PrintWriter writer = new PrintWriter(
				"src/syntaxAnalyzer/SyntaxAnalyzer.java", "UTF-8");
		writer.println("package syntaxAnalyzer;");
		writer.println();
		writer.println("public class SyntaxAnalyzer {");
		writer.println("\tpublic static Tokenizer tokenizer = new Tokenizer();");
		while ((line = grammarReader.readLine()) != null) {
			String methodName = line.split("::=")[0];
			methodName = getMethodName(methodName);
			writer.println("\tpublic static boolean " + methodName
					+ "(Token tok){");
			writer.println("\tint oldTokenCount = tokenizer.index;");
			switch (methodName) {
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
			case "SingleTypeImportDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "PackageDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "TypeImportOnDemandDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "Super":
				write(writer, line.split("::=")[1]);
				break;
			case "Interfaces":
				write(writer, line.split("::=")[1]);
				break;
			case "StaticInitializer":
				write(writer, line.split("::=")[1]);
				break;
			case "FormalParameter":
				write(writer, line.split("::=")[1]);
				break;
			case "Throws":
				write(writer, line.split("::=")[1]);
				break;
			case "MethodDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "ConstantDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "ClassType":
				write(writer, line.split("::=")[1]);
				break;
			case "InterfaceType":
				write(writer, line.split("::=")[1]);
				break;
			case "ArrayType":
				write(writer, line.split("::=")[1]);
				break;
			case "LocalVariableDeclarationStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "LocalVariableDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "LabeledStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "LabeledStatementNoShortIf":
				write(writer, line.split("::=")[1]);
				break;
			case "ExpressionStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "IfThenStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "IfThenElseStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "IfThenElseStatementNoShortIf":
				write(writer, line.split("::=")[1]);
				break;
			case "SwitchStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "SwitchBlockStatementGroup":
				write(writer, line.split("::=")[1]);
				break;
			case "WhileStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "WhileStatementNoShortIf":
				write(writer, line.split("::=")[1]);
				break;
			case "DoStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "ForUpdate":
				write(writer, line.split("::=")[1]);
				break;
			case "ThrowsStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "SynchronizedStatement":
				write(writer, line.split("::=")[1]);
				break;
			case "CatchClause":
				write(writer, line.split("::=")[1]);
				break;
			case "Finally":
				write(writer, line.split("::=")[1]);
				break;
			case "ConstantExpression":
				write(writer, line.split("::=")[1]);
				break;
			case "Experssion":
				write(writer, line.split("::=")[1]);
				break;
			case "Assignment":
				write(writer, line.split("::=")[1]);
				break;
			case "PredecrementExpression":
				write(writer, line.split("::=")[1]);
				break;
			case "PreincrementExpression":
				write(writer, line.split("::=")[1]);
				break;
			case "PostdecrementExpression":
				write(writer, line.split("::=")[1]);
				break;
			case "PostincrementExpression":
				write(writer, line.split("::=")[1]);
				break;
			case "DimExpr":
				write(writer, line.split("::=")[1]);
				break;
			case "ExponentPart":
				write(writer, line.split("::=")[1]);
				break;
			case "CompilationUnit":
				write(writer, line.split("::=")[1]);
				break;
			case "ClassBody":
				write(writer, line.split("::=")[1]);
				break;
			case "ClassInstanceCreationExpression":
				write(writer, line.split("::=")[1]);
				break;	
			case "ClassDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "ConstructorDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "ConstructorDeclarator":
				write(writer, line.split("::=")[1]);
				break;
			case "ConstructorBody":
				write(writer, line.split("::=")[1]);
				break;
			case "FieldDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "MethodHeader":
				write(writer, line.split("::=")[1]);
				break;
			case "MethodDeclarator":
				write(writer, line.split("::=")[1]);
				break;
			case "InterfaceDeclaration":
				write(writer, line.split("::=")[1]);
				break;
			case "InterfaceBody":
				write(writer, line.split("::=")[1]);
				break;
			default:
				writer.println("\treturn false;");
			}

			writer.println("\t}");
		}
		addIdentifier(writer);
		writer.println("}");
		writer.close();
		grammarReader.close();
		grammar.close();
	}

	private static void addIdentifier(PrintWriter writer) {
		writer.println("\tpublic static boolean Identifier(Token tok){");
		writer.println("\t\treturn tok.type.equals(\"Identifier\");");
		writer.println("\t}");
	}

	public static void terminalCase(PrintWriter writer, String line) {
		String normalized = line.replace(' ', '\0');
		writer.println("\t\tString tokenValue= tok.value;");
		writer.println("\t\tswitch(tokenValue){");
		String[] Keywords = normalized.split("\\|");
		for (int i = 0; i < Keywords.length; i++) {
			writer.println("\t\t\tcase \"" + Keywords[i] + "\" : return true;");
		}
		writer.println("\t\t\tdefault : return false;");
		writer.println("\t\t}");
	}

	public static void variableCase(PrintWriter writer, String line) {
		writer.println("int oldTokenCount = tokenizer.index;");
		if (line.contains("|")) {
			// String[] Keywords = line.split("\\|");

			// for (int i = 0; i < Keywords.length; i++) {
			// if(i+1 == Keywords.length)
			// sequencing(writer, Keywords[i],true,true);
			// else
			// sequencing(writer, Keywords[i],true,false);
			// }

		} else {
			// sequencing(writer, line,false,false);
		}
	}

	public static void write(PrintWriter writer, String line) {
		ArrayList<String> tokens = tokenize(line);
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<State> classesWithOneOrZero = new ArrayList<State>();
		ArrayList<State> classesWithOnlyOne = new ArrayList<State>();
		for (String s : tokens) {
			if (s.startsWith("<") && s.length() > 1) {
				if (s.endsWith("?")) {
					String normalized = s.substring(0, s.length() - 1);
					ClassHolder cls = new ClassHolder();
					cls.name = getMethodName(normalized);
					cls.oneOrzero = true;
					states.add(cls);
					classesWithOneOrZero.add(cls);
				} else {
					ClassHolder cls = new ClassHolder();
					cls.name = getMethodName(s);
					cls.oneOrzero = false;
					states.add(cls);
					classesWithOnlyOne.add(cls);
				}
			} else {
				if (s.endsWith("?")) {
					String normalized = s.substring(0, s.length() - 1);
					Token t = new Token();
					t.name = normalized;
					t.oneOrzero = true;
					states.add(t);
					classesWithOneOrZero.add(t);
				} else {
					Token t = new Token();
					t.name = s;
					t.oneOrzero = false;
					states.add(t);
					classesWithOnlyOne.add(t);
				}

			}
		}

		int lastIteration = states.size() - 1;
		for (int i = 0; i < states.size(); i++) {
			if (!states.get(i).oneOrzero) {
				if (lastIteration == i && i == 0)
					sequencing(writer, states.get(i), true, true);
				if (lastIteration != i && i == 0)
					sequencing(writer, states.get(i), true, false);
				if (lastIteration == i && i != 0)
					sequencing(writer, states.get(i), false, true);
				if (lastIteration != i && i != 0)
					sequencing(writer, states.get(i), false, false);
			} else {
				if (lastIteration == i && i == 0)
					optional(writer, states.get(i), true, true);
				if (lastIteration != i && i == 0)
					optional(writer, states.get(i), true, false);
				if (lastIteration == i && i != 0)
					optional(writer, states.get(i), false, true);
				if (lastIteration != i && i != 0)
					optional(writer, states.get(i), false, false);
			}
		}

	}

	public static void sequencing(PrintWriter writer, State state,
			boolean firstIteration, boolean lastIteration) {
		if (firstIteration || lastIteration) {
			if (state instanceof ClassHolder) {
				writer.println("if (" + state + "(tok)) {");
				if (!lastIteration) {
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("} else {");
					writer.println("return false;");
					writer.println("}");
				} else {
					writer.println("return true;");
					writer.println("} else {");
					if (!firstIteration) {
						writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					} else {
						writer.println("return false;");
					}
					writer.println("}");
				}

			} else {
				writer.println("if (tok.value.equals((\"" + state + "\"))) {");
				if (!lastIteration) {
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("} else {");
					writer.println("return false;");
					writer.println("}");
				} else {

					writer.println("return true;");

					writer.println("} else {");
					if (!firstIteration) {
						writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					} else {
						writer.println("return false;");
					}
					writer.println("}");
				}
			}

		} else {
			if (state instanceof ClassHolder) {
				writer.println("if (" + state + "(tok)) {");
				writer.println("tok = tokenizer.getNextToken();");
				writer.println("} else {");
				writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
				writer.println("}");
			} else {
				writer.println("if (tok.value.equals((\"" + state + "\"))) {");
				writer.println("tok = tokenizer.getNextToken();");
				writer.println("} else {");
				writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
				writer.println("}");
			}
		}

	}

	public static ArrayList<String> tokenize(String s) {
		boolean seenAngluarBracket = false;
		ArrayList<String> tokens = new ArrayList<String>();
		String currentToken = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ' && !seenAngluarBracket) {
				if (currentToken.length() > 0) {
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

	public static String getMethodName(String s) {
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

	public static void alternatingSquencing(PrintWriter writer, String line) {

	}

	public static void optional(PrintWriter writer, State state,
			boolean firstIteration, boolean lastIteration) {

		if (firstIteration && lastIteration) {
			writer.println("return " + state + "(tok);");
		} else {
			if (lastIteration) {
				writer.println(state + "(tok);");
				writer.println("return true;");
			} else {
				writer.println(state + "(tok);");
				writer.println("tok = tokenizer.getCurrentToken();");
			}
		}

	}

	static abstract class State {
		public String name;
		public boolean oneOrzero;
	}

	static class Token extends State {

		public String toString() {
			return name;
		}
	}

	static class ClassHolder extends State {

		public String toString() {
			return name;
		}
	}
}

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class CodeGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			String fileName = args[0];
			InputStream grammar = new FileInputStream(fileName);
			BufferedReader grammarReader = new BufferedReader(
					new InputStreamReader(grammar));

			String line = "";
			PrintWriter writer = new PrintWriter(args[1]
					+ "SyntaxAnalyzer.java", "UTF-8");
			writer.println("import java.io.BufferedReader;");
			writer.println("import java.io.FileInputStream;");
			writer.println("import java.io.IOException;");
			writer.println("import java.io.InputStream;");
			writer.println("import java.io.InputStreamReader;");
			writer.println();
			writer.println("public class SyntaxAnalyzer {");
			writer.println("\tpublic static Tokenizer tokenizer = new Tokenizer();");
			while ((line = grammarReader.readLine()) != null) {
				String methodName = line.split("::=")[0];
				methodName = getMethodName(methodName);
				writer.println("\tpublic static boolean " + methodName
						+ "(Token tok){");
				writer.println("\tint oldTokenCount = tokenizer.index;");
				writer.println("\tif(tok !=null){");
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
				case "AssignmentOperator":
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
				case "CompilationUnit":
					write(writer, line.split("::=")[1], true);
					break;
				default:
					write(writer, line.split("::=")[1], false);

				}
				writer.println("\t}else{return false;}");
				writer.println("\t}");
			}
			addIdentifier(writer);
			addSingleCharacter(writer);
			addStringCharacter(writer);
			addEmpty(writer);
			addMainMethod(writer);
			writer.println("}");
			writer.close();
			grammarReader.close();
			grammar.close();
			System.out.println("Code Generated !!");
		} else {
			System.out.println("Problem with Parameters !!");
		}
	}

	private static void addIdentifier(PrintWriter writer) {
		writer.println("\tpublic static boolean Identifier(Token tok){");
		writer.println("\t\treturn tok.type.equals(\"Identifier\");");
		writer.println("\t}");
	}

	private static void addSingleCharacter(PrintWriter writer) {
		writer.println("\tpublic static boolean CharacterLiteral(Token tok){");
		writer.println("\t\treturn tok.type.equals(\"Character\");");
		writer.println("\t}");
	}

	private static void addStringCharacter(PrintWriter writer) {
		writer.println("\tpublic static boolean StringLiteral(Token tok){");
		writer.println("\t\treturn tok.type.equals(\"String\");");
		writer.println("\t}");
	}

	private static void addEmpty(PrintWriter writer) {
		writer.println("\tpublic static boolean Empty(Token tok){");
		writer.println("\t\ttokenizer.pushBack(1);");
		writer.println("\t\treturn true;");
		writer.println("\t}");
	}

	private static void addMainMethod(PrintWriter writer) {
		writer.println("\tpublic static void main(String [] args) throws IOException {");
		writer.println("if(args.length>0){");
		writer.println("String fileName = args[0];");
		writer.println("\t\tInputStream input = new FileInputStream(fileName);");
		writer.println("\t\tBufferedReader inputReader = new BufferedReader(new InputStreamReader(input));");
		writer.println("\t\tString line = \"\";");
		writer.println("\t\twhile ((line = inputReader.readLine()) != null) {");
		writer.println("\t\ttry{");
		writer.println("\t\tToken t = new Token(line.split(\"\\t\")[2], line.split(\"\\t\")[1],line.split(\"\\t\")[0]);");
		writer.println("\t\tSyntaxAnalyzer.tokenizer.add(t);");
		writer.println("\t\t}catch(Exception e){");
		writer.println("\t\t}");
		writer.println("\t\t}");
		writer.println("\t\tSyntaxAnalyzer.tokenizer.add(new Token(\"EOF\",\"EOF\"));");
		writer.println("SyntaxAnalyzer.CompilationUnit(SyntaxAnalyzer.tokenizer.getCurrentToken());");
		writer.println("SyntaxAnalyzer.tokenizer.reset();");
		writer.println("}");
		writer.println("else{");
		writer.println("System.out.println(\"Problem With Pramaters\");");
		writer.println("}");
		writer.println("\t}");

	}

	public static void terminalCase(PrintWriter writer, String line) {
		String normalized = line.replace(" ", "");
		writer.println("\t\tString tokenValue= tok.value;");
		String[] Keywords = normalized.split("\\|");
		for (int i = 0; i < Keywords.length; i++) {
			Keywords[i] = Keywords[i].replace('`', '|');
			Keywords[i] = Keywords[i].replace('@', '<');
			Keywords[i] = Keywords[i].replace('#', '>');
			Keywords[i] = Keywords[i].replace('$', '"');
			writer.println("\t\tif(\"" + Keywords[i]
					+ "\".equals(tokenValue)){");
			writer.println("\t\t\treturn true;");
			writer.println("\t\t}");
		}
		writer.println("\t\treturn false;");

	}

	public static void write(PrintWriter writer, String line,
			boolean compilationUnit) {
		ArrayList<String> tokens = tokenize(line);
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<State> classesWithOneOrZero = new ArrayList<State>();
		ArrayList<State> classesWithOnlyOne = new ArrayList<State>();
		boolean alteratingCase = line.contains("|");
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

		if (!alteratingCase) {
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
						optional(writer, states.get(i), true, true,
								compilationUnit);
					if (lastIteration != i && i == 0)
						optional(writer, states.get(i), true, false,
								compilationUnit);
					if (lastIteration == i && i != 0)
						optional(writer, states.get(i), false, true,
								compilationUnit);
					if (lastIteration != i && i != 0)
						optional(writer, states.get(i), false, false,
								compilationUnit);
				}
			}
		} else {
			boolean lookAheadForGuard = false;
			boolean lookAheadForEOT = false;
			int lastIteration = states.size() - 1;
			writer.println("\tboolean noSkip = true;");
			for (int i = 0; i < states.size(); i++) {
				if (i + 1 < states.size()) {
					if (states.get(i + 1).name.equals("|")) {
						lookAheadForGuard = true;
					}
				} else {
					lookAheadForEOT = true;
				}
				if (!states.get(i).oneOrzero) {
					if (lastIteration == i && i == 0)
						sequencingAlt(writer, states.get(i), true, true,
								lookAheadForGuard, lookAheadForEOT);
					if (lastIteration != i && i == 0)
						sequencingAlt(writer, states.get(i), true, false,
								lookAheadForGuard, lookAheadForEOT);
					if (lastIteration == i && i != 0)
						sequencingAlt(writer, states.get(i), false, true,
								lookAheadForGuard, lookAheadForEOT);
					if (lastIteration != i && i != 0)
						sequencingAlt(writer, states.get(i), false, false,
								lookAheadForGuard, lookAheadForEOT);
				} else {
					if (lastIteration == i && i == 0)
						optionalAlt(writer, states.get(i), true, true,
								lookAheadForGuard, lookAheadForEOT);
					if (lastIteration != i && i == 0)
						optionalAlt(writer, states.get(i), true, false,
								lookAheadForGuard, lookAheadForEOT);
					if (lastIteration == i && i != 0)
						optionalAlt(writer, states.get(i), false, true,
								lookAheadForGuard, lookAheadForEOT);
					if (lastIteration != i && i != 0)
						optionalAlt(writer, states.get(i), false, false,
								lookAheadForGuard, lookAheadForEOT);
				}
				if (lookAheadForGuard) {
					lookAheadForGuard = false;
					i++;
				}
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
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("if (tok.value.equals((\"" + state + "\"))) {");
				if (!lastIteration) {
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("} else {");
					writer.println("return false;");
					writer.println("}");
				} else {
					// writer.println("tokenizer.getNextToken();");
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
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("if (tok.value.equals((\"" + state + "\"))) {");
				writer.println("tok = tokenizer.getNextToken();");
				writer.println("} else {");
				writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
				writer.println("}");
			}
		}

	}

	// <block><block><block>T | <block><block><block>T | <block><block><block>F

	public static void sequencingAlt(PrintWriter writer, State state,
			boolean firstIteration, boolean lastIteration,
			boolean lookAheadForGuard, boolean lookAheadForEOT) {
		writer.println("if (noSkip){");
		if (state instanceof ClassHolder) {
			if (!lookAheadForEOT) {
				if (lookAheadForGuard) {
					writer.println("if (" + state + "(tok)) {");
					writer.println("return true;");
					writer.println("}");
					writer.println("else{");
					writer.println("tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					writer.println("tok = tokenizer.getCurrentToken();");
					writer.println("}");
				} else {
					writer.println("if (" + state + "(tok)) {");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("}");
					writer.println("else{");
					writer.println("tokenizer.pushBack(tokenizer.index - oldTokenCount);");
					writer.println("tok = tokenizer.getCurrentToken();");
					writer.println("noSkip = false;");
					writer.println("}");
				}
			} else {
				writer.println("if (" + state + "(tok)) {");
				writer.println("return true;");
				writer.println("}");
				writer.println("else{");
				writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
				writer.println("}");
			}
		} else {
			if (!lookAheadForEOT) {
				if (lookAheadForGuard) {
					state.name = state.name.replace('`', '|');
					state.name = state.name.replace('@', '<');
					state.name = state.name.replace('#', '>');
					state.name = state.name.replace('$', '"');
					writer.println("if (tok.value.equals(\"" + state + "\")) {");
					writer.println("return true;");
					writer.println("}");
					writer.println("else{");
					writer.println("tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					writer.println("tok = tokenizer.getCurrentToken();");
					writer.println("}");
				} else {
					state.name = state.name.replace('`', '|');
					state.name = state.name.replace('@', '<');
					state.name = state.name.replace('#', '>');
					state.name = state.name.replace('$', '"');
					writer.println("if (tok.value.equals(\"" + state + "\")) {");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("}");
					writer.println("else{");// Remember !!!!
					writer.println("tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					writer.println("tok = tokenizer.getCurrentToken();");
					writer.println("noSkip = false;");
					writer.println("}");
				}
			} else {
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("if (tok.value.equals(\"" + state + "\")) {");
				writer.println("return true;");
				writer.println("}");
				writer.println("else{");
				writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
				writer.println("}");
			}
		}
		writer.println("}");
		if (lookAheadForGuard) {
			writer.println("else{");
			writer.println("noSkip = true;");
			writer.println("}");
		}
		if (lookAheadForEOT) {
			writer.println("else{");
			writer.println("return false;");
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

	public static void optional(PrintWriter writer, State state,
			boolean firstIteration, boolean lastIteration,
			boolean compilationUnit) {

		if (lastIteration) {
			if (state instanceof ClassHolder) {
				if (compilationUnit) {
					writer.println("if(" + state + "(tok)){");
					writer.println("System.out.println(\"Parsing Sucessfull !!!\");");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("}");
					writer.println("else{");
					writer.println("System.out.println(\"Failed at line: \"+ tokenizer.getToken().getLine() +\" near >>> \"+tokenizer.getToken().value);");
					writer.println("}");
				} else {
					writer.println(state + "(tok);");
					writer.println("tokenizer.pushBack(1);");
				}
			} else {
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("return \"" + state + "\".equals(tok.value);");
			}
			writer.println("return true;");
		} else {
			if (state instanceof ClassHolder) {
				if (compilationUnit) {
					writer.println("if(" + state + "(tok)){");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("}");
					writer.println("else{");
				//	writer.println("System.out.println(\"Failed at line:\"+ tokenizer.getToken().getLine() +\" \"+tokenizer.getToken().value);");
					writer.println("}");
				} else {
					writer.println("if(" + state + "(tok)){");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("}");
				}
			} else {
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("if(\"" + state + "\".equals(tok.value)){");
				writer.println("tok = tokenizer.getNextToken();");
				writer.println("}");
			}
		}

	}

	public static void optionalAlt(PrintWriter writer, State state,
			boolean firstIteration, boolean lastIteration,
			boolean lookAheadForGuard, boolean lookAheadForEOT) {
		writer.println("if (noSkip){");
		if (lastIteration) {
			if (state instanceof ClassHolder) {
				writer.println("if (!" + state + "(tok)){");
				writer.println("tokenizer.pushBack(1);");
				writer.println("}");
			} else {
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("return \"" + state + "\".equals(tok.value);");
			}
			writer.println("return true;");
		} else {
			if (state instanceof ClassHolder) {
				if (!lookAheadForGuard) {
					writer.println("if(" + state + "(tok)){");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("}");
				} else {
					writer.println("if(" + state + "(tok)){");
					writer.println("return true;");
					writer.println("}");
					writer.println("else{");
					writer.println("tokenizer.pushBack(1);");
					writer.println("return true;");
					writer.println("}");
				}
			} else {
				state.name = state.name.replace('`', '|');
				state.name = state.name.replace('@', '<');
				state.name = state.name.replace('#', '>');
				state.name = state.name.replace('$', '"');
				writer.println("if(\"" + state + "\".equals(tok.value)){");
				writer.println("tok = tokenizer.getNextToken();");
				writer.println("}");
			}
		}
		writer.println("}");
		if (lookAheadForGuard) {
			writer.println("else{");
			writer.println("noSkip = true;");
			writer.println("}");
		}
		if (lookAheadForEOT) {
			writer.println("else{");
			writer.println("return false;");
			writer.println("}");
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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SyntaxAnalyzer {
	public static Tokenizer tokenizer = new Tokenizer();

	public static boolean CompilationUnit(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (PackageDeclaration(tok)) {
				tok = tokenizer.getNextToken();
			} else {
			}
			if (ImportDeclarations(tok)) {
				tok = tokenizer.getNextToken();
			} else {
			}
			if (TypeDeclarations(tok)) {
				System.out.println("Parsing Sucessfull !!!");
				tok = tokenizer.getNextToken();
			} else {
				System.out.println("Failed at line: "
						+ tokenizer.getToken().getLine() + " near >>> "
						+ tokenizer.getToken().value);
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean PackageDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("package"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (PackageName(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ImportDeclarations(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ImportDeclaration(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ImportDeclarations1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ImportDeclarations1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ImportDeclarations(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ImportDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (SingleTypeImportDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (TypeImportOnDemandDeclaration(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean SingleTypeImportDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("import"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (TypeName(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean TypeImportOnDemandDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("import"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (PackageName(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("."))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("*"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean TypeDeclarations(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (TypeDeclaration(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (TypeDeclarations1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean TypeDeclarations1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (TypeDeclarations(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean TypeDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ClassDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (InterfaceDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals(";")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ClassModifiers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("class"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Super(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (Interfaces(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (ClassBody(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ClassModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ClassModifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassModifiers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ClassModifiers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ClassModifiers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassModifier(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("abstract".equals(tokenValue)) {
				return true;
			}
			if ("final".equals(tokenValue)) {
				return true;
			}
			if ("static".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean Super(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("extends"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassType(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Interfaces(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("implements"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (InterfaceTypeList(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceTypeList(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (InterfaceType(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (InterfaceTypeList1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceTypeList1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (InterfaceTypeList(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassBody(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("{"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassBodyDeclarations(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("}"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ClassBodyDeclarations(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ClassBodyDeclaration(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassBodyDeclarations1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ClassBodyDeclarations1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ClassBodyDeclarations(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassBodyDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ClassMemberDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (StaticInitializer(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ConstructorDeclaration(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassMemberDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (FieldDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (MethodDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ClassDeclaration(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean StaticInitializer(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("static"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Block(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConstructorDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ConstructorModifiers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (ConstructorDeclarator(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Throws(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (ConstructorBody(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConstructorModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ConstructorModifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ConstructorModifiers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConstructorModifiers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ConstructorModifiers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ConstructorModifier(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("protected".equals(tokenValue)) {
				return true;
			}
			if ("private".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean ConstructorDeclarator(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (SimpleTypeName(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (FormalParameterList(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((")"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean FormalParameterList(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (FormalParameter(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (FormalParameterList1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean FormalParameterList1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (FormalParameterList(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean FormalParameter(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Type(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableDeclaratorId(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Throws(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("throws"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassTypeList(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ClassTypeList(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ClassType(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassTypeList1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ClassTypeList1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ClassTypeList(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ConstructorBody(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("{"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ExplicitConstructorInvocation(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (BlockStatements(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("}"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ExplicitConstructorInvocation(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("this")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("super")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean FieldDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (FieldModifiers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (Type(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (VariableDeclarators(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean FieldModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (FieldModifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (FieldModifiers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean FieldModifiers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (FieldModifiers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean FieldModifier(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("protected".equals(tokenValue)) {
				return true;
			}
			if ("private".equals(tokenValue)) {
				return true;
			}
			if ("static".equals(tokenValue)) {
				return true;
			}
			if ("final".equals(tokenValue)) {
				return true;
			}
			if ("transient".equals(tokenValue)) {
				return true;
			}
			if ("volatile".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean VariableDeclarators(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (VariableDeclarator(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableDeclarators1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean VariableDeclarators1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (VariableDeclarators(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean VariableDeclarator(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (VariableDeclaratorId(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableDeclarator1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean VariableDeclarator1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("=")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (VariableInitializer(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean VariableDeclaratorId(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableDeclaratorId1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean VariableDeclaratorId1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (VariableDeclaratorId1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean MethodDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (MethodHeader(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (MethodBody(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean MethodHeader(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (MethodModifiers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (ResultType(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (MethodDeclarator(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			Throws(tok);
			tokenizer.pushBack(1);
			return true;
		} else {
			return false;
		}
	}

	public static boolean ResultType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Type(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("void")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean MethodModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (MethodModifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (MethodModifiers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean MethodModifiers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (MethodModifiers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean MethodModifier(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("protected".equals(tokenValue)) {
				return true;
			}
			if ("private".equals(tokenValue)) {
				return true;
			}
			if ("static".equals(tokenValue)) {
				return true;
			}
			if ("abstract".equals(tokenValue)) {
				return true;
			}
			if ("final".equals(tokenValue)) {
				return true;
			}
			if ("synchronized".equals(tokenValue)) {
				return true;
			}
			if ("native".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean MethodDeclarator(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (FormalParameterList(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((")"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean MethodBody(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Block(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals(";")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (InterfaceModifiers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("interface"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (ExtendsInterfaces(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (InterfaceBody(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (InterfaceModifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (InterfaceModifiers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceModifiers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (InterfaceModifiers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceModifier(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("abstract".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean ExtendsInterfaces(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("extends")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (InterfaceType(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ExtendsInterfaces(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (InterfaceType(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceBody(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("{"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (InterfaceMemberDeclarations(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("}"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceMemberDeclarations(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (InterfaceMemberDeclaration(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (InterfaceMemberDeclarations1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceMemberDeclarations1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (InterfaceMemberDeclarations(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceMemberDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ConstantDeclaration(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (AbstractMethodDeclaration(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ConstantDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ConstantModifiers(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Type(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (VariableDeclarator(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConstantModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("static".equals(tokenValue)) {
				return true;
			}
			if ("final".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean AbstractMethodDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (AbstractMethodModifiers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (ResultType(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (MethodDeclarator(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Throws(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean AbstractMethodModifiers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (AbstractMethodModifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (AbstractMethodModifiers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean AbstractMethodModifiers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (AbstractMethodModifiers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean AbstractMethodModifier(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("public")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("abstract")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ArrayInitializer(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("{"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableInitializers(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (",".equals(tok.value)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("}"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean VariableInitializers(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (VariableInitializer(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableInitializers1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean VariableInitializers1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (VariableInitializers(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean VariableInitializer(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Expression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ArrayInitializer(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Type(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (PrimitiveType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Type1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ClassOrInterfaceType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Type1(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Type1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Type1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PrimitiveType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (NumericType(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("boolean")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean NumericType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (IntegralType(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (FloatingPointType(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean IntegralType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("byte".equals(tokenValue)) {
				return true;
			}
			if ("short".equals(tokenValue)) {
				return true;
			}
			if ("int".equals(tokenValue)) {
				return true;
			}
			if ("long".equals(tokenValue)) {
				return true;
			}
			if ("char".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean FloatingPointType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("float".equals(tokenValue)) {
				return true;
			}
			if ("double".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean ReferenceType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ClassOrInterfaceType(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ArrayType(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassOrInterfaceType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ClassType(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (InterfaceType(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (TypeName(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean InterfaceType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (TypeName(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ArrayType(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Type(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("["))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("]"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Block(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("{"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (BlockStatements(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("}"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean BlockStatements(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (BlockStatement(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (BlockStatements1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean BlockStatements1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (BlockStatements(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean BlockStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (LocalVariableDeclarationStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Statement(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean LocalVariableDeclarationStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (LocalVariableDeclaration(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean LocalVariableDeclaration(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Type(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (VariableDeclarators(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Statement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (StatementWithoutTrailingSubstatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (LabeledStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (IfThenStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (IfThenElseStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (WhileStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ForStatement(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean StatementNoShortIf(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (StatementWithoutTrailingSubstatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (LabeledStatementNoShortIf(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (IfThenElseStatementNoShortIf(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (WhileStatementNoShortIf(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ForStatementNoShortIf(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean StatementWithoutTrailingSubstatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Block(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (EmptyStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ExpressionStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (SwitchStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (DoStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (BreakStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ContinueStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ReturnStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (SynchronizedStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ThrowsStatement(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (TryStatement(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean EmptyStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if (";".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean LabeledStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals((":"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Statement(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean LabeledStatementNoShortIf(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals((":"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (StatementNoShortIf(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ExpressionStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (StatementExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean StatementExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Assignment(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PreincrementExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PostincrementExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PredecrementExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PostdecrementExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (MethodInvocation(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ClassInstanceCreationExpression(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean IfThenStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("if"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Statement(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean IfThenElseStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("if"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (StatementNoShortIf(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("else"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Statement(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean IfThenElseStatementNoShortIf(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("if"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (StatementNoShortIf(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("else"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (StatementNoShortIf(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("switch"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (SwitchBlock(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchBlock(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("{"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (SwitchBlockStatementGroups(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (SwitchLabels(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals(("}"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchBlockStatementGroups(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (SwitchBlockStatementGroup(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (SwitchBlockStatementGroups1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchBlockStatementGroups1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (SwitchBlockStatementGroups(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchBlockStatementGroup(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (SwitchLabels(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (BlockStatements(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchLabels(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (SwitchLabel(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (SwitchLabels1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchLabels1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (SwitchLabels(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean SwitchLabel(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("case")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ConstantExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(":")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("default")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(":")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean WhileStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("while"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Statement(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean WhileStatementNoShortIf(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("while"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (StatementNoShortIf(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean DoStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("do"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Statement(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("while"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ForStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("for"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (ForInit(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (ForUpdate(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Statement(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ForStatementNoShortIf(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("for"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (ForInit(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (ForUpdate(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (StatementNoShortIf(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ForInit(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (StatementExpressionList(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (LocalVariableDeclaration(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ForUpdate(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (StatementExpressionList(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean StatementExpressionList(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (StatementExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (StatementExpressionList1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean StatementExpressionList1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (StatementExpressionList(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean BreakStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("break"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ContinueStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("continue"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ReturnStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("return"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ThrowsStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("throw"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((";"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean SynchronizedStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("synchronized"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Block(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean TryStatement(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("try")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Block(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Catches(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("try")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Block(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Catches(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (Finally(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Catches(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (CatchClause(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Catches1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Catches1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Catches(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean CatchClause(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("catch"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (FormalParameter(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals((")"))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (Block(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Finally(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("finally"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Block(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConstantExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Expression(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Expression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (AssignmentExpression(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean AssignmentExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ConditionalExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Assignment(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Assignment(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (LeftHandSide(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (AssignmentOperator(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (AssignmentExpression(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean LeftHandSide(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (FieldAccess(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ArrayAccess(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ExpressionName(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean AssignmentOperator(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("=".equals(tokenValue)) {
				return true;
			}
			if ("*=".equals(tokenValue)) {
				return true;
			}
			if ("/=".equals(tokenValue)) {
				return true;
			}
			if ("%=".equals(tokenValue)) {
				return true;
			}
			if ("+=".equals(tokenValue)) {
				return true;
			}
			if ("-=".equals(tokenValue)) {
				return true;
			}
			if ("<<=".equals(tokenValue)) {
				return true;
			}
			if (">>=".equals(tokenValue)) {
				return true;
			}
			if (">>>=".equals(tokenValue)) {
				return true;
			}
			if ("&=".equals(tokenValue)) {
				return true;
			}
			if ("^=".equals(tokenValue)) {
				return true;
			}
			if ("|=".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean ConditionalExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ConditionalOrExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ConditionalExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConditionalExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if ("".equals(tok.value)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (Expression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(":")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ConditionalExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ConditionalOrExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ConditionalAndExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ConditionalOrExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConditionalOrExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("||")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ConditionalOrExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ConditionalAndExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (InclusiveOrExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ConditionalAndExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ConditionalAndExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("&&")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ConditionalAndExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean InclusiveOrExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ExclusiveOrExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (InclusiveOrExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean InclusiveOrExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("|")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (InclusiveOrExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ExclusiveOrExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (AndExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ExclusiveOrExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ExclusiveOrExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("^")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ExclusiveOrExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean AndExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (EqualityExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (AndExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean AndExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("&")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (AndExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean EqualityExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (RelationalExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (EqualityExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean EqualityExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("==")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (EqualityExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("!=")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (EqualityExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean RelationalExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ShiftExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (RelationalExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean RelationalExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("<")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (RelationalExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals(">")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (RelationalExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("<=")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (RelationalExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals(">=")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (RelationalExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("instanceof")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ReferenceType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (RelationalExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ShiftExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (AdditiveExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ShiftExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ShiftExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("<<")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals(">>")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals(">>>")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ShiftExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean AdditiveExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (MultiplicativeExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (AdditiveExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean AdditiveExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("+")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (MultiplicativeExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (AdditiveExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("-")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (MultiplicativeExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (AdditiveExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean MultiplicativeExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (UnaryExpression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (MultiplicativeExpression1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean MultiplicativeExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("*")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (MultiplicativeExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("/")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (MultiplicativeExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("%")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (MultiplicativeExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean CastExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimitiveType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ReferenceType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpressionNotPlusMinus(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean UnaryExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (PreincrementExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PredecrementExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("+")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("-")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (UnaryExpressionNotPlusMinus(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PredecrementExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("--"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (UnaryExpression(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean PreincrementExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("++"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (UnaryExpression(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean UnaryExpressionNotPlusMinus(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (PostfixExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("~")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("!")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (UnaryExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (CastExpression(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PostdecrementExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (PostfixExpression(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PostincrementExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (PostfixExpression(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PostfixExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Primary(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PostfixExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ExpressionName(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PostfixExpression1(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PostfixExpression1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("++")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PostfixExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("--")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PostfixExpression1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean MethodInvocation(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (MethodName(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Primary(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("super")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean FieldAccess(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Primary(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("super")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Primary(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ArrayCreationExpression(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PrimaryNoNewArray(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PrimaryNoNewArray(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Literal(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("this")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Expression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ClassInstanceCreationExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ArrayCreationExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ArrayCreationExpression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("super")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (MethodName(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("super")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("(")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					tok = tokenizer.getNextToken();
				}
			}
			if (noSkip) {
				if (tok.value.equals(")")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (ExpressionName(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Expression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PrimaryNoNewArray1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Identifier(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Expression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimaryNoNewArray1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ClassInstanceCreationExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("new"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ClassType(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("("))) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (ArgumentList(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (tok.value.equals((")"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ArgumentList(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ArgumentList1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ArgumentList1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(",")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ArgumentList(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ArrayCreationExpression(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("new")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PrimitiveType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (DimExprs(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Dims(tok)) {
					return true;
				} else {
					tokenizer.pushBack(1);
					return true;
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("new")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (ClassOrInterfaceType(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (DimExprs(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (!Dims(tok)) {
					tokenizer.pushBack(1);
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean DimExprs(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (DimExpr(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (DimExprs1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean DimExprs1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (DimExprs(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean DimExpr(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("["))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Expression(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (tok.value.equals(("]"))) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Dims(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Dims(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ArrayAccess(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (ExpressionName(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Expression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (PrimaryNoNewArray(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("[")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (Expression(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("]")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean PackageName(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (PackageName1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean PackageName1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PackageName(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean TypeName(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (TypeName1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean TypeName1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (PackageName(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean SimpleTypeName(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean ExpressionName(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (ExpressionName1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ExpressionName1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (AmbiguousName(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean MethodName(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (MethodName1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean MethodName1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (AmbiguousName(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean AmbiguousName(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Identifier(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (AmbiguousName1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean AmbiguousName1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals(".")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (AmbiguousName(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Literal(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (FloatingPointLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (IntegerLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (BooleanLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (CharacterLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (StringLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (NullLiteral(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean IntegerLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (DecimalIntegerLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (HexIntegerLiteral(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (OctalIntegerLiteral(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean DecimalIntegerLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (DecimalNumeral(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			IntegerTypeSuffix(tok);
			tokenizer.pushBack(1);
			return true;
		} else {
			return false;
		}
	}

	public static boolean HexIntegerLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (HexNumeral(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			IntegerTypeSuffix(tok);
			tokenizer.pushBack(1);
			return true;
		} else {
			return false;
		}
	}

	public static boolean OctalIntegerLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (OctalNumeral(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			IntegerTypeSuffix(tok);
			tokenizer.pushBack(1);
			return true;
		} else {
			return false;
		}
	}

	public static boolean IntegerTypeSuffix(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("l".equals(tokenValue)) {
				return true;
			}
			if ("L".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean DecimalNumeral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("0")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (NonZeroDigit(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (!Digits(tok)) {
					tokenizer.pushBack(1);
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Digits(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Digit(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (Digits1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Digits1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (Digits(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Digit(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("0")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (NonZeroDigit(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean NonZeroDigit(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("1".equals(tokenValue)) {
				return true;
			}
			if ("2".equals(tokenValue)) {
				return true;
			}
			if ("3".equals(tokenValue)) {
				return true;
			}
			if ("4".equals(tokenValue)) {
				return true;
			}
			if ("5".equals(tokenValue)) {
				return true;
			}
			if ("6".equals(tokenValue)) {
				return true;
			}
			if ("7".equals(tokenValue)) {
				return true;
			}
			if ("8".equals(tokenValue)) {
				return true;
			}
			if ("9".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean HexNumeral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("0")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("x")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (HexDigit(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (HexNumeral1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("0")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (tok.value.equals("X")) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (HexDigit(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (HexNumeral1(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean HexNumeral1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (HexDigit(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (HexNumeral1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean HexDigit(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("0".equals(tokenValue)) {
				return true;
			}
			if ("1".equals(tokenValue)) {
				return true;
			}
			if ("2".equals(tokenValue)) {
				return true;
			}
			if ("3".equals(tokenValue)) {
				return true;
			}
			if ("4".equals(tokenValue)) {
				return true;
			}
			if ("5".equals(tokenValue)) {
				return true;
			}
			if ("6".equals(tokenValue)) {
				return true;
			}
			if ("7".equals(tokenValue)) {
				return true;
			}
			if ("8".equals(tokenValue)) {
				return true;
			}
			if ("9".equals(tokenValue)) {
				return true;
			}
			if ("a".equals(tokenValue)) {
				return true;
			}
			if ("b".equals(tokenValue)) {
				return true;
			}
			if ("c".equals(tokenValue)) {
				return true;
			}
			if ("d".equals(tokenValue)) {
				return true;
			}
			if ("e".equals(tokenValue)) {
				return true;
			}
			if ("f".equals(tokenValue)) {
				return true;
			}
			if ("A".equals(tokenValue)) {
				return true;
			}
			if ("B".equals(tokenValue)) {
				return true;
			}
			if ("C".equals(tokenValue)) {
				return true;
			}
			if ("D".equals(tokenValue)) {
				return true;
			}
			if ("E".equals(tokenValue)) {
				return true;
			}
			if ("F".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean OctalNumeral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (tok.value.equals(("0"))) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (OctalDigit(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
			if (OctalNumeral1(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean OctalNumeral1(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (OctalDigit(tok)) {
					tok = tokenizer.getNextToken();
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
					noSkip = false;
				}
			}
			if (noSkip) {
				if (OctalNumeral1(tok)) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (Empty(tok)) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean OctalDigit(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("0".equals(tokenValue)) {
				return true;
			}
			if ("1".equals(tokenValue)) {
				return true;
			}
			if ("2".equals(tokenValue)) {
				return true;
			}
			if ("3".equals(tokenValue)) {
				return true;
			}
			if ("4".equals(tokenValue)) {
				return true;
			}
			if ("5".equals(tokenValue)) {
				return true;
			}
			if ("6".equals(tokenValue)) {
				return true;
			}
			if ("7".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean FloatingPointLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Digits(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (".".equals(tok.value)) {
				tok = tokenizer.getNextToken();
			}
			if (Digits(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (ExponentPart(tok)) {
				tok = tokenizer.getNextToken();
			}
			FloatTypeSuffix(tok);
			tokenizer.pushBack(1);
			return true;
		} else {
			return false;
		}
	}

	public static boolean ExponentPart(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (ExponentIndicator(tok)) {
				tok = tokenizer.getNextToken();
			} else {
				return false;
			}
			if (SignedInteger(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean ExponentIndicator(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("e".equals(tokenValue)) {
				return true;
			}
			if ("E".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean SignedInteger(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			if (Sign(tok)) {
				tok = tokenizer.getNextToken();
			}
			if (Digits(tok)) {
				return true;
			} else {
				return tokenizer.pushBack(tokenizer.index - oldTokenCount);
			}
		} else {
			return false;
		}
	}

	public static boolean Sign(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("+".equals(tokenValue)) {
				return true;
			}
			if ("-".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean FloatTypeSuffix(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			boolean noSkip = true;
			if (noSkip) {
				if (tok.value.equals("f")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("F")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("d")) {
					return true;
				} else {
					tokenizer.pushBack(tokenizer.index - oldTokenCount);
					tok = tokenizer.getCurrentToken();
				}
			} else {
				noSkip = true;
			}
			if (noSkip) {
				if (tok.value.equals("D")) {
					return true;
				} else {
					return tokenizer.pushBack(tokenizer.index - oldTokenCount);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean BooleanLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("true".equals(tokenValue)) {
				return true;
			}
			if ("false".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean NullLiteral(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("null".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean Keyword(Token tok) {
		int oldTokenCount = tokenizer.index;
		if (tok != null) {
			String tokenValue = tok.value;
			if ("abstract".equals(tokenValue)) {
				return true;
			}
			if ("boolean".equals(tokenValue)) {
				return true;
			}
			if ("break".equals(tokenValue)) {
				return true;
			}
			if ("byte".equals(tokenValue)) {
				return true;
			}
			if ("case".equals(tokenValue)) {
				return true;
			}
			if ("catch".equals(tokenValue)) {
				return true;
			}
			if ("char".equals(tokenValue)) {
				return true;
			}
			if ("class".equals(tokenValue)) {
				return true;
			}
			if ("const".equals(tokenValue)) {
				return true;
			}
			if ("continue".equals(tokenValue)) {
				return true;
			}
			if ("default".equals(tokenValue)) {
				return true;
			}
			if ("do".equals(tokenValue)) {
				return true;
			}
			if ("double".equals(tokenValue)) {
				return true;
			}
			if ("else".equals(tokenValue)) {
				return true;
			}
			if ("extends".equals(tokenValue)) {
				return true;
			}
			if ("final".equals(tokenValue)) {
				return true;
			}
			if ("finally".equals(tokenValue)) {
				return true;
			}
			if ("float".equals(tokenValue)) {
				return true;
			}
			if ("for".equals(tokenValue)) {
				return true;
			}
			if ("goto".equals(tokenValue)) {
				return true;
			}
			if ("if".equals(tokenValue)) {
				return true;
			}
			if ("implements".equals(tokenValue)) {
				return true;
			}
			if ("import".equals(tokenValue)) {
				return true;
			}
			if ("instanceof".equals(tokenValue)) {
				return true;
			}
			if ("int".equals(tokenValue)) {
				return true;
			}
			if ("interface".equals(tokenValue)) {
				return true;
			}
			if ("long".equals(tokenValue)) {
				return true;
			}
			if ("native".equals(tokenValue)) {
				return true;
			}
			if ("new".equals(tokenValue)) {
				return true;
			}
			if ("package".equals(tokenValue)) {
				return true;
			}
			if ("private".equals(tokenValue)) {
				return true;
			}
			if ("protected".equals(tokenValue)) {
				return true;
			}
			if ("public".equals(tokenValue)) {
				return true;
			}
			if ("return".equals(tokenValue)) {
				return true;
			}
			if ("short".equals(tokenValue)) {
				return true;
			}
			if ("static".equals(tokenValue)) {
				return true;
			}
			if ("super".equals(tokenValue)) {
				return true;
			}
			if ("switch".equals(tokenValue)) {
				return true;
			}
			if ("synchronized".equals(tokenValue)) {
				return true;
			}
			if ("this".equals(tokenValue)) {
				return true;
			}
			if ("throw".equals(tokenValue)) {
				return true;
			}
			if ("throws".equals(tokenValue)) {
				return true;
			}
			if ("transient".equals(tokenValue)) {
				return true;
			}
			if ("try".equals(tokenValue)) {
				return true;
			}
			if ("void".equals(tokenValue)) {
				return true;
			}
			if ("volatile".equals(tokenValue)) {
				return true;
			}
			if ("while".equals(tokenValue)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static boolean Identifier(Token tok) {
		return tok.type.equals("Identifier");
	}

	public static boolean CharacterLiteral(Token tok) {
		return tok.type.equals("Character");
	}

	public static boolean StringLiteral(Token tok) {
		return tok.type.equals("String");
	}

	public static boolean Empty(Token tok) {
		tokenizer.pushBack(1);
		return true;
	}

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			String fileName = args[0];
			InputStream input = new FileInputStream(fileName);
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(input));
			String line = "";
			while ((line = inputReader.readLine()) != null) {
				try {
					Token t = new Token(line.split("\t")[2],
							line.split("\t")[1], line.split("\t")[0]);
					SyntaxAnalyzer.tokenizer.add(t);
				} catch (Exception e) {
				}
			}
			SyntaxAnalyzer.tokenizer.add(new Token("EOF", "EOF"));
			SyntaxAnalyzer.CompilationUnit(SyntaxAnalyzer.tokenizer
					.getCurrentToken());
			SyntaxAnalyzer.tokenizer.reset();
		} else {
			System.out.println("Problem With Pramaters");
		}
	}
}

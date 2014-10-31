import java.util.ArrayList;

public class Tokenizer {

	ArrayList<Token> tokens = new ArrayList<Token>();
	
	int index = 0;
	int index_max = -1;
	Token failed;
	
	public Tokenizer() {
		
	}

	public Token getCurrentToken() {
		if (index < tokens.size()) {
			if (index > index_max) {
				index_max = index;
				failed = tokens.get(index);
			}
			return tokens.get(index);
		} else {
			return null;
		}
	}
	

	public Token getNextToken() {
		if (index + 1 < tokens.size()) {
			tokens.get(++index);
			if (index > index_max) {
				index_max = index;
				failed = tokens.get(index);
			}
			return tokens.get(index);
		} else {
			return null;
		}
	}

	public boolean pushBack(int delta) {
		if (index >= delta)
			index -= delta;
		else
			index = 0;

		return false;
	}

	public void add(Token token) {
		tokens.add(token);

	}

	public int getIndexMax() {
		return index_max;
	}
	
	public Token getToken() {
		return failed;
	}
	
	public void reset(){
		index = 0;
		tokens.clear();
	}
	

}

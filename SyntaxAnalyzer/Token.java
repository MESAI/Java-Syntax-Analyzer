public class Token {

	public String line;
	public String type;
	public String value;
	public int lineValueInt;

	public Token(String value, String type, String line) {
		super();
		this.line = line;
		this.type = type;
		this.value = value;
		lineValueInt  = Integer.parseInt(line.split(":")[0]);
	}
	
	public Token(String value, String type) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public String toString(){
		return line+" "+type+" "+value;
	}
	
	public int getLine(){
		return lineValueInt;
	}

}

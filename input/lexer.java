import java.util.*;
import java.io.*;
/* semantic value of token returned by scanner */
public class lexer
{
	public static void main (String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
		Yylex yy = new Yylex (reader);
		while(true)
		{
			String x =yy.next_token();
			if(x==null)
				break;
			writer.write(x);	
			writer.write('\n');
		}
		reader.close();
		writer.close();
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

  public String sourceFilename;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int COMMENTSBLOCK2 = 3;
	private final int COMMENTSBLOCK1 = 2;
	private final int YYINITIAL = 0;
	private final int COMMENTS = 1;
	private final int yy_state_dtrans[] = {
		0,
		53,
		56,
		59
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NOT_ACCEPT,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"2:8,5:2,3,2,5:2,2:18,5,15,42,2:2,7,12,43,2:2,4,8,2,9,2,1,41:10,2:2,10,6,11," +
"2:2,40:26,2,44,2,13,40,2,16,17,21,31,24,32,34,28,29,40,26,23,35,25,22,36,40" +
",20,18,19,30,37,38,33,27,39,2,14,2:3,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,195,
"0,1,2,3,1:11,4,1:7,5,1:11,6,1,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22," +
"23,24,25,26,8,27,28,29,30,31,32,33,34,35,36,37,11,38,39,40,41,42,43,44,45,4" +
"6,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,7" +
"1,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,9" +
"6,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115," +
"116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134" +
",135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,5,150,151,152," +
"153,154,155,156,157,158,159,160,5")[0];

	private int yy_nxt[][] = unpackFromString(161,45,
"1,2,36,3,40,3,43,46,49,52,55,58,61,63,65,67,35,110,143,111,164,112,194,113," +
"114,77,194:3,39,194,42,78,194,115,194,165,194:4,36,69,71,36,-1:46,4,-1:2,5," +
"-1,6,-1:41,3,-1,3,-1:45,26,-1:54,194:25,182,-1:19,194,179,194:23,182,-1:9,2" +
"7,-1:4,50,-1:49,194:2,23,194:22,182,-1:19,194:9,48,194:6,23,194:2,149,194:5" +
",182,-1:9,7,-1:39,41:41,24,41:2,-1:16,194:6,142,194,168,194:16,182,-1:9,8,-" +
"1:81,25,-1:17,194:22,23,194:2,182,-1:9,9,-1:39,44:42,25,44,-1:16,194:2,187," +
"178,194:21,182,-1:9,10,-1,11,-1:42,28,-1:54,194:4,23,194:20,182,-1:9,12,-1:" +
"2,13,-1:35,1,29:2,30,29:41,-1:16,194:8,23,194:16,182,-1:9,14,-1:3,15,-1:34," +
"1,31:3,32,31:40,-1:6,16,-1:4,37,-1:33,1,33,34:43,-1:16,194:18,23,194:6,182," +
"-1:9,17,-1:5,18,-1:48,194:10,23,194:14,182,-1:9,19,-1:54,194:22,38,194:2,18" +
"2,-1:9,20,-1:7,21,-1:46,194:12,23,194:12,182,-1:9,22,-1:54,194:7,76,194:17," +
"182,-1:19,194:5,23,194:19,182,-1:4,44:42,25,47,-1:16,194:9,23,194:15,182,-1" +
":19,194:11,23,194:13,182,-1:19,194:15,23,194:9,182,-1:19,194:16,23,194:8,18" +
"2,-1:19,194:7,73,194:17,182,-1:19,148,194:7,45,194:16,182,-1:19,194:6,51,12" +
"1,194:5,122,194:11,182,-1:19,194:3,54,194:21,182,-1:19,194:4,89,194:8,57,19" +
"4:11,182,-1:19,194:2,54,90,194:21,182,-1:19,51,194:24,182,-1:19,194:9,60,19" +
"4:15,182,-1:19,194:2,54,194:22,182,-1:19,194:3,144,194:21,182,-1:19,62,194:" +
"24,182,-1:19,194:4,180,194:20,182,-1:19,194:8,51,194:16,182,-1:19,194:6,64," +
"194:18,182,-1:19,194:5,66,194:19,182,-1:19,194:2,180,157,194:21,182,-1:19,1" +
"94:2,57,194:22,182,-1:19,180,194:24,182,-1:19,68,194:24,182,-1:19,194:13,70" +
",194:11,182,-1:19,194:4,72,194:20,182,-1:19,194:21,54,194:3,182,-1:19,194:7" +
",54,194:17,182,-1:19,72,194:24,182,-1:19,194:15,57,194:9,182,-1:19,194:7,18" +
"0,194:17,182,-1:19,194:18,54,194:6,182,-1:19,194:5,180,194:19,182,-1:19,194" +
":14,54,194:10,182,-1:19,194:9,180,194:15,182,-1:19,194:5,54,194:19,182,-1:1" +
"9,194:8,74,194:16,182,-1:19,194:6,75,194:18,182,-1:19,194:3,57,194:21,182,-" +
"1:19,194:4,116,194,166,194:4,79,194:13,182,-1:19,194:4,184,194:7,80,194:12," +
"182,-1:19,81,194:5,119,120,194:4,82,194:12,182,-1:19,194:6,83,194:18,182,-1" +
":19,194:7,84,194:9,167,194:7,182,-1:19,194:6,85,194:18,182,-1:19,194:8,86,1" +
"94:16,182,-1:19,194:6,87,194:18,182,-1:19,194:20,88,194:4,182,-1:19,194:9,9" +
"1,194:15,182,-1:19,92,194:24,182,-1:19,194:6,93,194:18,182,-1:19,194:9,94,1" +
"94:15,182,-1:19,194:3,95,194:21,182,-1:19,194:3,90,194:21,182,-1:19,194:14," +
"96,194:10,182,-1:19,194:13,97,194:11,182,-1:19,194,98,194:23,182,-1:19,194:" +
"7,95,194:17,182,-1:19,194:8,99,194:16,182,-1:19,194:9,100,194:15,182,-1:19," +
"194:14,101,194:10,182,-1:19,102,194:24,182,-1:19,79,194:24,182,-1:19,103,19" +
"4:24,182,-1:19,194:9,104,194:15,182,-1:19,194:8,105,194:16,182,-1:19,106,19" +
"4:24,182,-1:19,194:3,107,194:21,182,-1:19,194:8,108,194:16,182,-1:19,194:9," +
"109,194:15,182,-1:19,194:23,107,194,182,-1:19,194:14,127,194:10,182,-1:19,1" +
"94:3,145,194:7,183,117,194,118,194:7,146,194:2,182,-1:19,194:6,23,194:18,18" +
"2,-1:19,123,194:24,182,-1:19,194:13,124,194:11,182,-1:19,194:3,125,194:21,1" +
"82,-1:19,194:3,126,194:21,182,-1:19,194:20,181,194:4,182,-1:19,194,128,194:" +
"23,182,-1:19,194:7,129,194:17,182,-1:19,194:8,130,194:16,182,-1:19,131,194:" +
"24,182,-1:19,194:10,132,194:14,182,-1:19,194:21,133,194:3,182,-1:19,194:4,1" +
"34,194:20,182,-1:19,194:13,135,194:11,182,-1:19,194:13,136,194:11,182,-1:19" +
",194:16,137,194:8,182,-1:19,194:5,138,194:19,182,-1:19,194:5,139,194:19,182" +
",-1:19,194:8,140,194:16,182,-1:19,194:13,141,194:11,182,-1:19,194:8,147,194" +
":16,182,-1:19,169,194:3,170,194:9,150,194:10,182,-1:19,194:6,151,194:18,182" +
",-1:19,194:3,152,194:21,182,-1:19,194:16,153,194:8,182,-1:19,194:5,154,194:" +
"19,182,-1:19,194:6,188,194:6,155,194:11,182,-1:19,194:3,156,194:21,182,-1:1" +
"9,194:2,158,194:22,182,-1:19,194:4,159,194:20,182,-1:19,194:8,160,194:16,18" +
"2,-1:19,194:9,161,194:15,182,-1:19,194:19,162,194:5,182,-1:19,194:9,163,194" +
":15,182,-1:19,194:8,173,194:16,182,-1:19,194:2,171,194:22,182,-1:19,194:3,2" +
"3,194:21,182,-1:19,194:6,87,191,194:17,182,-1:19,194:9,185,194:15,182,-1:19" +
",186,194:24,182,-1:19,194:5,189,194:19,182,-1:19,194:9,172,194:15,182,-1:19" +
",194:3,190,194:21,182,-1:19,194:3,174,194:21,182,-1:19,194:12,192,194:12,18" +
"2,-1:19,175,194:24,182,-1:19,194:8,176,194:16,182,-1:19,194:4,193,194:20,18" +
"2,-1:19,194:6,177,194:18,182,-1:3");

	public String next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -4:
						break;
					case 3:
						{ }
					case -5:
						break;
					case 4:
						{
  yybegin(COMMENTS);
}
					case -6:
						break;
					case 5:
						{
  	yybegin(COMMENTSBLOCK1);
}
					case -7:
						break;
					case 6:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t/="; 
}
					case -8:
						break;
					case 7:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t*="; 
}
					case -9:
						break;
					case 8:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t=="; 
}
					case -10:
						break;
					case 9:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t%="; 
}
					case -11:
						break;
					case 10:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t+="; 
}
					case -12:
						break;
					case 11:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t++"; 
}
					case -13:
						break;
					case 12:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t-="; 
}
					case -14:
						break;
					case 13:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t--"; 
}
					case -15:
						break;
					case 14:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t<="; 
}
					case -16:
						break;
					case 16:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t>="; 
}
					case -17:
						break;
					case 17:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t&="; 
}
					case -18:
						break;
					case 18:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t&&"; 
}
					case -19:
						break;
					case 19:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t^="; 
}
					case -20:
						break;
					case 20:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t|="; 
}
					case -21:
						break;
					case 21:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t||"; 
}
					case -22:
						break;
					case 22:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t!="; 
}
					case -23:
						break;
					case 23:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext();
}
					case -24:
						break;
					case 24:
						{ 
  return yyline+":"+yychar+"\tString\t"+yytext();
}
					case -25:
						break;
					case 25:
						{ 
  return yyline+":"+yychar+"\tCharacter\t"+yytext();
}
					case -26:
						break;
					case 26:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t<<="; 
}
					case -27:
						break;
					case 27:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t>>="; 
}
					case -28:
						break;
					case 28:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t>>>="; 
}
					case -29:
						break;
					case 29:
						{
}
					case -30:
						break;
					case 30:
						{
  yybegin(YYINITIAL);
}
					case -31:
						break;
					case 31:
						{
  	yybegin(COMMENTSBLOCK1);
}
					case -32:
						break;
					case 32:
						{
  	yybegin(COMMENTSBLOCK2);
}
					case -33:
						break;
					case 33:
						{
	yybegin(YYINITIAL);
}
					case -34:
						break;
					case 34:
						{
	yybegin(COMMENTSBLOCK1);
}
					case -35:
						break;
					case 35:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -36:
						break;
					case 36:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -37:
						break;
					case 38:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext();
}
					case -38:
						break;
					case 39:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -39:
						break;
					case 40:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -40:
						break;
					case 42:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -41:
						break;
					case 43:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -42:
						break;
					case 45:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -43:
						break;
					case 46:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -44:
						break;
					case 48:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -45:
						break;
					case 49:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -46:
						break;
					case 51:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -47:
						break;
					case 52:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -48:
						break;
					case 54:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -49:
						break;
					case 55:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -50:
						break;
					case 57:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -51:
						break;
					case 58:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -52:
						break;
					case 60:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -53:
						break;
					case 61:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -54:
						break;
					case 62:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -55:
						break;
					case 63:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -56:
						break;
					case 64:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -57:
						break;
					case 65:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -58:
						break;
					case 66:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -59:
						break;
					case 67:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -60:
						break;
					case 68:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -61:
						break;
					case 69:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -62:
						break;
					case 70:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -63:
						break;
					case 71:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext(); 
}
					case -64:
						break;
					case 72:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -65:
						break;
					case 73:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -66:
						break;
					case 74:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -67:
						break;
					case 75:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -68:
						break;
					case 76:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext();
}
					case -69:
						break;
					case 77:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -70:
						break;
					case 78:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -71:
						break;
					case 79:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -72:
						break;
					case 80:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -73:
						break;
					case 81:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -74:
						break;
					case 82:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -75:
						break;
					case 83:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -76:
						break;
					case 84:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -77:
						break;
					case 85:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -78:
						break;
					case 86:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -79:
						break;
					case 87:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -80:
						break;
					case 88:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -81:
						break;
					case 89:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -82:
						break;
					case 90:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -83:
						break;
					case 91:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -84:
						break;
					case 92:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -85:
						break;
					case 93:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -86:
						break;
					case 94:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -87:
						break;
					case 95:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -88:
						break;
					case 96:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -89:
						break;
					case 97:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -90:
						break;
					case 98:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -91:
						break;
					case 99:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -92:
						break;
					case 100:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -93:
						break;
					case 101:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -94:
						break;
					case 102:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -95:
						break;
					case 103:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -96:
						break;
					case 104:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -97:
						break;
					case 105:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -98:
						break;
					case 106:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -99:
						break;
					case 107:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -100:
						break;
					case 108:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -101:
						break;
					case 109:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -102:
						break;
					case 110:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -103:
						break;
					case 111:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -104:
						break;
					case 112:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -105:
						break;
					case 113:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -106:
						break;
					case 114:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -107:
						break;
					case 115:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -108:
						break;
					case 116:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -109:
						break;
					case 117:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -110:
						break;
					case 118:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -111:
						break;
					case 119:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -112:
						break;
					case 120:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -113:
						break;
					case 121:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -114:
						break;
					case 122:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -115:
						break;
					case 123:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -116:
						break;
					case 124:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -117:
						break;
					case 125:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -118:
						break;
					case 126:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -119:
						break;
					case 127:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -120:
						break;
					case 128:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -121:
						break;
					case 129:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -122:
						break;
					case 130:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -123:
						break;
					case 131:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -124:
						break;
					case 132:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -125:
						break;
					case 133:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -126:
						break;
					case 134:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -127:
						break;
					case 135:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -128:
						break;
					case 136:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -129:
						break;
					case 137:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -130:
						break;
					case 138:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -131:
						break;
					case 139:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -132:
						break;
					case 140:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -133:
						break;
					case 141:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -134:
						break;
					case 142:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext();
}
					case -135:
						break;
					case 143:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -136:
						break;
					case 144:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -137:
						break;
					case 145:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -138:
						break;
					case 146:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -139:
						break;
					case 147:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -140:
						break;
					case 148:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -141:
						break;
					case 149:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -142:
						break;
					case 150:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -143:
						break;
					case 151:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -144:
						break;
					case 152:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -145:
						break;
					case 153:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -146:
						break;
					case 154:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -147:
						break;
					case 155:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -148:
						break;
					case 156:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -149:
						break;
					case 157:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -150:
						break;
					case 158:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -151:
						break;
					case 159:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -152:
						break;
					case 160:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -153:
						break;
					case 161:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -154:
						break;
					case 162:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -155:
						break;
					case 163:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -156:
						break;
					case 164:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -157:
						break;
					case 165:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -158:
						break;
					case 166:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -159:
						break;
					case 167:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -160:
						break;
					case 168:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -161:
						break;
					case 169:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -162:
						break;
					case 170:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -163:
						break;
					case 171:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -164:
						break;
					case 172:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -165:
						break;
					case 173:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -166:
						break;
					case 174:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -167:
						break;
					case 175:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -168:
						break;
					case 176:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -169:
						break;
					case 177:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -170:
						break;
					case 178:
						{ 
  return yyline+":"+yychar+"\tNoCategory\t"+yytext();
}
					case -171:
						break;
					case 179:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -172:
						break;
					case 180:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -173:
						break;
					case 181:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -174:
						break;
					case 182:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -175:
						break;
					case 183:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -176:
						break;
					case 184:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -177:
						break;
					case 185:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -178:
						break;
					case 186:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -179:
						break;
					case 187:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -180:
						break;
					case 188:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -181:
						break;
					case 189:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -182:
						break;
					case 190:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -183:
						break;
					case 191:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -184:
						break;
					case 192:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -185:
						break;
					case 193:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -186:
						break;
					case 194:
						{ 
  return yyline+":"+yychar+"\tIdentifier\t"+yytext();
}
					case -187:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}

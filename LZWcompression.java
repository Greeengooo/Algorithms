import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.TST;

public class LZWcompression {

    private static final int R = 256; //number of input chars
    private static final int L = 4096; //number of codewords = 2 ^ 12
    private static final int W = 12; //codeword width

    public static void compress()  {
        String input = "ABABABA";
        TST<Integer> st = new TST<Integer>();

        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R + 1; //R is codeword for EOF

        while(input.length() > 0) {
            String s = st.longestPrefixOf(input); //find max prefix match
            BinaryStdOut.write(st.get(s), W); //Print s's encoding
            int t = s.length();
            if (t < input.length() && code < L) //Add s to symbol table
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t); //Scan past s in input
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    private static void expand() {
        String[] st = new String[L];
        int i; //next available codeword

        for (i = 0; i < R; i ++) //Init table for chars
            st[i] = "" + (char) i;
        st[i++] = " "; //(unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val);//Write current string
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword]; //Get next codeword
            if (i == codeword) //if lookahead is invalid
                s = val + val.charAt(0); //make codeword form last one
            if (i < L)
                st[i++] = val + s.charAt(0); //Add new entry to code table
            val = s; //Update current codeword
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        compress();
        expand();
    }
}

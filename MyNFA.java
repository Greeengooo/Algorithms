import edu.princeton.cs.algs4.*;

public class MyNFA {
    private char[] re; //regular expression array with all the chars
    private Digraph G; // epsilon transitions digraph
    private int M; // number of state (number of elements in array re)

    /*

    ( . * A B ( ( C | D * E ) F ) * G )

     */

    public MyNFA(String regexp) {

        //Create the NFA for the given regular expression
        Stack<Integer> ops = new Stack<Integer>();              // stack for indices of left parenthesis and ors ( | )
        re = regexp.toCharArray();                        // transform String RE to char array re
        M = re.length;                                    // number of states
        G = new Digraph(M + 1);                       // create epsilon transition digraph

        for (int i = 0; i < M; i++) {
            int lp = i;                                   //left parenthesis index
            if (re[i] == '(' || re[i] == '|')
                ops.push(i);                              //push the index to the stack
            else if (re[i] == ')') {
                int or = ops.pop();                       //pop the index
                if (re[or] == '|') {                      //if we encounter or then
                    lp = ops.pop();                       //pop the index of the left parenthesis
                    G.addEdge(lp, or + 1);            //add edge to the next char after the or
                    G.addEdge(or, i);                     //add edge to the right parenthesis from the or operation
                }
                else lp = or;
            }
            if (i < M - 1 && re[i + 1] == '*') {          //add epsilon transition in case we encounter * operation
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')')
                G.addEdge(i, i + 1);                    //add epsilon transition to the next char
        }
    }

    public boolean recognize(String txt) {
        //Does the NFA recognize txt
        Bag<Integer> pc = new Bag<Integer>();               //set of all possible states that nfa could be in
        DirectedDFS dfs = new DirectedDFS(G, 0);   //build dfs from epsilon transition digraph
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);             //put all the epsilon transitions states from the state 0

        for (int i = 0; i < txt.length(); i++) {
            //compute possible NFA states for txt[i + 1]
            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc)
                if (v < M)
                    if (re[v] == txt.charAt(i) || re[v] == '.') //if we find a matching char at re and txt wwe go to v + 1
                        match.add(v + 1);

            //follow the e-transitions
            pc = new Bag<Integer>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);
        }

        //if we reach accept state -> return true
        for (int v : pc) if (v == M) return true;
        return false;
    }

    public static void main(String[] args) {
        String txt = "AD";
        String re = "A(B|C)*D";

        MyNFA nfa = new MyNFA(re);
        System.out.println(nfa.recognize(txt));
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class VowelOrNot {
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while (st == null || !st.hasMoreElements()){
                try{ st = new StringTokenizer(br.readLine()); } catch (IOException e){ e.printStackTrace(); }
            }
            return st.nextToken();
        }

        String nextLine(){
            String str = "";
            try{ str = br.readLine(); } catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }

    public static void main(String args[])throws IOException
    {
        FastReader sc = new FastReader();
        int t = Integer.parseInt(sc.next());
        while(t-- > 0)
        {
            char c = sc.next().charAt(0);

            VowelOrNotSolution ob = new VowelOrNotSolution();
            System.out.println(ob.isVowel(c));
        }
    }
}
// } Driver Code Ends


/*
Given an English alphabet c, Write a program to check whether a character is a vowel or not.

You don't need to read input or print anything. Your task is to complete the function isVowel()
which takes a character c and returns 'YES' or 'NO'.
 */

//User function Template for Java
class VowelOrNotSolution{
    String isVowel(char c){
        // code here
        if (c < 97) {
            c = (char) (c + 32);
        }
        if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
            return "YES";
        } else {
            return "NO";
        }
    }
}

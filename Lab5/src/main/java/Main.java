public class Main
{

    public static void main(String[] args)
    {
        String txt = "CDCDABCDCDCDABCDCDCD";
        String pat = "CDCD";

        KMP(txt, pat);
    }

    public static void KMP(String txt, String pat)
    {

        if (pat == null || pat.length() == 0)
        {
            System.out.println("Pattern at index 0");
        }

        if (txt == null || pat.length() > txt.length())
        {
            System.out.println("Pattern not found");
        }

        char[] chars = pat.toCharArray();

        int[] pi = new int[pat.length() ];
        int i = 1;
        int j = 0;
        while ( i < pat.length())
        {
            if (chars[j] == chars[i]) {
                pi[i] = j + 1;
                i++;
                j++;
            }

            else if (j==0 && chars[j] != chars[i] ){
                pi[i]=0;
                i++;
            }

            else{
                j = pi[j-1];
            }
        }

        int k=0;
        int l=0;
        while (k < txt.length() )
        {
            if (txt.charAt(k) == pat.charAt(l)) {
                k++;
                l++;
            }
            if (l == pat.length()) {
                System.out.println("Pattern at index " + (k - l));
                l = pi[l-1];
            }
            else if (k < txt.length() && pat.charAt(l) != txt.charAt(k))
            {
                if (l!=0){
                    l = pi[l-1];
                }
                else
                    k = k+1;
            }
        }
    }
}
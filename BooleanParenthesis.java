public class BooleanParenthesis {

    public int getTruth(String str)
    {
        int[][] T = new int[str.length()][str.length()];
        int[][] F = new int[str.length()][str.length()];

        for(int i=0; i < str.length(); i += 2)
        {
            if(str.charAt(i) == 'T')
            {
                T[i][i] = 1;
                F[i][i] = 0;
            }
            else if(str.charAt(i) == 'F'){
                T[i][i] = 0;
                F[i][i] = 1;
            }
        }

        for(int len = 2; len < str.length(); len = len + 2)
        {
            for(int i=0; i < str.length() - len ; i += 2)
            {
                int j = i + len;
                for(int k= i; k < j; k += 2)
                {
                    if(str.charAt(k+1) == '&')
                    {
                        T[i][j] += T[i][k] *T[k+2][j];
                        F[i][j] += T[i][k] * F[k+2][j] + F[i][k] * T[k+2][j] + F[i][k] * F[k+2][j];
                    }
                    else if(str.charAt(k+1) == '^')
                    {
                        T[i][j] += T[i][k] * F[k+2][j] + F[i][k] * T[k+2][j];
                        F[i][j] += T[i][k] * T[k+2][j] + F[i][k] * F[k+2][j];
                    }
                    else if(str.charAt(k+1) == '|')
                    {
                        T[i][j] += T[i][k] * F[k+2][j] + F[i][k] * T[k+2][j] + T[i][k] * T[k+2][j];
                        F[i][j] += F[i][k] * F[k+2][j];
                    }
                }
            }
        }
        return T[0][str.length() - 1];

    }

    public static void main (String[] args)
    {
        //code
        BooleanParenthesis obj = new BooleanParenthesis();
        String str = "T|T&F^T";
        //String str = "T&T";
        System.out.println(obj.getTruth(str));
    }

}

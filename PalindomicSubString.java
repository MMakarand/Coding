import java.util.Scanner;

import java.util.*;
        import java.lang.*;
        import java.io.*;
class PalindomicSubString
{
    public static void main (String[] args)
    {
        //code
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        String str = null;
        PalindomicSubString obj = new PalindomicSubString();

        for(int i=0; i<tc;i++)
        {
            str = sc.next();
            System.out.println(obj.getPalindromicPartition(str));
        }
    }

    public int getPalindromicPartition(String str)
    {
        int[][] count = new int[str.length()][str.length()];
        boolean[][] isPalindrome = new boolean[str.length()][str.length()];

        for(int [] row : count)
            Arrays.fill(row, Integer.MAX_VALUE);

        for(int k=0; k < str.length(); k++)
        {
            for(int i = 0; i < str.length() - k; i++)
            {
                int j = i+k;

                if(j-i < 2 )
                {
                    if(str.charAt(i) == str.charAt(j))
                    {
                        isPalindrome[i][j] = true;
                        count[i][j] = 0;
                    }
                    else
                    {
                        isPalindrome[i][j] = false;
                        count[i][j] = 1;
                    }
                }
                else
                {
                    if(isPalindrome[i+1][j-1] && str.charAt(i) == str.charAt(j))
                    {
                        isPalindrome[i][j] = true;
                        count[i][j] = 0;
                    }
                    else
                    {
                        isPalindrome[i][j] = false;
                        count[i][j] = j-i;
                        for(int t = i; t < j; t++)
                        {
                            if(isPalindrome[i][t] && isPalindrome[t+1][j])
                            {
                                if( 1 + count[i][t] + count[t+1][j] < count[i][j])
                                {
                                    count[i][j] = 1 + count[i][t] + count[t+1][j];
                                }
                            }
                        }
                    }
                }
            }
        }
        return count[0][str.length()-1];
    }
}
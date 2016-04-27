package editdistance_withoutlcs;
import java.lang.*;

import java.lang.StringIndexOutOfBoundsException;
import java.io.*;
import java.util.*;
import java.math.*;
/**
 *
 * @author makarand 
 * This problem calculates number of add/remove/replace operations needed to convert one string to another
 */


public class EditDistance_withoutLCS {

     static int len1, len2;
     static int LCS[][];
     static String str1, str2;
     static int MAX = 32500;
     /**
     * @param args the command line arguments
     */
     
    /**
     *
     * @param a
     * @param args the command line arguments
     */
    public static int max(int a, int b)
     {
     
        // return ((a>b)? ((a>c)?a:c) : (b<c)?c:b);
          return ((a>b)? a:b);
     }
 
    public static int min(int a, int b, int c)
    {
    return ((a<b)? ((a<c)?a:c) : (b<c)?b:c);
    }
    public static int fill_LCS(int iStr1, int iStr2)
    {
 /*
  *This condition is needed when part of one string(which start at location iStr1) is substring of part (which start at iStr2)of another string 
  * example:- bacb and b
        Here we need to remove 3 characters a, c and one b
        But if we dont include this condition, when comparing b's, we dont increment value value of LCS at both b position
        Execution without this condition
               0            1           2
        0      2            2           Max
        1       1           2           Max   
        2       1           1           Max
        3       1           0           Max
        4       Max         Max         Max
        
        Execution with condition
                0            1           2
        0       3           3           Max
        1       1           2           Max   
        2       1           1           Max
        3       1           0           Max
        4       Max         Max         Max
   

*/
     if((str1.substring(iStr1).contains(str2.substring(iStr2))) || (str2.substring(iStr2).contains(str1.substring(iStr1))))
     {
                 LCS[iStr1][iStr2] = Math.abs(str1.substring(iStr1).length() - str2.substring(iStr2).length());       
     }
     /*In case we are evaluating last character of one string with another string we access value iStr+1 , iStr2+1 which may exceed len */
     if(iStr1 >= len1 || iStr2 >= len2){
         return 0;
           }
     else if(LCS[iStr1][iStr2] != 0) {
         return LCS[iStr1][iStr2];
     }
     else{
        /*
         if both character match
         */ 
        try{
         if(str1.charAt(iStr1) == str2.charAt(iStr2)){
             if(iStr1 < len1-1 && iStr2 < len2-1)
                    LCS[iStr1][iStr2] = LCS[iStr1+1][iStr2+1];
             else{
                 /*
                 If both characters are end characters of respective string then value will be 0
                 */
                    if(iStr1 ==len1-1 && iStr2 ==len2-1)
                        LCS[iStr1][iStr2] =0;
                    else
                        /*
                        if either character is last character of string
                        */
                    LCS[iStr1][iStr2] = min(LCS[iStr1+1][iStr2],LCS[iStr1][iStr2+1],MAX);
             }
           }
         else{
             //replace , remove, add
             if(iStr1 < len1-1 || iStr2 < len2-1)
             {
                 LCS[iStr1][iStr2] = min(1+LCS[iStr1+1][iStr2+1],1+LCS[iStr1+1][iStr2],1+LCS[iStr1][iStr2+1] );
             }
             else
             {
                    LCS[iStr1][iStr2] = 1;
             }              
            }
        }
         catch(StringIndexOutOfBoundsException sioobe)
         {
             System.out.println("str1"+str1+"iStr1"+iStr1);
             System.out.println("str2"+str2+"iStr2"+iStr2);
             sioobe.printStackTrace();
         }
     }
     return LCS[iStr1][iStr2];
    }
    
     
    public static void main(String[] args) throws IOException, NullPointerException{
        // TODO code application logic here
        int i=0,j=0, tc=0,arr[];
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        tc= Integer.parseInt(br.readLine());
        int ltc = 0;
        arr = new int[tc];
        
        Scanner sc;
    
        while(ltc < tc)
        {
        //Dont trust lengths provided by geeksforgeek, better to calculate them from string
        String lengthinput = br.readLine();
        String strinput = br.readLine();
        if (strinput != null){
        sc = new Scanner(strinput);
        str1 = sc.next();
        str2 = sc.next();
        try{
            len1 = str1.length();
        }
        catch (NullPointerException npe){
            len1=0;
        }
        
        try{
            len2 = str2.length();
        }
        catch (NullPointerException npe){
            len2=0;
        }
        //System.out.println("Length of first string"+len1);
        LCS =  new int[len1+1][len2+1];
        
        for(i=0;i<=len1;i++)
        {
             for(j=0;j<=len2;j++)
            {
                if(i== len1 || j==len2){
                    LCS[i][j] = MAX;
                }
                else{
                    LCS[i][j]=0;        
                }
            }
        }
        
        if(str2.contains(str1) || str1.contains(str2) )
        {         
          arr[ltc] = Math.abs(str1.length() - str2.length());
        }
        else
        {
            for(i=len1-1;i>=0;i--)
            {
                 for(j=len2-1;j>=0;j--)
                {
                         fill_LCS(i,j);
                }
            }
            arr[ltc] = LCS[0][0];
            LCS = null;
            str1 = null;
            str2 = null;
            
        }
        ltc++;
    }
   }
        for(i=0;i<tc;i++)
        System.out.println(arr[i]);
 }
}
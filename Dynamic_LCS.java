

ange this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package lcs;
import java.lang.*;
import java.lang.StringIndexOutOfBoundsException;
import java.io.*;
import java.util.*;
/**
 *
 * @author makarand 
 */


public class LCS {

     static int len1, len2;
     static int LCS[][];
     static String str1, str2;
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
 
    public static int fill_LCS(int iStr1, int iStr2)
    {
     if(iStr1 >= len1 || iStr2 >= len2){
         return 0;
           }
     else if(LCS[iStr1][iStr2] != 0) {
         return LCS[iStr1][iStr2];
     }
     else{
        /* try{
         if(str1.charAt(iStr1) == str2.charAt(iStr2))
            LCS[iStr1][iStr2] = 1+ fill_LCS(iStr1+1,iStr2+1);
         else
             LCS[iStr1][iStr2] = max(fill_LCS(iStr1+1,iStr2),fill_LCS(iStr1,iStr2+1) );
         }
         catch(StringIndexOutOfBoundsException sioobe)
         {
             System.out.println("str1"+str1+"iStr1"+iStr1);
             System.out.println("str2"+str2+"iStr2"+iStr2);
             sioobe.printStackTrace();
         }*/
         
        try{
         if(str1.charAt(iStr1) == str2.charAt(iStr2)){
            LCS[iStr1][iStr2] = 1+ LCS[iStr1+1][iStr2+1];
            //System.out.println("char at istr1:"+iStr1 + "matching at istr2:"+iStr2+"LCS[][]="+LCS[iStr1][iStr2]);
         }
         else{
             LCS[iStr1][iStr2] = max(LCS[iStr1+1][iStr2],LCS[iStr1][iStr2+1] );
              // System.out.println("LCS[][]="+LCS[iStr1][iStr2]);         
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
    
     
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        int i=0,j=0, tc=0,arr[];
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        tc= Integer.parseInt(br.readLine());
        int ltc = 0;
        arr = new int[tc];
        
        Scanner sc;
    
        while(ltc < tc){
        sc = new Scanner(br.readLine());
        if(sc.hasNextInt())
        len1 = sc.nextInt();
        
        
        if(sc.hasNextInt())
        len2 = sc.nextInt();
        
        //String temp = br.readLine();
       // str1 = br.readLine();
        //str2 = br.readLine();
        sc = new Scanner(br.readLine());
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
              LCS[i][j]=0;        
            }
        }
        
        
        
        for(i=len1-1;i>=0;i--)
        {
             for(j=len2-1;j>=0;j--)
            {
                     fill_LCS(i,j);
            }
        }
        arr[ltc] = LCS[0][0];
      // System.out.println(LCS[0][0]); 
        LCS = null;
        //str1 = null;
        //str2 = null;
        ltc++;
    }
    for(i=0;i<tc;i++)
        System.out.println(arr[i]);
    }
}


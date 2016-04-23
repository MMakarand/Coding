ange this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package longestincreasingsubsequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.*;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;
/**
 *
 * @author govind
 */
public class LongestIncreasingSubsequence {

    static int ELIS[], LIS[],arr[];
    static int MAX = 32760;
    static int in_length;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
          int i=0,j=0, tc=0,output[], ltc = 0;
          int arrSize =0;
          
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        tc= Integer.parseInt(br.readLine());
        
        output = new int[tc];
        
        Scanner sc;
         
        while(ltc < tc){

        arrSize = Integer.parseInt(br.readLine());
        arr = new int[arrSize];    
        sc = new Scanner(br.readLine());
        while(sc.hasNextInt())
        {
            try{
               arr[j] = sc.nextInt();
                
            }
            catch(ArrayIndexOutOfBoundsException aie)
            {
                
                System.out.println("j:-"+j);
                aie.printStackTrace();
            }
               
               
               
               j++;
        }
        
        in_length = arr.length;
        ELIS = new int[in_length];
        LIS = new int[in_length + 1];

        ELIS[in_length - 1] = 1;
        LIS[in_length - 1] = 1;
        LIS[in_length] = MAX;
        
        for(i=in_length-2; i >= 0; i--)
            build_ELIS(i);

        //for(i=0;i<in_length;i++)
            //System.out.print(ELIS[i]);
            
        for(i=in_length-2; i >= 0; i--)
            build_LIS(i);
    
        //for(i=0;i<in_length;i++)
          //  System.out.println(LIS[i]);
        //System.out.println("Compelted computation");
        output[ltc] = LIS[0];
        //System.out.println(output[ltc]);
        LIS = null;
        ELIS = null;
        arr = null;
        i=0;j=0;
        ltc++;
      }
        for(i=0;i<tc;i++)
         System.out.println(output[i]);
    }
    
    public static int max(int a, int b){
     return a>b ? a:b;
    
    }
    
    public static void build_LIS(int x)
    {
      if(ELIS[x]<= LIS[x+1])
          LIS[x] = LIS[x+1];
      else
          LIS[x] = ELIS[x];
    }
    
    
    public static void build_ELIS(int x)
    {
        int i=x+1;
        ELIS[x] = 1;
        int len = 1;
        while(i < in_length){
           if(arr[x] < arr[i])
           {
            ELIS[x] = max(ELIS[i]+1,len);
            len = ELIS[x];
           }
           i++;
        }
    }
    
    
}


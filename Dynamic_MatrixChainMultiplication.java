/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package matrixchainmultiplication;

import java.io.*;
import java.lang.*;
import java.util.Scanner;
/**
 *
 * @author makarand
 */
public class MatrixChainMultiplication  {

    /**
     * @param args the command line arguments
     */
    /*
        Time complexity O(n^3)
    
        i       j       k
        1       1       1
                2       1
                3       1
                ;
                ;
                ;
                n-1     1
    
        2       1       2
                2       2
                3       2
                ;
                ;
                ;
                n-2     2
    
        ;
        ;
        ;
        ;
        n-1     1       n-1
                2       n-1
                3       n-1
                ;
                ;
                ;
                n-(n-1) n-1
    
    
    total number of operations = (n-1) * 1 + (n-2) * 2 + (n-3) * 3+ ....(n-(n-1)) * (n-1)
                               = n-1+ 2n-(2*2) + 3n- (3*3)+ (n-1)n - (n-1)(n-1)
                               =O(n^3)
    
    */

    public static void main(String[] args) throws IOException {
        // TODO code application logic 
        int n=0,i,count =0;
        int MIN_VALUE = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Scanner sc = new Scanner(input);

        //We need to know size of array
        while(sc.hasNextInt())
        {
            sc.nextInt();
            n++;
        }
        //Create array for storing matrix dimension
         int p[] =  new int[n];
         
         /*create array to store number of multiplications 
          tot_mul[i][j] stores number multiplication needed for array starting at index i-1 till array ending at index j
         */
         int tot_mul[][] = new int[n][n];
         
         sc = new Scanner(input);
         i=0;
         while(sc.hasNextInt())
            p[i++] = sc.nextInt();
         
         //if it is single array i.e A[p[i-1] X p[i]] we are not doing any multiplication
         for(i=0;i<n;i++)
             tot_mul[i][i] = 0;
         
         for(i=1;i<n;i++)
         {
             //Filling bottom up, multiplication required for product of  all possible 2 matrices, then all possible 3 matrix etc
             for(int j = 1; j+i < n; j++)
             {
                 tot_mul[j][j+i] = MIN_VALUE;
                 
                 for(int k=j;k<j+i;k++)
                 {
                 
                 count = tot_mul[j][k] + tot_mul[k+1][j+i] + p[j-1]*p[k]*p[j+i];
                 if(count < tot_mul[j][j+i])
                     tot_mul[j][j+i] = count;
                 }
            
             }
         }
         
         System.out.println(tot_mul[1][n-1]);
        
    }
    
}

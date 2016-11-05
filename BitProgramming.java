/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitprogramming;

import java.io.*;
/**
 *
 * @author makarand
 */
public class BitProgramming {

    /**
     * @param args the command line arguments
     */
    static int M,N,i,j;
    
    public void insert(int M, int N, int i, int j)
    {
        
        
        
        int temp2=0;
        int temp3 =0;
       // for(int k=i;k<=j;k++)
         //   temp2 |= 1<<k;
                
         
         temp2 |= (~( 1 << 31) << (j+1));
         temp3 |=  (( 1 << i ) - 1);
         temp2 |= temp3;
                /* =             ((1 << (j+1)) - 1) & 
                                
                                (( 1 << i ) - 1);*/
        
        System.out.println("Mask "+ Integer.toBinaryString(temp2));
                    
        int temp = (M << i ) | (N & temp2);
        System.out.println( "Output" + Integer.toBinaryString(temp));
    
    }
    
    public boolean areArgumentsValid()
    {
    int temp = M;
    int count = 0;
    while(temp != 0)
    {
        temp = temp/2;
        count++;
    }
    if((j > 31) ||  (j < 0 ) ||  ( i > 31) || (i < 0))
    {
        return false;
    }
    if(count == j-i+1)
        return true;
    else
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        i = Integer.parseInt(br.readLine());
        j = Integer.parseInt(br.readLine());
        
        
        System.out.println("M ="+ Integer.toBinaryString(M));
        System.out.println("N ="+ Integer.toBinaryString(N));
        BitProgramming bp = new BitProgramming();
        if(bp.areArgumentsValid())
        {
            bp.insert(M, N, i, j);
        }
        else{
            System.err.println("Arguments are invalid");
        }
    }
    
}

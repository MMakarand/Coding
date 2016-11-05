/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitprogramming5.pkg2;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author makarand
 */
public class BitProgramming52 {

    /**
     * @param args the command line arguments
     */
    static double ipNum=0.0;
    
    public void printBinary()
    {
        int count = 0;
        double tempIpNum = ipNum;
        String binRep = "0.";
        
        while(tempIpNum != 0.0 && count < 31)
        {
            tempIpNum *=2;
            if(tempIpNum >= 1)
            {
             binRep += "1";
             tempIpNum -=1;
            }
            else
            {
             binRep += "0";
            }
            count++;
        }
        if(tempIpNum == 0)
            System.out.println(binRep);
        else
            System.err.println("Error");
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ipNum = Double.parseDouble(br.readLine());
        
        BitProgramming52 b52 = new BitProgramming52();
        if(ipNum > 0 && ipNum < 1)
            b52.printBinary();
        
    }
    
}

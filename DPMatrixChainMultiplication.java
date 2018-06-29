import java.util.*;
import java.lang.*;
import java.io.*;
public class DPMatrixChainMultiplication {

    public int getOptimalMultiplications(int[] M){

        int[][] mulCount = new int[M.length][M.length];

        for(int diff = 1; diff < M.length; diff++)
        {
            for(int i=0; i < (M.length - diff - 1); i++)
            {
                int j = i+diff+1;
                //int mul1 = M[i] * M[i+1] * M[j+1] + mulCount[i+1][j];
                //int mul2 = M[i] * M[j] * M[j+1] + mulCount[i][j-1];
                //mulCount[i][j] = Math.min(mul1,mul2);
                //this is intermediate where multiplication is done
                //eg 10*20, 20*30 , 30*40 B is 20 if (10*20) * (20*30 * 30*40)
                int tMul = Integer.MAX_VALUE;
                for(int k= i; k < j-1; k++)
                {
                    int mul = M[i]*M[k+1]*M[j] + mulCount[k+1][j]+mulCount[i][k+1];
                    if(mul < tMul )
                        tMul = mul;
                }
                mulCount[i][j] = tMul;
                System.out.println(" A " + i + " B " + (j) + " A * B " + mulCount[i][j]);
            }
        }
        return mulCount[0][M.length-1];

    }
    public static void main (String[] args)
    {
        //code
        int[] M = {10, 20, 30, 40, 30};
        DPMatrixChainMultiplication obj = new DPMatrixChainMultiplication();
        obj.getOptimalMultiplications(M);
    }
}

import java.util.Scanner;

public class ZeroOneKnapsack_DP {

    public int getMaxVal(int W, int[] val, int[] w)
    {
        int[][] valRange = new int[val.length][val.length];
        int[][] wRange = new int[val.length][val.length];

        for(int diff =0 ; diff < w.length; diff++)
        {
            for(int i=0; i < w.length-diff; i++)
            {
                int j = i + diff;
                if(i == j){
                    if(w[i] <= W) {
                        valRange[i][j] = val[i];

                    }
                    wRange[i][j] = w[i];
                }
                else{
                    if(wRange[i][j-1] + w[j] <= W)
                    {
                        wRange[i][j] =  wRange[i][j-1] + w[j];
                        valRange[i][j] =valRange[i][j-1] + val[j];
                    }
                    else{
                        wRange[i][j] =  wRange[i][j-1];
                        valRange[i][j] =valRange[i][j-1];

                        for(int k = i+1; k < j-1; k++)
                        {
                            if(wRange[i][k-1] + wRange[k+1][j-1] - w[k] + w[j] <= W)
                            {
                                if(valRange[i][j-1] + val[j]-val[k] >= valRange[i][j])
                                {
                                    wRange[i][j] = wRange[i][j-1] + w[j] - w[k];
                                    valRange[i][j] = valRange[i][j-1] + val[j]-val[k];
                                }
                            }
                        }
                        }
                }
            }
        }
        return valRange[0][val.length - 1];
    }
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for(int ii=0; ii < tc; ii++)
        {
            int size = sc.nextInt();
            int W = sc.nextInt();
            int [] val = new int[size];
            int[] w = new int[size];

            for(int i =0; i < size; i++)
            {
                val[i] = sc.nextInt();
            }

            for(int i =0; i < size; i++)
            {
                w[i] = sc.nextInt();
            }
          /*  double[] ratio = new double[val.length];
            boolean[] isVisited = new boolean[val.length];

            for(int i=0; i < val.length; i++)
            {
                ratio[i] = (double)w[i] / val[i];
                isVisited[i] = false;
            }
*/
            ZeroOneKnapsack_DP obj = new ZeroOneKnapsack_DP();
            System.out.println(obj.getMaxVal(W, val, w));
        }




    }
}

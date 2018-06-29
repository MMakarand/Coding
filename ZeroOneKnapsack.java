import java.util.Scanner;

public class ZeroOneKnapsack {




    int curProfit, curWeight;

    public int getMaxVal(int W, int[] val, int[] w, double[] ratio, boolean[] isVisited)
    {
        curProfit = 0;
        curWeight = 0;
        int index = 0;

        for(int i=0; i < val.length; i++)
        {
            index = getMinValIndex(ratio,isVisited);
            if(curWeight + w[index]  <= W )
            {
                isVisited[index] = true;
                curProfit += val[index];
                curWeight += w[index];
            }
        }
    return curProfit;
    }

    private int getMinValIndex(double[] ratio, boolean[] isVisited) {
        int cur =0;
        double curRatio = Double.MAX_VALUE;
        for(int i=0; i < ratio.length; i++)
        {
            if(!isVisited[i] && ratio[i] < curRatio)
            {
                cur = i;
            }
        }
        return cur;
    }


    /*
    *
    * Useful testcase where greedy doesnt work
    *          1
        3
        50
        60 100 120
        10 20 30
    * */
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
            double[] ratio = new double[val.length];
            boolean[] isVisited = new boolean[val.length];

            for(int i=0; i < val.length; i++)
            {
                ratio[i] = (double)w[i] / val[i];
                isVisited[i] = false;
            }

            ZeroOneKnapsack obj = new ZeroOneKnapsack();
            System.out.println(obj.getMaxVal(W, val, w, ratio,isVisited));
        }




    }

}

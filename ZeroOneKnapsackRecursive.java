import java.util.Scanner;

public class ZeroOneKnapsackRecursive {

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
            boolean[] isVisited = new boolean[val.length];

          ZeroOneKnapsackRecursive obj = new ZeroOneKnapsackRecursive();

            System.out.println(obj.getMaxVal(W, val, w,isVisited,0));
        }
    }

    private int getMaxVal(int W, int[] val, int[] w, boolean[] isVisited, int index) {
        if(W <= 0 || index >= val.length)
            return 0;
        //int curVal = 0;
        int max = 0;
        int max1 = 0, max2 = 0;

        if(w[index] <= W)
            max1 = val[index]+getMaxVal(W-w[index],val,w,isVisited,index+1);

        max2 = getMaxVal(W,val,w,isVisited,index+1);
        max = Math.max(max1,max2);

        return max;
    }
}

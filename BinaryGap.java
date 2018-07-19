package Contest.WW93;

public class BinaryGap {
    public int binaryGap(int N) {
        if(N <= 0)
            return 0;
    int start =0 , end = 0, count = 1, ones = 0, maxGap = 0;

        while (N != 0)
        {
            if((N & 1) == 1)
            {
                if(start == 0)
                {
                    start = count;
                }
                else {
                    end = count;

                    maxGap = (end - start > maxGap) ? end - start : maxGap;
                    start = end;
                    end = 0;

                }
            }
            count++;
            N = N >> 1;
        }
        return maxGap;
    }

    public static void main(String[] args)throws Exception
    {
        BinaryGap obj = new BinaryGap();
        System.out.println(obj.binaryGap(22));
    }
}

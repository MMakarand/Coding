package Contest.WW93;

import java.util.ArrayList;
import java.util.List;

public class ReOrderPower2 {

    public boolean reorderedPowerOf2(int N) {
        if(N <= 0)
            return false;

        if(isPowerOf2(N))
            return true;

        String ip = Integer.toString(N);
        List<String> permutations = new ArrayList<>();
        generateAllPermutation(ip,permutations, 0, ip.length() - 1);

        for (String str : permutations)
        {
            if(isPowerOf2(Integer.parseInt(str)))
            {
                return true;
            }
        }
        return false;
    }

    private void  generateAllPermutation(String ip, List<String> permutations, int l, int r) {
        if(l == r)
        {
            if(ip.charAt(0) != '0')
                permutations.add(ip);

        }
        else{

            for(int i=l ; i <= r; i++)
            {
                boolean check = shouldSwap(ip.toCharArray(), l, i);
                if(check) {
                    ip = swap(ip, l, i);
                    generateAllPermutation(ip, permutations, l + 1, r);
                    ip = swap(ip, l, i);
                }
            }
        }

    }

    private String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public boolean isPowerOf2(int N)
    {
        int count =0;
        while (N != 0)
        {
            count++;
            N = N & (N-1);
        }

        return count == 1;
    }

    boolean shouldSwap(char str[], int start, int curr)
    {
        for (int i = start; i < curr; i++)
            if (str[i] == str[curr])
                return false;
        return true;
    }
    public static void main(String[] args)throws Exception
    {
        ReOrderPower2 obj = new ReOrderPower2();
        System.out.println(obj.reorderedPowerOf2(214806876));
    }
}

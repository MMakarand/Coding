import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IPtoCIDR {
    public List<String> ipToCIDR(String ip, int n) {
        List<String> op = new ArrayList<String>();

        long start = ipToLong( ip);
        while( n > 0)
        {
            int mask = Math.max(33- bitLength(Long.lowestOneBit(start)),33 - bitLength(n));
            op.add(longToIP(start) + "/"+ mask);
            start += 1 << (32 - mask);
            n -= (1 << (32 - mask));
        }
        return op;
    }

    private String longToIP(long num)
    {
        return String.format("%s.%s.%s.%s", num>>24, (num >> 16) %256, (num>>8) %256, num %256);
    }
    public long ipToLong(String ip)
    {
        long ans = 0;
        for(String x : ip.split("\\."))
        {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }

    private int bitLength(long num)
    {
        if(num == 0)
            return 1;

        int count = 0;
        while(num != 0)
        {
            count++;
            num >>= 1;
        }
        return count;
    }

    public static void main(String[] args)throws IOException
    {
        int n=10;
        String ip = "255.0.0.7";

        IPtoCIDR obj = new IPtoCIDR();
        List<String> op = obj.ipToCIDR(ip,n);

        Iterator itr = op.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
           // itr = itr.next();
        }
    }
}



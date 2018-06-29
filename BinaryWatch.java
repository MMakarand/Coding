import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> stringList = new ArrayList<>();

        HashMap<Integer, List<String>>hour = new HashMap<>();
        HashMap<Integer, List<String>>min = new HashMap<>();

        for(int i=0; i<12;i++)
        {
            int ones = oneCount(i);
            List<String> tList = null;
                if(!hour.containsKey(ones))
                {
                    tList = new ArrayList<>();
                    hour.put(ones,tList);
                }
            hour.get(ones).add(Integer.toString(i));
        }

        for(int i=0; i<59;i++)
        {
            int ones = oneCount(i);
            List<String> tList = null;
            if(!min.containsKey(ones))
            {
                tList = new ArrayList<>();
                min.put(ones,tList);
            }
            if(i == 0)
                min.get(ones).add("00");
            else if(i == 1)
                min.get(ones).add("01");
            else if(i == 2)
                min.get(ones).add("02");
            else if(i == 3)
                min.get(ones).add("03");
            else if(i == 4)
                min.get(ones).add("04");
            else if(i == 5)
                min.get(ones).add("05");
            else if(i == 6)
                min.get(ones).add("06");
            else if(i == 7)
                min.get(ones).add("07");
            else if(i == 8)
                min.get(ones).add("08");
            else if(i == 9)
                min.get(ones).add("09");
            else {
                min.get(ones).add(Integer.toString(i));
            }
        }

        for(int i=0; i <=num; i++)
        {
            int hours = i;
            int minutes = num-i;
            List<String> hList = hour.get(hours);
            List<String> mList = min.get(minutes);
            if(hours <4 && minutes < 6)
            {
                for (String ho : hList) {
                    for (String mi : mList) {
                        stringList.add(ho + ":" + mi);
                    }
                }
            }

        }
        return stringList;
    }

    private int oneCount(int num)
    {
        int count = 0;
        while(num != 0)
        {
            count++;
            num = num & (num-1);
        }
        return count;
    }

    public static void main(String[] args)throws Exception
    {
        BinaryWatch obj = new BinaryWatch();
        List<String> op = obj.readBinaryWatch(4);
        for(String s: op)
        {
            System.out.print(s + "  ");
        }
    }
}

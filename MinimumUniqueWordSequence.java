import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//This solution gives TLE on LeetCode
public class MinimumUniqueWordSequence {


    public List<String> generateAbbreviations(String word) {
        List<String> strings = new ArrayList<>();
        if(word.length() == 0)
        {
            strings.add("");
            return strings;
        }


        int l = 2 << (word.length() - 1);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i < l; i++)
        {
            int count = 0;
            for(int j=0; j < word.length(); j++)
            {

                if((i &(1 << j)) > 0)
                {
                    if(count != 0) {
                        sb.append(count);
                        count = 0;
                    }

                    sb.append(word.charAt(j));
                }
                else{
                    count++;
                }
            }
            if(count != 0)
                sb.append(count);

            strings.add(new String(sb));
            sb.setLength(0);
        }

        return strings;
    }

    public String minAbbreviation(String target, String[] dictionary) {
        int len = Integer.MAX_VALUE;
        HashMap<String, Boolean> hMap = new HashMap<>();

        List<String> temp = null;

        for(String str: dictionary)
        {
            temp = generateAbbreviations(str);
            for(String word: temp)
            {
                if(!hMap.containsKey(word))
                {
                    hMap.put(word, true);
                }
            }

        }
        temp = generateAbbreviations(target);
        String op = null;
        for(String str: temp)
        {
            if(!hMap.containsKey(str))
            {
                System.out.println(str);
                if(wordLength(str) < len )
                {
                    len = wordLength(str);
                    op = str;
                }
            }
        }
        return op;
    }

    public int wordLength(String str)
    {
        int count = 0;
        int numCount = 0;
        if(str.length() == 0)
            return count;

        for(int i=0; i < str.length(); i++)
        {
            if(str.charAt(i)-'0' >= 0 && str.charAt(i) -'0' < 10)
            {
                numCount++;
            }
            else{
                if(numCount > 0)
                {
                    count += 2;
                    numCount = 0;
                }
                else
                {
                    count++;
                }

            }

        }
        return count + (numCount > 0 ? 1 : 0);
    }

    public static void main(String[] args)throws IOException
    {
        MinimumUniqueWordSequence obj = new MinimumUniqueWordSequence();
        String word = "leetcode";
        String []dict = {"leetcold"};//{"lyftcode","leetcold","litecode","lietcode","leetccod","lyftcold"};

        System.out.println(obj.minAbbreviation(word,dict));
    }
}

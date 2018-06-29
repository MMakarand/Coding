import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word) {
        List<String> strings = new ArrayList<>();
        if(word.length() == 0)
            return strings;

        int l = 2 << (word.length() - 1);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i <= l; i++)
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
         /*   strings.add(word);

        for(int len=1; len <= word.length(); len++)
        {
            for(int i=0; i <= word.length() - len; i++ )
            {
                int start1  = 0;
                int end1 = i;
                int start2 = i+len;
                int end2 = word.length();

                String temp= word.substring(start1,end1) +  len + word.substring(start2,end2);
                strings.add(temp);
            }
        }
*/
    }

    public static void main(String[] args)throws IOException
    {
        String str = "word";
        GeneralizedAbbreviation obj = new GeneralizedAbbreviation();
        List<String> opStringList = obj.generateAbbreviations(str);
        for(String s : opStringList)
        {
            System.out.println(s);
        }
    }
}

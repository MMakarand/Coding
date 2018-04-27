import java.util.*;

public class Solution {
    public List<String> ambiguousCoordinates(String S) {
        List<String> coord = new ArrayList<String>();

        for(int i=1; i < S.length(); i++)
        {
            String s1 = S.substring(0,i);
            String s2 = S.substring(i, S.length());

            System.out.println(s1 + " "+ s2);

            if(isValidString(s1) && isValidString(s2))
            {
                List<String> vs1 = new LinkedList<String>();
                List<String> vs2 = new LinkedList<String>();
                getValidCoord(s1,vs1);
                getValidCoord(s2,vs2);

                Iterator<String> iterator1= vs1.iterator();

                while(iterator1.hasNext())
                {
                    String t1 = iterator1.next();
                    Iterator<String> iterator2 = vs2.iterator();
                    while(iterator2.hasNext())
                    {
                        String t2 = iterator2.next();
                        String newS = "("+t1+", "+ t2+")";
                        coord.add(newS);
                    }
                }

            }
        }
        return coord;
    }

    private void getValidCoord(String s1, List<String> vs1)
    {
        if(s1.charAt(0) == '0')
        {
            if(s1.length() == 1)
            {
                vs1.add(s1);
                return;
            }
            else {
                String temp = s1.substring(0, 1) + "." + s1.substring(1, s1.length());
                vs1.add(temp);
                return;
            }
        }


        vs1.add(s1);

        for(int i=1;i<s1.length();i++)
        {
            String deci = s1.substring(i, s1.length());
//            if(Integer.parseInt(deci) != 0) {
            if(deci.charAt(deci.length()-1) != '0') {
                String temp = s1.substring(0, i) + "." + s1.substring(i, s1.length());
                vs1.add(temp);
            }
            }
        }



    public boolean isValidString(String s)
    {
        if(s.charAt(0) == '0' && s.charAt(s.length()-1)=='0' && s.length() > 1)
            return false;

        return true;
    }


  /*  public static void main(String args[])
    {
        String s = "100";
        Solution sol = new Solution();
        List<String> op = sol.ambiguousCoordinates(s);

        Iterator<String> itr = op.listIterator();
        while(itr.hasNext())
            System.out.print(itr.next() + " ");

    }*/
}

import java.io.IOException;
import java.util.*;

public class pyramid_transition_matrix {

    public boolean pyramidTransition(String bottom, List<String> allowed) {

        HashMap<String,List<String>> hMap = new HashMap<String, List<String>>();
        Iterator<String> itr = allowed.iterator();

        while(itr.hasNext())
        {
            String temp = itr.next();
            List<Character> tempList = null;
            if(!hMap.containsKey(temp.substring(0, 2)))
            {
                hMap.put(temp.substring(0, 2),new ArrayList<String>());
            }

            hMap.get(temp.substring(0, 2)).add(temp.substring(2,3));
        }

        return pyramidTransitionR(bottom,hMap);
    }

    private void getList(String bottom, int index, List<String> ls,StringBuilder sb, HashMap<String, List<String>> hMap) {
        if(index == bottom.length()-1) {
            ls.add(new String(sb));
            return;
        }
        for(String s: hMap.get(bottom.substring(index, index+2)))
        {
            
            sb.append(s);
            getList(bottom,index+1,ls,sb,hMap);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private boolean pyramidTransitionR(String bottom, HashMap<String, List<String>> hMap) {
        StringBuilder sb = new StringBuilder();

        if(bottom.length() == 1)
            return true;


        for(int i=0; i<bottom.length()-1; i++) {
            if (!hMap.containsKey(bottom.substring(i, i + 2)))
                return false;
        }

        List<String> ls = new ArrayList<String>();

        getList(bottom,0,ls,sb, hMap);

        for(String str : ls)
        {
            if(pyramidTransitionR(str,hMap))
                return true;
        }
        return false;

    }


    public static void main(String[] args)throws IOException
    {
        pyramid_transition_matrix obj = new pyramid_transition_matrix();
        String bottom = "CCC";
        List<String> allowed = new ArrayList<>(Arrays.asList("CBB","ACB","ABD","CDB","BDC","CBC","DBA","DBB","CAB","BCB","BCC","BAA","CCD","BDD","DDD","CCA","CAA","CCC","CCB"));
        if(obj.pyramidTransition(bottom,allowed))
        {
            System.out.println("Pyramid possible");
        }
        else {
            System.out.println("Pyramid not possible");
        }
    }
}

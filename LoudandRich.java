import java.util.*;

public class LoudandRich {

    public int[] loudAndRich(int[][] richer, int[] quiet) {

        List<Set<Integer>> rich = new ArrayList<Set<Integer>>();
        for(int i=0; i < quiet.length; i++) {
            Set<Integer> seti = new HashSet<Integer>();
            rich.add(i,seti);
            //rich.set(i, seti);
            rich.get(i).add(i);

        }

        for(int i=0; i < richer.length; i++) {
            rich.get(richer[i][1]).add(richer[i][0]);
        }

        boolean isVisited[] = new boolean[quiet.length];

        int op[] = new int[quiet.length];

        for (int i=0; i < rich.size(); i++)
        {
            Arrays.fill(isVisited,false);
            isVisited[i] = true;
            int min = quiet[i];
            op[i] = i;
            Stack<Integer> st = new Stack<Integer>();
            st.push(i);

            while(!st.isEmpty())
            {
                int curr = st.pop();
                Set<Integer> tSet = rich.get(curr);
                for(Integer j : tSet)
                {
                    if(!isVisited[j])
                    {
                        if(quiet[j] < min)
                        {
                            min = quiet[j];
                            op[i] = j;
                        }
                        st.push(j);
                        isVisited[j] = true;
                    }
                }
            }

        }

        return op;
    }

    public static void main(String[] args)
    {
        LoudandRich obj = new LoudandRich();
        int [][] richer = {
                {1,0}, {2,1},{3,1},{3,7},{4,3},{5,3},{6,3}
        };

        int[] quiet = {3,2,5,4,6,1,7,0};

        int []op = obj.loudAndRich(richer,quiet);

        for(int i : op)
        {
            System.out.println(i);
        }
    }
}

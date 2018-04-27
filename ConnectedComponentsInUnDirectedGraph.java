import java.util.*;

public class ConnectedComponentsInUnDirectedGraph {

    public int countComponents(int n, int[][] edges) {


        /* DFS version
        int []componentCount = new int[n];

        int component = 1;
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();

        for(int j =0; j < edges.length; j++)
        {
            List<Integer> tList = graph.get(edges[j][0]);
            if(tList == null)
            {
                tList = new ArrayList<Integer>();
            }
            tList.add(edges[j][1]);
            graph.put(edges[j][0],tList);
            tList = graph.get(edges[j][1]);
            if(tList == null)
            {
                tList = new ArrayList<Integer>();
            }
            tList.add(edges[j][0]);
            graph.put(edges[j][1],tList);
        }

        System.out.print(graph);

        Stack<Integer> st = new Stack<Integer>();
        for(int i=0; i <n; i++)
        {
            if(componentCount[i] > 0)
                continue;

            st.push(i);

            while(!st.isEmpty())
            {
                int cur = st.pop();
                if(componentCount[cur] > 0)
                    continue;

                componentCount[cur] = component;
                List<Integer> tList = graph.get(cur);

                if(tList != null)
                {
                    ListIterator<Integer> itr= tList.listIterator();
                    while(itr.hasNext())
                    {
                        int t = itr.next();
                        if(componentCount[t] > 0)
                            continue;

                        st.push(t);
                    }
                }

            }
            component++;
        }
        return component-1;
        */

        //Find - Union version

        int roots[] = new int[n];
        int count = n;

        for(int i=0; i < n; i++)
        {
            roots[i] = i;
        }

        for(int[] edge: edges)
        {
            int root1= find(roots, edge[0]);
            int root2= find(roots, edge[1]);

            if(root1 != root2)
            {
                roots[edge[1]] = root1;
                count--;
            }


        }
        return count;
    }

    public int find(int[] roots, int index)
    {
        // return roots[index];

        while(roots[index] != index)
        {
            roots[index] = roots[roots[index]];
            index = roots[index];
        }
        return  index;
    }

    public  static  void main(String []args)
    {
        int n = 5;
        int[][] edges = {{0,1},{2,1},{2,0},{2,4}};
        ConnectedComponentsInUnDirectedGraph obj = new ConnectedComponentsInUnDirectedGraph();
        System.out.println(obj.countComponents(n, edges));
    }
}

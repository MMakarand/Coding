import java.util.*;
import java.lang.*;
import java.io.*;
public class NodesAtEvenDistance {


    public static void main (String[] args) {
        //code
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++)
        {
            int V = sc.nextInt();
            LinkedList<Integer>[] graph;
            graph = new LinkedList[V];

            for(int j =0 ; j < V; j++)
                graph[j] = new LinkedList<Integer>();

            for(int j=0;j < V-1; j++)
            {
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph[u-1].add(v-1);
                graph[v-1].add(u-1);
            }
            for(int j=0;j < V; j++)
                System.out.print(graph[j]);


            //NodesAtEvenDistance obj = new NodesAtEvenDistance();
            System.out.println(getEvenDistanceNodes(graph,V));
        }
    }

    public static int getEvenDistanceNodes(LinkedList<Integer>[] graph, int V)
    {
       int even=0,odd=0;
        int[] isVisited = new int[V];
       class Node{
           public int val;
           public int dist;

           Node(int val, int dist)
           {
               this.val = val;
               this.dist = dist;
           }
       };

       Stack<Node> st = new Stack<Node>();
       st.push(new Node(0,0));

       while(!st.isEmpty())
       {
           Node temp = st.peek();
           st.pop();
           isVisited[temp.val]=1;

           if(temp.dist%2 == 0)
               even++;
           else
               odd++;

           List<Integer> uList = graph[temp.val];
           if(uList.isEmpty())
               continue;
           Iterator<Integer> itr= uList.listIterator();

           while (itr.hasNext())
           {
               Integer nextNode = itr.next();
              if(isVisited[nextNode]==0)
                    st.push(new Node(nextNode, temp.dist+1));
           }
       }
       return (int) (even*(even-1)*0.5 + odd*(odd-1)*0.5);
    }
}

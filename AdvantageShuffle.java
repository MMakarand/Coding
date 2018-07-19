package Contest.WW93;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {

        int []op = new int[A.length];
        Map<Integer,Integer> countMap = new HashMap<>();
        TreeSet<Integer> treeSet= new TreeSet<Integer>();

        for(int i=0; i < A.length; i++)
        {
            treeSet.add(A[i]);
            if(!countMap.containsKey(A[i]))
            {
                countMap.put(A[i],1);
            }
            else{
                countMap.put(A[i], 1 + countMap.get(A[i]));
            }
        }

        TreeSet<Integer> treeTailSet = new TreeSet<>();
        TreeSet<Integer> treeHeadSet = new TreeSet<>();

        boolean gotBigger = false;
        for(int i=0; i<A.length; i++) {
            gotBigger = false;
            treeTailSet = (TreeSet<Integer>) treeSet.tailSet(B[i]+1);

            if (!treeTailSet.isEmpty()) {
                    Iterator<Integer> itr = treeTailSet.iterator();
                    while (itr.hasNext()) {
                        int b = itr.next();
                        if (b > B[i]) {
                            gotBigger = true;
                            op[i] = b;
                            if(countMap.get(b) == 1)
                            {
                                countMap.remove(b);
                                treeSet.remove(b);

                            }
                            else{
                                countMap.put(b, countMap.get(b) - 1);
                            }
                            break;
                        }
                    }
            }
            //gotBigger is false means all element are equal to B[i];
             else if(treeTailSet.isEmpty() || !gotBigger){
                treeHeadSet = (TreeSet<Integer>) treeSet.headSet(B[i]);
                Iterator<Integer> itr = treeHeadSet.iterator();

                while (itr.hasNext()) {
                    int b = itr.next();
                    if (b <= B[i]) {
                       // gotBigger = true;
                        op[i] = b;
                        if(countMap.get(b) == 1)
                        {
                            countMap.remove(b);
                            treeSet.remove(b);

                        }
                        else{
                            countMap.put(b, countMap.get(b) - 1);
                        }
                        break;
                    }
                }
            }
            else{
                //op[i] = A[i];
            }
        }
        return op;
    }

    public static void main(String[] args)throws Exception
    {
       AdvantageShuffle obj = new AdvantageShuffle();
      // int A[] = {2,7,11,15};
       //int B[] = {1,10,4,11};
        //int A[] = {12,24,8,32};
        //int B[] = {13,25,32,11};
        //int A[] = {2,0,4,1,2};
        //int B[] = {1,3,0,0,2};
        int A[] = {15777,7355,6475,15448,18412};
        int B[] = {986,13574,14234,18412,19893};
       int[] op = obj.advantageCount(A,B);

       for(Integer i : op)
            System.out.println(i);
    }
}

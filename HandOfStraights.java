import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {

        boolean result = false;
        if(hand.length % W != 0)
            return result;

        Arrays.sort(hand);

        ArrayList<Integer> handList = new ArrayList<Integer>();
        for(int i=0; i <hand.length; i++)
        {
            handList.add(i,hand[i]);
        }

        while(!handList.isEmpty())
        {
            int start = handList.get(0);
            for(int j= start; j < start+W; j++)
            {
                if(handList.contains(j))
                {
                    int index = handList.indexOf(j);
                    handList.remove(index);
                }
                else{
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args)
    {

        int[] hand = {1,2,3,6,2,3,4,7,8};
        int W = 3;
        HandOfStraights obj =  new HandOfStraights();
        System.out.println(obj.isNStraightHand(hand,W));
    }

}

import java.util.Arrays;
import java.util.Collections;

public class FriendsOfAppropriateAges {
  /*  public int numFriendRequests(int[] ages) {

        Arrays.sort(ages);
        for(int i:ages)
            System.out.print( i + " ");
        int count = 0;
        for(int i=ages.length-1; i>0; i--)
        {
            for(int j=i-1;  j >= 0; j--)
            {
        //i is request sender

                if(ages[i] == ages[j] && isValidRequest(ages[i],ages[j]))
                    count += 2;
                else if(isValidRequest(ages[i],ages[j]))
                    count++;
            }
        }
        System.out.println(count);
    return count;
    }*/
  public int numFriendRequests(int[] ages) {

      Arrays.sort(ages);
      int count = 0;
      for(int i=ages.length-1; i>0; i--)
      {
          int ageA = ages[i];
          for(int j=i-1;  j >= 0; j--)
          {
              //i is request sender

              int ageB = ages[j];

              if((ageB <= (0.5 * ageA)+7) || ageB > ageA || (ageB >100 && ageA < 100))
              {
                  continue;
              }


              if(ages[i] == ages[j])
              {
                  count += 2;
              }
              else{
                  count++;
              }
          }

      }

    return count;
}


    private boolean isValidRequest(int ageA, int ageB) {

        if((ageB <= (0.5 * ageA)+7) || ageB > ageA || (ageB >100 && ageA < 100)) {
            return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        FriendsOfAppropriateAges obj = new FriendsOfAppropriateAges();
        int []arr = {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110};
        System.out.println(obj.numFriendRequests(arr));
    }
}

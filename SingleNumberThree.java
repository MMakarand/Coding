import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SingleNumberThree {
    public int[] singleNumber(int[] nums) {

        int[] result = new int[2];

        for(int i=0; i < 32; i++)
        {
            List<Integer> evenList = new ArrayList<Integer>();
            List<Integer> oddList = new ArrayList<Integer>();


            for(int j=0; j < nums.length; j++)
            {
                if(((nums[j] >> i) & (1)) == 1)
                    oddList.add(nums[j]);
                else
                    evenList.add(nums[j]);


            }

            if(oddList.size() % 2 == 1)
            {
                result[0] = getSingleOccurence(oddList);
                result[1] = getSingleOccurence(evenList);

            }

        }
        return result;
    }

    public int getSingleOccurence(List<Integer> list)
    {
        Iterator<Integer> it = list.iterator();
        int temp=0;
        while(it.hasNext())
        {
            temp ^= it.next();
        }

        return temp;
    }

    public static  void main(String[] args)
    {
        int[] nums = {1, 2, 1, 3, 2, 5};
        SingleNumberThree obj = new SingleNumberThree();
        int []op = obj.singleNumber(nums);
        System.out.println(op[0] + " " + op[1]);

    }
}

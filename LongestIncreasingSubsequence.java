public class LongestIncreasingSubsequence {
    public static void main(String[] args)
    {
        LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
        int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int max = obj.lengthOfLIS(nums);
        System.out.println(max);
    }
    public int lengthOfLIS(int[] nums) {

        int[] tails = new int[nums.length];

        //Binary Search Version
        int size = 0;
        for(int num: nums)
        {
            int i=0, j=size;
            while(i != j)
            {
                int mid = (i + j)/2;
                if(tails[mid] < num)
                {
                    i = mid+1;
                }
                else{
                    j = mid;
                }
            }
            tails[i]=num;
            if(i == size)
                size++;
        }
        return size;

        /* DP Version
        if(nums.length == 0)
            return 0;
        int[] count = new int[nums.length];
        count[nums.length - 1] = 1;

        int max=1, currMax = 1;
        for(int i= nums.length-2; i>=0; i--)
        {
            currMax = 1;
            for(int j= nums.length-1; j >i; j--)
            {
                if(nums[i] < nums[j])
                {
                    if(currMax < 1+ count[j])
                    {
                        currMax = 1+ count[j];
                    }
                }
            }
            count[i] = currMax;
            if(currMax > max)
                max = currMax;
        }
        return max;
        */
    }
}

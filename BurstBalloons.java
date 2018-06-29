public class BurstBalloons
{
    public int maxCoins(int[] nums) {

        if(nums.length == 0)
            return 0;
        int prev = 1, next = 1;
        int D[][] = new int[nums.length][nums.length];

            for(int diff = 0; diff < nums.length; diff++)
            {
             for(int i = 0 ; i < nums.length - diff; i++)
             {
                 int j = i+diff;

                 if(i==0)
                     prev = 1;
                 else
                     prev = nums[i-1];

                 if(j == nums.length-1)
                     next = 1;
                 else
                     next = nums[j+1];

                 for(int k = i; k <= j; k++)
                 {
                     if(k == i)
                     {
                         if(k == j)
                         {
                             D[i][j] = prev * nums[k] * next;
                         }
                         else{
                             D[i][j] = Math.max(D[i][j], (prev * nums[k] * next) + D[k+1][j] );
                         }
                     }
                     else if( k == j)
                     {
                         D[i][j] = Math.max(D[i][j], (prev * nums[k] * next)+ D[i][k-1]);
                     }
                     else{
                         D[i][j] = Math.max(D[i][j], (prev * nums[k] * next)+ D[i][k-1] + D[k+1][j]);
                     }
                 }
             }
            }
            return D[0][nums.length-1];
        }

        public static void main(String []args)
        {
            int []nums= {3,1,5,8};//{3,1 ,5,8};
            BurstBalloons obj = new BurstBalloons();
            System.out.println(obj.maxCoins(nums));

        }
}

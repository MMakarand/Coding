public class CoinChange {
    int[] change;
    public int coinChange(int[] coins, int amount) {

        if(amount < 0)
            return 0;

        change = new int[amount+1];
        for(int i=1; i <=  amount; i++)
            change[i] = -1;


        for(int i=1; i <=  amount; i++) {
            int changeCount = Integer.MAX_VALUE;
            int res = -1;
            for (int coin : coins) {

                if (i - coin >= 0) {
                    res = change[i - coin];
                    if (res < changeCount)
                        changeCount = res + 1;

                }
            }
            change[i] = (changeCount == Integer.MAX_VALUE) ? -1 : changeCount;
        }

        return change[amount];
    }

    public int getMyChange(int[] coins, int amount)
    {
        if(amount < 0)
            return -1;

        if(amount == 0)
            return 0;

        if(change[amount] != -1)
            return change[amount];

        int changeCount = Integer.MAX_VALUE;
        int res = -1;
        for(int coin : coins)
        {

            if(amount - coin >= 0)
            {

                if(change[amount - coin] != -1)
                {
                    res = change[amount - coin];
                }
                else
                {
                    change[amount - coin] =  getMyChange(coins, amount - coin);
                    res = change[amount - coin];
                }
            }
            else
            {
                res = -1;

            }

            if(res >= 0 && res < changeCount)
            {
                changeCount = res+1;
            }
        }
        change[amount] = (changeCount == Integer.MAX_VALUE) ? -1 :changeCount  ;
        return change[amount];
    }
    public static void main(String[] args)
    {
        CoinChange  obj = new CoinChange();
        int  coins[] = {1,2};
        int amount = 10000;
        int ans = obj.coinChange(coins,amount);
        System.out.println(ans);
    }
}

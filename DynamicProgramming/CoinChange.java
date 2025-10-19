package DynamicProgramming;

public class CoinChange {

    public int rec(int[] coins, int amount, int n) {

        //not possible
        if(n< 0)  return -1;

        //no coins needed
        if(amount==0) return 0;

        int take=-1;
        if(amount >= coins[n]) take = rec(coins, amount-coins[n], n);

        int notTake = rec(coins, amount, n-1);

        if(take == -1)  return notTake;
        if(notTake == -1) return take;

        return Math.min(take+1, notTake);
    }

    public int memoization(int[] coins, int amount, int n, int[][] dp) {

        //not possible
        if(n< 0 || amount<0)  return -1;

        //no coins needed
        if(amount==0) return 0;

        if(dp[n][amount]!=-2)   return dp[n][amount];

        int take = rec(coins, amount-coins[n], n);
        int notTake = rec(coins, amount, n-1);

        if(take == -1)  dp[n][amount] = notTake;
        else if(notTake == -1) dp[n][amount] = take;
        else dp[n][amount] = Math.min(take, notTake);

        return dp[n][amount];
    }
}

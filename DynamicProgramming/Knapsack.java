package DynamicProgramming;

import java.util.Arrays;

public class Knapsack {

    public static int knapsackRec(int[] wt, int[] val, int W, int n) {
        if(n<0 || W==0)    return 0;

        int take=0;

        //Take item only if weight is less than max allowed, W= W-wt[i]
        if(wt[n] <= W) take = knapsackRec(wt, val, W-wt[n], n-1) + val[n];

        int notTake = knapsackRec(wt, val, W, n-1);

        return Math.max(take, notTake);
    }

    public static int knapsackMemoization(int[] wt, int[] val, int W, int n, int[][] dp) {
        if(n<0 || W==0)    return 0;

        if(dp[n][W] != -1) return dp[n][W];

        int take=0;

        //Take item only if weight is less than max allowed, W= W-wt[i]
        if(wt[n] <= W) take = knapsackMemoization(wt, val, W-wt[n], n-1, dp) + val[n];

        int notTake = knapsackMemoization(wt, val, W, n-1, dp);

        dp[n][W] =  Math.max(take, notTake);

        return dp[n][W];
    }

    public static int knapsackTabulation(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n][W+1];

        //Handling first element
        for(int w=0;w<=W;w++) {
            if(wt[0] > W)   dp[0][w] = 0;
            else dp[0][w] = val[0];
        }

        for(int i=1;i<n;i++) {
            for(int w=0;w<=W;w++) {
                if(w==0)    dp[i][w] =0;
                //Take an item
                else if(wt[i]<=W)
                    dp[i][W] = Math.max(dp[i-1][W], val[i] + dp[i-1][W-wt[i]]);
                else
                    dp[i][W] = dp[i-1][W];
            }
        }
        return dp[n-1][W];
    }

    static int knapsack(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n][W+1];
        for(int[] d : dp) Arrays.fill(d, -1);

        return knapsackMemoization(wt, val, W, n-1, dp);
    }

    public static void main(String[] args) {

    }


}

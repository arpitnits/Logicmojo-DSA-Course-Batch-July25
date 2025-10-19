package DynamicProgramming;

import java.util.Arrays;

public class ClimbingStairs {

    //Nth --> (N-1)th + (N-2)th

    public static int climbingStairsRec(int n) {
        if(n==1 || n==2)    return 1;

        return climbingStairsRec(n-1) + climbingStairsRec(n-2);
    }

    public static int climbingStairsMemoization(int n, int[] dp) {
        if(n==1 || n==2)    return 1;

        if(dp[n]!=-1) return dp[n];

        dp[n] = climbingStairsMemoization(n-1, dp) + climbingStairsMemoization(n-2, dp);
        return dp[n];
    }

    public static int climbingStairsTabulation(int n, int[] dp) {
        dp[1] = dp[2] = 1;

        for(int i=3;i<=n;i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 6;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        System.out.println(climbingStairsRec(6));
        //System.out.println(climbingStairsMemoization(6, dp));
        System.out.println(climbingStairsTabulation(6, dp));
    }
}

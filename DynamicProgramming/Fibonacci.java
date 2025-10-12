package DynamicProgramming;

public class Fibonacci {

    /*
    F(N) = F(N-1) + F(N-2)

    0, 1, 1, 2, 3, 5, 8, 13, 21...
     */

    public int fibRecursive(int n) {
        //Base Cases
        if(n==0) return 0;
        if(n==1) return 1;

        return fibRecursive(n-1) + fibRecursive(n-2);
    }

    public int fibMemoization(int n, int[] dp) {
        //Base Cases
        if(n==0) return 0;
        if(n==1) return 1;

        if(dp[n]!=-1)   return dp[n]; //avoid recomputing

        dp[n] = fibMemoization(n-1, dp) + fibMemoization(n-2, dp);
        return dp[n];
    }

    public int fibTabulation(int n, int[] dp) {
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2;i<=n;i++) dp[i] = dp[i-1] + dp[i-2];

        return dp[n];
    }
}

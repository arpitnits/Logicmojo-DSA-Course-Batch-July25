package DynamicProgramming;

import java.util.Arrays;

public class FrogJump {

    //O(2^n)
    public static int frogJumpRecursive(int n, int[] heights) {
        if(n<=0)    return 0;
        if(n==1)    return Math.abs(heights[n]-heights[n-1]);

        int oneStep = Math.abs(heights[n] - heights[n-1]) +
                        frogJumpRecursive(n-1, heights);

        int twoStep = Math.abs(heights[n] - heights[n-2]) +
                        frogJumpRecursive(n-2, heights);

        return Math.min(oneStep, twoStep);
    }

    public static int frogJumpMemoization(int n, int[] heights, int[] dp) {
        if(n<=0)    return 0;
        if(n==1)    return Math.abs(heights[n]-heights[n-1]);

        if(dp[n]!=-1)   return dp[n];

        int oneStep = Math.abs(heights[n] - heights[n-1]) +
                frogJumpMemoization(n-1, heights, dp);

        int twoStep = Math.abs(heights[n] - heights[n-2]) +
                frogJumpMemoization(n-2, heights, dp);

        dp[n] = Math.min(oneStep, twoStep);
        return dp[n];
    }

    public static int frogJumpTabulation(int n, int[] heights, int[] dp) {
        dp[0] = 0;
        dp[1] = Math.abs(heights[1]-heights[0]);

        for(int i=2;i<n;i++) {
            int oneStep = Math.abs(heights[i] - heights[i-1]) +
                    dp[i-1];

            int twoStep = Math.abs(heights[i] - heights[i-2]) +
                    dp[i-2];

            dp[i] = Math.min(oneStep, twoStep);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int n = 4;
        int[] height = {10,20,30,10};

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        System.out.println(frogJumpRecursive(3, height));
        System.out.println(frogJumpMemoization(3, height, dp));
    }
}

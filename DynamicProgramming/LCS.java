package DynamicProgramming;

public class LCS {

    public int lcsRecursive(String text1, String text2, int n, int m) {
        if(n<0 || m<0)  return 0;

        //If both equal
        if(text1.charAt(n) == text2.charAt(m))
            return lcsRecursive(text1, text2, n-1, m-1) + 1;

        return Math.max(
                lcsRecursive(text1, text2, n, m-1),
                lcsRecursive(text1, text2, n-1, m)
        );
    }

    public int lcsMemoization(String text1, String text2, int n, int m, int[][] dp) {
        if(n<0 || m<0)  return 0;

        if(dp[n][m] !=-1)   return dp[n][m];

        //If both equal
        if(text1.charAt(n) == text2.charAt(m))
            dp[n][m] = lcsRecursive(text1, text2, n-1, m-1) + 1;

        else
            dp[n][m] = Math.max(
                    lcsMemoization(text1, text2, n, m-1, dp),
                    lcsMemoization(text1, text2, n-1, m, dp)
        );

        return dp[n][m];
    }
}

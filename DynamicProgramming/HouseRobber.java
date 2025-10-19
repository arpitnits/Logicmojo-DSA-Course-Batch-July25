package DynamicProgramming;

public class HouseRobber {

    //rob from left --> right
    public int robRecursive(int[] nums, int currIndex, int n) {
        if(currIndex > n-1) return 0; //going out of window

        //include currentHouse
        int include = nums[currIndex] + robRecursive(nums, currIndex+2, n);

        //ignore currentHouse
        int exclude = robRecursive(nums, currIndex+1, n);

        return Math.max(include, exclude);
    }

    //rob from left --> right
    public int robMemoization(int[] nums, int currIndex, int n, int[] dp) {
        if(currIndex > n-1) return 0; //going out of window

        if(dp[currIndex]!=-1)   return dp[currIndex];

        //include currentHouse
        int include = nums[currIndex] + robMemoization(nums, currIndex+2, n, dp);

        //ignore currentHouse
        int exclude = robMemoization(nums, currIndex+1, n, dp);

        dp[currIndex] = Math.max(include, exclude);
        return dp[currIndex];
    }

    public int robTabulation(int[] nums, int n, int[] dp) {
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for(int i=2;i<n;i++) {
            //exclude, include
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }
}

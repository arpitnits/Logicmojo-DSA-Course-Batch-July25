package DynamicProgramming;

public class LIS {

    //4-->6-->7-->11

    static int ans = 1;



    //n=7
    /*
    n=7
    n=6

     */
    public static int LISRecursive(int[] arr, int n) {
        if(n==0)    return 1;

        int lis =1;
        for(int i=0;i<n;i++) {
            if(arr[i] < arr[n]) {
                lis = Math.max(lis, LISRecursive(arr, i));
            }
        }

        LISRecursive(arr, n-1);
        ans = Math.max(ans, lis);
        return lis;
    }


    public static int LISRecursive(int[] arr, int n, int[] dp) {
        if(n==0)    return 1;

        if(dp[n]!=-1)   return dp[n]; //get and use (avoid recalculation)

        int lis =1;
        for(int i=0;i<n;i++) {
            if(arr[i] < arr[n]) {
                lis = Math.max(lis, LISRecursive(arr, i));
            }
        }

        LISRecursive(arr, n-1);
        ans = Math.max(ans, lis);
        dp[n] = lis;  //save
        return lis;
    }

    public static int LISTabulation(int[] arr, int n, int[] dp) {
        for(int i=0;i<n;i++) {
            dp[i] = 1;

            //Check all prev number in array
            for(int j=i-1;j>=0;j--) {
                if(arr[j] < arr[i])  {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}

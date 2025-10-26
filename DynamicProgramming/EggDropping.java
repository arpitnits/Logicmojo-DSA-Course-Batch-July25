package DynamicProgramming;

import java.util.Arrays;

public class EggDropping {

    public int recursive(int eggs, int floor) {
        //Only 1 egg remaining
        if(eggs==1) return floor;

        //Only 1 floor or 0 floor remaning
        if(floor==1 || floor==0)    return floor;

        int res = Integer.MAX_VALUE;
        for(int f=1;f<=floor;f++) {
            int eggBreak = recursive(eggs-1, floor-1);//check all below floors
            int notBreak = recursive(eggs, floor - f); //check all above floors

            res = Math.min(res, Math.max(eggBreak, notBreak));
        }
        return res+1;
    }
    // K floor
    // N eggs
    // O(K^n)

    public static int memoization(int eggs, int floor, int[][] dp) {
        //Only 1 egg remaining
        if(eggs==1) return floor;

        //Only 1 floor or 0 floor remaining
        if(floor==1 || floor==0)    return floor;

        if(dp[eggs][floor]!=-1) return dp[eggs][floor];

        int res = Integer.MAX_VALUE;
        for(int f=1;f<=floor;f++) {
            int eggBreak = memoization(eggs-1, f-1, dp);//check all below floors
            int notBreak = memoization(eggs, floor - f, dp); //check all above floors

            res = Math.min(res, Math.max(eggBreak, notBreak));
        }
        dp[eggs][floor] = res+1;
        return dp[eggs][floor];
    }
    //TC = O(N*K^2)

    public static void main(String[] args) {
        int eggs=3;
        int floors=4;
        int[][] dp = new int[eggs+1][floors+1];

        for(int[] temp : dp)
            Arrays.fill(temp, -1);

        System.out.println(memoization(eggs, floors, dp));
    }
}

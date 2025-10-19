package DynamicProgramming;

public class FrogJumpII {

    public int maxJump(int[] stones) {
        int maxJump=0;
        int n = stones.length;

        if(n==2)    return Math.abs(stones[1]-stones[0]);

        int j=2;
        while(j<n) {
            maxJump = Math.max(maxJump, stones[j]-stones[j-2]);
            j++;
        }
        return maxJump;
    }
}

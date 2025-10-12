package Greedy;

import java.util.Arrays;

public class MinimumPlatform {

    public int minPlatform(int arr[], int dep[]) {

        int i=0,j=0, n= arr.length;

        Arrays.sort(arr);
        Arrays.sort(dep); //O(NlogN)
        int platform=0, minPlatform=0;

        //O(N)
        while(i<n && j<n) {
            //If arrival is lesser than departure, then increase the required platform
            if(arr[i] <= dep[j]) {
                platform++;
                i++;
            } else {
                platform--;
                j++;
            }
            minPlatform = Math.max(minPlatform, platform);
        }
        return minPlatform;
    }
}

package by.leetCode75;

import java.util.ArrayList;
import java.util.List;

public class Solution75_1 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> newCandies = new ArrayList<>();
        int maxCandies = 0;
        for (int i: candies){
            if (i >= maxCandies)
                maxCandies = i;
        }
        for (int i: candies){
            newCandies.add((i + extraCandies) >= maxCandies);
        }
        return newCandies;
    }
}

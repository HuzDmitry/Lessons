package interview75;

import by.leetCode75.Solution75_1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Solution71_1Test {
    private static Stream<Arguments>next(){
        return Stream.of(
                Arguments.of(new int[] {2,3,5,1,3}, 3, Arrays.asList(true, true, true, false, true)),
                Arguments.of(new int[] {4,2,1,1,2}, 1, Arrays.asList(true, false, false, false, false)),
                Arguments.of(new int[] {12,1,12}, 10, Arrays.asList(true, false, true))
        );
    }
    @ParameterizedTest
    @MethodSource("next")
    public void kidsWitchCandiesTest(int[]candies, int extraCandies, List<Boolean> result){
        Solution75_1 solution751 = new Solution75_1();
        List<Boolean> out = solution751.kidsWithCandies(candies,extraCandies);
        Assertions.assertEquals(result,out);
    }
}

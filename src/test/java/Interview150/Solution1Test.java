package Interview150;

import by.Interview150.Solution1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.stream.Stream;

public class Solution1Test {
    private static Stream<Arguments> next(){
        return Stream.of(
                Arguments.of(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3,new int[]{1,2,2,3,5,6}),
                Arguments.of(new int[]{1},1,new int[]{},0,new int[]{1}),
                Arguments.of(new int[]{0},0,new int[]{1},1,new int[]{1}),
                Arguments.of(new int[]{0,0,0,0,0},0,new int[]{1,2,3,4,5},5,new int[]{1,2,3,4,5})
        );
    }
    @ParameterizedTest
    @MethodSource("next")
    public void merge1Test(int [] nums1,int m,int[] nums2,int n,int [] out){
        Solution1 sol = new Solution1();
        sol.merge1(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
        Assertions.assertArrayEquals(out,nums1);
    }


}

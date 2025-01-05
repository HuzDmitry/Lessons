package by.Interview150;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m+n;
        while (n>0 && m>0){
            nums1[k-1] = nums2[n-1];
            k--;
            n--;

        }
        Arrays.sort(nums1);
        if (m==0) nums1[m] = nums2[n-1];
    }
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }
    }
}

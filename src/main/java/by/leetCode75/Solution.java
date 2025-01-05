package by.leetCode75;

import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m1 = word1.length();
        int m2 = word2.length();
        int i = 0;
        int j = 0;

        StringBuilder result = new StringBuilder();
        while (i < m1 || j < m2) {
            if (i < m1) {
                result.append(word1.charAt(i++));
            }
            if (j < m2) {
                result.append(word2.charAt(j++));
            }
        }

        return result.toString();
    }
}
class Main1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mergeAlternately("abc", "rde"));
        Runtime run = Runtime.getRuntime();
        System.out.println(run.freeMemory()/1024/1024);
        System.out.println(run.maxMemory()/1024/1024);
        System.out.println(run.totalMemory()/1024/1024);
//        try {
//            Process proc = run.exec("notepad");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(Arrays.stream(Thread.currentThread().getStackTrace()).toList());
//        Package[] pac = Package.getPackages();
//        for (int i = 0; i < pac.length; i++){
//            System.out.println(
//                pac[i].getName()
//
//            );
//        }


    }
}

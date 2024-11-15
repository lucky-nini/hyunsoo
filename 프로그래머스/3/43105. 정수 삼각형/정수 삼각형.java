import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] dp = new int[triangle.length][];
        for (int i=0; i<triangle.length; i++) {
            dp[i] = new int[i+1];
        }
        
        dp[0][0] = triangle[0][0];
        
        for (int r=1; r<dp.length; r++) {
            for (int c=0; c<=r; c++) {
                int maxVal = 0;
                if (c>0) {
                    maxVal = Math.max(dp[r-1][c-1]+triangle[r][c], maxVal);
                }
                if (c<r) {
                    maxVal = Math.max(dp[r-1][c]+triangle[r][c], maxVal);
                }
                dp[r][c] = maxVal;
            }
        }
        
        for (int c=0; c<dp[dp.length-1].length; c++) {
            answer = Math.max(dp[dp.length-1][c], answer);
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = Integer.MAX_VALUE;
        
        int answer = INF;
        
        int[][] cost = new int[n+1][n+1];
        for (int r=1; r<n+1; r++) {
            for (int c=1; c<n+1; c++) {
                if (r==c) cost[r][c] = 0;
                else cost[r][c] = INF;
            }
        }
        
        for (int r=0; r<fares.length; r++) {
            cost[fares[r][0]][fares[r][1]] = fares[r][2];
            cost[fares[r][1]][fares[r][0]] = fares[r][2];
        }
        
        for (int k=1; k<n+1; k++) {
            for (int i=1; i<n+1; i++) {
                for (int j=1; j<n+1; j++) {
                    cost[i][j] = Math.min(cost[i][j], (cost[i][k]!=INF && cost[k][j]!=INF) ? cost[i][k]+cost[k][j] : INF);
                    cost[j][i] = cost[i][j];
                }
            }
        }
        
        for (int i=1; i<n+1; i++) {
            answer = Math.min(answer, cost[i][s]+cost[i][a]+cost[i][b]);
        }
        
        answer = Math.min(answer, cost[s][a]+cost[s][b]);
        
        
        return answer;
    }
}
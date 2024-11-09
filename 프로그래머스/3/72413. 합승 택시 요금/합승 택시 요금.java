import java.util.*;

class Solution {
    public static class Road {
        int rNum, val;
        Road(int rNum, int val) {
            this.rNum = rNum;
            this.val = val;
        }
    }
    
    static List<Road>[] fareList;
    static int[] fromS;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        fareList = new ArrayList[n+1];
        for (int i=1; i<n+1; i++) {
            fareList[i] = new ArrayList<>();
        }
        
        for (int f=0; f<fares.length; f++) {
            fareList[fares[f][0]].add(new Road(fares[f][1], fares[f][2]));
            fareList[fares[f][1]].add(new Road(fares[f][0], fares[f][2]));
        }
        
        fromS = new int[n+1]; // 0은 비울게용 s까지 최저비용 구하기
        for (int i=1; i<n+1; i++) {
            if (i!=s) fromS[i] = Integer.MAX_VALUE;
        }
        
        for (Road r : fareList[s]) {
            fromS[r.rNum] = r.val;
            dfs(r.rNum);
        }
        
        System.out.println(Arrays.toString(fromS));
        
        return answer;
    }
    
    static void dfs(int idx) {
        
    }
}
import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] costs = new int[N+1][3];
    for (int r=1; r<=N; r++) {
      String[] strArr = br.readLine().split(" ");
      for (int c=0; c<3; c++) {
        costs[r][c] = Integer.parseInt(strArr[c]);
      }
    }
    // 입력받기 끝!

    int[][] dp = new int[N+1][3]; // i번째 집을 j(0: R, 1: G, 2: B)로 칠했을 때 최소 비용
    int minCost = Integer.MAX_VALUE;
    for (int ic=0; ic<3; ic++) { // 첫번째 집의 색이 0, 1, 2 일 때 순회
      for (int color=0; color<3; color++) {
        if (ic==color) dp[1][color] = costs[1][color]; // 첫번째 집 색 무조건 ic로 설정! 아니면 값 최대
        else dp[1][color] = 1000000;
      }

      for (int h=2; h<=N; h++) {
        dp[h][0] = Math.min(dp[h-1][1], dp[h-1][2]) + costs[h][0];
        dp[h][1] = Math.min(dp[h-1][0], dp[h-1][2]) + costs[h][1];
        dp[h][2] = Math.min(dp[h-1][0], dp[h-1][1]) + costs[h][2];
      }

      for (int color=0; color<3; color++) {
        if (color!=ic) { // 첫번째 색이랑 다를 때만 보기
          minCost = Math.min(minCost, dp[N][color]);
        }
      }
    }

    
    System.out.println(minCost);
  }
}
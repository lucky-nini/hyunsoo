import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] counsel = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			counsel[i][0] = Integer.parseInt(st.nextToken()); // 기간
			counsel[i][1] = Integer.parseInt(st.nextToken()); // 가격
		}

		int[] dp = new int[N];

		dp[N - 1] = counsel[N - 1][0] > N - (N - 1) ? 0 : counsel[N - 1][1];

		for (int day = N - 2; day >= 0; day--) {
			dp[day] = day + counsel[day][0] < N ? dp[day + counsel[day][0]] : 0;
			dp[day] += counsel[day][0] > N - day ? 0 : counsel[day][1];
			dp[day] = Math.max(dp[day+1], dp[day]);
		}
		
		int maxCost = 0;
		for (int i=0; i<N; i++) {
			maxCost = Math.max(maxCost, dp[i]);
		}

		System.out.println(maxCost);
	}
}
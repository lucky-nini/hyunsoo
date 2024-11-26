import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n];
		

		dp[0] = nums[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
		}

		int maxVal = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			maxVal = Math.max(maxVal, dp[i]);
		}

		System.out.println(maxVal);
	}
}
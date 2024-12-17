import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] boxes = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			boxes[i] = Integer.parseInt(st.nextToken());
		}

		// 무조건 i번째 상자를 사용할 때 현재까지 최대 개수
		int[] dp = new int[n];
		int maxVal = 0;
		for (int i = 0; i < n; i++) {
			int maxBox = 0;
			for (int j = 0; j < i; j++) {
				if (boxes[j] < boxes[i]) {
					maxBox = Math.max(maxBox, dp[j]);
				}
			}
			dp[i] = maxBox + 1;
			maxVal = Math.max(maxVal, dp[i]);
		}
		
		System.out.println(maxVal);
	}
}
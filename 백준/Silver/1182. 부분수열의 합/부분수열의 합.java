import java.util.*;
import java.io.*;

public class Main {
	static int[] nums;
	static int S;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		findPartial(0, 0, 0);
		System.out.println(cnt);
	}

	public static void findPartial(int idx, int sum, int selectCnt) {
		if (idx == nums.length) {
			if (sum == S && selectCnt > 0) {
				cnt++;
			}
			return;
		}
		findPartial(idx + 1, sum + nums[idx], selectCnt + 1);
		findPartial(idx + 1, sum, selectCnt);
	}
}
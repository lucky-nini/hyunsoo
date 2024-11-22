import java.io.*;
import java.util.*;

public class Main {
	static int maxVal = Integer.MIN_VALUE;
	static int minVal = Integer.MAX_VALUE;

	static int N;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int[] cal = new int[4]; // 덧, 뺄, 곱, 나
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 4; i++) {
			if (cal[i] > 0) {
				calculate(0, i, cal, nums[0]);
			}
		}

		System.out.println(maxVal);
		System.out.println(minVal);
	}

	public static void calculate(int idx, int calNum, int[] cal, int result) {
		cal[calNum] -= 1;
		if (calNum == 0) {
			result += nums[idx + 1];
		} else if (calNum == 1) {
			result -= nums[idx + 1];
		} else if (calNum == 2) {
			result *= nums[idx + 1];
		} else if (calNum == 3) {
			if (result < 0 && nums[idx + 1] > 0) {
				result = ((-1) * result / nums[idx + 1]) * (-1);
			} else
				result /= nums[idx + 1];
		}
		if (idx + 1 == N - 1) {
			cal[calNum]++;
			maxVal = Math.max(maxVal, result);
			minVal = Math.min(minVal, result);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (cal[i] > 0) {
				calculate(idx + 1, i, cal, result);
			}
		}
		cal[calNum]++;
	}
}
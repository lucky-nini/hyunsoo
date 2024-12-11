import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 현재 휴게소의 개수
		int M = Integer.parseInt(st.nextToken()); // 더 지으려고 하는 휴게소의 개수
		int L = Integer.parseInt(st.nextToken()); // 고속도로의 길이
		int[] restArea = new int[N + 2];
		restArea[0] = 0;
		restArea[N + 1] = L;
		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				restArea[i] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(restArea);

		int start = 1;
		int end = 1;
		for (int i = 0; i < N + 1; i++) {
			end = Math.max(end, restArea[i + 1] - restArea[i]);
		}

		int answer = end;

		while (start <= end) {
			int mid = (start + end) / 2;

			int used = 0; // 새로 세운 휴게소
			for (int i = 0; i < N + 1; i++) {
				int dist = restArea[i + 1] - restArea[i];
				if (dist > mid) {
					used += (dist - 1) / mid; // 필요한 휴게소 개수 계산
				}
			}

			if (used <= M ) {
				answer = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}

		System.out.println(answer);
	}
}
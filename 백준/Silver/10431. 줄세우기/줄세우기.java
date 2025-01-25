import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int cnt;
	static List<Integer> line;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < P; tc++) {
			cnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] order = new int[20];
			for (int i = 0; i < 20; i++) {
				order[i] = Integer.parseInt(st.nextToken());
			}

			line = new ArrayList<>();
			line.add(order[0]);

			for (int i = 1; i < 20; i++) {
				makeOrder(i, order[i]);
			}
			sb.append(N + " " + cnt + "\n");
		}
		System.out.println(sb);
	}

	public static void makeOrder(int idx, int val) {
		// 전체 리스트에서 내가 들어갈 위치를 찾는다
		int start = 0;
		int end = line.size() - 1;
		int answer = end + 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (line.get(mid) >= val) {
				end = mid - 1;
				answer = mid;
			} else {
				start = mid + 1;
			}
		}
		line.add(answer, val);
		cnt += line.size() - 1 - answer;
	}
}
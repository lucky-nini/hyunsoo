import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static class Tower {
		int idx, height;

		Tower(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<Tower> stack = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			while (!stack.isEmpty() && stack.peek().height < h) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				sb.append("0 ");
			} else {
				Tower t = stack.peek();
				sb.append(t.idx + 1 + " ");
			}
			stack.push(new Tower(i, h));
		}

		System.out.println(sb);
	}
}
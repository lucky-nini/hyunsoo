import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken()); // 전체 층수
		int S = Integer.parseInt(st.nextToken()); // 지금 있는 곳
		int G = Integer.parseInt(st.nextToken()); // 도착 위치
		int U = Integer.parseInt(st.nextToken()); // 위로 U층
		int D = Integer.parseInt(st.nextToken()); // 아래로 D층

		int[] move = new int[F]; // 0층부터 F-1층
		for (int i = 0; i < F; i++) {
			move[i] = Integer.MAX_VALUE;
		}
		move[S-1] = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.add(S-1);

		while (!queue.isEmpty()) {
			int top = queue.poll();
			if (top + U < F && move[top + U] == Integer.MAX_VALUE) {
				queue.offer(top + U);
				move[top + U] = move[top] + 1;
			}
			if (top - D >= 0 && move[top - D] == Integer.MAX_VALUE) {
				queue.offer(top - D);
				move[top - D] = move[top] + 1;
			}
		}
		System.out.println(move[G - 1] == Integer.MAX_VALUE ? "use the stairs" : move[G - 1]);
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static boolean[] visited;

	public static class Node {
		int x, time;

		Node(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		System.out.println(moveSoobin());
	}

	public static int moveSoobin() {
		Deque<Node> deque = new LinkedList<>();
		deque.offer(new Node(N, 0));

		while (!deque.isEmpty()) {
			Node node = deque.poll();
			if (node.x == K) {
				return node.time;
			}

			if (visited[node.x])
				continue;
			visited[node.x] = true;

			if (node.x * 2 <= 100000) {
				deque.offerFirst(new Node(node.x * 2, node.time));
			}
			if (node.x + 1 <= 100000) {
				deque.offerLast(new Node(node.x + 1, node.time + 1));
			}
			if (node.x - 1 >= 0) {
				deque.offerLast(new Node(node.x - 1, node.time + 1));
			}
		}

		return -1;
	}
}
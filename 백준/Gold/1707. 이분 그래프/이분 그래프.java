import java.io.*;
import java.util.*;

public class Main {
	static boolean isBinary = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < K; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수

			List<Integer>[] graph = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				graph[a].add(b);
				graph[b].add(a);
			}

			sb.append(isBinary(graph, V) ? "YES\n" : "NO\n");
		}
		System.out.println(sb);
	}

	public static boolean isBinary(List<Integer>[] graph, int V) {
		int[] team = new int[V];

		for (int i = 0; i < V; i++) {
			if (team[i] == 0) {
				if (!bfs(graph, team, i)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean bfs(List<Integer>[] graph, int[] team, int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		team[start] = 1;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int connected : graph[cur]) {
				if (team[connected] == 0) {
					team[connected] = -team[cur];
					queue.offer(connected);
				} else if (team[connected] == team[cur]) {
					return false;
				}
			}
		}
		return true;
	}
}
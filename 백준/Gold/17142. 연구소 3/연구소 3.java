import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int emptyCnt = 0;
	static int minTime = Integer.MAX_VALUE;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] map;

	static List<Integer[]> virus = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2)
					virus.add(new Integer[] { r, c });
				else if (map[r][c] == 0)
					emptyCnt++;
			}
		}

		boolean[] activeVirus = new boolean[virus.size()];
		activateVirus(activeVirus, 0, 0);

		System.out.println(minTime != Integer.MAX_VALUE ? minTime : -1);
	}

	public static void activateVirus(boolean[] active, int idx, int cnt) {
		if (cnt == M) {
			checkMap(active);
			return;
		}
		if (idx == virus.size())
			return;

		active[idx] = true;
		activateVirus(active, idx + 1, cnt + 1);
		active[idx] = false;
		activateVirus(active, idx + 1, cnt);
	}

	public static void checkMap(boolean[] activeVirus) {
		Queue<Integer[]> queue = new LinkedList<>();
		int[][] copyMap = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copyMap[r][c] = map[r][c];
			}
		}
		boolean[][] visited = new boolean[N][N];
		int filled = 0;
		int time = 0;

		for (int i = 0; i < activeVirus.length; i++) {
			if (activeVirus[i]) {
				queue.offer(virus.get(i));
				copyMap[virus.get(i)[0]][virus.get(i)[1]] = 3;
			}
		}

		while (!queue.isEmpty()) {
			if (filled == emptyCnt)
				break;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer[] top = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = top[0] + dr[d];
					int nc = top[1] + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
							&& (copyMap[nr][nc] == 0 || copyMap[nr][nc] == 2)) {
						visited[nr][nc] = true;
						if (copyMap[nr][nc] == 0)
							filled++;
						queue.offer(new Integer[] { nr, nc });
					}
				}
			}
			time++;
			if (time >= minTime)
				return;
		}

		if (filled == emptyCnt)
			minTime = Math.min(minTime, time);
	}
}
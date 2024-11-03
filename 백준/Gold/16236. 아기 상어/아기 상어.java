import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static Shark shark;

	static int time = 0;

	static class Shark {
		int r, c, s;
		int cnt = 0;

		Shark(int r, int c) {
			this.r = r;
			this.c = c;
			this.s = 2;
		}

		void move(int r, int c, int s) {
			this.cnt++;
			if (this.cnt == this.s) {
				this.cnt = 0;
				this.s++;
			}
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[" + r + ", " + c + ", " + s + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					shark = new Shark(r, c);
					map[r][c] = 0;
				}
			}
		}

		while (true) {
			if (!moveShark())
				break;
		}

		System.out.println(time);
	}

	public static boolean moveShark() {
		int minDist = N * N;
		Queue<Integer[]> queue = new LinkedList<>(); // r, c, dist
		List<Integer[]> fishList = new ArrayList<>();
		boolean[][] visited = new boolean[N][N];

		queue.add(new Integer[] { shark.r, shark.c, 0 });
		visited[shark.r][shark.c] = true;
		outer: while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer[] top = queue.poll();
				if (top[2] >= minDist) {
					break outer;
				}
				for (int d = 0; d < 4; d++) {
					int nr = top[0] + dr[d];
					int nc = top[1] + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
						if (map[nr][nc] < shark.s && map[nr][nc] > 0) {
							minDist = top[2] + 1;
							fishList.add(new Integer[] { nr, nc, map[nr][nc] });
						} else if (map[nr][nc] == shark.s || map[nr][nc] == 0) {
							queue.add(new Integer[] { nr, nc, top[2] + 1 });
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		if (fishList.size() > 0) {
			time += minDist;
			Collections.sort(fishList, new Comparator<Integer[]>() {
				@Override
				public int compare(Integer[] i1, Integer[] i2) {
					if (i1[0] != i2[0])
						return i1[0] - i2[0];
					else
						return i1[1] - i2[1];
				}
			});
			Integer[] fish = fishList.get(0);
			shark.move(fish[0], fish[1], map[fish[0]][fish[1]]);
			map[fish[0]][fish[1]] = 0;
			return true;
		} else
			return false;
	}
}
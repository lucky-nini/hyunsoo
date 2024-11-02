import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static class UnionFind {
		int[] parent, rank;

		int change1D(int[] coor) {
			return coor[0] * N + coor[1];
		}

		int[] change2D(int n) {
			int r = n / N;
			int c = n % N;
			return new int[] { r, c };
		}

		UnionFind() {
			this.parent = new int[N * N];
			this.rank = new int[N * N];
			for (int i = 0; i < N * N; i++) {
				this.parent[i] = i;
			}
		}

		int find(int[] coor) {
			int idx = change1D(coor);
			if (parent[idx] != idx) {
				parent[idx] = find(change2D(parent[idx]));
			}
			return parent[idx];
		}

		void union(int[] coor1, int[] coor2) {
			int root1 = find(coor1);
			int root2 = find(coor2);

			if (root1 != root2) {
				if (rank[root1] > rank[root2]) {
					parent[root2] = root1;
				} else if (rank[root1] < rank[root2]) {
					parent[root1] = root2;
				} else {
					rank[root1]++;
					parent[root2] = root1;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int day = 0;
		while (true) {
			UnionFind uf = new UnionFind();
			boolean[][] visited = new boolean[N][N];
			boolean moved = false;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					visited[r][c] = true;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
							int diff = Math.abs(map[r][c] - map[nr][nc]);
							if (diff >= L && diff <= R) {
								uf.union(new int[] { r, c }, new int[] { nr, nc });
								moved = true;
							}
						}
					}
				}
			}
			if (!moved) break;
			
			Map<Integer, List<int[]>> groups = new HashMap<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int root = uf.find(new int[]{i, j});
                    groups.putIfAbsent(root, new ArrayList<>());
                    groups.get(root).add(new int[]{i, j});
                }
            }

            for (Map.Entry<Integer, List<int[]>> entry : groups.entrySet()) {
                int sum = 0;
                int cnt = entry.getValue().size();
                for (int[] coor : entry.getValue()) {
                    sum += map[coor[0]][coor[1]];
                }
                int newValue = sum / cnt;
                for (int[] coor : entry.getValue()) {
                    map[coor[0]][coor[1]] = newValue;
                }
            }
            day++;
        }
        System.out.println(day);
	}
}
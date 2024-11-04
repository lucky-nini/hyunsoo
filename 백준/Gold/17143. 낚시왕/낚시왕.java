import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[] dr = { -1, 0, 1, 0 }; // 상 좌 하 우
	static int[] dc = { 0, -1, 0, 1 };

	static class Shark {
		int r, c, d, s, z;

		Shark(int r, int c, int d, int s, int z) {
			this.r = r;
			this.c = c;
			this.d = d; // 이동 방향
			this.s = s; // 속력
			this.z = z; // 크기
		}

		void move() {
			int cnt = 0;
			int nr = r - dr[this.d];
			int nc = c - dc[this.d];
			while (cnt <= this.s) {
				nr += dr[this.d];
				nc += dc[this.d];

				if (this.d == 0 && nr == 0)
					this.d = 2;
				else if (this.d == 2 && nr == R - 1)
					this.d = 0;
				else if (this.d == 1 && nc == 0)
					this.d = 3;
				else if (this.d == 3 && nc == C - 1)
					this.d = 1;
				cnt++;
			}
			this.r = nr;
			this.c = nc;
		}

		@Override
		public String toString() {
			return "[" + r + ", " + c + ", " + d + ", " + s + ", " + z + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int total = 0;
		List<Shark> sharks = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			sharks.add(new Shark(r, c, d == 0 ? 0 : d == 1 ? 2 : d == 2 ? 3 : 1, s, z));
		}
		Collections.sort(sharks, new Comparator<Shark>() {
			@Override
			public int compare(Shark s1, Shark s2) {
				if (s1.c != s2.c)
					return s1.c - s2.c;
				if (s1.r != s2.r)
					return s1.r - s2.r;
				return s2.z - s1.z;
			}
		});
		for (int man = 0; man < C; man++) {
			for (Shark s : sharks) {
				if (s.c == man) {
					total += s.z;
					sharks.remove(s);
					break;
				}
			}
			for (Shark s : sharks) {
				s.move();
			}
			Collections.sort(sharks, new Comparator<Shark>() {
				@Override
				public int compare(Shark s1, Shark s2) {
					if (s1.c != s2.c)
						return s1.c - s2.c;
					if (s1.r != s2.r)
						return s1.r - s2.r;
					return s2.z - s1.z;
				}
			});
			int idx = 0;
			if (sharks.size() > 0) {
				int cr = sharks.get(idx).r;
				int cc = sharks.get(idx).c;
				while (true) {
					idx++;
					if (idx == sharks.size())
						break;
					if (sharks.get(idx).r == cr && sharks.get(idx).c == cc) {
						sharks.remove(idx);
						idx--;
					} else {
						cr = sharks.get(idx).r;
						cc = sharks.get(idx).c;
					}
				}
			}
		}

		System.out.println(total);
	}
}
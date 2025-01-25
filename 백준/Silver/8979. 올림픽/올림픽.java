import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static class Nation implements Comparable<Nation> {
		int idx, g, s, b, rank;

		Nation(int idx, int g, int s, int b) {
			this.idx = idx;
			this.g = g;
			this.s = s;
			this.b = b;
		}

		@Override
		public int compareTo(Nation o) {
			if (o.g != this.g)
				return o.g - this.g;
			else if (o.s != this.s)
				return o.s - this.s;
			return o.b - this.b;
		}

		@Override
		public String toString() {
			return idx + ", [" + g + ", " + s + ", " + b + ", score: " + rank + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Nation[] nations = new Nation[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nations[i] = new Nation(idx, g, s, b);
		}
		Arrays.sort(nations);

		nations[0].rank = 1;
		int diff = 1;
		int score = -1;
		for (int i = 0; i < N; i++) {
			if (i != 0 && nations[i - 1].g == nations[i].g && nations[i - 1].s == nations[i].s
					&& nations[i - 1].b == nations[i].b) {
				nations[i].rank = nations[i - 1].rank;
				diff++;
			} else if (i != 0) {
				nations[i].rank = nations[i - 1].rank + diff;
				diff = 1;
			}
			if (nations[i].idx == K) {
				score = nations[i].rank;
				break;
			}
		}

		System.out.println(score);
	}
}
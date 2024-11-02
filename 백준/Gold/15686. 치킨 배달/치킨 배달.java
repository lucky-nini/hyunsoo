import java.io.*;
import java.util.*;

public class Main {
	static int M;
	static int minChickenDist = Integer.MAX_VALUE;

	static List<Chicken> chickens = new ArrayList<>();
	static List<House> houses = new ArrayList<>();

	public static class Chicken {
		int r, c;

		Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}

	public static class House {
		int r, c;
		Map<Integer, Integer> chickenDist = new HashMap<>();
		List<Integer> keySet;

		House(int r, int c) {
			this.r = r;
			this.c = c;
		}

		void addChicken(int idx, Chicken chicken) {
			int dist = Math.abs(this.r - chicken.r) + Math.abs(this.c - chicken.c);
			chickenDist.put(idx, dist);
		}

		void finishChicken() {
			this.keySet = new ArrayList<>(chickenDist.keySet());
			Collections.sort(this.keySet, new Comparator<Integer>() {
				@Override
				public int compare(Integer i1, Integer i2) {
					return chickenDist.get(i1) - chickenDist.get(i2);
				}
			});
		}

		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					chickens.add(new Chicken(r, c));
				} else if (map[r][c] == 1) {
					houses.add(new House(r, c));
				}
			}
		}

		for (House h : houses) {
			for (int c = 0; c < chickens.size(); c++) {
				h.addChicken(c, chickens.get(c));
			}
			h.finishChicken();
		}

		boolean[] open = new boolean[chickens.size()];
		chooseChicken(open, 0, 0);
		
		System.out.println(minChickenDist);
	}

	public static void chooseChicken(boolean[] open, int idx, int chosen) {
		if (chosen == M) {
			calculateChicken(open);
			return;
		}
		if (idx == open.length)
			return;
		open[idx] = true;
		chooseChicken(open, idx + 1, chosen + 1);
		open[idx] = false;
		chooseChicken(open, idx + 1, chosen);

	}

	public static void calculateChicken(boolean[] open) {
		int dist = 0;
		for (int h = 0; h < houses.size(); h++) {
			House house = houses.get(h);
			for (int i=0; i<chickens.size(); i++) {
				int idx = house.keySet.get(i);
				if (open[idx]) {
					dist+=house.chickenDist.get(idx);
					if (dist >= minChickenDist) return;
					break;
				}
			}
		}
		minChickenDist = Math.min(minChickenDist, dist);
	}
}
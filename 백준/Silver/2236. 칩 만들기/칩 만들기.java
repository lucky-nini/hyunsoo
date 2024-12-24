import java.io.*;
import java.util.*;

public class Main {
	public static class Item implements Comparable<Item> {
		int idx;
		int val;

		Item(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Item item) {
			return item.val - this.val;
		}

		@Override
		public String toString() {
			return "idx: " + idx + ", val: " + val;
		}
	}

	public static int findIdx(int[] power, Item[] items) {
		int minIdx = -1;
		int minVal = Integer.MAX_VALUE;

		for (int i = 0; i < power.length; i++) {
			if (power[i] > 0) {
				int val = items[power[i] - 1].val;
				if (val < minVal) {
					minVal = val;
					minIdx = -1;
				}
			}
		}
		return minIdx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Item[] items = new Item[N];
		for (int i = 0; i < N; i++) {
			items[i] = new Item(i + 1, Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(items);

		int[] connection = new int[N];
		Arrays.fill(connection, 0);

		int[] power = new int[K];
		Arrays.fill(power, 0);

		int idx = 0;
		for (Item item : items) {
			if (idx < K) {
				power[idx] = item.idx;
				connection[item.idx - 1] = item.idx;
				idx++;
			} else {
				int minIdx = findIdx(power, items);
				if (minIdx != -1)
					connection[item.idx - 1] = power[minIdx];
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < K; i++) {
			sb.append(power[i]).append("\n");
		}

		for (int i = 0; i < N; i++) {
			sb.append(connection[i]).append("\n");
		}

		System.out.println(sb);
	}
}
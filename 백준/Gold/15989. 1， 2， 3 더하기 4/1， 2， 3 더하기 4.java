import java.io.*;

public class Main {
	static int[] cnts;
	static int[] cnts23;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		cnts = new int[10001];
		cnts[1] = 1;
		cnts[2] = 2;
		cnts[3] = 3;

		cnts23 = new int[10001];
		cnts23[2] = 1;
		cnts23[3] = 1;

		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(getCnt(n)).append("\n");
		}

		System.out.println(sb);
	}

	public static int getCnt23(int n) {
		if (cnts23[n] == 0 && n > 3) {
			if (n % 2 == 0) {
				cnts23[n] = getCnt23(n - 3) + 1;
			} else
				cnts23[n] = getCnt23(n - 3);
		}
		return cnts23[n];
	}

	public static int getCnt(int n) {
		if (cnts[n] == 0 && n > 3) {
			cnts[n] = getCnt(n - 1) + getCnt23(n - 2);
			if (n % 3 == 0)
				cnts[n] += 1;
		}
		return cnts[n];
	}
}
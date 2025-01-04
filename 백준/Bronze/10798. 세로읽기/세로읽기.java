import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = new String[5];
		int[] lengths = new int[5];
		int maxLength = 0;
		for (int i=0; i<5; i++) {
			strs[i] = br.readLine();
			lengths[i] = strs[i].length();
			maxLength = Math.max(maxLength, lengths[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int c=0; c<maxLength; c++) {
			for (int r=0; r<5; r++) {
				if (lengths[r] > c) {
					sb.append(strs[r].charAt(c));
				}
			}
		}
		System.out.println(sb);
	}
}
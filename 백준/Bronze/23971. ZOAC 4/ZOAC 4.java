import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken()); // 행 개수
		int W = Integer.parseInt(st.nextToken()); // 열 개수
		int N = Integer.parseInt(st.nextToken()); // 세로로 몇 칸 비워야 하는지
		int M = Integer.parseInt(st.nextToken()); // 가로로 몇 칸 비워야 하는지
		
		int r = H / (N + 1);
		if (H % (N + 1) > 0) r++;
		
		int c = W / (M + 1);
		if (W % (M + 1) > 0) c++;
		
		System.out.println(r * c);
	}
}
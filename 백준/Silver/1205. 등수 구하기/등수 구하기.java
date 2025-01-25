import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int ts = Integer.parseInt(st.nextToken()); 
        int P = Integer.parseInt(st.nextToken()); 

        if (N == 0) {
            System.out.println(1);
            return;
        }

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int rank = 1;
        int count = 0; 

        for (int i = 0; i < N; i++) {
            if (ts < nums[i]) {
                rank++;
            } else if (ts == nums[i]) {
                rank = rank; 
            } else {
                break; 
            }
            count++;
        }

        if (count < P) {
            System.out.println(rank);
        } else {
            System.out.println(-1);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            ArrayList<Integer>[] positions = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                positions[i] = new ArrayList<>();
            }

            for (int i = 0; i < W.length(); i++) {
                positions[W.charAt(i) - 'a'].add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = -1;

            // 각 알파벳에 대해 K번 이상 등장하는 경우를 처리
            for (int i = 0; i < 26; i++) {
                if (positions[i].size() >= K) {
                    for (int j = 0; j <= positions[i].size() - K; j++) {
                        int start = positions[i].get(j);
                        int end = positions[i].get(j + K - 1);
                        int len = end - start + 1;
                        minLen = Math.min(minLen, len);
                        maxLen = Math.max(maxLen, len);
                    }
                }
            }

            if (maxLen == -1) {
                sb.append("-1\n");
            } else {
                sb.append(minLen).append(" ").append(maxLen).append("\n");
            }
        }

        System.out.println(sb);
    }
}
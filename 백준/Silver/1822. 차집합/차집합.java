import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int aSize = Integer.parseInt(st.nextToken());
		int bSize = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] aArr = new int[aSize];
		
		for (int i = 0; i < aSize; i++) {
			aArr[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < bSize; i++) {
			map.put(Integer.parseInt(st.nextToken()), 1);
		}
		
		for (int i=0; i<aSize; i++) {
			if (!map.containsKey(aArr[i])) {
				result.add(aArr[i]);
			}
		}

		Collections.sort(result);
		if (result.size() > 0) {
			System.out.println(result.size());
			for (int i = 0; i < result.size(); i++) {
				System.out.print(result.get(i) + " ");
			}
		} else {
			System.out.println(0);
		}
	}
}
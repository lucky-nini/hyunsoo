import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer[]> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			list.add(new Integer[] { first, second });
			map.put(second, first);
		}

		Collections.sort(list, new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] i1, Integer[] i2) {
				return i1[0] - i2[0];
			}
		});

		int[] lines = new int[list.size()];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = list.get(i)[1];
		}

		int[] result = findLIS(lines);

		System.out.println(lines.length - result.length);
		Set<Integer> lisSet = new HashSet<>();
		for (int num : result) {
			lisSet.add(num);
		}
		for (int num : lines) {
			if (!lisSet.contains(num)) {
				System.out.println(map.get(num));
			}
		}
	}

	public static int[] findLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return new int[0];

		int n = nums.length;
		int[] lis = new int[n];
		int[] index = new int[n];
		int[] prev = new int[n];
		Arrays.fill(prev, -1);

		int length = 0;

		for (int i = 0; i < n; i++) {
			int pos = binarySearch(lis, length, nums[i]);
			lis[pos] = nums[i];
			index[pos] = i;

			if (pos > 0)
				prev[i] = index[pos - 1];
			if (pos == length)
				length++;
		}

		List<Integer> result = new ArrayList<>();
		int currentIndex = index[length - 1];

		while (currentIndex >= 0) {
			result.add(nums[currentIndex]);
			currentIndex = prev[currentIndex];
		}

		int[] resultArr = new int[result.size()];
		for (int i = 0; i < resultArr.length; i++) {
			resultArr[i] = result.get(result.size() - 1 - i);
		}

		return resultArr;
	}

	public static int binarySearch(int[] lis, int length, int target) {
		int left = 0;
		int right = length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (lis[mid] < target)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return left;
	}
}
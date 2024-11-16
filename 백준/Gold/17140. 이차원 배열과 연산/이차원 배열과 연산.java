import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()) - 1;
		int C = Integer.parseInt(st.nextToken()) - 1;
		int K = Integer.parseInt(st.nextToken());
		int[][] A = new int[3][3];
		for (int r = 0; r < 3; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while (time <= 100) {

			if (A.length > R && A[0].length > C && A[R][C] == K)
				break;

			if (A.length >= A[0].length) { // R 연산
				List<Integer>[] rowList = new ArrayList[A.length];
				int cLen = 0;
				for (int r = 0; r < A.length; r++) {
					rowList[r] = new ArrayList<>();
				}

				for (int r = 0; r < A.length; r++) {
					Map<Integer, Integer> rMap = new HashMap<>();
					for (int c = 0; c < A[r].length; c++) {
						if (A[r][c] != 0) {
							rMap.put(A[r][c], rMap.getOrDefault(A[r][c], 0) + 1);
						}
					}
					List<Integer> keyList = new ArrayList<>(rMap.keySet());
					Collections.sort(keyList, new Comparator<Integer>() {
						@Override
						public int compare(Integer i1, Integer i2) {
							if (rMap.get(i1) != rMap.get(i2))
								return rMap.get(i1) - rMap.get(i2);
							else
								return i1 - i2;
						}
					});
					for (Integer key : keyList) {
						rowList[r].add(key);
						rowList[r].add(rMap.get(key));
					}
					cLen = Math.max(cLen, rowList[r].size());
				}

				A = new int[rowList.length <= 100 ? rowList.length : 100][cLen <= 100 ? cLen : 100];
				for (int r = 0; r < A.length; r++) {
					for (int c = 0; c < A[0].length; c++) {
						if (c < rowList[r].size())
							A[r][c] = rowList[r].get(c);
						else
							A[r][c] = 0;
					}
				}

			} else { // C 연산
				List<Integer>[] colList = new ArrayList[A[0].length];
				int rLen = 0;
				for (int c = 0; c < A[0].length; c++) {
					colList[c] = new ArrayList<>();
				}

				for (int c = 0; c < A[0].length; c++) {
					Map<Integer, Integer> cMap = new HashMap<>();
					for (int r = 0; r < A.length; r++) {
						if (A[r][c] != 0) {
							cMap.put(A[r][c], cMap.getOrDefault(A[r][c], 0) + 1);
						}
					}
					List<Integer> keyList = new ArrayList<>(cMap.keySet());
					Collections.sort(keyList, new Comparator<Integer>() {
						@Override
						public int compare(Integer i1, Integer i2) {
							if (cMap.get(i1) != cMap.get(i2))
								return cMap.get(i1) - cMap.get(i2);
							else
								return i1 - i2;
						}
					});
					for (Integer key : keyList) {
						colList[c].add(key);
						colList[c].add(cMap.get(key));
					}
					rLen = Math.max(rLen, colList[c].size());
				}

				A = new int[rLen <= 100 ? rLen : 100][colList.length <= 100 ? colList.length : 100];
				for (int c = 0; c < A[0].length; c++) {
					for (int r = 0; r < A.length; r++) {
						if (r < colList[c].size())
							A[r][c] = colList[c].get(r);
						else
							A[r][c] = 0;
					}
				}
			}

			time++;
		}

		if (time == 101)
			System.out.println(-1);
		else
			System.out.println(time);
	}
}
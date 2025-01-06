import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static int emptyCnt = 0;

	public static class Belt {
		int idx, durability;
		int robotCnt = 0;

		Belt(int idx, int durability) {
			this.idx = idx;
			this.durability = durability;
		}

		@Override
		public String toString() {
			return "{ idx: " + idx + ", durability: " + durability + " }";
		}
	}

	public static class Conv {
		Belt[] belts;
		int firstIdx = 0;
		List<Integer> robots = new ArrayList<>();

		Conv() {
			belts = new Belt[2 * N];
		}

		void moveConv() {
		    firstIdx = (firstIdx - 1 + 2 * N) % (2 * N);
		    int nIdx = (firstIdx + N - 1) % (2 * N);

		    for (int i = robots.size() - 1; i >= 0; i--) {
		        if (robots.get(i) == nIdx) {
		            robots.remove(i);
		            belts[nIdx].robotCnt--;
		        }
		    }
		}


		void moveRobot() {
		    int nIdx = (firstIdx + N - 1) % (2 * N);
		    List<Integer> removeIdxes = new ArrayList<>();
		    for (int i = 0; i < robots.size(); i++) {
		        int currIdx = robots.get(i);
		        int newIdx = (currIdx + 1) % (2 * N);

		        if (belts[newIdx].robotCnt == 0 && belts[newIdx].durability > 0) {
		            belts[currIdx].robotCnt--;
		            belts[newIdx].robotCnt++;
		            belts[newIdx].durability--;

		            if (belts[newIdx].durability == 0) {
		                emptyCnt++;
		            }

		            robots.set(i, newIdx);

		            if (newIdx == nIdx) {
		                belts[newIdx].robotCnt--;
		                removeIdxes.add(i);
		            }
		        }
		    }
		    
		    for (int i : removeIdxes) {
		    	robots.remove(i);
		    }
		}

		void putRobot() {
		    if (belts[firstIdx].durability > 0 && belts[firstIdx].robotCnt == 0) {
		        robots.add(firstIdx);
		        belts[firstIdx].robotCnt++;
		        belts[firstIdx].durability--;

		        if (belts[firstIdx].durability == 0) {
		            emptyCnt++;
		        }
		    }
		}



		@Override
		public String toString() {
			return Arrays.toString(belts) + "\n" + robots + "\n" + "firstIdx: " + firstIdx + ", emptyCnt: " + emptyCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Conv conv = new Conv();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			conv.belts[i] = new Belt(i, Integer.parseInt(st.nextToken()));
		}

		int T = 0;
		while (K > emptyCnt) {
//			System.out.println("Turn: " + T);
//			System.out.println("firstIdx: " + conv.firstIdx);
//			System.out.println("robots: " + conv.robots);
//			System.out.println("belts: " + Arrays.toString(conv.belts));
//			System.out.println("emptyCnt: " + emptyCnt);
//			System.out.println("--------------------");

			T++;
			conv.moveConv();
			conv.moveRobot();
			conv.putRobot();
		}

		System.out.println(T);
	}
}